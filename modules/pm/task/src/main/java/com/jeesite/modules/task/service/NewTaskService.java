/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.task.service;

import com.jeesite.common.entity.Page;
import com.jeesite.common.service.CrudService;
import com.jeesite.modules.file.utils.FileUploadUtils;
import com.jeesite.modules.task.dao.NewTaskDao;
import com.jeesite.modules.task.entity.NewTask;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 新增任务Service
 * @author Liuzy
 * @version 2023-04-07
 */
@Service
@Transactional(readOnly=true)
public class NewTaskService extends CrudService<NewTaskDao, NewTask> {
	
	/**
	 * 获取单条数据
	 * @param newTask
	 * @return
	 */
	@Override
	public NewTask get(NewTask newTask) {
		return super.get(newTask);
	}
	
	/**
	 * 查询分页数据
	 * @param newTask 查询条件
	 * @return
	 */
	@Override
	public Page<NewTask> findPage(NewTask newTask) {
		return super.findPage(newTask);
	}
	
	/**
	 * 保存数据（插入或更新）
	 * @param newTask
	 */
	@Override
	@Transactional(readOnly=false)
	public void save(NewTask newTask) {
		super.save(newTask);
		// 保存上传图片
		FileUploadUtils.saveFileUpload(newTask, newTask.getId(), "task_image");
		// 保存上传附件
		FileUploadUtils.saveFileUpload(newTask, newTask.getId(), "task_file");
	}
	
	/**
	 * 更新状态
	 * @param newTask
	 */
	@Override
	@Transactional(readOnly=false)
	public void updateStatus(NewTask newTask) {
		super.updateStatus(newTask);
	}
	
	/**
	 * 删除数据
	 * @param newTask
	 */
	@Override
	@Transactional(readOnly=false)
	public void delete(NewTask newTask) {
		super.delete(newTask);
	}

	/**
	 * 查询项目列表
	 * @param newTask
	 */
	public List<NewTask> findProjectList(NewTask newTask) {
		return dao.findProjectList(newTask);
	}

	/**
	 * 自动生成任务编码
	 * @param newTask
	 */
	public NewTask buildTaskCode(NewTask newTask) {
		if (null != newTask.getId()) {
			return newTask;
		} else {
			String taskCode = "PM" + newTask.getProjectCode().substring(7) + "-task";
			List<NewTask> existList = dao.findList(newTask);
			if (existList.size() == 0) {
				newTask.setTaskCode(taskCode + "001");
			} else {
				String lastCode = existList.get(0).getTaskCode();
				int i = Integer.parseInt(lastCode.substring(9)) + 1;
				newTask.setTaskCode(taskCode + (i < 10 ? "00" + i : "0" + i));
			}
			newTask.setIsNewRecord(true);
		}
		return newTask;
	}
}