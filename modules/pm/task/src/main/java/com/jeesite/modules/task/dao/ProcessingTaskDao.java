/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.task.dao;

import com.jeesite.common.dao.CrudDao;
import com.jeesite.common.mybatis.annotation.MyBatisDao;
import com.jeesite.modules.task.entity.ProcessingTask;

/**
 * 处理任务DAO接口
 * @author Liuzy
 * @version 2023-04-10
 */
@MyBatisDao
public interface ProcessingTaskDao extends CrudDao<ProcessingTask> {
	
}