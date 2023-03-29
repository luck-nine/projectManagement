/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.project.service;

import com.jeesite.common.entity.Page;
import com.jeesite.common.service.CrudService;
import com.jeesite.modules.project.dao.NewProjectDao;
import com.jeesite.modules.project.entity.NewProject;
import com.jeesite.modules.project.entity.Project;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.List;

/**
 * 项目信息Service
 * @author Liuzy
 * @version 2023-03-28
 */
@Service
@Transactional(readOnly=true)
public class NewProjectService extends CrudService<NewProjectDao, NewProject> {
	
	/**
	 * 获取单条数据
	 * @param newProject
	 */
	@Override
	public NewProject get(NewProject newProject) {
		return super.get(newProject);
	}
	
	/**
	 * 查询分页数据
	 * @param newProject 查询条件
	 */
	@Override
	public Page<NewProject> findPage(NewProject newProject) {
		return super.findPage(newProject);
	}
	
	/**
	 * 保存数据（插入或更新）
	 * @param newProject
	 */
	@Override
	@Transactional(readOnly=false)
	public void save(NewProject newProject) {
		super.save(newProject);
	}
	
	/**
	 * 更新状态
	 * @param newProject
	 */
	@Override
	@Transactional(readOnly=false)
	public void updateStatus(NewProject newProject) {
		super.updateStatus(newProject);
	}
	
	/**
	 * 删除数据
	 * @param newProject
	 */
	@Override
	@Transactional(readOnly=false)
	public void delete(NewProject newProject) {
		super.delete(newProject);
	}

	public Project buildSaleContractCode(NewProject newProject) {
		if (null != newProject.getId()) {
			return newProject;
		} else {
			Calendar calendar = Calendar.getInstance();
			String projectCode = "PM" + calendar.get(Calendar.YEAR) + "-";
			List<NewProject> existList = dao.findList(newProject);
			if (existList.size() == 0) {
				newProject.setProjectCode(projectCode + "01");
			} else {
				String lastCode = existList.get(existList.size() - 1).getProjectCode();
				int i = Integer.parseInt(lastCode.substring(7)) + 1;
				newProject.setProjectCode(projectCode + (i < 10 ? "0" + i : i));
			}
			newProject.setIsNewRecord(true);
		}
		return newProject;
	}
}