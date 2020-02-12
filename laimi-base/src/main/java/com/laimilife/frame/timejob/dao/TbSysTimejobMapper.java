package com.laimilife.frame.timejob.dao;

import java.util.List;
import java.util.Map;

import com.laimilife.frame.timejob.model.TbSysTimejob;
import com.laimilife.frame.timejob.vo.TbSysTimejobVO;

public interface TbSysTimejobMapper {
    int deleteByPrimaryKey(String id);

    int insert(TbSysTimejob record);

    int insertSelective(TbSysTimejob record);

    TbSysTimejob selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(TbSysTimejob record);

    int updateByPrimaryKey(TbSysTimejob record);

    List<TbSysTimejobVO> searchJob(Map<String, String> searchValue);

    TbSysTimejob searchJobByClassName(String className);
}