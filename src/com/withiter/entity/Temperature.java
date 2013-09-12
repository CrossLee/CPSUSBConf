package com.withiter.entity;

public class Temperature {

	public String content;
	
	public Temperature(String content) {
		super();
		this.content = content;
	}
	
	public static void main(String[] args){
		String s = "a=b";
		int spliterIndex = s.indexOf("=");
		System.out.println(spliterIndex);
		String index = s.substring(0, spliterIndex);
		String content = s.substring(spliterIndex + 1, s.length());
		System.out.println(index);
		System.out.println(content);
	}
}
