/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.project.web;

import com.jeesite.common.config.Global;
import com.jeesite.common.entity.Page;
import com.jeesite.common.web.BaseController;
import com.jeesite.modules.project.entity.ProcessingProject;
import com.jeesite.modules.project.entity.Project;
import com.jeesite.modules.project.service.ProcessingProjectService;
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
 * 维护项目信息Controller
 * @author Liuzy
 * @version 2023-04-10
 */
@Controller
@RequestMapping(value = "${adminPath}/project/processingProject")
public class ProcessingProjectController extends BaseController {

	private final ProcessingProjectService processingProjectService;

	public ProcessingProjectController(ProcessingProjectService processingProjectService) {
		this.processingProjectService = processingProjectService;
	}
	
	/**
	 * 获取数据
	 */
	@ModelAttribute
	public ProcessingProject get(String id, boolean isNewRecord) {
		return processingProjectService.get(id, isNewRecord);
	}
	
	/**
	 * 查询列表
	 */
	@RequiresPermissions("project:processingProject:view")
	@RequestMapping(value = {"list", ""})
	public String list(ProcessingProject processingProject, Model model) {
		model.addAttribute("processingProject", processingProject);
		return "modules/project/processingProjectList";
	}
	
	/**
	 * 查询列表数据
	 */
	@RequiresPermissions("project:processingProject:view")
	@RequestMapping(value = "listData")
	@ResponseBody
	public Page<ProcessingProject> listData(ProcessingProject processingProject, HttpServletRequest request, HttpServletResponse response) {
		processingProject.setHasEffective(Project.EFFECTIVE);
		processingProject.setPage(new Page<>(request, response));
		Page<ProcessingProject> page = processingProjectService.findPage(processingProject);
		return page;
	}

	/**
	 * 查看编辑表单
	 */
	@RequiresPermissions("project:processingProject:view")
	@RequestMapping(value = "form")
	public String form(ProcessingProject processingProject, Model model) {
		model.addAttribute("processingProject", processingProject);
		return "modules/project/processingProjectForm";
	}

	/**
	 * 保存数据
	 */
	@RequiresPermissions("project:processingProject:edit")
	@PostMapping(value = "save")
	@ResponseBody
	public String save(@Validated ProcessingProject processingProject) {
		processingProjectService.save(processingProject);
		return renderResult(Global.TRUE, text("保存项目信息成功！"));
	}
	
}