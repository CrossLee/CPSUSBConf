package com.withiter.utils;

public class StringUtils {

	/**
	 * return the suffix without dot of given string
	 * @param s
	 * @return the suffix without dot of given string
	 */
	public static String getSuffixWithoutDot(String s){
		if(s == null || s.isEmpty()){
			return null;
		}
		
		int lastDotIndex = s.lastIndexOf(".");
		int length = s.length();
		String suffix = s.substring(lastDotIndex+1, length);
		return suffix;
	}
	
	/**
	 * return the suffix with dot of given string
	 * @param s
	 * @return the suffix with dot of given string
	 */
	public static String getSuffixWithDot(String s){
		if(s == null || s.isEmpty()){
			return null;
		}
		
		int lastDotIndex = s.lastIndexOf(".");
		int length = s.length();
		String suffix = s.substring(lastDotIndex, length);
		return suffix;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String s = getSuffixWithoutDot("asdfas.aa");
		System.out.println(s);
	}

}
