package com.xiaoluo.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import com.xiaoluo.model.Groups;


public class GroupDao {
	
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
		Session sess = sf.openSession();
		Transaction tx = sess.beginTransaction();
		Query query = sess.createQuery("from groups");
		List<Groups> allGroup = query.list();
		tx.commit();
		sess.close();
		sf.close();
		return allGroup;
	}
	
}
