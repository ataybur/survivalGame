package com.ataybur.utils;

public class StringUtils {
	final public static String EMPTY = "";
	
	public static boolean isEmpty(String str){
		return str == null || str.isEmpty();
	}
	
	public static boolean isNotEmpty(String str){
		return !isEmpty(str);
	}
}
