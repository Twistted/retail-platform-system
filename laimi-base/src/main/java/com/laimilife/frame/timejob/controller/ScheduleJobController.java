package com.laimilife.frame.timejob.controller;

import java.util.List;
import java.util.Map;

import org.quartz.SchedulerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.Page;
import com.laimilife.base.controller.BaseController;
import com.laimilife.base.vo.Result;
import com.laimilife.frame.timejob.model.TbSysTimejob;
import com.laimilife.frame.timejob.model.TbSysTimejobLog;
import com.laimilife.frame.timejob.service.IScheduleJobService;
import com.laimilife.frame.timejob.vo.TbSysTimejobVO;


@Controller
@RequestMapping("/frame/timeJob")
public class ScheduleJobController extends BaseController {
    private final static transient Logger logger = LoggerFactory.getLogger(ScheduleJobController.class);
    
    @Autowired
    private IScheduleJobService scheduleJobService;

    /**
     * 添加一个定时任务
     * @author: zhangweiqiang
     * @date: 2018年3月30日 上午10:10:47
     */
    @RequestMapping("/addJob")
    public @ResponseBody Result addJob(@RequestBody TbSysTimejob job) {
        // TODO 校验必填字段
        // TODO 校验类是否存在
        String id = scheduleJobService.addJob(job);
        return super.setControllerResult("0", "添加成功", id);
    }

    /**
     * 修改一个定时任务
     * @author: zhangweiqiang
     * @date: 2018年3月30日 上午10:10:47
     */
    @RequestMapping("/updateJob")
    public @ResponseBody Result updateJob(@RequestBody TbSysTimejob job) {
        scheduleJobService.updateJob(job);
        return super.setControllerResult("0", "修改成功");
    }
    
    /**
     * 删除一个定时任务
     * @author: zhangweiqiang
     * @date: 2018年3月30日 上午10:36:54
     */
    @RequestMapping("/deleteJob")
    public @ResponseBody Result deleteJob(@RequestBody String id) {
        scheduleJobService.deleteJob(id);
        return super.setControllerResult("0", "删除成功");
    }
    
    /**
     * 查询定时任务
     * @author: zhangweiqiang
     * @throws SchedulerException 
     * @date: 2018年3月30日 上午10:38:01
     */
    @RequestMapping("pageSearchJob")
    public @ResponseBody Result searchJob(@RequestBody Map<String,String> searchValue) throws SchedulerException {
        Page<Object> page = super.prePage(searchValue);
        List<TbSysTimejobVO> list = scheduleJobService.searchJob(searchValue);
        return setPager(list, page);
    }
    
    /**
     * 查询定时任务日志
     * @author: zhangweiqiang
     * @date: 2018年3月30日 上午10:38:01
     */
    @RequestMapping("pageSearchJobLog")
    public @ResponseBody Result searchJobLog(@RequestBody Map<String,String> searchValue) {
        Page<Object> page = super.prePage(searchValue);
        List<TbSysTimejobLog> list = scheduleJobService.searchJobLog(searchValue);
        return setPager(list, page);
    }
}
