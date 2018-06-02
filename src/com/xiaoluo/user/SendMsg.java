package com.xiaoluo.user;

import com.opensymphony.xwork2.ActionContext;
import com.xiaoluo.model.User;
import com.xiaoluo.utils.MailUtils;

public class SendMsg implements Runnable{
	private User user;
	private String text;
	public SendMsg(String text,User user ) {
		// TODO Auto-generated constructor stub
		this.user = user;
		this.text = text;
	}
	@Override
	public void run() {
		 try{
			 MailUtils.send(text, user.getEmail());
		 }catch(Exception e){
			 System.out.println(e);
		 }
	}
	
}
