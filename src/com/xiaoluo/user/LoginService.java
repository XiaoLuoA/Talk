package com.xiaoluo.user;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import com.xiaoluo.dao.MenuDao;
import com.xiaoluo.dao.UserDao;
import com.xiaoluo.model.Menu;
import com.xiaoluo.model.User;
import com.xiaoluo.utils.MenuComparator;



public class LoginService {
	public static LoginService me = new LoginService();
	
	private LoginService(){
		
	}
	
	/**
	 * 根据用户名查询User对象
	 * @param name 用户名
	 * @return User对象
	 */
	public User findUser(String name){
		User user=UserDao.me.findUser(name);	
		return user;		
	}
	
}
