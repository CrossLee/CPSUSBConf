package com.withiter.utils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import com.withiter.dao.VideoDao;
import com.withiter.entity.USBConfig;
import com.withiter.entity.Video;
import com.withiter.frame.MainPanel;

public class CopyWorker implements Runnable {
	private CountDownLatch downLatch;
	private String name;
	private File src;
	private File des;

	public CopyWorker(CountDownLatch downLatch, String name, File src, File des) {
		this.downLatch = downLatch;
		this.name = name;
		this.src = src;
		this.des = des;
	}

	public void run() {
		this.doWork();
		long start = System.currentTimeMillis();
		System.out.println(this.name + " task finished!");
		this.downLatch.countDown();
		String name = des.getName();
		String ext = name.substring(name.lastIndexOf("."), name.length());
		String path = des.getAbsolutePath();
		String size = String.valueOf((des.length() / 1024 / 1024));
		String updateDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
				.format(new Date(des.lastModified()));
		Video v = new Video(name, ext, path, size, updateDate);
		VideoDao.instance().addVideo(v);
		MainPanel.instance().refresh();
		
		// update ini file
		String videoIni = USBConfig.drivePath + USBConfig.INIT_NEW_FOLDER + "\\video.ini";
		File f = new File(videoIni);
		f.deleteOnExit();
		try {
			f.createNewFile();
			VideoDao.instance().writeToIniFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		long end = System.currentTimeMillis();
		long period = end - start;
		System.out.println("Copying file cost time in millis : " + period);
		if(period < 3 * 1000){
			try {
				TimeUnit.SECONDS.sleep(2);
			} catch (InterruptedException ie) {
			}
		}
	}

	private void doWork() {
		System.out.println(this.name + "start to work!");
		FileReaderUtils.copy(this.src, this.des);
	}
}
