package com.betaboy00;

public class AlarmThread implements Runnable{

	private long interval;
	private boolean stop = false;
	
	AlarmThread(long interval) {
		this.interval = interval;
	}
	
	@Override
	public void run() {
		while (!stop) {
			System.out.println("Running....");
			try {
				Thread.sleep(interval);
			} catch (InterruptedException e) {
				System.out.println("Interrupted. Stopping thread");
				stop = true;
			}
		}
		
	}

}
