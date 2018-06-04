package com.xiaoluo.utils;

import java.util.Comparator;

import com.xiaoluo.model.UserMess;

public class MessComparator implements Comparator<UserMess>{

	@Override
	public int compare(UserMess o1, UserMess o2) {
		if(o1.getSendTime() < o2.getSendTime() ) {
			return -1;
		}
		return 0;
	}

}
