package com.sudhir.test.jcp;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
/**
 * 
 * @author sudhir
 *
 */
public class LogWritter {
	private final BlockingQueue<String> queue;
	private final LoggerThread logger;
	private boolean shutDownRequested;
	public LogWritter() {
		this.queue = new LinkedBlockingQueue<>(20);
		this.logger = new LoggerThread();
	}

	public void start() {
		logger.start();
	}

	public void logMessage(String message) throws InterruptedException {
		if(!shutDownRequested){
			queue.put(message);
		}else{
			throw new IllegalStateException("Logger is shutdown");
		}
		
	}
	public void stop(){
		shutDownRequested=true;
		logger.interrupt();
	}

	private class LoggerThread extends Thread {

		public void run() {
			while (true) {
				try {
					System.out.println(queue.take());
				} catch (InterruptedException e) {
					
					System.out.println("Logger Thread Interrupted");
					break;
				}
			}
		}
	}
}
