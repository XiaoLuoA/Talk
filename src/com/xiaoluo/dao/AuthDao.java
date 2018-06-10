package com.xiaoluo.dao;

import org.hibernate.SessionFactory;

import com.xiaoluo.model.User;
import com.xiaoluo.utils.SessionFactoryUtils;

public class AuthDao {
	
	SessionFactory sf = SessionFactoryUtils.sf;
	public static AuthDao me = new AuthDao();
	
	public static void main(String[] args) {
			
	}
	
	public void  listAllUserAuthority(){
	   
	}
	
	

}
