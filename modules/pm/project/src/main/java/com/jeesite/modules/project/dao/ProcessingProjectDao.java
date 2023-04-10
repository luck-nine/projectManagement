/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.project.dao;

import com.jeesite.common.dao.CrudDao;
import com.jeesite.common.mybatis.annotation.MyBatisDao;
import com.jeesite.modules.project.entity.ProcessingProject;

/**
 * 维护项目信息DAO接口
 * @author Liuzy
 * @version 2023-03-28
 */
@MyBatisDao
public interface ProcessingProjectDao extends CrudDao<ProcessingProject> {
	
}