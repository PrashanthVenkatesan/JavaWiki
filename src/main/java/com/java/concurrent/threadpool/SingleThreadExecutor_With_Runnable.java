package com.java.concurrent.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SingleThreadExecutor_With_Runnable {
	/*
	 * Sample Use Case: Factorial of a number
	 */
	private static ExecutorService executorService;

	public static void main(String[] args) {
		executorService = Executors.newSingleThreadExecutor();

		for (int number = 1; number < 10; number++)
			executorService.submit(new WorkerThread(number));
		
		executorService.shutdown();
	}
}

class WorkerThread implements Runnable {
	private int number;

	public WorkerThread(final int n) {
		this.number = n;
	}

	public void run() {
		System.out.println(Thread.currentThread().getName());
		int fact = 1;
		for (int i = 2; i <= this.number; i++) {
			fact *= i;
		}
		System.out.printf("Factorial of a number is %d \n" , fact);
	}
}