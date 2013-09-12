package com.withiter.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class FileReaderUtils {

	public static List<String> readFile(File f) throws IOException {
		if (f == null || !f.exists()) {
			return null;
		}
		List<String> list = new ArrayList<String>();
		String encoding = code(f);
		InputStreamReader read = new InputStreamReader(new FileInputStream(f),
				encoding);
		BufferedReader br = new BufferedReader(read);
		String line = null;
		while ((line = br.readLine()) != null) {
			list.add(line);
		}
		br.close();
		return list;
	}

	public static String code(File f) throws IOException {
		InputStream inputStream = new FileInputStream(f);
		byte[] head = new byte[3];
		inputStream.read(head);
		String code = "gb2312";
		if (head[0] == -17 && head[1] == -69 && head[2] == -65)
			code = "UTF-8";
		if (head[0] == -1 && head[1] == -2)
			code = "UTF-16";
		if (head[0] == -2 && head[1] == -1)
			code = "Unicode";

		return code;
	}

	public static void main(String[] args) throws IOException {
//		List<String> list = readFile(new File(
//				"C:\\Users\\eacfgjl\\Desktop\\需求说明\\initnew\\news.ini"));
//		for (String s : list) {
//			System.out.println(s);
//		}
		
		System.out.println(FileReaderUtils.code(
				new File("C:\\Users\\server\\Desktop\\CPSUSB\\需求说明\\NEWS.INI")));
		System.out.println(FileReaderUtils.code(
				new File("C:\\Users\\server\\Desktop\\CPSUSB\\需求说明\\logfile.txt")));
		System.out.println(FileReaderUtils.code(
				new File("C:\\Users\\server\\Desktop\\CPSUSB\\需求说明\\temperature.ini")));
	}

}
