package com.xiaoluo.utils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

//import org.apache.commons.dbcp.BasicDataSource;
//
//	public class JDBCUtils {
//		
//	    // 创建数据库连接对象 ( 数据源 )  
//	    private static BasicDataSource dataSource=new BasicDataSource();  
//	    // 配置数据源  
//	    static   
//	    {  
//	        DataSourceConfig();  
//	    }  
//	    /** 
//	     * 设置 dataSource各属性值 
//	     */  
//	    private static void DataSourceConfig()  
//	    {     
//	        	dataSource.setDriverClassName("com.mysql.jdbc.Driver"); //数据库驱动  
//	            dataSource.setUrl(ResourceBundle.getBundle("dbConfig").getString("url"));  //连接url  
//	            dataSource.setUsername(ResourceBundle.getBundle("dbConfig").getString("name"));  //用户名  
//	            dataSource.setPassword(ResourceBundle.getBundle("dbConfig").getString("password"));  //密码  
//	            dataSource.setInitialSize(10); // 初始的连接数
//	            dataSource.setMaxIdle(80);  // 设置最大空闲连接  
//	            dataSource.setMinIdle(10);  // 设置最小空闲连接  
//	    }  
//	   
//
//		public static Connection getConnection() throws SQLException{
//			
//	            // 从连接池中获得连接对象  
//				return   dataSource.getConnection();  
//		}
//		
//		
//		
//		
//		
//		public static void close(Connection conn,Statement state ,ResultSet rs){
//			if(rs != null){
//				try {
//					rs.close();
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//			
//			close(conn,state);
//		}
//
//		public static void close(Connection conn,Statement state){
//			
//			if(state != null){
//				try {
//					state.close();
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//			close(conn);
//			
//		}
//
//		public static void close(Connection conn){
//			if(conn != null){
//				try {
//					conn.close();
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		} 
//
//		
//
//}
