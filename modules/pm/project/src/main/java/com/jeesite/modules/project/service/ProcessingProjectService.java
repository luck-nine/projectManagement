/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.project.service;

import com.jeesite.common.entity.Page;
import com.jeesite.common.service.CrudService;
import com.jeesite.modules.project.dao.ProcessingProjectDao;
import com.jeesite.modules.project.entity.ProcessingProject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 维护项目信息Service
 * @author Liuzy
 * @version 2023-04-10
 */
@Service
@Transactional(readOnly=true)
public class ProcessingProjectService extends CrudService<ProcessingProjectDao, ProcessingProject> {
	
	/**
	 * 获取单条数据
	 * @param processingProject
	 * @return
	 */
	@Override
	public ProcessingProject get(ProcessingProject processingProject) {
		return super.get(processingProject);
	}
	
	/**
	 * 查询分页数据
	 * @param processingProject 查询条件
	 * @param processingProject.page 分页对象
	 * @return
	 */
	@Override
	public Page<ProcessingProject> findPage(ProcessingProject processingProject) {
		return super.findPage(processingProject);
	}
	
	/**
	 * 保存数据（插入或更新）
	 * @param processingProject
	 */
	@Override
	@Transactional(readOnly=false)
	public void save(ProcessingProject processingProject) {
		super.save(processingProject);
	}
	
	/**
	 * 更新状态
	 * @param processingProject
	 */
	@Override
	@Transactional(readOnly=false)
	public void updateStatus(ProcessingProject processingProject) {
		super.updateStatus(processingProject);
	}
	
	/**
	 * 删除数据
	 * @param project
	 */
	@Override
	@Transactional(readOnly=false)
	public void delete(ProcessingProject processingProject) {
		super.delete(processingProject);
	}
	
}