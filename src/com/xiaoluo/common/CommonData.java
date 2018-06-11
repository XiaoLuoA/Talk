package com.xiaoluo.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.xiaoluo.model.GroupsMess;
import com.xiaoluo.model.User;
import com.xiaoluo.utils.Ret;

public class CommonData {
	/**
	 * 登录用户
	 */
	public static Ret loginUser = new Ret();
	
	/**
	 * 登录用户id
	 */
	public static List<String> loginUserID = new ArrayList<String>();
	
	/**
	 * 群组消息
	 */
	public static Map<Integer,MyQueue<GroupsMess>> groupsMess = new HashMap<Integer,MyQueue<GroupsMess>>();
	
	/**
	 * 群组中的用户
	 */
	public static Map<Integer,List<User>> userInGroup = new HashMap<Integer, List<User>>();
	
}
