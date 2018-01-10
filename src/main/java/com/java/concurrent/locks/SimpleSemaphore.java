package com.java.concurrent.locks;

import java.util.concurrent.Semaphore;

public class SimpleSemaphore {
	/**
	 * Real life example for Semaphore:
	 * 
	 * ATM: ATM center will have fixed number of machines say 4 machines. At any
	 * time, maximum of 4 people can use the ATM service once a person leaves, next
	 * person can use the atm
	 */
	public static void main(String[] args) {
		Semaphore s = new Semaphore(3);
		// Below commented code will acquire 2 threads permanently for main thread
		// hence it can't be used by others until it is released
		// s.tryAcquire(2);
		for (int number = 1; number < 10; number++)
			new Worker1(number, s).start();

		while (s.hasQueuedThreads()) {
			System.out.println(s.getQueueLength());
		}
	}

}

class Worker1 extends Thread {
	private int number;
	private Semaphore s;

	public Worker1(final int n, Semaphore s) {
		this.number = n;
		this.s = s;
	}

	public void run() {
		try {
			s.acquire();
			System.out.println(Thread.currentThread().getName());
			int fact = 1;
			for (int i = 2; i <= this.number; i++) {
				fact *= i;
			}
			System.out.printf("Factorial of a number is %d \n", fact);
			s.release();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}