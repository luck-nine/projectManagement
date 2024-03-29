/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.task.web;

import com.jeesite.common.config.Global;
import com.jeesite.common.entity.Page;
import com.jeesite.common.lang.DateUtils;
import com.jeesite.common.utils.excel.ExcelExport;
import com.jeesite.common.web.BaseController;
import com.jeesite.modules.task.entity.ViewTask;
import com.jeesite.modules.task.service.ViewTaskService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 查看任务Controller
 * @author Liuzy
 * @version 2023-04-12
 */
@Controller
@RequestMapping(value = "${adminPath}/task/viewTask")
public class ViewTaskController extends BaseController {

	private final ViewTaskService viewTaskService;

	public ViewTaskController(ViewTaskService viewTaskService) {
		this.viewTaskService = viewTaskService;
	}
	
	/**
	 * 获取数据
	 */
	@ModelAttribute
	public ViewTask get(String taskCode, boolean isNewRecord) {
		return viewTaskService.get(taskCode, isNewRecord);
	}
	
	/**
	 * 查询列表
	 */
	@RequiresPermissions("task:viewTask:view")
	@RequestMapping(value = {"list", ""})
	public String list(ViewTask viewTask, Model model) {
		List<ViewTask> list = viewTaskService.findList(viewTask);
		model.addAttribute("allIds", list.stream().map(ViewTask::getId).toArray(String[]::new));
		model.addAttribute("viewTask", viewTask);
		return "modules/task/viewTaskList";
	}
	
	/**
	 * 查询列表数据
	 */
	@RequiresPermissions("task:viewTask:view")
	@RequestMapping(value = "listData")
	@ResponseBody
	public Page<ViewTask> listData(ViewTask viewTask, HttpServletRequest request, HttpServletResponse response) {
		viewTask.setPage(new Page<>(request, response));
		Page<ViewTask> page = viewTaskService.findPage(viewTask);
		return page;
	}

	/**
	 * 查看编辑表单
	 */
	@RequiresPermissions("task:viewTask:view")
	@RequestMapping(value = "form")
	public String form(ViewTask viewTask, Model model) {
		model.addAttribute("viewTask", viewTask);
		return "modules/task/viewTaskForm";
	}

	/**
	 * 保存数据
	 */
	@RequiresPermissions("task:viewTask:edit")
	@PostMapping(value = "save")
	@ResponseBody
	public String save(@Validated ViewTask viewTask) {
		viewTaskService.save(viewTask);
		return renderResult(Global.TRUE, text("保存任务信息成功！"));
	}
	
	/**
	 * 删除数据
	 */
	@RequiresPermissions("task:viewTask:edit")
	@RequestMapping(value = "delete")
	@ResponseBody
	public String delete(ViewTask task) {
		viewTaskService.delete(task);
		return renderResult(Global.TRUE, text("删除任务信息成功！"));
	}

	/**
	 * 导出任务列表数据
	 */
	@RequestMapping(value = "exportTaskListData")
	public void exportTaskListData(ViewTask viewTask, HttpServletResponse response, @RequestParam("ids") String[] ids) {
		List<ViewTask> list = viewTaskService.findList(viewTask);
		viewTaskService.buildViewList(list);
		list = list.stream().filter(x -> Arrays.asList(ids).contains(x.getId())).collect(Collectors.toList());
		String fileName = "任务列表数据" + DateUtils.getDate("yyyyMMddHHmmss") + ".xlsx";
		try(ExcelExport ee = new ExcelExport("任务列表数据", ViewTask.class)){
			ee.setDataList(list).write(response, fileName);
		}
	}
}