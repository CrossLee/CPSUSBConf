package com.withiter.utils;

import java.util.concurrent.CountDownLatch;

public class LodingWorker implements Runnable {  
	  
    private CountDownLatch downLatch;  
    private BusyDialog bd;
      
    public LodingWorker(CountDownLatch downLatch, BusyDialog bd){  
        this.downLatch = downLatch;  
        this.bd = bd;
    }  
      
    public void run() {  
        System.out.println("Loading......");  
        try {  
            this.downLatch.await();  
        } catch (InterruptedException e) {
        }
//        try {
//			Thread.sleep(2000);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
        this.bd.frame.dispose();
        System.out.println("LodingWorker finished");  
    }  
  
}  