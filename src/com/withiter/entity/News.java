package com.withiter.entity;

public class News {

	public String index;
	public String content;
	
	public News(String index, String content) {
		super();
		this.index = index;
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
