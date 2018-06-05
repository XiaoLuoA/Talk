package com.xiaoluo.model;

import java.util.HashSet;
import java.util.Set;

public class Role {

	private java.lang.Integer id;
	private java.lang.String roleName;
	private java.lang.Long createTime;
	/*private Set<Menu> menus=new HashSet<Menu>();  */
	public Role setId(java.lang.Integer id) {
		this.id = id;
		return this;
	}

/*	public Set<Menu> getMenus() {
		return menus;
	}

	public void setMenus(Set<Menu> menus) {
		this.menus = menus;
	}*/

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
