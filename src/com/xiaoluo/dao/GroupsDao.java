package com.xiaoluo.dao;
import java.util.Iterator;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import com.xiaoluo.model.Groups;
import com.xiaoluo.utils.SessionFactoryUtils;




public class GroupsDao {
	
	SessionFactory sf = SessionFactoryUtils.sf;
	public static void main(String[] args) {
		//findGroups(2);
		
	}
	
	public static GroupsDao me = new GroupsDao();
	
	private GroupsDao(){
		
	}
	
	/**
	 * 获取所有的Group对象
	 * @return
	 */

	public List<Groups> getAllGroups(){

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
		return groupList;
	}
	
public Groups findGroups(int id){
		
	    Session session = sf.openSession();
	    Groups group=new Groups();
	    Transaction transaction = session.beginTransaction();
		

		  //参数是一个字符串,是HQL的查询语句.注意此时的的UserU为大写,为对象的,而不是表的.
        SQLQuery query = session.createSQLQuery("select * from group where id = ?");
        //从第一个开始查起.可以设置从第几个查起.
        query.setInteger(0, id);
        
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
		
		
		return group;
		
	}


    public void addGroups(Groups group){
	    Session sess = sf.openSession();
	    Transaction tx = sess.beginTransaction();					
		sess.save(group);		
		tx.commit();
		sess.close();
   } 
    
    public String deleteGroups(int id){
		
		
	    Session session = sf.openSession();
        Transaction transaction = session.beginTransaction();
        Groups groups=findGroups(id);
        session.delete(groups);
        transaction.commit(); 
        session.close();
	
	
	    return "删除"+groups.getGroupName()+"成功";
   }

    
    
    public String updateGroups (Groups group){
		
		Session session = sf.openSession();
		Transaction transaction = session.beginTransaction();
		
		session.saveOrUpdate(group);
		transaction.commit(); 
        session.close();
	
		return group.getGroupName()+"已更新完成";
		
	
  
	}

	
}
