package com.xiaoluo.user;

import com.xiaoluo.dao.UserDao;
import com.xiaoluo.model.User;
import com.xiaoluo.utils.MailUtils;

public class UserService {
	public static UserService me = new UserService();
	
	private UserService(){}
	
	/**
	 * 用户初始化：状态为2(正常);角色为1(普通用户);举报数为0;
	 */
	public User regist(User user){
		user.setStatus("2").setCreateTime(System.currentTimeMillis()).setPic("")
		.setRoles("1").setTel(user.getTel()).setReportNum(0);
		UserDao.me.addUser(user);
		return user;
	}
	
	public User sendReg(User user){
		 int m=(int)(Math.random()*8999+1000); 	
		 String n="【Talk】您正在修改密码，验证码为："+m;
		 user.setRegNum(m);
		 
		 Thread t =new Thread(new SendMsgThread(n,user));
		 t.start();
		 
		 return user;
	}
	
	public void saveOrUpdate(User user){
		UserDao.me.updateUser(user);
	}
}
