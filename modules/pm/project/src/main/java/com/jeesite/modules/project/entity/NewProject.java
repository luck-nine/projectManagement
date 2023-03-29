/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.project.entity;

import com.jeesite.common.mybatis.annotation.Column;
import com.jeesite.common.mybatis.annotation.JoinTable;
import com.jeesite.common.mybatis.annotation.Table;
import com.jeesite.modules.sys.entity.User;
import com.jeesite.modules.sys.entity.UserRole;

/**
 * 新增项目信息Entity
 *
 * @author Liuzy
 * @version 2023-03-28
 */
@Table(name="pm_project", alias="a", label="新增项目信息", columns={
		@Column(includeEntity = Project.class),
	}, joinTable = {
		@JoinTable(type = JoinTable.Type.LEFT_JOIN, entity = User.class, alias = "u",
				on = "u.user_code=a.principal_code", attrName = "this",
				columns = {
					@Column(name = "user_name", attrName = "userName", label = "用户姓名"),
				}),
		@JoinTable(type = JoinTable.Type.LEFT_JOIN, entity = UserRole.class, alias = "r",
				on = "r.user_code=u.user_code", attrName = "this",
				columns = {
					@Column(name = "role_code", attrName = "roleCode", label = "用户等级"),
				}
		),
}, orderBy="a.project_code"
)
public class NewProject extends Project {

	public final static String DEPTROLE = "dept";
	private String userName;
	private String roleCode;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getRoleCode() {
		return roleCode;
	}

	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}
}