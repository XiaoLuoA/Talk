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
	public static Ret loginGroup = new Ret().set("users", new ArrayList<User>());
	public static List<User> usersInGroup = new ArrayList<User>();
	public static Map<String,MyQueue<GroupsMess>> groupsMess = new HashMap<String,MyQueue<GroupsMess>>();
}
