package com.xiaoluo.admin.group;

import java.util.List;


import com.xiaoluo.dao.GroupsDao;
import com.xiaoluo.model.Groups;

public class AdminGroupsService {
	
	 public static AdminGroupsService me = new AdminGroupsService();
	  public static List<Groups> allGroups =  GroupsDao.me.getAllGroups();
	  
	  public static void main(String[] args) {
		  System.out.println(allGroups.toString());
	  }
	  
	  public  void  addGroups(Groups Groups){
		  GroupsDao.me.addGroups(Groups);
	  }
	  
	  public void  findGroups(int id){
		  GroupsDao.me.findGroups(id);
		  
	  }
	  public  void deleteGroups(Groups Groups){
		  GroupsDao.me.deleteGroups(Groups);
	  }
	  
	  public void updateGroups(Groups Groups){
		  GroupsDao.me.updateGroups(Groups);
	  }

}
