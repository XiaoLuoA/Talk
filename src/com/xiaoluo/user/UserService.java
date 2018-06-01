package com.xiaoluo.user;

import com.xiaoluo.dao.UserDao;
import com.xiaoluo.model.User;

public class UserService {
	public static UserService me = new UserService();
	
	private UserService(){}
	
	public User regist(User user){
		UserDao.me.addUser(user);
		return user;
	}
}
