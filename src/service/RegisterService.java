package service;

import com.xiaoluo.dao.UserDao;
import com.xiaoluo.model.User;

public class RegisterService {
	public static boolean register(User user){
		try {
			UserDao.addUser(user);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
		
				
	}

}
