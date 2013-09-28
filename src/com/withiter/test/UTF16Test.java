package com.withiter.test;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
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
		String basePath = UTF16Test.class.getResource("/").getPath();
		File f = new File(basePath+"/files/news.ini");
		File f1 = new File(basePath+"/files/logfile.txt");
		String code = FileReaderUtils.getEncode(f);
		String code1 = FileReaderUtils.getEncode(f1);
		System.out.println(code);
		System.out.println(code1);
		
		List<String> lines = new ArrayList<String>();
		lines.add("[news]");
		lines.add("sum=2");
		lines.add("news0=asdfas");
		lines.add("news1=阿斯顿发生地方");
		File tmp = new File("C:/temp.ini");
		if(tmp.exists()){
			tmp.delete();
		}
		tmp.createNewFile();
		FileReaderUtils.copy(f, tmp);
		FileReaderUtils.writeToFile(lines, tmp);
		
		System.out.println(FileReaderUtils.readFile(tmp));
		
		System.out.println(FileReaderUtils.readFile(f1));
		
		List<String> list = FileReaderUtils.readFile(f1);
		System.out.println("logs's size is: " + list.size());
		for(int i=0; i< list.size(); i++){
			System.out.println(list.get(i));
		}
	}

}
