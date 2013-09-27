package com.withiter.test;

import java.io.UnsupportedEncodingException;

import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.Platform;

/** Simple example of JNA interface mapping and usage. */
public class HelloWorld {
    // This is the standard, stable way of mapping, which supports extensive
    // customization and mapping of Java to native types.
	//BOOL  WriteProfileStr(const WCHAR* sSection,const WCHAR* sEntry,const WCHAR* sValue,const WCHAR* sIniPath)
    public interface CLibrary extends Library {
        CLibrary INSTANCE = (CLibrary)
            Native.loadLibrary((Platform.isWindows() ? "c:/cross/forunicode" : "c"),
                               CLibrary.class);

        void printf(String format, Object... args);
        public boolean WriteProfileStr(String sSection,String sEntry,String sValue,String sIniPath);
    }

    public static void main(String[] args) throws UnsupportedEncodingException {
//        CLibrary.INSTANCE.printf("Hello, World\n");
    	boolean f = CLibrary.INSTANCE.WriteProfileStr("news", "sum", "1", "C:/cross/test2.ini");
    	boolean f2 = CLibrary.INSTANCE.WriteProfileStr("news", "news0", "测试1", "C:/cross/test2.ini");
    	System.out.println(f);
    	System.out.println(f2);
    	
    	System.out.println(CLibrary.INSTANCE.WriteProfileStr("video", "video13", "阿斯蒂芬", "C:/cross/video2.ini"));
    	
        for (int i=0;i < args.length;i++) {
            CLibrary.INSTANCE.printf("Argument %d: %s\n", i, args[i]);
        }
    }
}
