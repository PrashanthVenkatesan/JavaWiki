package com.java.concurrent.locks;

import java.util.concurrent.CountDownLatch;

public class SimpleLatch {
	/**
	 * Real life example for Latch:
	 * 
	 * Examination Hall: Supervisor will wait for all students to give the papers 
	 * If the students completes exam , he / she can leave immediately
	 * But supervisor will leave only after all students handover the papers
	 * 
	 * */
	public static void main(String[] args) throws InterruptedException {
		CountDownLatch latch = new CountDownLatch(2);

		new JobThread(latch).start();
		new JobThread(latch).start();
		new ExecutionThread(latch).start();
		
	}
}

class JobThread extends Thread {
	private final CountDownLatch latch;

	public JobThread(final CountDownLatch latch) {
		this.latch = latch;
	}

	@Override
	public void run() {
		for (int i = 0; i < 10; i++) {
			System.out.println(i);
		}
		latch.countDown();
	}

}

class ExecutionThread extends Thread {
	private final CountDownLatch latch;

	public ExecutionThread(final CountDownLatch latch) {
		this.latch = latch;
	}

	@Override
	public void run() {
		try {
			latch.await();
			System.out.println("Completed 2 jobs");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}