package com.java.concurrent.threadpool;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class SingleThreadExecutor_With_CallableAndFuture {
	/*
	 * Sample Use Case: Factorial of a number
	 */
	private static ExecutorService executorService;

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		executorService = Executors.newSingleThreadExecutor();

		List<Future<Integer>> callbackList = new ArrayList<>();
		for (int number = 1; number < 10; number++) {
			callbackList.add(executorService.submit(new WorkerThread2(number)));
		}

		for (Future<Integer> f : callbackList) {
			System.out.println("The factorial is " + f.get());
		}
		executorService.shutdown();
	}
}

class WorkerThread2 implements Callable<Integer> {
	private int number;

	public WorkerThread2(final int n) {
		this.number = n;
	}

	public Integer call() throws Exception {
		System.out.println(Thread.currentThread().getName());
		int fact = 1;
		for (int i = 2; i <= this.number; i++) {
			fact *= i;
		}
		return fact;
	}
}