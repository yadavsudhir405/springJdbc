package com.sudhir.test.jcp;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public class GraceFulLoggerRunner {
	private static final GraceFulLogger graceFulLogger = new GraceFulLogger(new LinkedBlockingQueue<String>());

	public static void main(String[] args) throws InterruptedException {

		graceFulLogger.start();
		Runnable task = new Task();
		Thread t1 = new Thread(task, "Thread1");
		Thread t2 = new Thread(task, "Thread2");
		Thread t3 = new Thread(task, "Thread3");
		Thread t4 = new Thread(task, "Thread4");
		t1.start();
		t2.start();
		t3.start();
		t4.start();
		TimeUnit.SECONDS.sleep(10);
		graceFulLogger.stop();
	}

	private static class Task implements Runnable {
		@Override
		public void run() {
			while (true) {
				try {
					graceFulLogger.logMessage(Thread.currentThread().getName());
					TimeUnit.SECONDS.sleep(1);
				} catch (InterruptedException e) {
					System.out.println(Thread.currentThread().getName() + " is inturrepted");
					Thread.currentThread().interrupt();
					return;
				}
			}
		}
	}

}
