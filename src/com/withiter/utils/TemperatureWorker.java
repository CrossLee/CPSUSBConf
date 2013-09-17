package com.withiter.utils;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

import com.withiter.dao.TemperatureDao;
import com.withiter.frame.MainPanel;

public class TemperatureWorker implements Runnable {
	private CountDownLatch downLatch;
	private String name;

	public TemperatureWorker(CountDownLatch downLatch, String name) {
		this.downLatch = downLatch;
		this.name = name;
	}

	public void run() {
		this.doWork();
	}

	private void doWork() {
		System.out.println("TemperatureWorker start to work!");
		long start = System.currentTimeMillis();

		try {
			TemperatureDao.instance().writeToIniFile();
			long end = System.currentTimeMillis();
			long period = end - start;
			System.out.println("TemperatureWorker cost time in millis : " + period);
			if (period < 1000) {
				try {
					Thread.sleep(1000);
					System.out.println("Sleeping 1 seconds");
				} catch (InterruptedException ie) {
					ie.printStackTrace();
				}
			}
			System.out.println("Sleep 1 seconds");
		} catch (IOException e) {
			e.printStackTrace();
		}
		MainPanel.instance().refresh();
		this.downLatch.countDown();
		System.out.println("TemperatureWorker task finished!");
	}
	
	public String toString(){
		return this.name;
	}
}
