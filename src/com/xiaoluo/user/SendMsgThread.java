package com.xiaoluo.user;

import com.xiaoluo.model.User;
import com.xiaoluo.utils.MailUtils;

public class SendMsgThread implements Runnable{
	private User user;
	private String text;
	public SendMsgThread(String text,User user ) {
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
