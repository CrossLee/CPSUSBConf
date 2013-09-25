package com.withiter.test;

import com.sun.jna.Native;

public class Dllforunicode {

	static {
		System.load("c:\\dllforunicode.dll");
//        Native.register("c:\\dllforunicode.dll");
    }
	/**
	 * 
	 * @param sSection sSection为ini文件[ ]中的内容,
	 * @param sEntry sEntry 为关键字的内容
	 * @param sValue sValue 为关键字的值
	 * @param sIniPath sIniPath 为文件的路径
	 * @return true if success, otherwise false
	 */
	public native boolean WriteProfileStr(String sSection,String sEntry,String sValue,String sIniPath);

	/**
	 * @param args
	 */
	public static void main(String[] args) {
//		new Dllforunicode().WriteProfileStr("news", "", "", "C:/newstest.ini");
		
	}

}
