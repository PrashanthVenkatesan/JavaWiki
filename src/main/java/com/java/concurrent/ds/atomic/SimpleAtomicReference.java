package com.java.concurrent.ds.atomic;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicReference;

public class SimpleAtomicReference {

	public static void main(String[] args) {
		// The AtomicReference  class provides an object reference variable which can be
		// read and written atomically.

		AtomicReference<String> atomicReference = new AtomicReference<String>();
		ExecutorService e = Executors.newFixedThreadPool(5);
		for (int i = 0; i < 10; i++) {
			e.submit(new Updater(atomicReference));
			System.out.println(atomicReference.get());
		}

		// CompareAndSet
		// compare two atomic reference objects with "==" not ".equals()" --
		// if true swaps, return true
		// else no swap return false
		e.shutdown();

		// AtomicStampedReference
		// AtomicStampedReference keeps both an object reference and a stamp internally.
		/*
		 * other known atomic classes
		 * AtomicBoolean AtomicInteger AtomicLong AtomicIntegerArray AtomicLongArray AtomicReferenceArray
		 */
	}

}

class Updater implements Runnable {
	private AtomicReference<String> atomicReference;

	public Updater(AtomicReference<String> atomicReference) {
		this.atomicReference = atomicReference;
	}

	@Override
	public void run() {
		this.atomicReference.set("newValue " + new Random().nextInt(100));
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
