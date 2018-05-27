package com.xiaoluo.model;

public class RoleMenu {

	private java.lang.Integer id;
	private java.lang.Integer roleId;
	private java.lang.Integer menuId;
	private java.lang.Long createTime;
	public RoleMenu setId(java.lang.Integer id) {
		this.id = id;
		return this;
	}

	public java.lang.Integer getId() {
		return this.id;
	}

	public RoleMenu setRoleId(java.lang.Integer roleId) {
		this.roleId = roleId;
		return this;
	}

	public java.lang.Integer getRoleId() {
		return this.roleId;
	}

	public RoleMenu setMenuId(java.lang.Integer menuId) {
		this.menuId = menuId;
		return this;
	}

	public java.lang.Integer getMenuId() {
		return this.menuId;
	}

	public RoleMenu setCreateTime(java.lang.Long createTime) {
		this.createTime = createTime;
		return this;
	}

	public java.lang.Long getCreateTime() {
		return this.createTime;
	}

}
