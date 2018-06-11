package com.xiaoluo.dao;

import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import com.xiaoluo.model.Menu;
import com.xiaoluo.model.User;
import com.xiaoluo.utils.SessionFactoryUtils;


public class MenuDao {
	
	private SessionFactory sf = SessionFactoryUtils.sf;
	
	public static MenuDao me = new MenuDao();
	
	private MenuDao(){
		
	}
	public static void main(String[] args) {

		System.out.println(me.findMenuById(1));

	}
	
	//@drj
		public List<Menu> SearchLikeMenuList(String name){
			    Session session = sf.openSession();
		        List<Menu> menuList = null;
		        Transaction transaction = session.beginTransaction();  
		        
		   try{
		        Query query = session.createQuery("from Menu  where menuName like'%"+name+"%'");		
		        menuList = query.list();
		         		        
		       }
		   catch(Exception e){
			   e.printStackTrace();
		   }
		                
		        transaction.commit();  
		        session.close();
				return menuList;
			
		}
	
	//@drj
	public void addMenu(Menu menu){
		Session sess = sf.openSession();
		Transaction tx = sess.beginTransaction();
	    sess.save(menu);
        tx.commit();
		sess.close();
		
		
	}
	
	//@drj
	public void deleteMenu(int id){
		Session sess = sf.openSession();
		Transaction tx = sess.beginTransaction();
		Menu menu=me.findMenuById(id);
	    sess.delete(menu);
        tx.commit();
		sess.close();
	}
	//@drj
	public void updateMenu(Menu menu){
		Session sess = sf.openSession();
		Transaction tx = sess.beginTransaction();
	    sess.saveOrUpdate(menu);
        tx.commit();
		sess.close();
	}
	
	//@drj
	public Menu findMenuById(int id){	
		String hql="from Menu where id=? ";
		Session sess = sf.openSession();
		Transaction tx = sess.beginTransaction();
		Query query=sess.createQuery(hql);
		query.setInteger(0, id);		
		List<Menu> menulist = query.list(); 
		tx.commit();
		sess.close();
		if(menulist.size()>0){
			return menulist.get(0);
		}
		return null;	
	}
	
	
	
	/**
	 * 从数据库获取所有菜单，并没有任何处理
	 * @return List<Menu>
	 */
	public List<Menu> findAll() {
		Session sess = sf.openSession();
		Transaction tx = sess.beginTransaction();
		Query query = sess.createQuery("from Menu");
		List<Menu> allMenu = query.list();
		tx.commit();
		sess.close();
		return allMenu;
	}
	
	
	/**
	 * 返回一级菜单对应的所有二级菜单
	 * @param menus 一级菜单List<Menu>
	 * @return List<Menu> 对应的二级菜单
	 */
	public List<Menu> findSubMenu(List<Menu> menus){
		Session sess = sf.openSession();
		Transaction tx = sess.beginTransaction();
		String ids = "";
		for(int i = 0;i<menus.size();i++){
			ids+=menus.get(i).getId();
			if(i<menus.size()-1)
				ids+=",";
		}
		
			List<Menu> allSubMenu = null;
			Query query = sess.createSQLQuery(
					"select *"
					+ "from  menu "
					+ "where parent_id in("+ids+")").addEntity(Menu.class);
			allSubMenu = query.list();
			tx.commit();
			sess.close();
			return allSubMenu;
	}
	
	
	/**
	 * 返回user对应的所有的一级菜单
	 * @param user 
	 * @return 一级菜单 List<Menu>
	 */
	public List<Menu> findFirstMenu(User user) {
		
		Session sess = sf.openSession();
		Transaction tx = sess.beginTransaction();
		String roles = user.getRoles();
		List<Menu> allMenu = null;
		if(roles!=null||!roles.equals("")){
			Query query = sess.createSQLQuery(
					"select distinct b.* "
					+ "from role_menu a join menu b on a.menu_id=b.id "
					+ "where a.role_id in("+roles+")").addEntity(Menu.class);
			allMenu = query.list();
		}
		
		tx.commit();
		sess.close();
		return allMenu;
	}
	
	
	
	
}
