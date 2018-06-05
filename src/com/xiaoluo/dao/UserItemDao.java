package com.xiaoluo.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.tio.utils.json.Json;

import com.xiaoluo.model.User;
import com.xiaoluo.model.UserItem;
import com.xiaoluo.utils.SessionFactoryUtils;

public class UserItemDao {
	
	private SessionFactory sf = SessionFactoryUtils.sf;
	
	public static UserItemDao me = new UserItemDao();
	
	private UserItemDao(){
		
	}
	
	public List<UserItem> getAllUserItem(User user){
		Session sess = sf.openSession();
		Transaction tx = sess.beginTransaction();
		Query query = sess.createQuery("from UserItem where user_id = ?");
		query.setInteger(0, user.getId());
		List<UserItem> userItemList = query.list();
		tx.commit();
		sess.close();
		return userItemList;
	}
	
	
}
