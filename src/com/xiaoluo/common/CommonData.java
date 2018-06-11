package com.xiaoluo.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.xiaoluo.model.GroupsMess;
import com.xiaoluo.model.User;
import com.xiaoluo.utils.EhCacheUtil;
import com.xiaoluo.utils.Ret;

public class CommonData {
	/**
	 * 登录用户
	 */
	public static Ret loginUser = new Ret();
//	public static Ret loginUser = (Ret) 
//			EhCacheUtil.getInstance().get("ehcache001", "loginUser");
	
	/**
	 * 登录用户id
	 */
	public static List<String> loginUserID = new ArrayList<String>();
//	@SuppressWarnings("unchecked")
//	public static List<String> loginUserID =
//			(List<String>) EhCacheUtil.getInstance()
//			.get("ehcache001", "loginUserID");
	
	/**
	 * 群组消息
	 */
	public static Map<Integer,MyQueue<GroupsMess>> groupsMess = new HashMap<Integer,MyQueue<GroupsMess>>();
//	@SuppressWarnings("unchecked")
//	public static Map<Integer,MyQueue<GroupsMess>> groupsMess =  
//			(Map<Integer, MyQueue<GroupsMess>>) EhCacheUtil.getInstance()
//			.get("ehcache001", "groupsMess");
	/**
	 * 群组中的用户
	 */
	public static Map<Integer,List<User>> userInGroup = new HashMap<Integer, List<User>>();
//	@SuppressWarnings("unchecked")
//	public static Map<Integer,List<User>> userInGroup =  
//			(Map<Integer, List<User>>) EhCacheUtil.getInstance()
//			.get("ehcache001", "userInGroup");
	
}
