package com.java.concurrent.future;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

public class SimpleFutureTask_With_Runnable {
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		FutureTask<Integer> f = new FutureTask<Integer>(new Runnable() {
			@Override
			public void run() {
				System.out.println("Task Completed");
			}

		}, 1);

		ExecutorService e = Executors.newSingleThreadExecutor();
		e.submit(f);

		System.out.println(f.get());
		e.shutdown();
	}
}
