/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.task.web;

import com.jeesite.common.config.Global;
import com.jeesite.common.entity.Page;
import com.jeesite.common.web.BaseController;
import com.jeesite.modules.project.entity.Project;
import com.jeesite.modules.task.entity.NewTask;
import com.jeesite.modules.task.entity.Task;
import com.jeesite.modules.task.service.NewTaskService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 任务信息Controller
 * @author Liuzy
 * @version 2023-04-07
 */
@Controller
@RequestMapping(value = "${adminPath}/task/newTask")
public class NewTaskController extends BaseController {

	private final NewTaskService newTaskService;

	public NewTaskController(NewTaskService newTaskService){
		this.newTaskService = newTaskService;
	}
	
	/**
	 * 获取数据
	 */
	@ModelAttribute
	public NewTask get(String id, boolean isNewRecord) {
		return newTaskService.get(id, isNewRecord);
	}

	/**
	 * 查询列表
	 */
	@RequiresPermissions("task:newTask:view")
	@RequestMapping(value = {"list", ""})
	public String list(NewTask newTask, Model model) {
		model.addAttribute("newTask", newTask);
		return "modules/task/newTaskList";
	}

	/**
	 * 查看任务列表--跳转链接
	 */
	@RequiresPermissions("task:newTask:view")
	@RequestMapping(value = {"newTaskList"})
	public String newTaskList(NewTask newTask, Model model) {
		model.addAttribute("newTask", newTask);
		return "modules/task/newProjectTaskList";
	}

	/**
	 * 查询列表数据
	 */
	@RequiresPermissions("task:newTask:view")
	@RequestMapping(value = "listData")
	@ResponseBody
	public Page<NewTask> listData(NewTask newTask, HttpServletRequest request, HttpServletResponse response) {
		Page<NewTask> page = new Page<>(request, response);
		List<NewTask> projectList = newTaskService.findProjectList(newTask);
		page.setList(projectList);
		page.setCount(projectList.size());
		return page;
	}

	/**
	 * 查询任务列表数据
	 */
	@RequiresPermissions("task:newTask:view")
	@RequestMapping(value = "taskListData")
	@ResponseBody
	public Page<NewTask> taskListData(NewTask newTask, HttpServletRequest request, HttpServletResponse response) {
		newTask.setHasEffective(Task.NOT_EFFECTIVE);
		newTask.setPage(new Page<>(request, response));
		Page<NewTask> page = newTaskService.findPage(newTask);
		return page;
	}

	/**
	 * 查看编辑表单
	 */
	@RequiresPermissions("task:newTask:view")
	@RequestMapping(value = "form")
	public String form(NewTask newTask, Model model) {
		newTaskService.buildTaskCode(newTask);
		model.addAttribute("newTask", newTask);
		return "modules/task/newProjectTaskForm";
	}

	/**
	 * 保存数据
	 */
	@RequiresPermissions("task:newTask:edit")
	@PostMapping(value = "save")
	@ResponseBody
	public String save(@Validated NewTask newTask) {
		newTaskService.save(newTask);
		return Task.NOT_EFFECTIVE == newTask.getHasEffective() ?
				renderResult(Global.TRUE, text("保存任务信息成功！")) :
				renderResult(Global.TRUE, text("提交任务信息成功！"));
	}
	
	/**
	 * 删除数据
	 */
	@RequiresPermissions("task:newTask:edit")
	@RequestMapping(value = "delete")
	@ResponseBody
	public String delete(NewTask newTask) {
		newTaskService.delete(newTask);
		return renderResult(Global.TRUE, text("删除任务信息成功！"));
	}
	
}