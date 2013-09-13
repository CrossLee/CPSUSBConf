package com.withiter.utils;

import java.io.File;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class CopyWorker implements Runnable{
	private CountDownLatch downLatch;  
    private String name;  
    private File src;
    private File des;
      
    public CopyWorker(CountDownLatch downLatch, String name, File src, File des){  
        this.downLatch = downLatch;  
        this.name = name;
        this.src = src;
        this.des = des;
    }  
      
    public void run() {  
        this.doWork();  
        try{  
            TimeUnit.SECONDS.sleep(10);  
        }catch(InterruptedException ie){  
        }  
        System.out.println(this.name + " task finished!");  
        this.downLatch.countDown();  
          
    }  
      
    private void doWork(){
        System.out.println(this.name + "start to work!");
        FileReaderUtils.copy(this.src, this.des);
    }  
}
