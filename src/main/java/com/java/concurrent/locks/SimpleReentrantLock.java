package com.java.concurrent.locks;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * The main differences between a Lock and a synchronized block are:
 * 
 * 1) A synchronized block makes no guarantees about the sequence in which
 * threads waiting to entering it are granted access. 2) You cannot pass any
 * parameters to the entry of a synchronized block. Thus, having a timeout
 * trying to get access to a synchronized block is not possible. 3) The
 * synchronized block must be fully contained within a single method. A Lock can
 * have it's calls to lock() and unlock() in separate methods.
 */
public class SimpleReentrantLock {
	public static void main(String[] args) throws InterruptedException {
		Lock l = new ReentrantLock();

		Worker3 t1 = new Worker3(l);
		Worker3 t2 = new Worker3(l);
		Worker3 t3 = new Worker3(l);

		t1.start();
		t2.start();
		t3.start();

		t1.join();
		t2.join();
		t3.join();

		System.out.println(Worker3.count);
	}
}

class Worker3 extends Thread {
	public static int count;
	private Lock l;

	public Worker3(final Lock l) {
		this.l = l;
	}

	@Override
	public void run() {
		for (int i = 0; i < 1000000; i++) {
			l.lock();
			count++;
			l.unlock();
		}
	}

}