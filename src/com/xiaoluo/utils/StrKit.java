package com.xiaoluo.utils;

import java.util.ArrayList;

public class StrKit {
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
	
	
	/**
	 * 首字母变小写
	 */
	public static String firstCharToLowerCase(String str) {
		char firstChar = str.charAt(0);
		if (firstChar >= 'A' && firstChar <= 'Z') {
			char[] arr = str.toCharArray();
			arr[0] += ('a' - 'A');
			return new String(arr);
		}
		return str;
	}
	
	/**
	 * 首字母变大写
	 */
	public static String firstCharToUpperCase(String str) {
		char firstChar = str.charAt(0);
		if (firstChar >= 'a' && firstChar <= 'z') {
			char[] arr = str.toCharArray();
			arr[0] -= ('a' - 'A');
			return new String(arr);
		}
		return str;
	}
	
	/**
	 * 字符串为 null 或者内部字符全部为 ' ' '\t' '\n' '\r' 这四类字符时返回 true
	 */
	public static boolean isBlank(String str) {
		if (str == null) {
			return true;
		}
		int len = str.length();
		if (len == 0) {
			return true;
		}
		for (int i = 0; i < len; i++) {
			switch (str.charAt(i)) {
			case ' ':
			case '\t':
			case '\n':
			case '\r':
			// case '\b':
			// case '\f':
				break;
			default:
				return false;
			}
		}
		return true;
	}
	
	public static boolean notBlank(String str) {
		return !isBlank(str);
	}
	
	public static boolean notBlank(String... strings) {
		if (strings == null) {
			return false;
		}
		for (String str : strings) {
			if (isBlank(str)) {
				return false;
			}
		}
		return true;
	}
	
	public static boolean notNull(Object... paras) {
		if (paras == null) {
			return false;
		}
		for (Object obj : paras) {
			if (obj == null) {
				return false;
			}
		}
		return true;
	}
	
	public static String toCamelCase(String stringWithUnderline) {
		if (stringWithUnderline.indexOf('_') == -1) {
			return stringWithUnderline;
		}
		
		stringWithUnderline = stringWithUnderline.toLowerCase();
		char[] fromArray = stringWithUnderline.toCharArray();
		char[] toArray = new char[fromArray.length];
		int j = 0;
		for (int i=0; i<fromArray.length; i++) {
			if (fromArray[i] == '_') {
				// 当前字符为下划线时，将指针后移一位，将紧随下划线后面一个字符转成大写并存放
				i++;
				if (i < fromArray.length) {
					toArray[j++] = Character.toUpperCase(fromArray[i]);
				}
			}
			else {
				toArray[j++] = fromArray[i];
			}
		}
		return new String(toArray, 0, j);
	}
	
	public static String join(String[] stringArray) {
		StringBuilder sb = new StringBuilder();
		for (String s : stringArray) {
			sb.append(s);
		}
		return sb.toString();
	}
	
	public static String join(String[] stringArray, String separator) {
		StringBuilder sb = new StringBuilder();
		for (int i=0; i<stringArray.length; i++) {
			if (i > 0) {
				sb.append(separator);
			}
			sb.append(stringArray[i]);
		}
		return sb.toString();
	}
	
	
	
	public static String getRandomUUID() {
		return java.util.UUID.randomUUID().toString().replace("-", "");
	}
}




