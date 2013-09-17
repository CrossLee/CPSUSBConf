package com.withiter.utils;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.CountDownLatch;

import com.withiter.dao.NewsDao;
import com.withiter.entity.USBConfig;
import com.withiter.frame.MainPanel;

public class NewsWorker implements Runnable {
	private CountDownLatch downLatch;
	private String name;

	public NewsWorker(CountDownLatch downLatch, String name) {
		this.downLatch = downLatch;
		this.name = name;
	}

	public void run() {
		this.doWork();
	}

	private void doWork() {
		System.out.println("NewsWorker start to work!");
		long start = System.currentTimeMillis();
		
		// update ini file
//		String videoIni = USBConfig.drivePath + USBConfig.INIT_NEW_FOLDER + "\\news.ini";
//		File f = new File(videoIni);
//		f.delete();
		try {
//			f.createNewFile();
			NewsDao.instance().writeToIniFile();
			long end = System.currentTimeMillis();
			long period = end - start;
			System.out.println("NewsWorker cost time in millis : " + period);
			if(period < 2000){
				try {
					Thread.sleep(2000);
					System.out.println("Sleeping 2 seconds");
				} catch (InterruptedException ie) {
					ie.printStackTrace();
				}
			}
			System.out.println("Sleep 2 seconds");
		} catch (IOException e) {
			e.printStackTrace();
		}
		MainPanel.instance().refresh();
		this.downLatch.countDown();
		System.out.println("NewsWorker task finished!");
	}
}
