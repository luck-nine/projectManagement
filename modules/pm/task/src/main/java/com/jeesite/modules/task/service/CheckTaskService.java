/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.task.service;

import com.jeesite.common.entity.Page;
import com.jeesite.common.service.CrudService;
import com.jeesite.modules.file.utils.FileUploadUtils;
import com.jeesite.modules.task.dao.CheckTaskDao;
import com.jeesite.modules.task.dao.TaskCheckDao;
import com.jeesite.modules.task.entity.CheckTask;
import com.jeesite.modules.task.entity.TaskCheck;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 审核任务Service
 * @author Liuzy
 * @version 2023-04-10
 */
@Service
@Transactional(readOnly=true)
public class CheckTaskService extends CrudService<CheckTaskDao, CheckTask> {

	@Autowired
	private TaskCheckDao taskCheckDao;

	/**
	 * 获取单条数据
	 * @param checkTask
	 * @return
	 */
	@Override
	public CheckTask get(CheckTask checkTask) {
		return super.get(checkTask);
	}
	
	/**
	 * 查询分页数据
	 * @param checkTask 查询条件
	 * @param checkTask.page 分页对象
	 * @return
	 */
	@Override
	public Page<CheckTask> findPage(CheckTask checkTask) {
		return super.findPage(checkTask);
	}
	
	/**
	 * 保存数据（插入或更新）
	 * @param checkTask
	 */
	@Override
	@Transactional(readOnly=false)
	public void save(CheckTask checkTask) {
		super.save(checkTask);
		// 保存上传图片
		FileUploadUtils.saveFileUpload(checkTask, checkTask.getId(), "task_image");
		// 保存上传附件
		FileUploadUtils.saveFileUpload(checkTask, checkTask.getId(), "task_file");
		TaskCheck taskCheck = new TaskCheck();
		taskCheck.setTaskCode(checkTask);
		if (TaskCheck.QUALIFIED.equals(checkTask.getAuditStatus())) {
			taskCheck.setCheckStatus(TaskCheck.FINISHED);
		} else if (TaskCheck.NOT_QUALIFIED.equals(checkTask.getAuditStatus())) {
			taskCheck.setCheckStatus(TaskCheck.REJECTED);
		}
		taskCheck.setCheckOpinion(checkTask.getCheckOpinion());
		taskCheck.preUpdate();
		taskCheckDao.update(taskCheck);
	}
	
	/**
	 * 更新状态
	 * @param checkTask
	 */
	@Override
	@Transactional(readOnly=false)
	public void updateStatus(CheckTask checkTask) {
		super.updateStatus(checkTask);
	}
	
	/**
	 * 删除数据
	 * @param checkTask
	 */
	@Override
	@Transactional(readOnly=false)
	public void delete(CheckTask checkTask) {
		super.delete(checkTask);
	}
	
}