package com.xiaoluo.admin.user;

import java.util.List;

import com.xiaoluo.dao.UserDao;
import com.xiaoluo.model.User;

public class AdminUserService {
	
	  public static AdminUserService me = new AdminUserService();
	  public static List<User> allUser =  UserDao.me.FindAllUserList();
	  
	  
	  public  void  addUser(User user){
		  UserDao.me.addUser(user);
	  }
	  
	  public void  findUser(int id){
		  UserDao.me.findUser(id);
		  
	  }
	  public  void deleteUser(User user){
		  UserDao.me.deleteUser(user);
	  }
	  
	  public void updateUser(User user){
		  UserDao.me.updateUser(user);
	  }

}
