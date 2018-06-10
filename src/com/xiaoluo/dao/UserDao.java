package com.xiaoluo.dao;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.tio.utils.json.Json;

import com.xiaoluo.model.BlackList;
import com.xiaoluo.model.User;
import com.xiaoluo.model.UserItem;
import com.xiaoluo.model.UserMess;
import com.xiaoluo.utils.SessionFactoryUtils;


public class UserDao {
	
	SessionFactory sf = SessionFactoryUtils.sf;
	
	public static UserDao me= new UserDao();
	
	public static void main(String[] args) {
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
		return user;
	}
	
	
	/**
	 * 根据用户名查询User对象
	 * @param name 用户名
	 * @return User对象
	 */
	public User findUser(String name){	
		String hql="from User where name=? ";
		Session sess = sf.openSession();
		Transaction tx = sess.beginTransaction();
		Query query=sess.createQuery(hql);
		query.setString(0,name);		
		List<User> userlist = query.list(); 
		tx.commit();
		sess.close();
		if(userlist.size()>0){
			return userlist.get(0);
		}
		return null;	
	}
	
	/**
	 * 根据用户名和状态查询User对象
	 * @param name 用户名
	 * @return User对象
	 */
	public User findUser(String name,Integer status){	
		String hql="from User where name=? and status = ?";
		Session sess = sf.openSession();
		Transaction tx = sess.beginTransaction();
		Query query=sess.createQuery(hql);
		query.setString(0,name);		
		query.setInteger(1, status);
		List<User> userlist = query.list(); 
		tx.commit();
		sess.close();
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
	public List<User> SearchLikeUserList(String name){
		    Session session = sf.openSession();
		    User user=new User();
	        List<User> userList = null;
	        Transaction transaction = session.beginTransaction();  
	        

	      
	        
	   try{
	        Query query = session.createQuery("from User  where name like'%"+name+"%'");
	        
	        query.setFirstResult(0);
	        userList = query.list();
	  
	       
	        
	       }
	   catch(Exception e){
		   e.printStackTrace();
	   }
	                
	        transaction.commit();  
	        session.close();
			return userList;
		
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
        
       }
   catch(Exception e){
	   e.printStackTrace();
   }
                
        transaction.commit();  
        session.close();
		return userList;
	
	
	
}   
	
	
	
	
	
	//@drj
	public  User findUser(int id){
		 Configuration config = new Configuration().configure() ;        
		    SessionFactory sf = config.buildSessionFactory() ;
		    //
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
          
        }
	 }
      catch(Exception e){  
    	  e.printStackTrace();
      }
        transaction.commit();  
        session.close();
		 
		
		
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
			 
		
			return user.getName()+"已更新完成";
			
		
	}
	
	
	
	
	
	
	
	
	/**
	 * 获取黑名单
	 * @param user
	 * @return
	 */
	public List<BlackList> getBlackList(User user){
		Session sess = sf.openSession();
		Transaction tx = sess.beginTransaction();
		Query query = sess.createQuery("from black_list where user_id = ?");
		query.setInteger(0, user.getId());
		List<BlackList> blackList = query.list();
		tx.commit();
		sess.close();
		
		return blackList;
	}
	
	
	/**
	 * 获得删除我的用户的id
	 * @param id
	 * @return
	 */
	public Integer[] getDeleteMe(Integer id){
		ArrayList<Integer> arr = new ArrayList<Integer>(); 
		Session sess = sf.openSession();
		Transaction tx = sess.beginTransaction();
		Query query = sess.createSQLQuery("select talker_id from who_delete where user_id = ?");
		query.setInteger(0, id);
		Iterator iter = query.list().iterator();
		while(iter.hasNext()){
			arr.add((Integer)iter.next());
		}
		tx.commit();
		sess.close();
		return arr.toArray(new Integer[0]);
	}
	
}
