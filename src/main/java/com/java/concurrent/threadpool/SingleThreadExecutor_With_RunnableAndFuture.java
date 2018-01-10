package com.java.concurrent.threadpool;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class SingleThreadExecutor_With_RunnableAndFuture {
	/*
	 * Sample Use Case: Factorial of a number
	 */
	private static ExecutorService executorService;

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		executorService = Executors.newSingleThreadExecutor();

		Boolean result = new Boolean(true);
		Future<Boolean> callback;
		for (int number = 1; number < 10; number++) {
			callback = executorService.submit(new WorkerThread1(number),result);
			
			System.out.println((callback.get()) ? "Success" : "Failure");
		}

		
		
		executorService.shutdown();
	}
}

class WorkerThread1 implements Runnable {
	private int number;

	public WorkerThread1(final int n) {
		this.number = n;
	}

	public void run() {
		System.out.println(Thread.currentThread().getName());
		int fact = 1;
		for (int i = 2; i <= this.number; i++) {
			fact *= i;
		}
		System.out.printf("Factorial of a number is %d \n", fact);
	}
}