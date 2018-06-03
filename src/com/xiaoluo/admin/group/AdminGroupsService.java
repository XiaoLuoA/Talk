package com.xiaoluo.admin.group;

import java.util.List;


import com.xiaoluo.dao.GroupsDao;
import com.xiaoluo.dao.UserDao;
import com.xiaoluo.model.Groups;
import com.xiaoluo.model.User;

public class AdminGroupsService {
	
	 public static AdminGroupsService me = new AdminGroupsService();
	 
	  
	  public static void main(String[] args) {
		 
	  }
	  
	  public  void  addGroups(Groups Groups){
		  GroupsDao.me.addGroups(Groups);
	  }
	  
	  public Groups  findGroups(int id){
		Groups groups=  GroupsDao.me.findGroups(id);
		  return groups;
		  
	  }
	  public  void deleteGroups(int id){
		  GroupsDao.me.deleteGroups(id);
	  }
	  
	  public void updateGroups(Groups Groups){
		  GroupsDao.me.updateGroups(Groups);
	  }
	  public List<Groups> findAllGroups(){
		  List<Groups> groups=GroupsDao.me.getAllGroups();
		  return groups;
	  }
	  public List<Groups> searchLikeGroupsList(String name){
		  List<Groups> searchLikeGroupsList = GroupsDao.me.SearchLikeGroupsList(name);
		  return searchLikeGroupsList;
	  }

}
