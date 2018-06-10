package com.xiaoluo.listener;

import java.io.IOException;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.hibernate.SessionFactory;

import com.xiaoluo.utils.SessionFactoryUtils;

import websocket.WSStarter;

@WebListener
public class StartListener implements ServletContextListener{

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		// TODO Auto-generated method stub
		try {
			WSStarter.stop();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		try {
			WSStarter.start();
			SessionFactoryUtils.load();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
