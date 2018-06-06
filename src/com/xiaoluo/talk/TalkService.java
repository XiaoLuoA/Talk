package com.xiaoluo.talk;

import com.xiaoluo.dao.UserItemDao;
import com.xiaoluo.dao.UserMessDao;
import com.xiaoluo.model.UserItem;
import com.xiaoluo.model.UserMess;

public class TalkService {
	
	
	
	public static TalkService me = new TalkService();
	
	public void saveMsgAndUserItem(UserMess userMess,UserItem userItem,UserItem talkerItem){
		UserItemDao.me.addUserItem(userItem);
		UserItemDao.me.addUserItem(talkerItem);
		UserMessDao.me.saveMess(userMess);
	}
	
	public UserItem getUserItem(Integer user,Integer toUser){
		return UserItemDao.me.getUserItem(user,toUser);
	}
	
	public void getGroupMess(){
		//公共数据区 群组消息
	}
	public void getGroupUser(){
		//公共数据区 群组成员
	}

	public void saveMsg(UserMess userMess) {
		UserMessDao.me.saveMess(userMess);
	}
}
