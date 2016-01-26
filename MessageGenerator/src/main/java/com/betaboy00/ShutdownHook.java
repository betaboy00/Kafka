package com.betaboy00;

import java.util.ArrayList;
import java.util.List;

public class ShutdownHook implements Runnable {

	private ArrayList<Thread> threads = new ArrayList<Thread>();
	
	ShutdownHook(List<Thread> threadList) {
		threadList.addAll(threadList);
	}
	
	@Override
	public void run() {

		for (Thread thread: threads) {
			System.out.println("Shutting down " + thread.getName());
			thread.interrupt();
			try {
				// ensure thread shuts down before terminating JVM
				thread.join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
	}

}
