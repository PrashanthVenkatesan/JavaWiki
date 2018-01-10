package com.java.concurrent.threadpool;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class FixedScheduledThreadExecutor_With_Runnable {
	private ScheduledExecutorService scheduledexecutorService;

	public static void main(String[] args) {
		FixedScheduledThreadExecutor_With_Runnable s = new FixedScheduledThreadExecutor_With_Runnable();
		s.start();

		s.schedule();
	}

	private void schedule() {
		final int taskid = 100;
		scheduledexecutorService.scheduleWithFixedDelay(new Runnable() {
			@Override
			public void run() {
				System.out.println(Thread.currentThread().getName());
				commit(taskid);
			}
		},
				// time to delay first execution
				0,
				// time to delay consecutive execution
				1000,
				// unit of time
				TimeUnit.MILLISECONDS);
	}

	private void start() {
		scheduledexecutorService = Executors.newScheduledThreadPool(5);
	}

	private void commit(final int taskid) {
		System.out.println("Commited task " + taskid);
	}
}