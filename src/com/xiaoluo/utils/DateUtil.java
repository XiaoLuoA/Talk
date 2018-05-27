package com.xiaoluo.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil 
{
	public static String toOutDay(String Date)
	{
		return toDay(Long.parseLong(Date)+432000000);
	}
	
	public static String toDay(String Date)
	{
		return toDay(Long.parseLong(Date));
	}
	
	public static String toDay(long time)
	{
		String res;
		Date date = new Date(time);
		SimpleDateFormat format = new SimpleDateFormat("yyyy 年  MM 月 dd 日"); 
		res = format.format(date);
		return res;
	}
}
