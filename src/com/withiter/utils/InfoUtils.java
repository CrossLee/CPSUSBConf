package com.withiter.utils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.swing.JOptionPane;

import com.withiter.dao.LogDao;
import com.withiter.dao.NewsDao;
import com.withiter.dao.TemperatureDao;
import com.withiter.dao.VideoDao;
import com.withiter.entity.Log;
import com.withiter.entity.News;
import com.withiter.entity.Temperature;
import com.withiter.entity.USBConfig;
import com.withiter.entity.Video;

public class InfoUtils {

	private static void checkUSB() {
		System.out.println("start to check USB's status。。。");

		// TODO update drive path here
//		String rootPath = "I:\\";
//		USBConfig.drivePath = "I:\\";
		String rootPath = USBConfig.drivePath;
		if (rootPath == null) {
			JOptionPane.showMessageDialog(null, "未检测到U盘，请在登陆前插入U盘。点击确定退出系统",
					"提示", JOptionPane.OK_OPTION);
			System.exit(0);
		}
	}

	public static void loadVideoInfo() {
		checkUSB();
		System.out.println("start to load videos' info from usb");
		String rootPath = USBConfig.drivePath;
		String videonewFolder = rootPath + USBConfig.VIDEO_NEW_FOLDER + "\\";

		System.out.println(videonewFolder);

		File videoFilesPath = new File(videonewFolder);
		File[] videos = videoFilesPath.listFiles();
		Video v = null;
		for (File f : videos) {
			System.out.println(f.getName());
			if(USBConfig.EXTS.contains(StringUtils.getSuffixWithoutDot(f.getName()))){
				String name = f.getName();
				String ext = name.substring(name.lastIndexOf("."), name.length());
				String path = f.getAbsolutePath();
				String size = String.valueOf((f.length() / 1024 / 1024));
				String updateDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
				.format(new Date(f.lastModified()));
				v = new Video(name, ext, path, size, updateDate);
				VideoDao.instance().addVideo(v);
			}
		}
	}

	public static void loadTemperatureInfo() throws IOException {
		checkUSB();
		System.out.println("start to load temperature info from usb");
		String rootPath = USBConfig.drivePath;
		String initnewFolder = rootPath + USBConfig.INIT_NEW_FOLDER + "\\";
		String temperatureiniFile = initnewFolder + "temperature.ini";

		File temperatureIni = new File(temperatureiniFile);
		if (!temperatureIni.exists()) {
			System.out.println(temperatureiniFile + " file does not exist!");
		} else {
			List<String> list = FileReaderUtils.readFile(temperatureIni);
			if (list != null) {
				Temperature temp = null;
				for (String s : list) {
					if (s.startsWith("strdata")) {
						int spliterIndex = s.indexOf("=");
						String content = s.substring(spliterIndex + 1,
								s.length());
						temp = new Temperature(content);
						TemperatureDao.instance().addTemperature(temp);
					}
				}
			}
		}

	}

	public static void loadNewsInfo() throws IOException {
		checkUSB();
		System.out.println("start to load news info from usb");
		String rootPath = USBConfig.drivePath;
		String initnewFolder = rootPath + USBConfig.INIT_NEW_FOLDER + "\\";
		String newsiniFile = initnewFolder + "news.ini";

		File newsIni = new File(newsiniFile);
		if (!newsIni.exists()) {
			System.out.println(newsiniFile + " file does not exist!");
		} else {
			List<String> list = FileReaderUtils.readFile(newsIni);
			if (list != null) {
				News news = null;
				for (String s : list) {
					if (s.startsWith("news")) {
						int spliterIndex = s.indexOf("=");
						String index = s.substring(0, spliterIndex);
						String content = s.substring(spliterIndex + 1,
								s.length());
						news = new News(index, content);
						NewsDao.instance().addNews(news);
					}
				}
			}
		}
	}

	public static void loadLogInfo() throws IOException {
		checkUSB();
		System.out.println("start to load log info from usb");
		String rootPath = USBConfig.drivePath;
		String logfile = rootPath + "logfile.txt";

		System.out.println(logfile);

		File logtext = new File(logfile);
		if (!logtext.exists()) {
			System.out.println(logtext + " file does not exist!");
		} else {
			List<String> list = FileReaderUtils.readFile(logtext);
			System.out.println("logs's size is: " + list.size());
			if (list != null) {
				Log log = null;
				for (int i = 0; i < list.size(); i += 3) {

					System.out.println("line: " + i + "," + list.get(i));
					System.out.println("line: " + (i + 1) + ","
							+ list.get(i + 1));
					System.out.println("line: " + (i + 2) + ","
							+ list.get(i + 2));

					String ip = list.get(i).split(":")[1];
					String mac = list.get(i + 1).split(":")[1];
					String date = "";
					String operation = "";
					String filename = "";
					String result = "";

					if (list.get(i + 2).contains("更新")) {
						String[] aStr = list.get(i + 2).split("更新");
						date = aStr[0];
						operation = "更新";
						if (aStr[1].contains("成功")) {
							filename = aStr[1].replace("成功", "");
							result = "成功";
						}
						if (aStr[1].contains("失败")) {
							filename = aStr[1].replace("失败", "");
							result = "失败";
						}
					}
					if (list.get(i + 2).contains("添加")) {
						String[] aStr = list.get(i + 2).split("添加");
						date = aStr[0];
						operation = "添加";
						if (aStr[1].contains("成功")) {
							filename = aStr[1].replace("成功", "");
							result = "成功";
						}
						if (aStr[1].contains("失败")) {
							filename = aStr[1].replace("失败", "");
							result = "失败";
						}
					}
					if (list.get(i + 2).contains("删除")) {
						String[] aStr = list.get(i + 2).split("删除");
						date = aStr[0];
						operation = "删除";
						if (aStr[1].contains("成功")) {
							filename = aStr[1].replace("成功", "");
							result = "成功";
						}
						if (aStr[1].contains("失败")) {
							filename = aStr[1].replace("失败", "");
							result = "失败";
						}
					}

					log = new Log(ip, mac, date, operation, filename, result);
					System.out.println(log.toString());
					LogDao.instance().addLog(log);
				}
			}
		}
	}
}
