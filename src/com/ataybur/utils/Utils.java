package com.ataybur.utils;

import java.util.function.Supplier;

public class Utils {
	public static Integer convertIntToString(String str){
		Integer result;
		try {
			result = Integer.valueOf(str);
		} catch (Exception e) {
			result = 0;
		}
		return result;
	}
	
	public static <T, E extends T> E getter(Supplier<T> getter, Class<E> clazz) throws InstantiationException, IllegalAccessException{
		T t = getter.get();
		if(t == null){
			t = clazz.newInstance();
		}
		return (E) t;
	}
}
