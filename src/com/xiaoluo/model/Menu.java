package com.xiaoluo.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Menu {

	private java.lang.Integer id;
	private java.lang.String menuName;
	private java.lang.String menuUrl;
	private java.lang.Integer parentId;
	private java.lang.String icon;
	private java.lang.Integer orderNum;
	private java.lang.Long createTime;
	/*private Set<Role> roles=new HashSet<Role>();*/  
	private List<Menu> subMenu = new ArrayList<Menu>();
	/*public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}*/

	public Menu setId(java.lang.Integer id) {
		this.id = id;
		return this;
	}

	public  java.lang.Integer getId() {
		return this.id;
	}

	public Menu setMenuName(java.lang.String menuName) {
		this.menuName = menuName;
		return this;
	}

	public java.lang.String getMenuName() {
		return this.menuName;
	}

	public Menu setMenuUrl(java.lang.String menuUrl) {
		this.menuUrl = menuUrl;
		return this;
	}

	public java.lang.String getMenuUrl() {
		return this.menuUrl;
	}

	public Menu setParentId(java.lang.Integer parentId) {
		this.parentId = parentId;
		return this;
	}

	public java.lang.Integer getParentId() {
		return this.parentId;
	}

	public Menu setIcon(java.lang.String icon) {
		this.icon = icon;
		return this;
	}

	public java.lang.String getIcon() {
		return this.icon;
	}

	public Menu setOrderNum(java.lang.Integer orderNum) {
		this.orderNum = orderNum;
		return this;
	}

	public java.lang.Integer getOrderNum() {
		return this.orderNum;
	}

	public Menu setCreateTime(java.lang.Long createTime) {
		this.createTime = createTime;
		return this;
	}

	public java.lang.Long getCreateTime() {
		return this.createTime;
	}

	public List<Menu> getSubMenuList() {
		// TODO Auto-generated method stub
		return this.subMenu;
	}

}
