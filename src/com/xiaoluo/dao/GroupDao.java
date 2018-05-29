package com.xiaoluo.dao;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import com.xiaoluo.model.Group;
import com.xiaoluo.model.User;




public class GroupDao {
	
	public static void main(String[] args) {
		getAllGroup();
		
	}
	
	/**
	 * 获取所有的Group对象
	 * @return
	 */
	public static List<Group> getAllGroup(){
		Configuration conf = new Configuration().configure();
	    SessionFactory sf = conf.buildSessionFactory();
	    Session session = sf.openSession();
        Group group =new Group();
        Transaction transaction = session.beginTransaction();  
        

        //参数是一个字符串,是HQL的查询语句.注意此时的的UserU为大写,为对象的,而不是表的.
        Query query = session.createQuery("from Group");
        //从第一个开始查起.可以设置从第几个查起.
        query.setFirstResult(0);
        
        //使用List方法.
        List<Group> groupList = query.list();
        //迭代器去迭代.
        for(Iterator iter=groupList.iterator();iter.hasNext();)
        {
            group =(Group)iter.next();
           System.out.println("id="+group.getId() + "name="+group.getGroupName());
        }
        
        
                
        transaction.commit();  
        session.close();
		sf.close(); 
		return groupList;
	}
	
	
}
