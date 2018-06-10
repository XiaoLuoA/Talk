package com.xiaoluo.dao;
import java.util.Iterator;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import com.xiaoluo.model.Groups;
import com.xiaoluo.model.Menu;
import com.xiaoluo.utils.SessionFactoryUtils;




public class GroupsDao {
	
	SessionFactory sf = SessionFactoryUtils.sf;
	public static void main(String[] args) {
		
		
		
		
	}
	
	public static GroupsDao me = new GroupsDao();
	
	private GroupsDao(){
		
	}
	
	
	//@drj
		public List<Groups> SearchLikeGroupsList(String name){
			   
			    Session session = sf.openSession();
		        Groups groups =new Groups();
		        Transaction transaction = session.beginTransaction();  	           
		        Query query = session.createQuery("from Groups  where groupName like'%"+name+"%'");   
		        query.setFirstResult(0);
		        List<Groups> groupList = query.list();             
		        transaction.commit();  
		        
		        session.close();
		   
				return groupList;
			
		}
		
		/**
		 * 获取所有的Group对象
		 * @return
		 */
	public List<Groups> getAllGroups(){

	    Session session = sf.openSession();
        Groups groups =new Groups();
        Transaction transaction = session.beginTransaction();  
        Query query = session.createQuery("from Groups");
        query.setFirstResult(0);        
        List<Groups> groupList = query.list();                
        transaction.commit();  
        session.close();
		return groupList;
	}
	
public Groups findGroups(int id){
		
		String hql="from Groups where id=? ";
		Session sess = sf.openSession();
		Transaction tx = sess.beginTransaction();
		Query query=sess.createQuery(hql);
		query.setInteger(0, id);		
		List<Groups> groupslist = query.list(); 
		tx.commit();
		sess.close();
		if(groupslist.size()>0){
			return groupslist.get(0);
		}
		return null;	
	}
		

		

        

     
		
	


    public String addGroups(Groups group){
	    Session sess = sf.openSession();
	    Transaction tx = sess.beginTransaction();					
		sess.save(group);		
		tx.commit();
		sess.close();
		return group.getGroupName()+"增加成功";
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
