package com.java.concurrent.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CachedThreadExecutor_With_Runnable {
	/*
	 * Sample Use Case: Factorial of a number
	 */
	private static ExecutorService executorService;
	public static void main(String[] args) {
		// Create Cached Thread Pool
		// Mainly used for short lived tasks
		// It will create a new thread and add it to the pool when task increases
		// It will also reuse the existing threads in pool wisely
		// if the thread is idle for more than 60 secs, it will remove from the pool
		executorService = Executors.newCachedThreadPool();

		for (int number = 1; number < 10; number++) {
			executorService.submit(new WorkerThread5(number));

			try {
				// Since we sleep here, we are not creating more threads to the pool
				// if you remove this sleep, then you can see 9 threads created in a pool
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		executorService.shutdown();
	}
}

class WorkerThread5 implements Runnable {
	private int number;

	public WorkerThread5(final int n) {
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