package com.xiaoluo.dao;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.xiaoluo.model.Groups;

import com.xiaoluo.model.User;






public class GroupDao {
	
	public static void main(String[] args) {
		findGroup(2);
		
	}
	
	public static GroupDao me = new GroupDao();
	
	private GroupDao(){
		
	}
	
	/**
	 * 获取所有的Group对象
	 * @return
	 */

	public List<Groups> getAllGroup(){

		Configuration conf = new Configuration().configure();
		
	    SessionFactory sf = conf.buildSessionFactory();
	    Session session = sf.openSession();
        Groups groups =new Groups();
        Transaction transaction = session.beginTransaction();  
        

        //参数是一个字符串,是HQL的查询语句.注意此时的的UserU为大写,为对象的,而不是表的.
        Query query = session.createQuery("from Group");
        //从第一个开始查起.可以设置从第几个查起.
        query.setFirstResult(0);
        
        //使用List方法.
        List<Groups> groupList = query.list();
        //迭代器去迭代.
        for(Iterator iter=groupList.iterator();iter.hasNext();)
        {
            groups =(Groups)iter.next();
           System.out.println("id="+groups.getId() + "name="+groups.getGroupName());
        }
        
        
                
        transaction.commit();  
        session.close();
		sf.close(); 
		return groupList;
	}
	
public static Groups findGroup(int id){
		
		Configuration conf = new Configuration().configure();
	    SessionFactory sf = conf.buildSessionFactory();
	    Session session = sf.openSession();
	    Groups group=new Groups();
	    Transaction transaction = session.beginTransaction();
		

		  //参数是一个字符串,是HQL的查询语句.注意此时的的UserU为大写,为对象的,而不是表的.
        Query query = session.createQuery(" from Group where id = "+id+"");
        //从第一个开始查起.可以设置从第几个查起.
        query.setFirstResult(0);
        
        //使用List方法.
        List<Groups> groupList = query.list();
        //迭代器去迭代.
        for(Iterator iter=groupList.iterator();iter.hasNext();)
        {
            group =(Groups)iter.next();
           System.out.println("id="+group.getId() + "name="+group.getGroupName());
        }
        
        
                
        transaction.commit();  
        session.close();
		sf.close(); 
		
		
		return group;
		
	}


    public static void addGroup(Groups group){
	    Configuration conf = new Configuration().configure();
	    SessionFactory sf = conf.buildSessionFactory();
	    Session sess = sf.openSession();
	    Transaction tx = sess.beginTransaction();					
		sess.save(group);		
		tx.commit();
		sess.close();
		sf.close();

			
   } 
    
    public static  String deleteGroup(Groups group){
		
		
	    Configuration conf = new Configuration().configure();
	    SessionFactory sf = conf.buildSessionFactory();
	    Session session = sf.openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(group);
        transaction.commit(); 
        session.close();
		sf.close(); 
	
	
	    return "删除"+group.getGroupName()+"成功";
   }

    
    
    public static String updateGroup (Groups group){
		
		Configuration conf = new Configuration().configure();
		SessionFactory sf = conf.buildSessionFactory();
		Session session = sf.openSession();
		Transaction transaction = session.beginTransaction();
		
		session.saveOrUpdate(group);
		transaction.commit(); 
        session.close();
		sf.close(); 
	
		return group.getGroupName()+"已更新完成";
		
	
  
	}

	
}
