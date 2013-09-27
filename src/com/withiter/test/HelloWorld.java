package com.withiter.test;

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
        public boolean WriteProfileStr(char[] sSection,char[] sEntry,char[] sValue,char[] sIniPath);
    }

    public static void main(String[] args) {
//        CLibrary.INSTANCE.printf("Hello, World\n");
    	boolean f = CLibrary.INSTANCE.WriteProfileStr("news".toCharArray(), "".toCharArray(), "".toCharArray(), "C:/cross/newstest.ini".toCharArray());
    	System.out.println(f);
        for (int i=0;i < args.length;i++) {
            CLibrary.INSTANCE.printf("Argument %d: %s\n", i, args[i]);
        }
    }
}
