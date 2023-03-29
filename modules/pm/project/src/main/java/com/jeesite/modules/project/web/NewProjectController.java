/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.project.web;

import com.jeesite.common.config.Global;
import com.jeesite.common.entity.Page;
import com.jeesite.common.web.BaseController;
import com.jeesite.modules.project.entity.NewProject;
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
	public NewProject get(String id, boolean isNewRecord) {
		return newProjectService.get(id, isNewRecord);
	}
	
	/**
	 * 查询列表
	 */
	@RequiresPermissions("project:newProject:view")
	@RequestMapping(value = {"list", ""})
	public String list(NewProject newProject, Model model) {
		model.addAttribute("newProject", newProject);
		return "modules/project/newProjectList";
	}
	
	/**
	 * 查询列表数据
	 */
	@RequiresPermissions("project:newProject:view")
	@RequestMapping(value = "listData")
	@ResponseBody
	public Page<NewProject> listData(NewProject newProject, HttpServletRequest request, HttpServletResponse response) {
		newProject.setHasEffective(Project.NOT_EFFECTIVE);
		newProject.setPage(new Page<>(request, response));
		Page<NewProject> page = newProjectService.findPage(newProject);
		return page;
	}

	/**
	 * 查看编辑表单
	 */
	@RequiresPermissions("project:newProject:view")
	@RequestMapping(value = "form")
	public String form(NewProject newProject, Model model) {
		newProjectService.buildSaleContractCode(newProject);
		model.addAttribute("project", newProject);
		return "modules/project/newProjectForm";
	}

	/**
	 * 保存数据
	 */
	@RequiresPermissions("project:newProject:edit")
	@PostMapping(value = "save")
	@ResponseBody
	public String save(@Validated NewProject newProject) {
		/*if (NewProject.DEPTROLE.equals(newProject.getRoleCode())) {
			return renderResult(Global.FALSE, text(""));
		}*/
		newProjectService.save(newProject);
		return Project.NOT_EFFECTIVE == newProject.getHasEffective() ?
				renderResult(Global.TRUE, text("保存项目信息成功！")) :
				renderResult(Global.TRUE, text("提交项目信息成功！"));
	}

	/**
	 * 删除数据
	 */
	@RequiresPermissions("project:newProject:edit")
	@RequestMapping(value = "delete")
	@ResponseBody
	public String delete(NewProject newProject) {
		newProjectService.delete(newProject);
		return renderResult(Global.TRUE, text("删除项目信息成功！"));
	}
}