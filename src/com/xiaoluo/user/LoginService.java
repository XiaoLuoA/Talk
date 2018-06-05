package com.xiaoluo.user;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import com.xiaoluo.dao.MenuDao;
import com.xiaoluo.dao.UserDao;
import com.xiaoluo.model.Menu;
import com.xiaoluo.model.User;
import com.xiaoluo.model.UserMess;
import com.xiaoluo.utils.MenuComparator;
import com.xiaoluo.utils.MessComparator;



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
	
	/**
	 * 根据用户名和用户状态查询User对象
	 * @param name 用户名
	 * @return User对象
	 */
	public User findUser(String name,Integer status){
		User user=UserDao.me.findUser(name);	
		return user;		
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
