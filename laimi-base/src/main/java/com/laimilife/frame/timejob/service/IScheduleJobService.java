package com.laimilife.frame.timejob.service;

import java.util.List;
import java.util.Map;

import org.quartz.SchedulerException;

import com.laimilife.frame.timejob.model.TbSysTimejob;
import com.laimilife.frame.timejob.model.TbSysTimejobLog;
import com.laimilife.frame.timejob.vo.TbSysTimejobVO;

public interface IScheduleJobService {

    /**
     * 添加一个定时任务
     * @author: zhangweiqiang
     * @param job 
     * @return 
     * @date: 2018年3月30日 上午10:10:47
     */
    String addJob(TbSysTimejob job);

    /**
     * 修改一个定时任务
     * @author: zhangweiqiang
     * @param job 
     * @date: 2018年3月30日 上午10:10:47
     */
    void updateJob(TbSysTimejob job);
    
    /**
     * 删除一个定时任务
     * @author: zhangweiqiang
     * @param id 
     * @date: 2018年3月30日 上午10:36:54
     */
    void deleteJob(String id);
    
    /**
     * 查询定时任务
     * @author: zhangweiqiang
     * @param searchValue 
     * @throws SchedulerException 
     * @date: 2018年3月30日 上午10:38:01
     */
    List<TbSysTimejobVO> searchJob(Map<String, String> searchValue) throws SchedulerException;

    /**
     * 查询定时任务日志
     * @author: zhangweiqiang
     * @date: 2018年3月31日 上午11:16:09
     */
    List<TbSysTimejobLog> searchJobLog(Map<String, String> searchValue);
    
    /**
     * 查询定时任务by类名
     * @author: zhangweiqiang
     * @date: 2018年3月31日 下午3:42:13
     */
    TbSysTimejob searchJobByClassName(String className);
    
    /**
     * 插入定时任务日志
     * @author: zhangweiqiang
     * @date: 2018年3月31日 下午3:42:13
     */
    void insertJobLog(TbSysTimejobLog log);
}
