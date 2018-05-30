package com.xiaoluo.dao;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
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
		/*String mes;
		mes=updateUser(6, "testsuccess", "11111", "1", "", "男", "", System.currentTimeMillis(), 10);
		System.out.println(mes);
		*/
		
		
		
	/*	user.setName("TestForUpdate").setCreateTime(System.currentTimeMillis())
		    .setPassword("123456").setReportNum(0).setRoles("").setSex("女")
		        .setPic("").setStatus("1");*/
		
		//String mes;
		//addUser(user);
		//System.out.println("添加成功");
		
		/*
		mes=deleteUser(user);
		System.out.println(mes);*/
		FindAllUserList();
		
		
		System.out.println("success");
	}
	
	
	//测试成功
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
	public static List<User> findUser(String name){		
		String hql="from User where name=?";
		Configuration conf = new Configuration().configure();
		SessionFactory sf = conf.buildSessionFactory();
		Session sess = sf.openSession();
		Transaction tx = sess.beginTransaction();
		Query query=sess.createQuery(hql);
		query.setString(0,name);		
		List<User> userlist = query.list();          		
		return userlist;		
	}
	/*修改用户信息*/
	public static List<User> updateUser(String name,String password){		
		String hql="update User SET password = ? WHERE name = ? ";
		Configuration conf = new Configuration().configure();
		SessionFactory sf = conf.buildSessionFactory();
		Session sess = sf.openSession();
		Transaction tx = sess.beginTransaction();
		Query query=sess.createQuery(hql);
		query.setString(0,name);		
		List<User> userlist = query.list();          		
		return userlist;		
	}

	
	
	//测试成功
	public static List<User> FindAllUserList(){
		 
        Configuration conf = new Configuration().configure();
	    SessionFactory sf = conf.buildSessionFactory();
	    Session session = sf.openSession();
        User user=new User();
        Transaction transaction = session.beginTransaction();  
        

        //参数是一个字符串,是HQL的查询语句.注意此时的的UserU为大写,为对象的,而不是表的.
        Query query = session.createQuery("from User");
        //从第一个开始查起.可以设置从第几个查起.
        query.setFirstResult(0);
        
        //使用List方法.
        List<User> userList = query.list();
        //迭代器去迭代.
        for(Iterator iter=userList.iterator();iter.hasNext();)
        {
            user =(User)iter.next();
           System.out.println("id="+user.getId() + "name="+user.getName());
        }
        
        
                
        transaction.commit();  
        session.close();
		sf.close(); 
		return userList;
	
	
	
}
	public static User findUser(int id){
		
		Configuration conf = new Configuration().configure();
	    SessionFactory sf = conf.buildSessionFactory();
	    Session session = sf.openSession();
	    User user=new User();
	    Transaction transaction = session.beginTransaction();
		

		  //参数是一个字符串,是HQL的查询语句.注意此时的的UserU为大写,为对象的,而不是表的.
        Query query = session.createQuery(" from User where id = "+id+"");
        //从第一个开始查起.可以设置从第几个查起.
        query.setFirstResult(0);
        
        //使用List方法.
        List<User> userList = query.list();
        //迭代器去迭代.
        for(Iterator iter=userList.iterator();iter.hasNext();)
        {
            user =(User)iter.next();
           System.out.println("id="+user.getId() + "name="+user.getName());
        }
        
        
                
        transaction.commit();  
        session.close();
		sf.close(); 
		
		
		return user;
		
	}
	
	
	
	
	public static  String deleteUser(User user){
		
		
		    Configuration conf = new Configuration().configure();
		    SessionFactory sf = conf.buildSessionFactory();
		    Session session = sf.openSession();
	        Transaction transaction = session.beginTransaction();
	        session.delete(user);
	        transaction.commit(); 
	        session.close();
			sf.close(); 
		
		
		
		
		return "删除"+user.getName()+"成功";
	}
	
	public static String updateUser (User user){
		
		  
			
			
			Configuration conf = new Configuration().configure();
			SessionFactory sf = conf.buildSessionFactory();
			Session session = sf.openSession();
			Transaction transaction = session.beginTransaction();
			
			session.saveOrUpdate(user);
			transaction.commit(); 
	        session.close();
			sf.close(); 
		
			return user.getName()+"已更新完成";
			
		
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
