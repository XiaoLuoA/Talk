package com.xiaoluo.index;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.xiaoluo.common.CommonData;
import com.xiaoluo.common.MyQueue;
import com.xiaoluo.dao.GroupsDao;
import com.xiaoluo.dao.UserDao;
import com.xiaoluo.dao.UserItemDao;
import com.xiaoluo.dao.UserMessDao;
import com.xiaoluo.model.Groups;
import com.xiaoluo.model.GroupsMess;
import com.xiaoluo.model.User;
import com.xiaoluo.model.UserItem;
import com.xiaoluo.model.UserMess;
import com.xiaoluo.utils.MessComparator;


public class IndexService {
	
	public static IndexService me = new IndexService();
	
	
	
	public List<Groups> allGroup = GroupsDao.me.getAllGroups();

	public static Map<Integer, MyQueue<GroupsMess>> allGroupMess = CommonData.groupsMess;
	public static Map<Integer,ArrayList<User>> userInGroup = new HashMap<Integer, ArrayList<User>>(); 
	
	private IndexService(){
		
	}
	
	public void addAMessToGroup(Integer groupId,GroupsMess aGroupMess){
		MyQueue<GroupsMess> messQueue = allGroupMess.get(groupId);
		if(messQueue==null){
			messQueue = new MyQueue<GroupsMess>(50);
		}
		allGroupMess.put(groupId, messQueue.add(aGroupMess));
	}
	
	public void addAUserToGroup(Integer groupId,User user){
		ArrayList<User> userList = userInGroup.get(groupId);
		if(userList==null){
			userList = new ArrayList<User>();
		}
		userList.add(user);
		userInGroup.put(groupId, userList);
	}
	
	public void removeAUserFromGroup(Integer groupId,User user){
		ArrayList<User> users = userInGroup.get(groupId);
		users.remove(user);
	}
	
	public Integer getUserSizeFromGroup(Integer groupId){
		return userInGroup.get(groupId)==null ? 0:userInGroup.get(groupId).size();
	}
	
	public List<User> getUsersFromGroup(Integer groupId){
		return userInGroup.get(groupId);
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
		
		List<UserMess> userMessList  = UserMessDao.me.getMess(user);
		
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
