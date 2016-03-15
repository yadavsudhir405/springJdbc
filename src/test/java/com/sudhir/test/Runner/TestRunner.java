package com.sudhir.test.Runner;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class TestRunner {
	private static final ExecutorService THREAD_POOL=Executors.newCachedThreadPool();
	private static final int TIME_OUT=6;
	public static void main(String[] args) throws InterruptedException {
		List<Callable<Boolean>> tasks=new ArrayList<Callable<Boolean>>();
		tasks.add(new Task());
		tasks.add(new Task1());
		
		List<Future<Boolean>> requests=THREAD_POOL.invokeAll(tasks, TIME_OUT, TimeUnit.SECONDS);
		for(Future<Boolean> f:requests){

			try {
				System.out.println(f.get());
			} catch (ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (CancellationException e) {
				System.out.println("********************************TimedOut*****************************\n"
						+ "***********************************************");
				
				
			}
		}
	}

	private static class Task implements Callable<Boolean> {

		@Override
		public Boolean call() throws Exception {
			System.out.println(Thread.currentThread().getName() + " is Running");
			try {
				TimeUnit.SECONDS.sleep(3);
			} catch (InterruptedException e) {
				System.out.println(Thread.currentThread().getName()+" has been interrupted");
				Thread.currentThread().interrupt();
				return false;
			}

			return true;
		}

	}
	private static class Task1 implements Callable<Boolean>{

		@Override
		public Boolean call() throws Exception {
			while(!Thread.currentThread().isInterrupted()){
				System.out.println(Thread.currentThread().getName()+" is Running");
				
			}
			System.out.println(Thread.currentThread().getName()+" is interrupted");
			return false;
			
		}
		
	}
	private static class InfiniteRunningThread implements Runnable{

		@Override
		public void run(){
			while(!Thread.currentThread().isInterrupted()){
				System.out.println(Thread.currentThread().getName()+" has  started running");
				try{
					TimeUnit.SECONDS.sleep(10);
				}catch(InterruptedException e){
					e.printStackTrace();
				}
				
			}
			
		}
		
	}
}
