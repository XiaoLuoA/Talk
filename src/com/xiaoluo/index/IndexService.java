package com.xiaoluo.index;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.xiaoluo.common.MyQueue;
import com.xiaoluo.dao.GroupDao;
import com.xiaoluo.model.Group;
import com.xiaoluo.model.GroupMess;

public class IndexService {
	
	public static IndexService me = new IndexService();
	
	public static List<Group> allGroup = GroupDao.me.getAllGroup();

	public static Map<Integer, MyQueue<GroupMess>> allGroupMess = new HashMap<Integer,MyQueue<GroupMess>>();
	
	private IndexService(){
		
	}
	
	public void addAMessToGroup(Integer groupId,GroupMess aGroupMess){
		MyQueue<GroupMess> messQueue = allGroupMess.get(groupId);
		if(messQueue==null){
			messQueue = new MyQueue<GroupMess>(1000);
		}
		allGroupMess.put(groupId, messQueue.add(aGroupMess));
	}
	
	public MyQueue<GroupMess> getMessFromGroup(Integer groupId){
		return allGroupMess.get(groupId);
	}
	
}
