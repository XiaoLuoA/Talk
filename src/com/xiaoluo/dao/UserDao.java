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
import com.xiaoluo.utils.SessionFactoryUtils;


public class UserDao {
	SessionFactory sf = SessionFactoryUtils.sf;
	
	public static UserDao me= new UserDao();
	
	public static void main(String[] args) {
		System.out.println("success");
	}
	
	
	/**
	 * 添加用户
	 * @param user User对象
	 * @return User 添加的对象 
	 */
	public User addUser(User user){
		Session sess = sf.openSession();
		Transaction tx = sess.beginTransaction();
		sess.save(user);
		tx.commit();
		sess.close();
		sf.close();
		return user;
	}
	
	
	/**
	 * 根据用户名查询User对象
	 * @param name 用户名
	 * @return User对象
	 */
	public User findUser(String name){	
		System.out.println("findUser"+name);
		String hql="from User where name=?";
		Session sess = sf.openSession();
		Transaction tx = sess.beginTransaction();
		Query query=sess.createQuery(hql);
		query.setString(0,name);		
		List<User> userlist = query.list(); 
		tx.commit();
		sess.close();
		sf.close();
		
		if(userlist.size()>0){
			return userlist.get(0);
		}
		
		return null;	
		
	}
	
	
	/*修改用户信息*/
	public List<User> updateUser(String name,String password){		
		String hql="update User SET password = ? WHERE name = ? ";
		Session sess = sf.openSession();
		Transaction tx = sess.beginTransaction();
		Query query=sess.createQuery(hql);
		query.setString(0,name);		
		List<User> userlist = query.list();          		
		return userlist;		
	}

	
	
	//@drj
	public List<User> FindAllUserList(){
		 
	    Session session = sf.openSession();
        User user=new User();
        List<User> userList = null;
        Transaction transaction = session.beginTransaction();  
        

        //参数是一个字符串,是HQL的查询语句.注意此时的的UserU为大写,为对象的,而不是表的.
        
   try{
        Query query = session.createQuery("from User");
        //从第一个开始查起.可以设置从第几个查起.
        query.setFirstResult(0);
        
        //使用List方法.
        userList = query.list();
        //迭代器去迭代.
        for(Iterator iter=userList.iterator();iter.hasNext();)
        {
            user =(User)iter.next();
           System.out.println("id="+user.getId() + "name="+user.getName());
        }
        
       }
   catch(Exception e){
	   e.printStackTrace();
   }
                
        transaction.commit();  
        session.close();
		sf.close(); 
		return userList;
	
	
	
}   
	//@drj
	public  User findUser(int id){
		
	    Session session = sf.openSession();
	    User user=new User();
	    Transaction transaction = session.beginTransaction();
		

		  //参数是一个字符串,是HQL的查询语句.注意此时的的UserU为大写,为对象的,而不是表的.
	    
	 try{   
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
	 }
      catch(Exception e){  
    	  e.printStackTrace();
      }
        transaction.commit();  
        session.close();
		sf.close(); 
		
		
		return user;
		
	}
	
	
	
	//@drj
	public  String deleteUser(int id){

		    Session session = sf.openSession();
	        Transaction transaction = session.beginTransaction();
	        User user=findUser(id);
	    try{
	        session.delete(user);
	       }
	    catch(Exception e){
	    	e.printStackTrace();
	    	
	    }
	        transaction.commit(); 
	        session.close();
			sf.close(); 
		
		
		
		
		return "删除"+user.getName()+"成功";
	}
	
	
	//@drj
	public String updateUser (User user){
			Session session = sf.openSession();
			Transaction transaction = session.beginTransaction();
		try{
			session.saveOrUpdate(user);
		   }
		catch(Exception e){
			
		}
			transaction.commit(); 
	        session.close();
			sf.close(); 
		
			return user.getName()+"已更新完成";
			
		
	}
	
	
	//增加会话
	public void addUserItem(UserItem userItem){
	
		Session sess = sf.openSession();
		Transaction tx = sess.beginTransaction();
		sess.save(userItem);
		tx.commit();
		sess.close();
		sf.close();
	}
	
	
	//删除会话
	public void deleteUserItem(UserItem userItem){
		Session sess = sf.openSession();
		Transaction tx = sess.beginTransaction();
		sess.delete(userItem);
		tx.commit();
		sess.close();
		sf.close();
	}
	
	
	//获取用户的所有的会话列表
	public List<UserItem> getUserItem(User user){
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
	public Map<Integer,UserMess> getMess(User user){
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
