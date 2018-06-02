package com.xiaoluo.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.xiaoluo.model.Menu;
import com.xiaoluo.model.User;
import com.xiaoluo.utils.SessionFactoryUtils;


public class MenuDao {
	SessionFactory sf = SessionFactoryUtils.sf;
	
	public static MenuDao me = new MenuDao();
	
	private MenuDao(){
		
	}
	public List<Menu> findAll() {
		
		Session sess = sf.openSession();
		Transaction tx = sess.beginTransaction();
		Query query = sess.createQuery("from menu");
		List<Menu> allMenu = query.list();
		tx.commit();
		sess.close();
		sf.close();
		return allMenu;
	}
	
	

	public List<Menu> findByUser(User user) {
		// TODO Auto-generated method stub
		
		Session sess = sf.openSession();
		Transaction tx = sess.beginTransaction();
		String roles = user.getRoles();
		List<Menu> allMenu = null;
		if(roles!=null||!roles.equals("")){
			Query query = sess.createQuery(
					"select distinct b.* "
					+ "from role_menu a join menu b on a.menu_id=b.id "
					+ "where a.role_id in(?)");
			query.setString(0, roles);
			allMenu = query.list();
		}
		
		tx.commit();
		sess.close();
		sf.close();
		return allMenu;
	}
	
	
	
}
