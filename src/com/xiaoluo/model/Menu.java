package com.xiaoluo.model;

public class Menu {

	private java.lang.String id;
	private java.lang.String menuName;
	private java.lang.String menuUrl;
	private java.lang.Integer parentId;
	private java.lang.String icon;
	private java.lang.Integer orderNum;
	private java.lang.Long createTime;
	public Menu setId(java.lang.String id) {
		this.id = id;
		return this;
	}

	public java.lang.String getId() {
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

}
