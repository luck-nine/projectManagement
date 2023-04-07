/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.project.service;

import com.jeesite.common.entity.Page;
import com.jeesite.common.service.CrudService;
import com.jeesite.modules.project.dao.ViewProjectDao;
import com.jeesite.modules.project.entity.ViewProject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 查看项目信息Service
 *
 * @author Liuzy
 * @version 2023-03-28
 */
@Service
@Transactional(readOnly=true)
public class ViewProjectService extends CrudService<ViewProjectDao, ViewProject> {
	
	/**
	 * 获取单条数据
	 * @param viewProject
	 * @return
	 */
	@Override
	public ViewProject get(ViewProject viewProject) {
		return super.get(viewProject);
	}
	
	/**
	 * 查询分页数据
	 * @param viewProject 查询条件
	 * @return
	 */
	@Override
	public Page<ViewProject> findPage(ViewProject viewProject) {
		return super.findPage(viewProject);
	}
	
	/**
	 * 保存数据（插入或更新）
	 * @param viewProject
	 */
	@Override
	@Transactional(readOnly=false)
	public void save(ViewProject viewProject) {
		super.save(viewProject);
	}
	
	/**
	 * 更新状态
	 * @param viewProject
	 */
	@Override
	@Transactional(readOnly=false)
	public void updateStatus(ViewProject viewProject) {
		super.updateStatus(viewProject);
	}
	
	/**
	 * 删除数据
	 * @param viewProject
	 */
	@Override
	@Transactional(readOnly=false)
	public void delete(ViewProject viewProject) {
		super.delete(viewProject);
	}
	
}