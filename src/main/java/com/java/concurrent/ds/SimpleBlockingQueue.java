package com.java.concurrent.ds;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class SimpleBlockingQueue {
	/**
	 * A BlockingQueue is typically used to have on thread produce objects, which
	 * another thread consumes. ArrayBlockingQueue DelayQueue LinkedBlockingQueue
	 * PriorityBlockingQueue SynchronousQueue
	 */
	public static void main(String[] args) {
		// ArrayBlockingQueue Like Array - fixed size
		// ArrayBlockingQueue is a bounded, blocking queue that stores the elements
		// internally in an array.
		BlockingQueue<Integer> b = new ArrayBlockingQueue<>(1024);

		// DelayQueue
		// The DelayQueue blocks the elements internally until a certain delay has
		// expired.
		/**
		 * BlockingQueue<Integer> queue = new DelayQueue(); Delayed element1 = new
		 * DelayedElement() [We have to implement this class]; queue.put(element1);
		 * Delayed element2 = queue.take();
		 */

		// LinkedBlockingQueue Like List - any size
		/** BlockingQueue<Integer> b = new LinkedBlockingQueue<>(); */

		// PriorityBlockingQueue
		// All elements inserted into the PriorityBlockingQueue must implement the
		// java.lang.Comparable interface
		/** BlockingQueue<Integer> b = new PriorityBlockingQueue(); */

		// SynchronousQueue -- Just Like Exchanger Class
		// The SynchronousQueue is a queue that can only contain a single element
		// internally. A thread inseting an element into the queue is blocked until
		// another thread takes that element from the queue. Likewise, if a thread tries
		// to take an element and no element is currently present, that thread is
		// blocked until a thread insert an element into the queue.

		new Thread(new Producer(b)).start();
		new Thread(new Consumer(b)).start();
	}
}

class Producer implements Runnable {
	private BlockingQueue<Integer> b;

	public Producer(final BlockingQueue<Integer> b) {
		this.b = b;
	}

	@Override
	public void run() {
		Random r = new Random();
		while (true) {
			try {
				b.put(r.nextInt(100000));
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}

class Consumer implements Runnable {
	private BlockingQueue<Integer> b;

	public Consumer(final BlockingQueue<Integer> b) {
		this.b = b;
	}

	@Override
	public void run() {
		while (true) {
			try {
				System.out.println(b.take());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}
}