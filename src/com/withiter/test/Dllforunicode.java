package com.withiter.test;

import java.io.File;

import com.sun.jna.Native;
import com.sun.jna.Platform;

public class Dllforunicode {

	static {
		System.load("c:\\forunicode.dll");
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
	 static {
	 System.load("c:" + File.separator + "dllforunicode");
	 // Native.register("c:\\dllforunicode.dll");
	 }
	// /**
	// *
	// * @param sSection sSection为ini文件[ ]中的内容,
	// * @param sEntry sEntry 为关键字的内容
	// * @param sValue sValue 为关键字的值
	// * @param sIniPath sIniPath 为文件的路径
	// * @return true if success, otherwise false
	// */
	// public native boolean WriteProfileStr(String sSection,String
	// sEntry,String sValue,String sIniPath);


	public interface DllforunicodeDLLLib {
		public String path = "C:/dllforunicode.dll";
		DllforunicodeDLLLib INSTANCE = (DllforunicodeDLLLib) Native
				.loadLibrary((Platform.isWindows() ? path : "c"),
						DllforunicodeDLLLib.class);

		public boolean WriteProfileStr(String sSection, String sEntry,
				String sValue, String sIniPath);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		DllforunicodeDLLLib.INSTANCE.WriteProfileStr("news", "", "",
				 "C:/newstest.ini");
		// new Dllforunicode().WriteProfileStr("news", "", "",
		// "C:/newstest.ini");

	}

}
