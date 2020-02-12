package com.laimilife.frame.timejob.service.impl;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.Trigger;
import org.quartz.Trigger.TriggerState;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.laimilife.base.exception.BaseException;
import com.laimilife.base.util.AssertUtil;
import com.laimilife.base.util.StringUtil;
import com.laimilife.frame.timejob.dao.TbSysTimejobLogMapper;
import com.laimilife.frame.timejob.dao.TbSysTimejobMapper;
import com.laimilife.frame.timejob.model.TbSysTimejob;
import com.laimilife.frame.timejob.model.TbSysTimejobLog;
import com.laimilife.frame.timejob.service.IScheduleJobService;
import com.laimilife.frame.timejob.vo.TbSysTimejobVO;

@Service("scheduleJobService")
public class ScheduleJobService implements IScheduleJobService {
    private final static transient Logger logger = LoggerFactory.getLogger(ScheduleJobService.class);

    @Autowired
    private TbSysTimejobMapper timejobMapper;

    @Autowired
    private TbSysTimejobLogMapper timejobLogMapper;
    
    private static SchedulerFactory schedulerFactory = new StdSchedulerFactory();


    /**
     * 添加一个定时任务
     * @author: zhangweiqiang
     * @date: 2018年3月30日 上午10:10:47
     */
    public String addJob(TbSysTimejob job) {
        job.setId(StringUtil.getUUID32());
        job.setCreateTime(new Date());
        timejobMapper.insert(job);
        // 添加到定时任务运行
        this.startJob(job);
        return job.getId();
    }

    /**
     * 修改一个定时任务
     * @author: zhangweiqiang
     * @date: 2018年3月30日 上午10:10:47
     */
    public void updateJob(TbSysTimejob job) {
        job.setUpdateTime(new Date());
        timejobMapper.updateByPrimaryKey(job);
        // 修改定时任务
        this.modifyRuningJob(job);
    }
    
    /**
     * 删除一个定时任务
     * @author: zhangweiqiang
     * @date: 2018年3月30日 上午10:36:54
     */
    public void deleteJob(String id) {
        TbSysTimejob job = timejobMapper.selectByPrimaryKey(id);
        if(AssertUtil.isNull(job)) {
            throw new BaseException("200001", "定时任务不存在");
        }
        timejobMapper.deleteByPrimaryKey(id);
        timejobLogMapper.deleteByJobId(id);
        // 从定时任务中删除
        this.removeJob(job);
    }
    
    /**
     * 查询定时任务
     * @author: zhangweiqiang
     * @throws SchedulerException 
     * @date: 2018年3月30日 上午10:38:01
     */
    public List<TbSysTimejobVO> searchJob(Map<String, String> searchValue) throws SchedulerException {
        List<TbSysTimejobVO> jobList = timejobMapper.searchJob(searchValue);
        // 查询现在定时任务状态
        Scheduler sched = schedulerFactory.getScheduler();
        for(TbSysTimejobVO job : jobList) {
            TriggerKey triggerKey =  TriggerKey.triggerKey(job.getJobName(), Scheduler.DEFAULT_GROUP);
            TriggerState triggerState = sched.getTriggerState(triggerKey);
            job.setTriggerState(triggerState.toString());
        }
        return jobList;
    }
    
    /**
     * 查询定时任务by类名
     * @author: zhangweiqiang
     * @date: 2018年3月31日 下午3:42:13
     */
    public TbSysTimejob searchJobByClassName(String className) {
        return timejobMapper.searchJobByClassName(className);
    }
    
    /**
     * 查询定时任务日志
     * @author: zhangweiqiang
     * @date: 2018年3月30日 上午10:38:01
     */
    public List<TbSysTimejobLog> searchJobLog(Map<String, String> searchValue) {
        List<TbSysTimejobLog> list = timejobLogMapper.searchJobLog(searchValue);
        return list;
    }
    
    /**
     * 移除定时任务
     * @author: zhangweiqiang
     * @date: 2018年3月31日 下午5:22:11
     */
    public static void removeJob(TbSysTimejob job) {
        String jobName = job.getJobName();
        try {
            Scheduler sched = schedulerFactory.getScheduler();
            TriggerKey triggerKey =  TriggerKey.triggerKey(jobName, Scheduler.DEFAULT_GROUP);
            sched.pauseTrigger(triggerKey); // 停止触发器
            sched.unscheduleJob(triggerKey); // 移除触发器
            sched.deleteJob(JobKey.jobKey(jobName, Scheduler.DEFAULT_GROUP)); // 删除任务
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
      }
    }
    
    /**
     * 修改运行中的定时任务
     * @author: zhangweiqiang
     * @date: 2018年3月31日 下午4:48:15
     */
    public void modifyRuningJob(TbSysTimejob job) {
        this.removeJob(job);
        this.startJob(job);
    }
    
    /**  
     * 启动一个定时任务
     */
    public void startJob(TbSysTimejob job) {
        if(!"ON".equals(job.getStatus())) {
            return;
        }
        try {
            if(null == Class.forName(job.getClassName())) {
                logger.error("ClassNotFound: " + job.getClassName());
                return ;
            }
            // 调度器
            Scheduler sched = schedulerFactory.getScheduler();
            // 任务
            JobDetail jobDetail = JobBuilder.newJob((Class<Job>) Class.forName(job.getClassName())).withIdentity(job.getJobName()).build();

            // 触发器  
            TriggerBuilder<Trigger> triggerBuilder = TriggerBuilder.newTrigger();
            // 触发器名,触发器组  
            triggerBuilder.withIdentity(job.getJobName(), Scheduler.DEFAULT_GROUP);
            triggerBuilder.startNow();
            // 触发器时间设定  
            triggerBuilder.withSchedule(CronScheduleBuilder.cronSchedule(job.getCron()));
            // 创建Trigger对象
            CronTrigger trigger = (CronTrigger) triggerBuilder.build();
            
            // 把作业和触发器注册到任务调度中
            sched.scheduleJob(jobDetail, trigger);
            // 启动    
            if (!sched.isShutdown()){
                sched.start();    
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
    
    /** 
     * 启动所有定时任务 
     */
    @PostConstruct
    public void startAllJobs() {
        
        List<TbSysTimejobVO> timejobs = timejobMapper.searchJob(new HashMap<String, String>());
        if(AssertUtil.isNull(timejobs) || timejobs.size() == 0) {
            return ;
        }
        try {
            Scheduler sched = schedulerFactory.getScheduler();
            logger.error("定时任务开始执行，任务数：" + timejobs.size());
            for(TbSysTimejobVO job : timejobs) {
                if(!"ON".equals(job.getStatus())) {
                    continue;
                }
                if(AssertUtil.isNull(Class.forName(job.getClassName()))) {
                    continue;
                }
                // 任务
                JobDetail jobDetail = JobBuilder.newJob((Class<Job>) Class.forName(job.getClassName())).withIdentity(job.getJobName()).build();
                // 触发器
                CronTrigger trigger = TriggerBuilder.newTrigger().withIdentity(job.getJobName())
                        .withSchedule(CronScheduleBuilder.cronSchedule(job.getCron()))
                        .startNow().build();
                // 把作业和触发器注册到任务调度中
                sched.scheduleJob(jobDetail, trigger);
            }
            sched.start();
        } catch (Exception e) {  
            StringWriter sw = new StringWriter();  
            PrintWriter pw = new PrintWriter(sw);  
            e.printStackTrace(pw);  
            logger.error("定时任务执行失败：" + sw.toString());
        }  
    }

    /**
     * 插入定时任务日志
     * @author: zhangweiqiang
     * @date: 2018年3月31日 下午3:43:59
     */
    public void insertJobLog(TbSysTimejobLog log) {
        timejobLogMapper.insert(log);
    }

}

