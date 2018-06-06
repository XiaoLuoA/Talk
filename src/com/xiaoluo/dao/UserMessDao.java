package com.xiaoluo.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.tio.utils.json.Json;

import com.xiaoluo.model.User;
import com.xiaoluo.model.UserMess;
import com.xiaoluo.utils.SessionFactoryUtils;

public class UserMessDao {
	
	private SessionFactory sf = SessionFactoryUtils.sf;
	
	public static UserMessDao me = new UserMessDao();
	
	private UserMessDao(){
		
	}

	/**
	 * 发给某个用户的所有消息
	 * @param user
	 * @return List<UserMess> 
	 */
	public List<UserMess> getMess(User user){
		
		Session sess = sf.openSession();
		Transaction tx = sess.beginTransaction();
		Query query = sess.createQuery("from UserMess where to_id = ?");
		query.setInteger(0, user.getId());
		
		List<UserMess> userMessList = query.list();
		try{
			System.out.println(userMessList.size());
			System.out.println(Json.toJson(userMessList));
		}catch(Exception e){
			e.printStackTrace();
		}
		
		tx.commit();
		sess.close();
		return userMessList;
	}
	
	public void saveMess(UserMess userMess){
		Session sess = sf.openSession();
		Transaction tx = sess.beginTransaction();
		sess.save(userMess);
		tx.commit();
		sess.close();
	}
}
