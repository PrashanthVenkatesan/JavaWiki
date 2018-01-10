package com.java.concurrent.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class CustomThreadPoolExecutor {
	/*
	 * Sample Use Case: Factorial of a number
	 */
	private static ExecutorService executorService;

	public static void main(String[] args) {
		executorService = new ThreadPoolExecutor(
				// number of threads needs to be present in pool even if it is idle
				1,
				// maximum number of threads allowed in the pool
				3,
				// Maximum time idle threads can reside in pool
				1L,
				// Unit of the keepAliveTime
				TimeUnit.SECONDS,
				// Queue to hold task before they executed
				
				// For this configuration, if you reduce this queue size less than 6, you will see the 
				// default abort policy rejection error
				new LinkedBlockingDeque<Runnable>(6));

		for (int number = 1; number < 10; number++) {
			executorService.submit(new WorkerThread6(number));
		}

		executorService.shutdown();
	}
}

class WorkerThread6 implements Runnable {
	private int number;

	public WorkerThread6(final int n) {
		this.number = n;
	}

	public void run() {
		System.out.printf("Thread %s --- Number %d\n", Thread.currentThread().getName(), this.number);
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