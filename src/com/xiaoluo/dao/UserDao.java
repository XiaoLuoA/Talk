package com.xiaoluo.dao;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.xiaoluo.model.BlackList;
import com.xiaoluo.model.User;
import com.xiaoluo.model.UserItem;
import com.xiaoluo.model.UserMess;


public class UserDao {
	
	public static void main(String[] args) {
		//addUser();
		System.out.println("success");
	}
	
	/*添加用户*/
	public static void addUser(User user){
		Configuration conf = new Configuration().configure();
		SessionFactory sf = conf.buildSessionFactory();
		Session sess = sf.openSession();
		Transaction tx = sess.beginTransaction();					
			sess.save(user);		
			tx.commit();
			sess.close();
			sf.close();
	      
	}
	/*查询用户*/
	public static boolean selectUser(User user){
		boolean a=false;
		String hql="select*from User where name=?";
		Configuration conf = new Configuration().configure();
		SessionFactory sf = conf.buildSessionFactory();
		Session sess = sf.openSession();
		Transaction tx = sess.beginTransaction();
		Query query=sess.createQuery(hql);
		query.setString(0,user.getName());
		List userlist = query.list();  
        if (userlist.size() > 0) {  
            a=true;  
        }  
		return a;		
	}
	
	
	//增加会话
	public static void addUserItem(UserItem userItem){
		Configuration conf = new Configuration().configure();
		SessionFactory sf = conf.buildSessionFactory();
		Session sess = sf.openSession();
		Transaction tx = sess.beginTransaction();
		sess.save(userItem);
		tx.commit();
		sess.close();
		sf.close();
	}
	
	
	//删除会话
	public static void deleteUserItem(UserItem userItem){
		Configuration conf = new Configuration().configure();
		SessionFactory sf = conf.buildSessionFactory();
		Session sess = sf.openSession();
		Transaction tx = sess.beginTransaction();
		sess.delete(userItem);
		tx.commit();
		sess.close();
		sf.close();
	}
	
	
	//获取用户的所有的会话列表
	public static List<UserItem> getUserItem(User user){
		Configuration conf = new Configuration().configure();
		SessionFactory sf = conf.buildSessionFactory();
		Session sess = sf.openSession();
		Transaction tx = sess.beginTransaction();
		Query query = sess.createQuery("from user_item where user_id = ? ");
		query.setInteger(0, user.getId());
		List<UserItem> userItems = query.list();
		tx.commit();
		sess.close();
		sf.close();
		return userItems;
	}
	
	
	//上线后获取发给某个用户的所有消息
	public static Map<Integer,UserMess> getMess(User user){
		Configuration conf = new Configuration().configure();
		SessionFactory sf = conf.buildSessionFactory();
		Session sess = sf.openSession();
		Transaction tx = sess.beginTransaction();
		Query query = sess.createQuery("from user_mess where send_id = ?");
		query.setInteger(0, user.getId());
		List<UserMess> userMessList = query.list();
		Map<Integer,UserMess> allMess = new HashMap<Integer,UserMess>();
		for(UserMess mess: userMessList){
			allMess.put(mess.getFromId(), mess);
		}
		tx.commit();
		sess.close();
		sf.close();
		return allMess;
	}
	
	public void saveMess(UserMess userMess){
		Configuration conf = new Configuration().configure();
		SessionFactory sf = conf.buildSessionFactory();
		Session sess = sf.openSession();
		Transaction tx = sess.beginTransaction();
		sess.save(userMess);
		tx.commit();
		sess.close();
		sf.close();
	}
	
	
	/**
	 * 获取黑名单
	 * @param user
	 * @return
	 */
	public static List<BlackList> getBlackList(User user){
		Configuration conf = new Configuration().configure();
		SessionFactory sf = conf.buildSessionFactory();
		Session sess = sf.openSession();
		Transaction tx = sess.beginTransaction();
		Query query = sess.createQuery("from black_list where user_id = ?");
		query.setInteger(0, user.getId());
		List<BlackList> blackList = query.list();
		tx.commit();
		sess.close();
		sf.close();
		return blackList;
	}
	
}
