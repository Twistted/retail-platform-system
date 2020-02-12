package com.laimilife.frame.timejob.timejob;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Date;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.laimilife.base.util.SpringBeanUtil;
import com.laimilife.base.util.StringUtil;
import com.laimilife.frame.timejob.controller.ScheduleJobController;
import com.laimilife.frame.timejob.model.TbSysTimejob;
import com.laimilife.frame.timejob.model.TbSysTimejobLog;
import com.laimilife.frame.timejob.service.IScheduleJobService;

public class TestJob implements Job {

    private static IScheduleJobService scheduleJobService = (IScheduleJobService) SpringBeanUtil.getBean("scheduleJobService");
    
    private final static transient Logger logger = LoggerFactory.getLogger(ScheduleJobController.class);

    public void execute(JobExecutionContext arg0) throws JobExecutionException {
        logger.debug("定时任务：测试任务-开始执行……");
        TbSysTimejobLog log = new TbSysTimejobLog();
        log.setId(StringUtil.getUUID32());
        log.setStartTime(new Date());
        TbSysTimejob job = scheduleJobService.searchJobByClassName(this.getClass().getName());
        log.setJobId(job.getId());
        try {
            System.out.println("测试任务执行: " + new Date());
            log.setResult("执行正常");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();  
            PrintWriter pw = new PrintWriter(sw);  
            e.printStackTrace(pw);  
            logger.error(sw.toString());
            log.setErrorLog(sw.toString());
            log.setResult("执行异常");
        } finally{
            log.setEndTime(new Date());
            scheduleJobService.insertJobLog(log);
            logger.debug("定时任务：测试任务-结束……");
        }
    }
    
    public TestJob() {
        
    }
}
