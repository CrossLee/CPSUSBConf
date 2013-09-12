package com.withiter.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class FileReaderUtils {

	public static List<String> readFile(File f) throws IOException {
		if (f == null || !f.exists()) {
			return null;
		}
		List<String> list = new ArrayList<String>();
		String encoding = "UTF-8";
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

	public static void main(String[] args) throws IOException {
		List<String> list = readFile(new File(
				"C:\\Users\\eacfgjl\\Desktop\\需求说明\\initnew\\news.ini"));
		for (String s : list) {
			System.out.println(s);
		}
	}

}
