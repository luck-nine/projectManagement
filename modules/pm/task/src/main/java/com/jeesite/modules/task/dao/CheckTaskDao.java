/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.task.dao;

import com.jeesite.common.dao.CrudDao;
import com.jeesite.common.mybatis.annotation.MyBatisDao;
import com.jeesite.modules.task.entity.CheckTask;

/**
 * 任务信息DAO接口
 * @author Liuzy
 * @version 2023-04-08
 */
@MyBatisDao
public interface CheckTaskDao extends CrudDao<CheckTask> {
	
}