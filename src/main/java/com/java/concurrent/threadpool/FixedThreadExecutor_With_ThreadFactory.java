package com.java.concurrent.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

public class FixedThreadExecutor_With_ThreadFactory {
	/*
	 * Sample Use Case: Factorial of a number
	 */
	private static ExecutorService executorService;

	public static void main(String[] args) {
		// Create Thread Pool with 5 worker threads
		// Thread Factory is mainly for creating user defined threads 
		// for ex, we can change the name of the thread
		executorService = Executors.newFixedThreadPool(5, new WorkerThreadFactory());

		for (int number = 1; number < 10; number++)
			executorService.submit(new WorkerThread4(number));

		executorService.shutdown();
	}
}

class WorkerThread4 implements Runnable {
	private int number;

	public WorkerThread4(final int n) {
		this.number = n;
	}

	public void run() {
		System.out.printf("Thread %s\n", Thread.currentThread().getName());
		int fact = 1;
		for (int i = 2; i <= this.number; i++) {
			fact *= i;
		}
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.printf("Factorial of a number is %d \n", fact);
	}
}

class WorkerThreadFactory implements ThreadFactory {
	private int counter;

	@Override
	public Thread newThread(Runnable r) {
		return new Thread(r, "WorkerThread-" + ++counter);
	}

}