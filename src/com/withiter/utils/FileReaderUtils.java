package com.withiter.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.nio.ByteBuffer;
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
		return list;
	}
	
	public static void writeToFile(List<String> lines, File f) throws IOException{
		FileWriter fw = new FileWriter(f);
		BufferedWriter bw = new BufferedWriter(fw);
		for(String s : lines){
			bw.append(s);
			bw.newLine();
		}
		bw.flush();
		bw.close();
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

	private static final int BUFFER_SIZE = 16 * 1024; // 16M

	// 删除指定path的文件
	public static boolean del(String path) {
		File file = new File(path);
		return file.delete();
	}

	// 文件拷贝
	public static void copy(File src, File dst) {
		System.out.println(src.getAbsolutePath());
		System.out.println(dst.getAbsolutePath());
		InputStream in = null;
		OutputStream out = null;
		try {
			in = new BufferedInputStream(new FileInputStream(src), BUFFER_SIZE);
			out = new BufferedOutputStream(new FileOutputStream(dst),
					BUFFER_SIZE);
			byte[] buffer = new byte[BUFFER_SIZE];
			while (in.read(buffer) > 0)
				out.write(buffer);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (null != in)
					in.close();
				if (null != out)
					out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	// file copy 2
	public static void ChannelCopy(File f1, File f2) throws Exception {
		int length = 2097152;
		FileInputStream in = new FileInputStream(f1);
		FileOutputStream out = new FileOutputStream(f2);
		FileChannel inC = in.getChannel();
		FileChannel outC = out.getChannel();
		ByteBuffer b = null;
		boolean flag = true;
		while (flag) {
			if (inC.position() == inC.size()) {
				inC.close();
				outC.close();
				flag = false;
			} else {
				if ((inC.size() - inC.position()) < length) {
					length = (int) (inC.size() - inC.position());
				} else
					length = 2097152;
				b = ByteBuffer.allocateDirect(length);
				inC.read(b);
				b.flip();
				outC.write(b);
				outC.force(false);
			}
		}
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
				"C:\\Users\\server\\Desktop\\CPSUSB\\需求说明\\NEWS.INI")));
		System.out.println(FileReaderUtils.code(new File(
				"C:\\Users\\server\\Desktop\\CPSUSB\\需求说明\\logfile.txt")));
		System.out.println(FileReaderUtils.code(new File(
				"C:\\Users\\server\\Desktop\\CPSUSB\\需求说明\\temperature.ini")));
	}

}
