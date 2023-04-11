/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.task.web;

import com.jeesite.common.config.Global;
import com.jeesite.common.entity.Page;
import com.jeesite.common.mybatis.mapper.query.QueryType;
import com.jeesite.common.web.BaseController;
import com.jeesite.modules.sys.utils.UserUtils;
import com.jeesite.modules.task.entity.ProcessingTask;
import com.jeesite.modules.task.entity.Task;
import com.jeesite.modules.task.service.ProcessingTaskService;
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
 * 处理任务Controller
 * @author Liuzy
 * @version 2023-04-10
 */
@Controller
@RequestMapping(value = "${adminPath}/task/processingTask")
public class ProcessingTaskController extends BaseController {

	private final ProcessingTaskService processingTaskService;

	public ProcessingTaskController(ProcessingTaskService processingTaskService){
		this.processingTaskService = processingTaskService;
	}
	
	/**
	 * 获取数据
	 */
	@ModelAttribute
	public ProcessingTask get(String id, boolean isNewRecord) {
		return processingTaskService.get(id, isNewRecord);
	}
	
	/**
	 * 查询列表
	 */
	@RequiresPermissions("task:processingTask:view")
	@RequestMapping(value = {"list", ""})
	public String list(ProcessingTask processingTask, Model model) {
		model.addAttribute("processingTask", processingTask);
		return "modules/task/processingTaskList";
	}
	
	/**
	 * 查询列表数据
	 */
	@RequiresPermissions("task:processingTask:view")
	@RequestMapping(value = "listData")
	@ResponseBody
	public Page<ProcessingTask> listData(ProcessingTask processingTask, HttpServletRequest request, HttpServletResponse response) {
		processingTask.setTaskActor("system".equals(UserUtils.getUser().getUserCode()) ? null : UserUtils.getUser().getUserCode());
		processingTask.setHasEffective(Task.EFFECTIVE);
		processingTask.getSqlMap().getWhere().andBracket("t.check_status", QueryType.IS_NULL, null, 2)
				.or("t.check_status", QueryType.EQ, "2", 3).endBracket();
		processingTask.setPage(new Page<>(request, response));
		Page<ProcessingTask> page = processingTaskService.findPage(processingTask);
		return page;
	}

	/**
	 * 查看编辑表单
	 */
	@RequiresPermissions("task:processingTask:view")
	@RequestMapping(value = "form")
	public String form(ProcessingTask processingTask, Model model) {
		model.addAttribute("processingTask", processingTask);
		return "modules/task/processingTaskForm";
	}

	/**
	 * 保存数据
	 */
	@RequiresPermissions("task:processingTask:edit")
	@PostMapping(value = "save")
	@ResponseBody
	public String save(@Validated ProcessingTask processingTask) {
		processingTaskService.save(processingTask);
		return null == processingTask.getCheckStatus() || "".equals(processingTask.getCheckStatus()) ?
				renderResult(Global.TRUE, text("保存任务信息成功！")) :
				renderResult(Global.TRUE, text("提交任务信息成功！"));
	}
	
	/**
	 * 删除数据
	 */
	@RequiresPermissions("task:processingTask:edit")
	@RequestMapping(value = "delete")
	@ResponseBody
	public String delete(ProcessingTask processingTask) {
		processingTaskService.delete(processingTask);
		return renderResult(Global.TRUE, text("删除任务信息成功！"));
	}
	
}