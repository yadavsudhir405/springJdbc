package com.sudhir.test.jcp;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * The {@code GraceFulLogger} class provides GracefulLogger Service
 * 
 * @author Sudhir
 *
 */
public class GraceFulLogger {
	private final BlockingQueue<String> queue;
	private final LoggerThread loggerThread;
	private volatile boolean isStopped;
	private int messageCount;

	public GraceFulLogger(BlockingQueue<String> queue) {
		this.queue = queue;
		this.loggerThread = new LoggerThread();
	}

	public void stop() {
		synchronized (this) {
			isStopped = true;
		}
		loggerThread.interrupt();
	}

	public void logMessage(String msg) throws InterruptedException {
		synchronized (this) {
			if (isStopped) {
				throw new IllegalStateException("Graceful Logger Service is shutdown");
			}
			messageCount++;
		}
		queue.put(msg);
	}

	public void start() {
		loggerThread.start();
	}

	private class LoggerThread extends Thread {

		/*
		 * public void run() { while (true) { try {
		 * System.out.println(queue.take()); synchronized (LoggerThread.this) {
		 * messageCount--; } TimeUnit.SECONDS.sleep(4); } catch
		 * (InterruptedException ignore) { System.out.println(
		 * "LoggerThread is inturrepted"); derainedQueuedMessages();
		 * System.out.println("Exiting LoggerThread"); break; } }
		 * 
		 * }
		 */
		public void run() {
			while (true) {
				try {

					if (isStopped && messageCount == 0) {
						break;
					}
					

					System.out.println(queue.take());
					synchronized (LoggerThread.this) {
						messageCount--;
					}
					TimeUnit.SECONDS.sleep(4);
				} catch (InterruptedException IgnoreThis) {

				}
			}
		}

		private void derainedQueuedMessages() {
			System.out.println("Starting Draining Queued Messages");
			while (true) {
				synchronized (LoggerThread.this) {
					if (isStopped && messageCount == 0) {
						break;
					}
				}
				try {
					System.out.println(queue.take());
					synchronized (LoggerThread.this) {
						messageCount--;
					}
				} catch (InterruptedException ignoreThis) {

				}

			}
			System.out.println("Done with draining messages");
		}
	}
}
