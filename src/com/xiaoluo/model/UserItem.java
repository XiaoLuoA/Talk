package com.xiaoluo.model;

import java.util.ArrayList;
import java.util.List;

public class UserItem {

	private java.lang.String userItemId;
	private java.lang.Integer userId;
	private java.lang.String userName;
	private java.lang.Integer talkerId;
	private java.lang.String talkerName;
	private java.lang.Integer newNum;
	private java.lang.Long lastTime;
	private java.lang.Integer isBlack;
	private java.lang.String talkerPic;
	private List<UserMess> messages = new ArrayList<UserMess>();
	public List<UserMess> getMessages() {
		return messages;
	}

	public void setMessages(List<UserMess> messages) {
		this.messages = messages;
	}

	public UserItem setUserItemId(java.lang.String userItemId) {
		this.userItemId = userItemId;
		return this;
	}

	public java.lang.String getUserItemId() {
		return this.userItemId;
	}

	public UserItem setUserId(java.lang.Integer userId) {
		this.userId = userId;
		return this;
	}

	public java.lang.Integer getUserId() {
		return this.userId;
	}

	public UserItem setUserName(java.lang.String userName) {
		this.userName = userName;
		return this;
	}

	public java.lang.String getUserName() {
		return this.userName;
	}

	public UserItem setTalkerId(java.lang.Integer talkerId) {
		this.talkerId = talkerId;
		return this;
	}

	public java.lang.Integer getTalkerId() {
		return this.talkerId;
	}

	public UserItem setTalkerName(java.lang.String talkerName) {
		this.talkerName = talkerName;
		return this;
	}

	public java.lang.String getTalkerName() {
		return this.talkerName;
	}

	public UserItem setNewNum(java.lang.Integer newNum) {
		this.newNum = newNum;
		return this;
	}

	public java.lang.Integer getNewNum() {
		return this.newNum;
	}

	public UserItem setLastTime(java.lang.Long lastTime) {
		this.lastTime = lastTime;
		return this;
	}

	public java.lang.Long getLastTime() {
		return this.lastTime;
	}

	public UserItem setIsBlack(java.lang.Integer isBlack) {
		this.isBlack = isBlack;
		return this;
	}

	public java.lang.Integer getIsBlack() {
		return this.isBlack;
	}

	public UserItem setTalkerPic(java.lang.String talkerPic) {
		this.talkerPic = talkerPic;
		return this;
	}

	public java.lang.String getTalkerPic() {
		return this.talkerPic;
	}

}
