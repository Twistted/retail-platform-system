package com.laimilife.frame.file.service;

import com.laimilife.frame.account.vo.TbBaseUser;
import com.laimilife.frame.file.model.TbBaseFile;

public interface FileService {

    /**
     * 插入一个文件记录
     * @author: zhangweiqiang
     * @date: 2018年4月10日 下午1:44:50
     */
    void insert(TbBaseFile baseFile, TbBaseUser user);

    /**
     * 查询文件by主键
     * @author: zhangweiqiang
     * @date: 2018年4月10日 下午1:49:43
     */
    TbBaseFile selectByPrimaryKey(String fileId);

}
