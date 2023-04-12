/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.task.dao;

import com.jeesite.common.dao.CrudDao;
import com.jeesite.common.mybatis.annotation.MyBatisDao;
import com.jeesite.modules.task.entity.ViewTask;

/**
 * 任务信息DAO接口
 * @author Liuzy
 * @version 2023-04-11
 */
@MyBatisDao
public interface ViewTaskDao extends CrudDao<ViewTask> {
	
}