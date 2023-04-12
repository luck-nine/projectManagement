/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.task.web;

import com.jeesite.common.config.Global;
import com.jeesite.common.entity.Page;
import com.jeesite.common.web.BaseController;
import com.jeesite.modules.sys.utils.UserUtils;
import com.jeesite.modules.task.entity.CheckTask;
import com.jeesite.modules.task.entity.Task;
import com.jeesite.modules.task.entity.TaskCheck;
import com.jeesite.modules.task.service.CheckTaskService;
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

/**
 * 审核任务Controller
 * @author Liuzy
 * @version 2023-04-10
 */
@Controller
@RequestMapping(value = "${adminPath}/task/checkTask")
public class CheckTaskController extends BaseController {

	private final CheckTaskService checkTaskService;

	public CheckTaskController(CheckTaskService checkTaskService) {
		this.checkTaskService = checkTaskService;
	}
	
	/**
	 * 获取数据
	 */
	@ModelAttribute
	public CheckTask get(String id, boolean isNewRecord) {
		return checkTaskService.get(id, isNewRecord);
	}
	
	/**
	 * 查询列表
	 */
	@RequiresPermissions("task:checkTask:view")
	@RequestMapping(value = {"list", ""})
	public String list(CheckTask checkTask, Model model) {
		model.addAttribute("task", checkTask);
		return "modules/task/checkTaskList";
	}
	
	/**
	 * 查询列表数据
	 */
	@RequiresPermissions("task:checkTask:view")
	@RequestMapping(value = "listData")
	@ResponseBody
	public Page<CheckTask> listData(CheckTask checkTask, HttpServletRequest request, HttpServletResponse response) {
		checkTask.setUserName("system".equals(UserUtils.getUser().getUserCode()) ? null : UserUtils.getUser().getUserName());
		checkTask.setTaskStatus(Task.COMPLETED);
		checkTask.setCheckStatus(TaskCheck.PENDING);
		checkTask.setPage(new Page<>(request, response));
		Page<CheckTask> page = checkTaskService.findPage(checkTask);
		return page;
	}

	/**
	 * 查看编辑表单
	 */
	@RequiresPermissions("task:checkTask:view")
	@RequestMapping(value = "form")
	public String form(CheckTask checkTask, Model model) {
		model.addAttribute("checkTask", checkTask);
		return "modules/task/checkTaskForm";
	}

	/**
	 * 保存数据
	 */
	@RequiresPermissions("task:checkTask:edit")
	@PostMapping(value = "save")
	@ResponseBody
	public String save(@Validated CheckTask checkTask) {
		checkTaskService.save(checkTask);
		return renderResult(Global.TRUE, text("审核完毕！"));
	}
	
	/**
	 * 删除数据
	 */
	@RequiresPermissions("task:checkTask:edit")
	@RequestMapping(value = "delete")
	@ResponseBody
	public String delete(CheckTask checkTask) {
		checkTaskService.delete(checkTask);
		return renderResult(Global.TRUE, text("删除任务信息成功！"));
	}
	
}