package com.withiter.utils;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import com.withiter.dao.VideoDao;
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
		try {
			TimeUnit.SECONDS.sleep(10);
		} catch (InterruptedException ie) {
		}
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
	}

	private void doWork() {
		System.out.println(this.name + "start to work!");
		FileReaderUtils.copy(this.src, this.des);
	}
}
