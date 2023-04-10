/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.project.web;

import com.jeesite.common.entity.Page;
import com.jeesite.common.web.BaseController;
import com.jeesite.modules.project.entity.Project;
import com.jeesite.modules.project.entity.ViewProject;
import com.jeesite.modules.project.service.ViewProjectService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 查看项目信息Controller
 * @author Liuzy
 * @version 2023-03-28
 */
@Controller
@RequestMapping(value = "${adminPath}/project/viewProject")
public class ViewProjectController extends BaseController {

	private final ViewProjectService viewProjectService;

	public ViewProjectController(ViewProjectService viewProjectService) {
		this.viewProjectService = viewProjectService;
	}

	/**
	 * 获取数据
	 */
	@ModelAttribute
	public ViewProject get(String id, boolean isNewRecord) {
		return viewProjectService.get(id, isNewRecord);
	}
	
	/**
	 * 查询列表
	 */
	@RequiresPermissions("project:viewProject:view")
	@RequestMapping(value = {"list", ""})
	public String list(ViewProject viewProject, Model model) {
		model.addAttribute("viewProject", viewProject);
		return "modules/project/viewProjectList";
	}
	
	/**
	 * 查询列表数据
	 */
	@RequiresPermissions("project:viewProject:view")
	@RequestMapping(value = "listData")
	@ResponseBody
	public Page<ViewProject> listData(ViewProject viewProject, HttpServletRequest request, HttpServletResponse response) {
		viewProject.setHasEffective(Project.EFFECTIVE);
		viewProject.setPage(new Page<>(request, response));
		Page<ViewProject> page = viewProjectService.findPage(viewProject);
		return page;
	}

	/**
	 * 查看项目提交表单
	 */
	@RequiresPermissions("project:viewProject:view")
	@RequestMapping(value = "commitForm")
	public String form(ViewProject viewProject, Model model) {
		model.addAttribute("viewProject", viewProject);
		return "modules/project/viewProjectCommitForm";
	}

}