package com.java.concurrent.future;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

public class SimpleFutureTask_With_Callable {
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		FutureTask<Integer> f = new FutureTask<>(new Callable<Integer>() {
			@Override
			public Integer call() throws Exception {
				return new Random().nextInt(1000);
			}
		});

		ExecutorService e = Executors.newSingleThreadExecutor();
		e.submit(f);

		System.out.println(f.get());
		e.shutdown();
	}
}
