package com.xiaoluo.index;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.xiaoluo.common.MyQueue;
import com.xiaoluo.dao.GroupsDao;
import com.xiaoluo.dao.UserDao;
import com.xiaoluo.model.Groups;
import com.xiaoluo.model.GroupsMess;
import com.xiaoluo.model.User;
import com.xiaoluo.model.UserMess;
import com.xiaoluo.utils.MessComparator;


public class IndexService {
	
	public static IndexService me = new IndexService();
	
	public static List<Groups> allGroup = GroupsDao.me.getAllGroups();

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
	 * 发给某个用户user的所有消息
	 * @param user
	 * @return Map<Integer, List<UserMess>> Integer为发送者id,
	 * List<UserMess>为id对应的消息列表
	 */
	public Map<Integer, List<UserMess>> getAllMsg(User user){
		List<UserMess> userMessList = UserDao.me.getMess(user);
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
		return allMess;
	}
	
	
}
