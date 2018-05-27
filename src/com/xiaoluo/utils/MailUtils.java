package com.xiaoluo.utils;
import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import java.security.GeneralSecurityException;
import java.util.Properties;

/**
 * 与发送邮件有关的工具
 */
public class MailUtils {
	
    private static final String HOST = "smtp.qq.com";//邮件服务器
    private static final String SENDER = "1019558287@qq.com";//发送者邮箱
    private static final String SENDER_PASSWORD = "vjdefhtrfmwfbdac";//发送者密码
    private static String[] RECEIVERS = {   
    	
    };
    
    
    
    /**
     * 向指定的一人或多人发送邮件
     * @param text 邮件内容
     * @param receiveEmail 接受者的邮箱，可以传入一人或者多人
     * @return 是否发送成功
     */
   public static boolean send(String text ,String... receiveEmail){
	   RECEIVERS = receiveEmail;
	   return send(text);
   }
    
    
    /**
     * 发送邮件
     * @param text 邮件内容
     */
    private static boolean send(String text) {
    	//接收者邮件地址获取
    	InternetAddress[] addrs = new InternetAddress[RECEIVERS.length]; 
    	for(int i = 0;i<RECEIVERS.length;i++){
				try {
					addrs[i] = new InternetAddress(RECEIVERS[i]);
				} catch (AddressException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
    	}
    	
        // 获取系统属性
        Properties properties = System.getProperties();

        // 设置邮件服务器
        properties.setProperty("mail.smtp.host", HOST);

        //设置校验
        properties.put("mail.smtp.auth", "true");
        
       // MailSSLSocketFactory sf;
//		try {
//			//sf = new MailSSLSocketFactory();
//	       // sf.setTrustAllHosts(true);
//	       // properties.put("mail.smtp.ssl.enable", "true");
//	     //   properties.put("mail.smtp.ssl.socketFactory", sf);
//		} catch (GeneralSecurityException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
        
        // 获取默认session对象
        Session session = Session.getDefaultInstance(properties,new Authenticator(){
            public PasswordAuthentication getPasswordAuthentication()
            {
                return new PasswordAuthentication(SENDER, SENDER_PASSWORD); //发件人邮件用户名、密码
            }
        });

        try{
            // 创建默认的 MimeMessage 对象
            MimeMessage message = new MimeMessage(session);

            //设置发送者
            message.setFrom(new InternetAddress(SENDER));

            //设置接收者
            
           message.addRecipients(Message.RecipientType.TO, addrs);

            // 设置邮件标题
           message.setSubject("From 1019558287 的邮件");

            // 设置消息
            message.setText(text);
            
            // 发送消息
            System.out.println("Start send...");
            long start = System.currentTimeMillis();
            Transport.send(message);
            long end = System.currentTimeMillis();
            System.out.println("Sent message successfully...."+(end - start));
        }catch (AddressException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}catch (MessagingException mex) {
            mex.printStackTrace();
            return false;
        }
		return true;
    }
    
}