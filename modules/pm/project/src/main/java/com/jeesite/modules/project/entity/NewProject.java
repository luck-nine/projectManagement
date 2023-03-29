/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.project.entity;

import com.jeesite.common.mybatis.annotation.Column;
import com.jeesite.common.mybatis.annotation.JoinTable;
import com.jeesite.common.mybatis.annotation.Table;
import com.jeesite.modules.sys.entity.User;

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
}, orderBy="a.project_code"
)
public class NewProject extends Project {

	private String userName;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
}