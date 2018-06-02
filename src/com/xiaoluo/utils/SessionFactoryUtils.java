package com.xiaoluo.utils;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class SessionFactoryUtils {
	
	public static SessionFactory sf = null;
	public static void load(){
		Configuration conf = new Configuration().configure();
		sf = conf.buildSessionFactory();
	}
	
}
