package com.sudhir.test.Runner;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

public class TestInterruption {
	private static  BlockingQueue<Integer> queue=new ArrayBlockingQueue<Integer>(5);
	public static void main(String[] args)throws InterruptedException{
		/*Task t=new Task();
		Thread t1=new Thread(t,"Sleep Thread");
		t1.start();
		TimeUnit.SECONDS.sleep(5);
		t1.interrupt();
		TimeUnit.SECONDS.sleep(1);
		System.out.println(t1.isInterrupted());
		System.out.println("***********************************");
		System.out.println("isInterrupted "+Thread.currentThread().isInterrupted());
		System.out.println("interrupted "+Thread.interrupted());
		System.out.println("interrupted "+Thread.interrupted());
		Task1 t2=new Task1();
		Thread tt3=new Thread(t2,"BlockingQueue Thread");
		tt3.start();
		TimeUnit.SECONDS.sleep(3);
		tt3.interrupt();
		TimeUnit.SECONDS.sleep(1);
		System.out.println(tt3.isInterrupted());*/
		Task2 task=new Task2();
		Thread t=new Thread(task);
		t.start();
		TimeUnit.SECONDS.sleep(3);
		t.interrupt();
		TimeUnit.SECONDS.sleep(1);
		System.out.println(t.isInterrupted());
		
	}
	private static class Task implements Runnable{
		@Override
		public void run(){
			try{
				TimeUnit.SECONDS.sleep(10);
			}catch(InterruptedException e){
				System.out.println(Thread.currentThread().getName()+" is interrupted");
				System.out.println("isInterrupted "+Thread.currentThread().isInterrupted());
				System.out.println("interrupted "+Thread.interrupted());
				System.out.println("interrupted "+Thread.interrupted());
			}
			
		}
	}
	private static class Task1 implements Runnable{
		public void run(){
			while(true){
				try{
					System.out.println(queue.take());
				}catch(InterruptedException e){
					System.out.println(Thread.currentThread().getName()+" is interrupted");
					System.out.println("isInterrupted "+Thread.currentThread().isInterrupted());
					System.out.println("interrupted "+Thread.interrupted());
					System.out.println("interrupted "+Thread.interrupted());
					return;
				}
				
			}
		}
	}
	private static class Task2 implements Runnable{

		@Override
		public void run() {
			while(!Thread.currentThread().isInterrupted()){
				System.out.println(Thread.currentThread().getName()+" is Running");
			}
			
		}
		
	}
}
