package com.xiaoluo.admin.user;

import java.util.List;

import com.xiaoluo.dao.UserDao;
import com.xiaoluo.model.User;

public class AdminUserService {
	
	  public static AdminUserService me = new AdminUserService();
	//  public static List<User> allUser =  UserDao.me.FindAllUserList();
	  
	  public static void main(String[] args) {
	
	  }
	  
	  public  void  addUser(User user){
		  UserDao.me.addUser(user);
	  }
	  
	  public User  findUser(int id){
		 User user= UserDao.me.findUser(id);
		  return user;
	  }
	  public  void deleteUser(int id){
		  UserDao.me.deleteUser(id);
	  }
	  
	  public void updateUser(User user){
		  UserDao.me.updateUser(user);
	  }
	  public List<User> findAllUsers(){
	      List<User> allUser = UserDao.me.FindAllUserList();
		  return allUser;
	  }

}
