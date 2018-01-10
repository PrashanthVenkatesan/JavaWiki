package com.java.concurrent.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class CustomThreadPoolExecutor_WithRejectionPolicy {
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
				new LinkedBlockingDeque<Runnable>(4),
				// Rejection Handler
				
				/*http://letslearnjavaj2ee.blogspot.in/2013/08/threadpoolexecutor-handler-policies-for.html
				 * Inbuilt Rejection Handlers
				 * 1) ThreadPoolExecutor.AbortPolicy (default)
				 * 	the handler will throw the exception RejectedExecutionException if any task is rejected by the Threadpool
				 * 	new ThreadPoolExecutor.AbortPolicy()
				 * 2) ThreadPoolExecutor.CallerRunsPolicy
				 *  the handler will invoke the rejected task in the thread which called the execute method
				 *  This handler will slowdown the task addition to the ThreadPool 
				 *  new ThreadPoolExecutor.CallerRunsPolicy()
				 * 3) ThreadPoolExecutor.DiscardPolicy
				 *  This  handler will silently discards the rejected task and will not take any action for the rejected task
				 *  new ThreadPoolExecutor.DiscardPolicy()
				 * 4)ThreadPoolExecutor.DiscardOldestPolicy
				 *    If any added task is about to be rejected,the handler will discards the oldest unallocated task until it finds the ideal thread and then  execute the current task through the ideal thread
				 *    new ThreadPoolExecutor.DiscardOldestPolicy()
				 *  
				 * We can create custom rejection handler also
				 * */
				new TaskRejectionHandler());

		for (int number = 1; number < 10; number++) {
			executorService.submit(new WorkerThread7(number));
		}

		executorService.shutdown();
	}
}

class WorkerThread7 implements Runnable {
	private int number;

	public WorkerThread7(final int n) {
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

class TaskRejectionHandler implements RejectedExecutionHandler {

	@Override
	public void rejectedExecution(Runnable r, ThreadPoolExecutor t) {
		System.out.println(" Running through RejectionHandler,Since " + "there are no ideal threads in ThreadPool");
	}

}