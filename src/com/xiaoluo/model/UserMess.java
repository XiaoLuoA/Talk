package com.xiaoluo.model;

public class UserMess {

	private java.lang.Integer userMessId;
	private java.lang.String itemId;
	private java.lang.Integer fromId;
	private java.lang.Long sendTime;
	private java.lang.Integer toId;
	private java.lang.Integer isRead;
	private java.lang.String content;
	public UserMess setUserMessId(java.lang.Integer userMessId) {
		this.userMessId = userMessId;
		return this;
	}

	public java.lang.Integer getUserMessId() {
		return this.userMessId;
	}

	public UserMess setItemId(java.lang.String itemId) {
		this.itemId = itemId;
		return this;
	}

	public java.lang.String getItemId() {
		return this.itemId;
	}

	public UserMess setFromId(java.lang.Integer fromId) {
		this.fromId = fromId;
		return this;
	}

	public java.lang.Integer getFromId() {
		return this.fromId;
	}

	public UserMess setSendTime(java.lang.Long sendTime) {
		this.sendTime = sendTime;
		return this;
	}

	public java.lang.Long getSendTime() {
		return this.sendTime;
	}

	public UserMess setToId(java.lang.Integer toId) {
		this.toId = toId;
		return this;
	}

	public java.lang.Integer getToId() {
		return this.toId;
	}

	public UserMess setIsRead(java.lang.Integer isRead) {
		this.isRead = isRead;
		return this;
	}

	public java.lang.Integer getIsRead() {
		return this.isRead;
	}

	public UserMess setContent(java.lang.String content) {
		this.content = content;
		return this;
	}

	public java.lang.String getContent() {
		return this.content;
	}

}
