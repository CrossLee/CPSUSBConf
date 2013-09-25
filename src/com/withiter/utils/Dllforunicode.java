package com.withiter.utils;

import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.Platform;

public class Dllforunicode {

	public interface DllforunicodeDLLLib extends Library {
		String dllPath = "C:/dllforunicode.dll";
//		String dllPath = DllforunicodeDLLLib.class.getResource("/").getPath()+"dllforunicode.dll";
		DllforunicodeDLLLib INSTANCE = (DllforunicodeDLLLib) Native.loadLibrary((Platform.isWindows() ? dllPath : "c"), DllforunicodeDLLLib.class);
//		DllforunicodeDLLLib INSTANCE = (DllforunicodeDLLLib) System.load((Platform.isWindows() ? dllPath : "c"));
		
		/**
		 * 
		 * @param sSection sSection为ini文件[ ]中的内容,
		 * @param sEntry sEntry 为关键字的内容
		 * @param sValue sValue 为关键字的值
		 * @param sIniPath sIniPath 为文件的路径
		 * @return true if success, otherwise false
		 */
		public boolean WriteProfileStr(String sSection,String sEntry,String sValue,String sIniPath );
	}
	
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		System.load((Platform.isWindows() ? "C:/dllforunicode.dll" : "c"));
//		boolean writeProfileStr = DllforunicodeDLLLib.WriteProfileStr("news", "", "", "C:\newstest.ini");
	}

}
