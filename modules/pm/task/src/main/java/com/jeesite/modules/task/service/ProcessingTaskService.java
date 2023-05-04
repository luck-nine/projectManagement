/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.task.service;

import com.jeesite.common.entity.Page;
import com.jeesite.common.service.CrudService;
import com.jeesite.modules.file.utils.FileUploadUtils;
import com.jeesite.modules.task.dao.ProcessingTaskDao;
import com.jeesite.modules.task.dao.TaskCheckDao;
import com.jeesite.modules.task.entity.ProcessingTask;
import com.jeesite.modules.task.entity.Task;
import com.jeesite.modules.task.entity.TaskCheck;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 处理任务Service
 * @author Liuzy
 * @version 2023-04-10
 */
@Service
@Transactional(readOnly=true)
public class ProcessingTaskService extends CrudService<ProcessingTaskDao, ProcessingTask> {

	@Autowired
	private TaskCheckDao taskCheckDao;

	/**
	 * 获取单条数据
	 * @param processingTask
	 * @return
	 */
	@Override
	public ProcessingTask get(ProcessingTask processingTask) {
		return super.get(processingTask);
	}
	
	/**
	 * 查询分页数据
	 * @param processingTask 查询条件
	 * @param processingTask.page 分页对象
	 * @return
	 */
	@Override
	public Page<ProcessingTask> findPage(ProcessingTask processingTask) {
		return super.findPage(processingTask);
	}
	
	/**
	 * 保存数据（插入或更新）
	 * @param processingTask
	 */
	@Override
	@Transactional(readOnly=false)
	public void save(ProcessingTask processingTask) {
		super.save(processingTask);
		// 保存上传图片
		FileUploadUtils.saveFileUpload(processingTask, processingTask.getId(), "task_image");
		// 保存上传附件
		FileUploadUtils.saveFileUpload(processingTask, processingTask.getId(), "task_file");
		TaskCheck taskCheck = new TaskCheck();
		taskCheck.setCheckStatus(processingTask.getCheckStatus());
		taskCheck.setTaskCode(processingTask);
		if (null == processingTask.getCheckStatus() && Task.COMPLETED.equals(processingTask.getTaskStatus())) {
			taskCheck.preInsert();
			taskCheckDao.insert(taskCheck);
		} else {
			taskCheck.preUpdate();
			taskCheckDao.update(taskCheck);
		}
	}
	
	/**
	 * 更新状态
	 * @param processingTask
	 */
	@Override
	@Transactional(readOnly=false)
	public void updateStatus(ProcessingTask processingTask) {
		super.updateStatus(processingTask);
	}
	
	/**
	 * 删除数据
	 * @param processingTask
	 */
	@Override
	@Transactional(readOnly=false)
	public void delete(ProcessingTask processingTask) {
		super.delete(processingTask);
	}
	
}