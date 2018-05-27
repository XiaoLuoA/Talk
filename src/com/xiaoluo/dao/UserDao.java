package com.xiaoluo.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.xiaoluo.model.User;


public class UserDao {
	public static void main(String[] args) {
		addUser();
		System.out.println("success");
	}
	public static void addUser(){
		
		Configuration conf = new Configuration().configure();
		
		SessionFactory sf = conf.buildSessionFactory();
		
		Session sess = sf.openSession();
		Transaction tx = sess.beginTransaction();
		User user = new User();
		user.setName("xiaoluo").setCreateTime(System.currentTimeMillis())
		    .setPassword("123456").setReportNum(0).setRoles("").setSex("男")
		        .setPic("").setStatus("1");
		sess.save(user);
		tx.commit();
		sess.close();
		sf.close();
	}
}
