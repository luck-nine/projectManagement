/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.project.web;

import com.jeesite.common.config.Global;
import com.jeesite.common.entity.Page;
import com.jeesite.common.web.BaseController;
import com.jeesite.modules.project.entity.Project;
import com.jeesite.modules.project.service.NewProjectService;
import com.jeesite.modules.project.service.ProjectService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
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
 * 项目信息Controller
 * @author Liuzy
 * @version 2023-03-28
 */
@Controller
@RequestMapping(value = "${adminPath}/project/newProject")
public class NewProjectController extends BaseController {

	private final NewProjectService newProjectService;

	public NewProjectController(NewProjectService newProjectService) {
		this.newProjectService = newProjectService;
	}
	
	/**
	 * 获取数据
	 */
	@ModelAttribute
	public Project get(String id, boolean isNewRecord) {
		return newProjectService.get(id, isNewRecord);
	}
	
	/**
	 * 查询列表
	 */
	@RequiresPermissions("project:newProject:view")
	@RequestMapping(value = {"list", ""})
	public String list(Project project, Model model) {
		model.addAttribute("project", project);
		return "modules/project/newProjectList";
	}
	
	/**
	 * 查询列表数据
	 */
	@RequiresPermissions("project:newProject:view")
	@RequestMapping(value = "listData")
	@ResponseBody
	public Page<Project> listData(Project project, HttpServletRequest request, HttpServletResponse response) {
		project.setPage(new Page<>(request, response));
		Page<Project> page = newProjectService.findPage(project);
		return page;
	}

	/**
	 * 查看编辑表单
	 */
	@RequiresPermissions("project:newProject:view")
	@RequestMapping(value = "form")
	public String form(Project project, Model model) {
		model.addAttribute("project", project);
		return "modules/project/newProjectForm";
	}

	/**
	 * 保存数据
	 */
	@RequiresPermissions("project:newProject:edit")
	@PostMapping(value = "save")
	@ResponseBody
	public String save(@Validated Project project) {
		newProjectService.save(project);
		return renderResult(Global.TRUE, text("保存项目信息成功！"));
	}
	
}