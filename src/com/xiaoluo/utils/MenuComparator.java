package com.xiaoluo.utils;

import java.util.Comparator;

import com.xiaoluo.model.Menu;


/**
 * Menu排序器
 * @author xiaoluo
 *
 */
public class MenuComparator implements Comparator<Menu>{

	@Override
	public int compare(Menu o1, Menu o2) {
		if(o1.getOrderNum() == null  || o2.getOrderNum() == null
				|| o1.getOrderNum() < o2.getOrderNum() ) {
			return -1;
		}
		return 0;
	}

}
