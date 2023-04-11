/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.task.entity;

import com.jeesite.common.mybatis.annotation.Column;
import com.jeesite.common.mybatis.annotation.JoinTable;
import com.jeesite.common.mybatis.annotation.Table;
import com.jeesite.modules.project.entity.Project;
import com.jeesite.modules.sys.entity.User;

/**
 * 审核任务Entity
 * @author Liuzy
 * @version 2023-04-10
 */
@Table(name="pm_task", alias="a", label="任务信息", columns={
		@Column(includeEntity = Task.class),
	},joinTable = {
		@JoinTable(type = JoinTable.Type.LEFT_JOIN, entity = Project.class, alias = "p",
				on = "p.project_code=a.project_code", attrName = "this",
				columns = {
						@Column(name = "project_name", attrName = "projectName", label = "项目名称"),
						@Column(name = "principal_code", attrName = "principalCode", label = "负责人编码")
				}),
		@JoinTable(type = JoinTable.Type.LEFT_JOIN, entity = User.class, alias = "u",
				on = "u.user_code=p.principal_code", attrName = "this",
				columns = {
						@Column(name = "user_name", attrName = "userName", label = "项目负责人"),
				}),
		@JoinTable(type = JoinTable.Type.LEFT_JOIN, entity = User.class, alias = "o",
				on = "o.user_code=a.task_actor", attrName = "this",
				columns = {
						@Column(name = "user_name", attrName = "actorName", label = "任务执行人"),
				})
}, orderBy="p.project_code DESC"
)
public class CheckTask extends Task {

	private static final long serialVersionUID = 1L;
	private String projectName;			// 项目名称
	private String userName;		// 项目负责人
	private String actorName;		// 任务执行人姓名

	public CheckTask() {
		this(null);
	}

	public CheckTask(String id){
		super(id);
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getActorName() {
		return actorName;
	}

	public void setActorName(String actorName) {
		this.actorName = actorName;
	}
}