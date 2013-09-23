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
		// TODO Auto-generated method stub
		String basePath = UTF16Test.class.getResource("/").getPath();
		File f = new File(basePath+"/files/news.ini");
		File f1 = new File(basePath+"/files/logfile.txt");
		String code = FileReaderUtils.getEncode(f);
		String code1 = FileReaderUtils.getEncode(f1);
		System.out.println(code);
		System.out.println(code1);
		
		List<String> lines = new ArrayList<String>();
		byte[] bbb = "你好".getBytes("UTF-16");
		byte[] bbbb = new byte[bbb.length - 2];
		for(int i=2;i<bbb.length; i++){
			bbbb[i-2] = bbb[i];
		}
		lines.add(new String(bbbb,Charset.forName("UTF-16")));
		lines.add(new String(bbbb,Charset.forName("UTF-16")));
//		lines.add("abc");
//		lines.add("abc");
		File tmp = new File("C:/temp.ini");
		if(tmp.exists()){
			tmp.delete();
		}
		tmp.createNewFile();
		FileReaderUtils.copy(f, tmp);
		
		Thread.sleep(10000);
		
		FileReaderUtils.writeToFile(lines, tmp);
		
//		String str = "我";   
//		System.out.println((str.getBytes(Charset.forName("UTF-16"))));   
//		System.out.println((str.getBytes(Charset.forName("UTF-16BE"))));   
//		System.out.println((str.getBytes(Charset.forName("UTF-16LE"))));   
//		System.out.println((str.getBytes(Charset.forName("UTF-8")))); 
		
//		File ff  = new File(basePath+"/files/news.ini");
//		FileInputStream in = new FileInputStream(ff);
//		// 指定读取文件时以UTF-8的格式读取
//		BufferedReader br = new BufferedReader(new InputStreamReader(in, "UTF-16"));
//		
//		String line = br.readLine();
//		while(line != null)
//		{
//			System.out.println(line);
//			byte[] allbytes = line.getBytes("UTF-16"); 
//			for (int i=0; i < allbytes.length; i++)
//			{
//				int tmpx = allbytes[i];
//				String hexString = Integer.toHexString(tmpx);
//				System.out.println(hexString);
//				// 1个byte变成16进制的，只需要2位就可以表示了，取后面两位，去掉前面的符号填充
//				hexString = hexString.substring(hexString.length() -2);
//				System.out.print(hexString.toUpperCase());
//				System.out.print(" ");
//			}
//			line = br.readLine();
//
//		}
	}

}
