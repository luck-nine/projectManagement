/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.project.entity;

import javax.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

import com.jeesite.common.entity.DataEntity;
import com.jeesite.common.mybatis.annotation.Column;
import com.jeesite.common.mybatis.annotation.Table;
import com.jeesite.common.mybatis.mapper.query.QueryType;

/**
 * 项目信息Entity
 * @author Liuzy
 * @version 2023-03-28
 */
@Table(name="pm_project", alias="a", label="项目信息信息", columns={
		@Column(name="id", attrName="id", label="标识"),
		@Column(name="project_code", attrName="projectCode", label="项目编码", isPK=true),
		@Column(name="project_name", attrName="projectName", label="项目名称", queryType=QueryType.LIKE),
		@Column(name="principal", attrName="principal", label="项目负责人"),
		@Column(name="project_description", attrName="projectDescription", label="项目描述"),
		@Column(name="has_effective", attrName="hasEffective", label="是否提交", comment="是否提交（0：否 1：是）", isUpdateForce = true),
		@Column(name="status", attrName="status", label="状态", comment="状态（0正常 1删除 2停用）", isUpdate=false),
		@Column(name="create_by", attrName="createBy", label="创建者", isUpdate=false, isQuery=false),
		@Column(name="create_date", attrName="createDate", label="创建时间", isUpdate=false, isQuery=false),
		@Column(name="update_by", attrName="updateBy", label="更新者", isQuery=false),
		@Column(name="update_date", attrName="updateDate", label="更新时间", isQuery=false),
	}, orderBy="a.project_code"
)
public class Project extends DataEntity<Project> {

	public final static int NOT_EFFECTIVE = 0;
	public final static int EFFECTIVE = 1;
	private static final long serialVersionUID = 1L;
	private String projectCode;		// 项目编码
	private String projectName;		// 项目名称
	private String principal;		// 项目负责人
	private String projectDescription;		// 项目描述
	private Integer hasEffective;		// 是否提交（0：否 1：是）
	
	public Project() {
		this(null);
	}

	public Project(String id){
		super(id);
	}
	
	@NotBlank(message="项目编码不能为空")
	@Length(min=0, max=64, message="项目编码长度不能超过 64 个字符")
	public String getProjectCode() {
		return projectCode;
	}

	public void setProjectCode(String projectCode) {
		this.projectCode = projectCode;
	}
	
	@Length(min=0, max=64, message="项目名称长度不能超过 64 个字符")
	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	
	@Length(min=0, max=64, message="项目负责人长度不能超过 64 个字符")
	public String getPrincipal() {
		return principal;
	}

	public void setPrincipal(String principal) {
		this.principal = principal;
	}
	
	@Length(min=0, max=255, message="项目描述长度不能超过 255 个字符")
	public String getProjectDescription() {
		return projectDescription;
	}

	public void setProjectDescription(String projectDescription) {
		this.projectDescription = projectDescription;
	}
	
	public Integer getHasEffective() {
		return hasEffective;
	}

	public void setHasEffective(Integer hasEffective) {
		this.hasEffective = hasEffective;
	}
	
}