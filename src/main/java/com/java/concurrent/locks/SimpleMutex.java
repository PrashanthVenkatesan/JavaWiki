package com.java.concurrent.locks;

import java.util.concurrent.Semaphore;

public class SimpleMutex {
	/**
	 * Mutex is the Semaphore with an access count of 1
	 * 
	 * @throws InterruptedException
	 */
	public static void main(String[] args) throws InterruptedException {
		// max 1 people
		Semaphore semaphore = new Semaphore(1);
		semaphore.acquire();
		//do something
		semaphore.release();
	}
}
