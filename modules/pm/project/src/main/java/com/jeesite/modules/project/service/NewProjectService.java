/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.project.service;

import com.jeesite.common.entity.Page;
import com.jeesite.common.service.CrudService;
import com.jeesite.modules.project.dao.ProjectDao;
import com.jeesite.modules.project.entity.Project;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 项目信息Service
 * @author Liuzy
 * @version 2023-03-28
 */
@Service
@Transactional(readOnly=true)
public class NewProjectService extends CrudService<ProjectDao, Project> {
	
	/**
	 * 获取单条数据
	 * @param project
	 * @return
	 */
	@Override
	public Project get(Project project) {
		return super.get(project);
	}
	
	/**
	 * 查询分页数据
	 * @param project 查询条件
	 * @param project.page 分页对象
	 * @return
	 */
	@Override
	public Page<Project> findPage(Project project) {
		return super.findPage(project);
	}
	
	/**
	 * 保存数据（插入或更新）
	 * @param project
	 */
	@Override
	@Transactional(readOnly=false)
	public void save(Project project) {
		super.save(project);
	}
	
	/**
	 * 更新状态
	 * @param project
	 */
	@Override
	@Transactional(readOnly=false)
	public void updateStatus(Project project) {
		super.updateStatus(project);
	}
	
	/**
	 * 删除数据
	 * @param project
	 */
	@Override
	@Transactional(readOnly=false)
	public void delete(Project project) {
		super.delete(project);
	}
	
}