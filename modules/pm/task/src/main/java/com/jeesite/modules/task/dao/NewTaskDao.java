/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.task.dao;

import com.jeesite.common.dao.CrudDao;
import com.jeesite.common.entity.Page;
import com.jeesite.common.mybatis.annotation.MyBatisDao;
import com.jeesite.modules.task.entity.NewTask;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 任务信息DAO接口
 * @author Liuzy
 * @version 2023-04-07
 */
@MyBatisDao
public interface NewTaskDao extends CrudDao<NewTask> {

    /**
     * 查询项目列表
     * @param newTask
     */
    List<NewTask> findProjectList(NewTask newTask);
}