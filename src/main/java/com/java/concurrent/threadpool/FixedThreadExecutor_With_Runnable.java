package com.java.concurrent.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FixedThreadExecutor_With_Runnable {
	/*
	 * Sample Use Case: Factorial of a number
	 */
	private static ExecutorService executorService;

	public static void main(String[] args) {
		// Create Thread Pool with 5 worker threads
		executorService = Executors.newFixedThreadPool(5);

		for (int number = 1; number < 10; number++)
			executorService.submit(new WorkerThread3(number));

		executorService.shutdown();
	}
}

class WorkerThread3 implements Runnable {
	private int number;

	public WorkerThread3(final int n) {
		this.number = n;
	}

	public void run() {
		System.out.printf("Thread %s --- Number %d\n",Thread.currentThread().getName(), this.number);
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