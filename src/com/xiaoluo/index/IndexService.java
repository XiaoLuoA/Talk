package com.xiaoluo.index;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.xiaoluo.common.MyQueue;
import com.xiaoluo.dao.GroupsDao;
import com.xiaoluo.model.Groups;
import com.xiaoluo.model.GroupsMess;


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
	
}
