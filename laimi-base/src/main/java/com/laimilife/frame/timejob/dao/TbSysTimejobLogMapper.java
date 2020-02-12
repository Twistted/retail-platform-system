package com.laimilife.frame.timejob.dao;

import java.util.List;
import java.util.Map;

import com.laimilife.frame.timejob.model.TbSysTimejobLog;

public interface TbSysTimejobLogMapper {
    int deleteByPrimaryKey(String id);

    int insert(TbSysTimejobLog record);

    int insertSelective(TbSysTimejobLog record);

    TbSysTimejobLog selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(TbSysTimejobLog record);

    int updateByPrimaryKeyWithBLOBs(TbSysTimejobLog record);

    int updateByPrimaryKey(TbSysTimejobLog record);
    
    int deleteByJobId(String jobId);

    /**
     * 查询定时任务日志
     * @author: zhangweiqiang
     * @date: 2018年3月31日 上午11:17:12
     */
    List<TbSysTimejobLog> searchJobLog(Map<String, String> searchValue);
}