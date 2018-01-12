package com.java.concurrent.future;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class SimpleCompletableFuture {

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		// We know about Future and its get() method to get the results
		// get() call is blocking until the process is done
		// With CompletableFuture, we can achieve this
		// CompletableFuture also implements the CompletionStage interface
		// It assures that the process will be done
		
		//TODO update more methods usage
		/**
		 * http://www.deadcoderising.com/java8-writing-asynchronous-code-with-completablefuture/
		 */
		Mail m = new Mail();
		int i = 0;
		for (; i < 100; i++) {
			CompletableFuture.supplyAsync(m::receiver).thenApply(m::getMessage).thenAccept(m::notify);
		}

		System.out.println("Non-blocking..Can do other task");
		System.out.println("Value of i is " + i);

		CompletableFuture<String> s = CompletableFuture.supplyAsync(m::receiver);
		s.thenApplyAsync(t -> {
			try {
				return m.sendAsync(t);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			return s;
		});
		System.out.println("Message is " + s.get());

	}
}

class Mail {
	private static int count;

	public String getMessage(String str) {
		return str.substring(0, 9);
	}

	public Future<String> sendAsync(String str) throws InterruptedException {
		CompletableFuture<String> completableFuture = new CompletableFuture<>();
		Executors.newCachedThreadPool().submit(() -> { // Callable task
			Thread.sleep(1);
			completableFuture.complete(str);
			return str;
		});

		return completableFuture;
	}

	public void notify(String str) {
		count++;
		System.out.println("Message - " + str + "  count is " + count);
	}

	public String receiver() {
		try {
			Thread.sleep(5);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return "Message-" + new Random().nextInt(100);
	}

}