package com.xiaoluo.model;

public class Role {

	private java.lang.Integer id;
	private java.lang.String roleName;
	private java.lang.Long createTime;
	public Role setId(java.lang.Integer id) {
		this.id = id;
		return this;
	}

	public java.lang.Integer getId() {
		return this.id;
	}

	public Role setRoleName(java.lang.String roleName) {
		this.roleName = roleName;
		return this;
	}

	public java.lang.String getRoleName() {
		return this.roleName;
	}

	public Role setCreateTime(java.lang.Long createTime) {
		this.createTime = createTime;
		return this;
	}

	public java.lang.Long getCreateTime() {
		return this.createTime;
	}

}
