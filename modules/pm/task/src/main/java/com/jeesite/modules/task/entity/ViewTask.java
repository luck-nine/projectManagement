/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.task.entity;

import com.jeesite.common.mybatis.annotation.Column;
import com.jeesite.common.mybatis.annotation.JoinTable;
import com.jeesite.common.mybatis.annotation.Table;
import com.jeesite.common.utils.excel.annotation.ExcelField;
import com.jeesite.common.utils.excel.annotation.ExcelFields;
import com.jeesite.modules.project.entity.Project;
import com.jeesite.modules.sys.entity.User;

/**
 * 查看任务Entity
 * @author Liuzy
 * @version 2023-04-12
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
				}),
		@JoinTable(type = JoinTable.Type.LEFT_JOIN, entity = TaskCheck.class, alias = "t",
				on = "t.task_code=a.task_code", attrName = "this",
				columns = {
						@Column(name = "check_status", attrName = "checkStatus", label = "审核状态"),
						@Column(name = "check_opinion", attrName = "checkOpinion", label = "审核意见")
				})
}, orderBy="a.task_code DESC"
)
public class ViewTask extends Task {

	private static final long serialVersionUID = 1L;
	private String projectName;			// 项目名称
	private String userName;		// 项目负责人
	private String actorName;		// 任务执行人姓名
	private String checkStatus;		// 审核状态
	private String checkOpinion;	// 审核意见

	public ViewTask() {
		this(null);
	}

	public ViewTask(String id){
		super(id);
	}

	@ExcelFields({
			@ExcelField(title="任务编码", attrName="taskCode", align= ExcelField.Align.CENTER, sort=10),
			@ExcelField(title="任务名称", attrName="taskName", align= ExcelField.Align.CENTER, sort=20),
			@ExcelField(title="项目编码", attrName="projectCode", align= ExcelField.Align.CENTER, sort=30),
			@ExcelField(title="项目负责人", attrName="userName", align= ExcelField.Align.CENTER, sort=40),
			@ExcelField(title="任务执行人", attrName="actorName", align= ExcelField.Align.CENTER, sort=50),
			@ExcelField(title="任务描述", attrName="taskDescription", align= ExcelField.Align.CENTER, sort=60),
			@ExcelField(title="完成说明", attrName="accomplishDescription", align= ExcelField.Align.CENTER, sort=70),
			@ExcelField(title="任务开始日期", attrName="taskBeginDate", align= ExcelField.Align.CENTER, sort=80, dataFormat="yyyy-MM-dd"),
			@ExcelField(title="任务结束日期", attrName="taskEndDate", align= ExcelField.Align.CENTER, sort=90, dataFormat="yyyy-MM-dd"),
			@ExcelField(title="优先级", attrName="priority", align= ExcelField.Align.CENTER, sort=100),
			@ExcelField(title="任务状态", attrName="taskStatus", align= ExcelField.Align.CENTER, sort=110),
			@ExcelField(title="审核状态", attrName="checkStatus", align= ExcelField.Align.CENTER, sort=120),
			@ExcelField(title="更新时间", attrName="updateDate", align= ExcelField.Align.CENTER, sort=130, dataFormat="yyyy-MM-dd HH:mm:ss"),
	})
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

	public String getCheckStatus() {
		return checkStatus;
	}

	public void setCheckStatus(String checkStatus) {
		this.checkStatus = checkStatus;
	}

	public String getCheckOpinion() {
		return checkOpinion;
	}

	public void setCheckOpinion(String checkOpinion) {
		this.checkOpinion = checkOpinion;
	}
}