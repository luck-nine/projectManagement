/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.task.entity;

import org.hibernate.validator.constraints.Length;

import com.jeesite.common.entity.DataEntity;
import com.jeesite.common.mybatis.annotation.Column;
import com.jeesite.common.mybatis.annotation.Table;

/**
 * 任务审核Entity
 * @author Liuzy
 * @version 2023-04-11
 */
@Table(name="pm_task_check", alias="a", label="任务信息信息", columns={
		@Column(name="id", attrName="id", label="标识"),
		@Column(name="task_code", attrName="taskCode.taskCode", label="任务编码", isPK=true),
		@Column(name="check_status", attrName="checkStatus", label="审核状态"),
		@Column(name="check_opinion", attrName="checkOpinion", label="审核意见"),
		@Column(name="status", attrName="status", label="状态", comment="状态（0正常 1删除 2停用）", isUpdate=false, isQuery=false),
		@Column(name="create_by", attrName="createBy", label="创建者", isUpdate=false, isQuery=false),
		@Column(name="create_date", attrName="createDate", label="创建时间", isUpdate=false, isQuery=false),
		@Column(name="update_by", attrName="updateBy", label="更新者", isQuery=false),
		@Column(name="update_date", attrName="updateDate", label="更新时间", isQuery=false),
	}, orderBy="a.create_date ASC"
)
public class TaskCheck extends DataEntity<TaskCheck> {

	public static final String PENDING = "0";
	public static final String FINISHED = "1";
	public static final String REJECTED = "2";
	private static final long serialVersionUID = 1L;
	private Task taskCode;		// 任务编码 父类
	private String checkStatus;		// 审核状态
	private String checkOpinion;		// 审核意见
	
	public TaskCheck() {
		this(null);
	}


	public TaskCheck(Task taskCode){
		this.taskCode = taskCode;
	}
	
	public Task getTaskCode() {
		return taskCode;
	}

	public void setTaskCode(Task taskCode) {
		this.taskCode = taskCode;
	}
	
	@Length(min=0, max=64, message="审核状态长度不能超过 64 个字符")
	public String getCheckStatus() {
		return checkStatus;
	}

	public void setCheckStatus(String checkStatus) {
		this.checkStatus = checkStatus;
	}
	
	@Length(min=0, max=255, message="审核意见长度不能超过 255 个字符")
	public String getCheckOpinion() {
		return checkOpinion;
	}

	public void setCheckOpinion(String checkOpinion) {
		this.checkOpinion = checkOpinion;
	}
	
}