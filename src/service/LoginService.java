package service;

import java.util.List;

import com.xiaoluo.dao.UserDao;
import com.xiaoluo.model.User;

public class LoginService {
	public static List<User> findUser(String name){
		List<User> userlist=UserDao.findUser(name);	
		return userlist;		
	}
	
	

}
