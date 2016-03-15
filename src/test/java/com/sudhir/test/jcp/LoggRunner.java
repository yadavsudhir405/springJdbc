package com.sudhir.test.jcp;

import java.util.concurrent.TimeUnit;

public class LoggRunner {
	private final static LogWritter lOGWRITTER=new LogWritter();
	public static void main(String[] args) throws InterruptedException {
		Runner task=new Runner();
		Thread t1=new Thread(task,"Thread1");
		Thread t2=new Thread(task,"Thread2");
		Thread t3=new Thread(task,"Thread3");
		lOGWRITTER.start();
		t1.start();
		t2.start();
		t3.start();
		TimeUnit.SECONDS.sleep(6);
		lOGWRITTER.stop();
	}
	private static class Runner implements Runnable{
		public void run(){
			while(true){
				try {
					TimeUnit.SECONDS.sleep(1);
					lOGWRITTER.logMessage(Thread.currentThread().getName()+" logged Message");
				} catch (InterruptedException e) {
					Thread.currentThread().interrupt();
					break;
					
				}
			}
		}
	}

}
