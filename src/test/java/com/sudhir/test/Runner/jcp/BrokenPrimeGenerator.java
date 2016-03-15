package com.sudhir.test.Runner.jcp;

import java.math.BigInteger;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class BrokenPrimeGenerator implements Runnable {
	
	private final BlockingQueue<BigInteger> queue;
	private volatile boolean isCancelled=false;
	public BrokenPrimeGenerator(BlockingQueue<BigInteger> queue){
		this.queue=queue;
	}
	@Override
	public void run() {
		BigInteger p=BigInteger.ONE;
		while(!isCancelled){
			try {
				queue.put(p=p.nextProbablePrime());
			} catch (InterruptedException consumed) {
				
				consumed.printStackTrace();
			}
		}

	}
	public void cancel(){
		isCancelled=true;
	}

}
