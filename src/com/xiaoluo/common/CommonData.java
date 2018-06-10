package com.xiaoluo.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.xiaoluo.model.GroupsMess;
import com.xiaoluo.model.User;
import com.xiaoluo.utils.Ret;

public class CommonData {
	
	public static Ret loginUser = new Ret();
	public static List<String> loginUserID = new ArrayList<String>();
	public static Map<Integer,MyQueue<GroupsMess>> groupsMess = new HashMap<Integer,MyQueue<GroupsMess>>();
	public static Map<Integer,List<User>> userInGroup = new HashMap<Integer, List<User>>();
	
}
