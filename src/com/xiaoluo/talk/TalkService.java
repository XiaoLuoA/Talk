package com.xiaoluo.talk;

import com.xiaoluo.dao.UserItemDao;
import com.xiaoluo.dao.UserMessDao;
import com.xiaoluo.model.UserItem;
import com.xiaoluo.model.UserMess;

public class TalkService {
	
	
	
	public static TalkService me = new TalkService();
	
	public void addMsgToUser(UserMess userMess,UserItem userItem){
		UserItemDao.me.addUserItem(userItem);
		UserMessDao.me.saveMess(userMess);
	}
	
	public void getGroupMess(){
		//公共数据区 群组消息
	}
	public void getGroupUser(){
		//公共数据区 群组成员
	}
}
