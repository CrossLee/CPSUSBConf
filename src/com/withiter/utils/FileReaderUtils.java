package com.withiter.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.channels.FileChannel;
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
		read.close();
		return list;
	}

	public static void writeToFile(List<String> lines, File f)
			throws IOException {
		System.out.println("writeToFile function started.");
		String encoding = code(f);
		FileOutputStream fos = new FileOutputStream(f);
		OutputStreamWriter writer = new OutputStreamWriter(fos, encoding);
		BufferedWriter bw = new BufferedWriter(writer);
		for (String s : lines) {
			bw.write(s);
			bw.newLine();
		}
		bw.flush();
		writer.flush();
		fos.flush();
		bw.close();
		writer.close();
		fos.close();
		System.out.println("writeToFile function finished.");
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

		inputStream.close();
		return code;
	}

	private static final int BUFFER_SIZE = 16 * 1024; // 16M

	// 删除指定path的文件
	public static boolean del(String path) {
		File file = new File(path);
		return file.delete();
	}

	// 文件拷贝
	public static void copy(File srcFile, File destDir) {
		System.out.println(srcFile.getAbsolutePath());
		System.out.println(destDir.getAbsolutePath());
		long copySizes = 0;
		
		try {  
            FileChannel fcin = new FileInputStream(srcFile).getChannel();  
            FileChannel fcout = new FileOutputStream(destDir).getChannel();  
            long size = fcin.size();  
            fcin.transferTo(0, fcin.size(), fcout);  
            fcin.close();  
            fcout.close();  
            copySizes = size;  
        } catch (FileNotFoundException e) {  
            e.printStackTrace();  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
		
//		InputStream in = null;
//		OutputStream out = null;
//		try {
//			in = new BufferedInputStream(new FileInputStream(src), BUFFER_SIZE);
//			out = new BufferedOutputStream(new FileOutputStream(dst),
//					BUFFER_SIZE);
//			byte[] buffer = new byte[BUFFER_SIZE];
//			while (in.read(buffer) > 0)
//				out.write(buffer);
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			try {
//				if (null != out) {
//					out.flush();
//					out.close();
//				}
//				if (null != in) {
//					in.close();
//				}
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//		}
	}

	// 获取文件后缀名
	public static String getSuffix(String fileName) {
		return fileName.substring(fileName.lastIndexOf("."));
	}

	public static void main(String[] args) throws IOException {
		// List<String> list = readFile(new File(
		// "C:\\Users\\eacfgjl\\Desktop\\需求说明\\initnew\\news.ini"));
		// for (String s : list) {
		// System.out.println(s);
		// }

		System.out.println(FileReaderUtils.code(new File(
				"C:\\Users\\server\\CPSUSBConf\\src\\files\\logfile.txt")));
		System.out.println(FileReaderUtils.code(new File(
				"C:\\Users\\server\\CPSUSBConf\\src\\files\\news.ini")));
		System.out.println(FileReaderUtils.code(new File(
				"C:\\Users\\server\\CPSUSBConf\\src\\files\\temperature.ini")));
		System.out.println(FileReaderUtils.code(new File(
				"C:\\Users\\server\\CPSUSBConf\\src\\files\\video.ini")));
		System.out.println(FileReaderUtils.code(new File(
				"I:\\ininew\\news.ini")));
	}

}
