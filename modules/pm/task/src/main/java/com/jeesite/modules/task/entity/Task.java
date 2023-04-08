/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.task.entity;

import javax.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.jeesite.common.mybatis.annotation.JoinTable;
import com.jeesite.common.mybatis.annotation.JoinTable.Type;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.jeesite.common.entity.DataEntity;
import com.jeesite.common.mybatis.annotation.Column;
import com.jeesite.common.mybatis.annotation.Table;
import com.jeesite.common.mybatis.mapper.query.QueryType;

/**
 * 任务信息Entity
 * @author Liuzy
 * @version 2023-04-08
 */
@Table(name="pm_task", alias="a", label="任务信息信息", columns={
		@Column(name="id", attrName="id", label="标识", isPK=true),
		@Column(name="task_code", attrName="taskCode", label="任务编码"),
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
	}, orderBy="a.update_date DESC"
)
public class Task extends DataEntity<Task> {

	public final static int NOT_EFFECTIVE = 0;
	public final static int EFFECTIVE = 1;
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
	
	public Task() {
		this(null);
	}

	public Task(String id){
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
	
	@JsonFormat(pattern = "yyyy-MM-dd")
	public Date getTaskBeginDate() {
		return taskBeginDate;
	}

	public void setTaskBeginDate(Date taskBeginDate) {
		this.taskBeginDate = taskBeginDate;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd")
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
	
	public Date getTaskBeginDate_gte() {
		return sqlMap.getWhere().getValue("task_begin_date", QueryType.GTE);
	}

	public void setTaskBeginDate_gte(Date taskBeginDate) {
		sqlMap.getWhere().and("task_begin_date", QueryType.GTE, taskBeginDate);
	}
	
	public Date getTaskBeginDate_lte() {
		return sqlMap.getWhere().getValue("task_begin_date", QueryType.LTE);
	}

	public void setTaskBeginDate_lte(Date taskBeginDate) {
		sqlMap.getWhere().and("task_begin_date", QueryType.LTE, taskBeginDate);
	}
	
	public Date getTaskEndDate_gte() {
		return sqlMap.getWhere().getValue("task_end_date", QueryType.GTE);
	}

	public void setTaskEndDate_gte(Date taskEndDate) {
		sqlMap.getWhere().and("task_end_date", QueryType.GTE, taskEndDate);
	}
	
	public Date getTaskEndDate_lte() {
		return sqlMap.getWhere().getValue("task_end_date", QueryType.LTE);
	}

	public void setTaskEndDate_lte(Date taskEndDate) {
		sqlMap.getWhere().and("task_end_date", QueryType.LTE, taskEndDate);
	}
	
}