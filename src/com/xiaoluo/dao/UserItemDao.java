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
	
	//增加会话
		public void addUserItem(UserItem userItem){
			Session sess = sf.openSession();
			Transaction tx = sess.beginTransaction();
			sess.saveOrUpdate(userItem);
			tx.commit();
			sess.close();
		}
		
		
		//删除会话
		public void deleteUserItem(UserItem userItem){
			Session sess = sf.openSession();
			Transaction tx = sess.beginTransaction();
			sess.delete(userItem);
			tx.commit();
			sess.close();
			
		}
		
		public void deleteUserItem(String itemId){
			Session sess = sf.openSession();
			Transaction tx = sess.beginTransaction();
			Query query = sess.createSQLQuery("delete from user_item where userItemId = ?");
			query.setString(0, itemId);
			query.executeUpdate();
			tx.commit();
			sess.close();
		}
		
		

		
		
		public UserItem getUserItem(Integer user, Integer toUser) {
			Session sess = sf.openSession();
			Transaction tx = sess.beginTransaction();
			Query query = sess.createQuery("from UserItem where user_id = ? and talker_id = ?");
			query.setInteger(0, user);
			query.setInteger(1, toUser);
			List<UserItem> userItemList = query.list();
			tx.commit();
			sess.close();
			if(userItemList.size()>0){
				return userItemList.get(0);
			}
			return null;
		}

		
		
		
	
	
}
