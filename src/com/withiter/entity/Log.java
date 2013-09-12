package com.withiter.entity;

public class Log {

	public String ip;
	public String mac;
	public String date;
	public String operation;
	public String filename;
	public String result;
	
	public Log(String ip, String mac, String date, String operation,
			String filename, String result) {
		super();
		this.ip = ip;
		this.mac = mac;
		this.date = date;
		this.operation = operation;
		this.filename = filename;
		this.result = result;
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
