package com.withiter.test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.withiter.utils.FileReaderUtils;

public class UTF16Test {

	/**
	 * @param args
	 * @throws IOException 
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		String basePath = UTF16Test.class.getResource("/").getPath();
		File f = new File(basePath+"/files/news.ini");
		File f1 = new File(basePath+"/files/logfile.txt");
		String code = FileReaderUtils.getEncode(f);
		String code1 = FileReaderUtils.getEncode(f1);
		System.out.println(code);
		System.out.println(code1);
		
		List<String> lines = new ArrayList<String>();
		lines.add("你好");
		lines.add("你好1");
		lines.add("abc");
		File tmp = new File("C:/temp.ini");
		if(tmp.exists()){
			tmp.delete();
		}
//		tmp.createNewFile();
		FileReaderUtils.copy(f, tmp);
		
		Thread.sleep(10000);
		
		FileReaderUtils.writeToFile(lines, tmp);
	}

}
