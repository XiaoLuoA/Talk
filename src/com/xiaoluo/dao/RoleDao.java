package com.xiaoluo.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.xiaoluo.model.Role;
import com.xiaoluo.model.RoleMenu;
import com.xiaoluo.model.User;
import com.xiaoluo.utils.SessionFactoryUtils;

public class RoleDao {
	
	
   private SessionFactory sf = SessionFactoryUtils.sf;
	
	public static RoleDao me = new RoleDao();
	
	public static void main(String[] args) {
		
		
	/*	
	 * Configuration config = new Configuration().configure() ;        
	    SessionFactory sf = config.buildSessionFactory() ;  
	    
	    *
	    */  //测试工具
		
		
	}
	
public List<Role> findAllRole(){
		
		Session session = sf.openSession();        
        List<Role> roleList = null;
        Transaction transaction = session.beginTransaction();     
        Query query = session.createQuery("from Role");     
        roleList = query.list();
        transaction.commit();  
        session.close();
		return roleList;
	
		
	}
	
	
	public Role findRole(int id){
	
		String hql="from Role where id=? ";
		Session sess = sf.openSession();
		Transaction tx = sess.beginTransaction();
		Query query=sess.createQuery(hql);
		query.setInteger(0, id);		
		List<Role> rolelist = query.list(); 
		tx.commit();
		sess.close();
		if(rolelist.size()>0){
			return rolelist.get(0);
		}
		return null;
		
	}
	
	
	public void addRole(Role role){
		Session sess = sf.openSession();
		Transaction tx = sess.beginTransaction();
	    sess.save(role);    
        tx.commit();
		sess.close();
		
	}
	public void updateRole(Role role){			
		Session sess = sf.openSession();
		Transaction tx = sess.beginTransaction();
		sess.saveOrUpdate(role);
		tx.commit();
		sess.close();
		
		                         
		
	}
	
	
	public void deleteRole(int id){
		    
		Session sess = sf.openSession();
		Transaction tx = sess.beginTransaction();
		Role role=me.findRole(id);
		List<RoleMenu> rolemenuList=RoleMenuDao.me.findAllRoleMenu();	
			for(int i=0;i<rolemenuList.size();i++){
				if(rolemenuList.get(i).getRoleId()==role.getId()){
				RoleMenuDao.me.deleteRoleMenu(rolemenuList.get(i).getId());
				
			}
			
			
			
			sess.delete(role);
			System.out.println("user中无role对象引用，删除role成功");
		}
	    
        tx.commit();
		sess.close();
		
	}
	

}
