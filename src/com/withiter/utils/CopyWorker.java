package com.withiter.utils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.CountDownLatch;

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
	}

	private void doWork() {
		System.out.println("CopyWorker start to work!");
		FileReaderUtils.copy(this.src, this.des);
		long start = System.currentTimeMillis();
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
		if(f.exists()){
			f.delete();
		}
		try {
			f.createNewFile();
			VideoDao.instance().writeToIniFile();
			long end = System.currentTimeMillis();
			long period = end - start;
			System.out.println("Copying file cost time in millis : " + period);
			if(period < 2000){
				try {
//					TimeUnit.SECONDS.sleep(2);
					Thread.sleep(2000);
					System.out.println("Sleeping 2 seconds");
				} catch (InterruptedException ie) {
					ie.printStackTrace();
				}
			}
			System.out.println("Sleep 2 seconds");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.downLatch.countDown();
		System.out.println("CopyWorker task finished!");
	}
}
