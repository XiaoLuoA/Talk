package com.xiaoluo.index;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.xiaoluo.common.MyQueue;
import com.xiaoluo.dao.GroupsDao;
import com.xiaoluo.dao.UserDao;
import com.xiaoluo.dao.UserItemDao;
import com.xiaoluo.model.Groups;
import com.xiaoluo.model.GroupsMess;
import com.xiaoluo.model.User;
import com.xiaoluo.model.UserItem;
import com.xiaoluo.model.UserMess;
import com.xiaoluo.utils.MessComparator;


public class IndexService {
	
	public static IndexService me = new IndexService();
	
	public List<Groups> allGroup = GroupsDao.me.getAllGroups();

	public static Map<Integer, MyQueue<GroupsMess>> allGroupMess = new HashMap<Integer,MyQueue<GroupsMess>>();
	
	private IndexService(){
		
	}
	
	public void addAMessToGroup(Integer groupId,GroupsMess aGroupMess){
		MyQueue<GroupsMess> messQueue = allGroupMess.get(groupId);
		if(messQueue==null){
			messQueue = new MyQueue<GroupsMess>(1000);
		}
		allGroupMess.put(groupId, messQueue.add(aGroupMess));
	}
	
	public MyQueue<GroupsMess> getMessFromGroup(Integer groupId){
		return allGroupMess.get(groupId);
	}
	
	
	/**
	 * 某个用户的所有会话;
	 * @param user
	 * @return List<UserItem> 
	 */
	public List<UserItem> getAllItem(User user){
		
		List<UserItem> userItemList  = UserItemDao.me.getAllUserItem(user);
		
		List<UserMess> userMessList  = UserDao.me.getMess(user);
		
		Collections.sort(userMessList,new MessComparator());
		
		Map<Integer,List<UserMess>> allMess = new HashMap<Integer,List<UserMess>>();
		
		for(UserMess mess: userMessList){
			List<UserMess> userMess = allMess.get(mess.getFromId());
			if(userMess==null){
				userMess = new ArrayList<UserMess>();
			}
			userMess.add(mess);
			allMess.put(mess.getFromId(), userMess);
		}
		
		for(UserItem userItem:userItemList){
			userItem.setMessages(allMess.get(userItem.getTalkerId()));
		}
		
		return userItemList;
	}
	
	
}
