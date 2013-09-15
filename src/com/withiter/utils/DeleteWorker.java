package com.withiter.utils;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CountDownLatch;

import com.withiter.dao.VideoDao;

public class DeleteWorker implements Runnable {
	private CountDownLatch downLatch;
	private String name;
	private List<String> files;

	public DeleteWorker(CountDownLatch downLatch, String name,
			List<String> files) {
		super();
		this.downLatch = downLatch;
		this.name = name;
		this.files = files;
	}

	public void run() {
		this.doWork();
	}

	private void doWork() {
		System.out.println("DeleteWorker start to work!");
		long start = System.currentTimeMillis();
		for (String file : files) {
			FileReaderUtils.del(file);
		}
		long end = System.currentTimeMillis();
		long period = end - start;
		System.out.println("Deleting files cost time in millis : " + period);
		if (period < 2000) {
			try {
				Thread.sleep(2000);
				System.out.println("Sleeping 2 seconds");
			} catch (InterruptedException ie) {
				ie.printStackTrace();
			}
		}
		try {
			VideoDao.instance().writeToIniFile();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		System.out.println("Sleep 2 seconds");
		this.downLatch.countDown();
		System.out.println("DeleteWorker task finished!");
	}
}
