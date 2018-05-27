package com.xiaoluo.utils;

import java.util.ArrayList;

public class StringUtils {
	 
	public static String captureName(String name) {
		//将首字母大写
		char[] cs=name.toCharArray();
		cs[0]-=32;
		return String.valueOf(cs);
	}
	
	
	/**
	 * 把一个字符串以一个字符分隔
	 * @param str  目标字符串
	 * @param marker 字符
	 * @return 结果字符串<br/>
	 * 例如：<br/>
	 * insertMarkerIntoStr("我是中国人","%")
	 * 返回：%我%是%中%国%人%
	 */
	public static String insertMarkerIntoStr(String str,String marker){
		StringBuilder sb = new StringBuilder(marker);
		for(int i = 0;i<str.length();i++){
			sb.append(str.charAt(i)+marker);
		}
		return sb.toString();
	}
	
	public static ArrayList<String> toArrayList(String[] str){
		ArrayList<String> arr = new ArrayList<String>();
		for(int i = 0;i<str.length;i++){  
		       arr.add(str[i]);
			}  
		return arr;
	}
	
	
	public static Integer getCharNumInString(char ch,String str){
		 	int length =0;  
		 	char[] c = str.toCharArray();
		 	for(int i = 0;i<c.length;i++){
		 		if(c[i]==ch){
		 			length++;
		 		}
		 	}
		 	
	        return length;
	}
	
	
}
