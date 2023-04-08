/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.task.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.jeesite.common.entity.DataEntity;
import com.jeesite.common.mybatis.annotation.Column;
import com.jeesite.common.mybatis.annotation.JoinTable;
import com.jeesite.common.mybatis.annotation.Table;
import com.jeesite.common.mybatis.mapper.query.QueryType;
import com.jeesite.modules.project.entity.Project;
import com.jeesite.modules.sys.entity.User;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import java.util.Date;

/**
 * 任务信息Entity
 * @author Liuzy
 * @version 2023-04-07
 */
@Table(name="pm_task", alias="a", label="任务信息信息", columns={
		@Column(name="id", attrName="id", label="标识"),
		@Column(name="task_code", attrName="taskCode", label="任务编码", isPK=true),
		@Column(name="task_name", attrName="taskName", label="任务名称", queryType=QueryType.LIKE),
		@Column(name="project_code", attrName="projectCode", label="项目编码"),
		@Column(name="task_actor", attrName="taskActor", label="任务执行人"),
		@Column(name="task_description", attrName="taskDescription", label="任务描述"),
		@Column(name="priority", attrName="priority", label="优先级", comment="优先级（编码，来自数据字典）"),
		@Column(name="task_status", attrName="taskStatus", label="任务状态", comment="任务状态（编码，来自数据字典）"),
		@Column(name="accomplish_description", attrName="accomplishDescription", label="完成说明"),
		@Column(name="task_begin_date", attrName="taskBeginDate", label="任务开始日期"),
		@Column(name="task_end_date", attrName="taskEndDate", label="任务结束日期"),
		@Column(name="has_effective", attrName="hasEffective", label="是否提交", comment="是否提交（0：否 1：是）"),
		@Column(name="status", attrName="status", label="状态", comment="状态（0正常 1删除 2停用）", isUpdate=false),
		@Column(name="create_by", attrName="createBy", label="创建者", isUpdate=false, isQuery=false),
		@Column(name="create_date", attrName="createDate", label="创建时间", isUpdate=false, isQuery=false),
		@Column(name="update_by", attrName="updateBy", label="更新者", isQuery=false),
		@Column(name="update_date", attrName="updateDate", label="更新时间", isQuery=false),
	},joinTable = {
		@JoinTable(type = JoinTable.Type.LEFT_JOIN, entity = Project.class, alias = "p",
				on = "p.project_code=a.project_code", attrName = "this",
				columns = {
						@Column(name = "principal_code", attrName = "principalCode", label = "负责人编码")
				}),
		@JoinTable(type = JoinTable.Type.LEFT_JOIN, entity = User.class, alias = "u",
				on = "u.user_code=p.principal_code", attrName = "this",
				columns = {
						@Column(name = "user_name", attrName = "userName", label = "项目负责人"),
				})
}, orderBy="p.project_code DESC"
)
public class NewTask extends DataEntity<NewTask> {

	private static final long serialVersionUID = 1L;
	private String taskCode;		// 任务编码
	private String taskName;		// 任务名称
	private String projectCode;		// 项目编码
	private String taskActor;		// 任务执行人
	private String taskDescription;		// 任务描述
	private String priority;		// 优先级（编码，来自数据字典）
	private String taskStatus;		// 任务状态（编码，来自数据字典）
	private String accomplishDescription;		// 完成说明
	private Date taskBeginDate;		// 任务开始日期
	private Date taskEndDate;		// 任务结束日期
	private Integer hasEffective;		// 是否提交（0：否 1：是）
	private String projectName;			// 项目名称
	private String userName;		// 项目负责人

	public NewTask() {
		this(null);
	}

	public NewTask(String id){
		super(id);
	}

	@NotBlank(message="任务编码不能为空")
	@Length(min=0, max=64, message="任务编码长度不能超过 64 个字符")
	public String getTaskCode() {
		return taskCode;
	}

	public void setTaskCode(String taskCode) {
		this.taskCode = taskCode;
	}

	@Length(min=0, max=64, message="任务名称长度不能超过 64 个字符")
	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	@Length(min=0, max=64, message="项目编码长度不能超过 64 个字符")
	public String getProjectCode() {
		return projectCode;
	}

	public void setProjectCode(String projectCode) {
		this.projectCode = projectCode;
	}

	@Length(min=0, max=64, message="任务执行人长度不能超过 64 个字符")
	public String getTaskActor() {
		return taskActor;
	}

	public void setTaskActor(String taskActor) {
		this.taskActor = taskActor;
	}

	@Length(min=0, max=255, message="任务描述长度不能超过 255 个字符")
	public String getTaskDescription() {
		return taskDescription;
	}

	public void setTaskDescription(String taskDescription) {
		this.taskDescription = taskDescription;
	}

	@Length(min=0, max=64, message="优先级长度不能超过 64 个字符")
	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	@Length(min=0, max=64, message="任务状态长度不能超过 64 个字符")
	public String getTaskStatus() {
		return taskStatus;
	}

	public void setTaskStatus(String taskStatus) {
		this.taskStatus = taskStatus;
	}

	@Length(min=0, max=255, message="完成说明长度不能超过 255 个字符")
	public String getAccomplishDescription() {
		return accomplishDescription;
	}

	public void setAccomplishDescription(String accomplishDescription) {
		this.accomplishDescription = accomplishDescription;
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getTaskBeginDate() {
		return taskBeginDate;
	}

	public void setTaskBeginDate(Date taskBeginDate) {
		this.taskBeginDate = taskBeginDate;
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getTaskEndDate() {
		return taskEndDate;
	}

	public void setTaskEndDate(Date taskEndDate) {
		this.taskEndDate = taskEndDate;
	}

	public Integer getHasEffective() {
		return hasEffective;
	}

	public void setHasEffective(Integer hasEffective) {
		this.hasEffective = hasEffective;
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
}