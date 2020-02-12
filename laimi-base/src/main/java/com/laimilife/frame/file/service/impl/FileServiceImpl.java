package com.laimilife.frame.file.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.laimilife.base.util.StringUtil;
import com.laimilife.frame.account.vo.TbBaseUser;
import com.laimilife.frame.file.dao.TbBaseFileMapper;
import com.laimilife.frame.file.model.TbBaseFile;
import com.laimilife.frame.file.service.FileService;


@Service("FileService") 
public class FileServiceImpl implements FileService {
	
    @Autowired
    private TbBaseFileMapper fileMapper;

    /**
     * 插入一个文件记录
     * @author: zhangweiqiang
     * @date: 2018年4月10日 下午1:44:50
     */
    public void insert(TbBaseFile baseFile, TbBaseUser user) {
        baseFile.setId(StringUtil.getUUID32());
        baseFile.setCreateId(user.getId());
        baseFile.setCreateName(user.getUserName());
        baseFile.setCreateTime(new Date());
        fileMapper.insert(baseFile);
    }

    /**
     * 查询文件by主键
     * @author: zhangweiqiang
     * @date: 2018年4月10日 下午1:49:43
     */
    public TbBaseFile selectByPrimaryKey(String id) {
        return fileMapper.selectByPrimaryKey(id);
    }
}
