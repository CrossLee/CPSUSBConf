package com.withiter.utils;

public class OSUtils {

	public static String getOS(){
		String os = System.getProperty("os.name");
		return os;
	}
	
	public static boolean isWin(){
		return getOS().startsWith("Win");
	}
	public static boolean isMac(){
		return getOS().startsWith("Mac");
	}
}
