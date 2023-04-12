/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.task.service;

import com.jeesite.common.entity.Page;
import com.jeesite.common.service.CrudService;
import com.jeesite.modules.file.utils.FileUploadUtils;
import com.jeesite.modules.task.dao.ViewTaskDao;
import com.jeesite.modules.task.entity.ViewTask;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 查看任务Service
 * @author Liuzy
 * @version 2023-04-12
 */
@Service
@Transactional(readOnly=true)
public class ViewTaskService extends CrudService<ViewTaskDao, ViewTask> {
	
	/**
	 * 获取单条数据
	 * @param viewTask
	 * @return
	 */
	@Override
	public ViewTask get(ViewTask viewTask) {
		return super.get(viewTask);
	}
	
	/**
	 * 查询分页数据
	 * @param viewTask 查询条件
	 * @param viewTask.page 分页对象
	 * @return
	 */
	@Override
	public Page<ViewTask> findPage(ViewTask viewTask) {
		return super.findPage(viewTask);
	}
	
	/**
	 * 保存数据（插入或更新）
	 * @param viewTask
	 */
	@Override
	@Transactional(readOnly=false)
	public void save(ViewTask viewTask) {
		super.save(viewTask);
		// 保存上传图片
		FileUploadUtils.saveFileUpload(viewTask, viewTask.getId(), "task_image");
		// 保存上传附件
		FileUploadUtils.saveFileUpload(viewTask, viewTask.getId(), "task_file");
	}
	
	/**
	 * 更新状态
	 * @param viewTask
	 */
	@Override
	@Transactional(readOnly=false)
	public void updateStatus(ViewTask viewTask) {
		super.updateStatus(viewTask);
	}
	
	/**
	 * 删除数据
	 * @param viewTask
	 */
	@Override
	@Transactional(readOnly=false)
	public void delete(ViewTask viewTask) {
		super.delete(viewTask);
	}
	
}