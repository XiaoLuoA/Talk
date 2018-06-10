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

public class RoleMenuDao {
	SessionFactory sf = SessionFactoryUtils.sf;
	public static RoleMenuDao me= new RoleMenuDao();
	
	public static void main(String[] args) {
				

		
	}
	
	public List<RoleMenu> findAllRoleMenu(){
		
		Session session = sf.openSession();        
        List<RoleMenu> rolemenuList = null;
        Transaction transaction = session.beginTransaction();     
        Query query = session.createQuery("from RoleMenu");     
        rolemenuList = query.list();
        transaction.commit();  
        session.close();
		return rolemenuList;
	
		
	}
	
	public RoleMenu findRoleMenu(int id){
		
		String hql="from RoleMenu where id=? ";
		Session sess = sf.openSession();
		Transaction tx = sess.beginTransaction();
		Query query=sess.createQuery(hql);
		query.setInteger(0, id);		
		List<RoleMenu> rolemenulist = query.list(); 
		tx.commit();
		sess.close();
		if(rolemenulist.size()>0){
			return rolemenulist.get(0);
		}
		return null;
		
		
		
	}
	
	public void updateRoleMenu(RoleMenu rolemenu){
		Session sess = sf.openSession();
		Transaction tx = sess.beginTransaction();  
		sess.saveOrUpdate(rolemenu);
		tx.commit();
		sess.close();
		
	}
	public void deleteRoleMenu (int id){
		
		Session sess = sf.openSession();
		Transaction tx = sess.beginTransaction();      
        RoleMenu  rolemenu = me.findRoleMenu(id);    
        sess.delete(rolemenu);
		tx.commit();
		sess.close();
		
	}
	public void addRoleMenu (RoleMenu rolemenu){
	
		Session sess = sf.openSession();
		Transaction tx = sess.beginTransaction();         
        sess.save(rolemenu);
		tx.commit();
		sess.close();
		
	}
	
	public RoleMenu findRoleMenuByRoleIdByMenuId(int roleId,int menuId){	
		String hql="from RoleMenu where roleId=? and menuId = ?";
		Session sess = sf.openSession();
		Transaction tx = sess.beginTransaction();
		Query query=sess.createQuery(hql);
		query.setInteger(0,roleId);		
		query.setInteger(1, menuId);
		List<RoleMenu> rolemenulist = query.list(); 
		tx.commit();
		sess.close();
		if(rolemenulist.size()>0){
			return rolemenulist.get(0);
		}
		return null;	
	}
	
	public String findRoleMenuString(Role role){
		Session sess = sf.openSession();
		Transaction tx = sess.beginTransaction();
		String hql="from RoleMenu where roleId=? ";
		Query query=sess.createQuery(hql);
		query.setInteger(0, role.getId());
		List<RoleMenu> rolemenulist = query.list(); 
		String s = "";
		for(int i=0;i<rolemenulist.size();i++){
			if(i!=rolemenulist.size()-1){
			s=rolemenulist.get(i).getMenuId()+",";
			}
			else{
				s=s+rolemenulist.get(i).getMenuId();
			}
		}
		return s ;
		
	}

}
