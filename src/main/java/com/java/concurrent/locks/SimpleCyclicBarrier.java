package com.java.concurrent.locks;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class SimpleCyclicBarrier {
	/**
	 * Real life example for CyclicBarrier:
	 * 
	 * Movie Hall: Operator will wait for all the spectators to come before
	 * starting the movie or show
	 * Here, even if a person comes early he has to wait for all peoples to come 
	 * to see the movie 
	 * */
	public static void main(String[] args) throws InterruptedException, BrokenBarrierException {
		int[] a = new int[] { 1, 3, 2, 6, 3, 34, 6, 6, 7, 36, 8, 8, 945, 0, 23, -2, 3, -5993, -23, 63, -84723892 };
		int decomposeFactor = (int) Math.ceil(Math.sqrt(a.length));
		final List<Worker> workerList = new ArrayList<>();

		final CyclicBarrier barrier = new CyclicBarrier(decomposeFactor, new Runnable() {
			@Override
			public void run() {
				int min = Integer.MAX_VALUE;
				for (Worker w : workerList) {
					min = Math.min(min, w.getMin());
				}
				System.out.println("Minimum number in array is " + min);
			}
		});

		for (int i = 0; i < decomposeFactor; i++) {
			workerList.add(i, new Worker(a, i, decomposeFactor, barrier));
			workerList.get(i).start();
		}

		barrier.reset();
	}
}

class Worker extends Thread {
	private int a[];
	private int offset;
	private int range;
	private int min = Integer.MAX_VALUE;
	private CyclicBarrier c;

	public int getMin() {
		return min;
	}

	public Worker(final int a[], final int offset, final int range, final CyclicBarrier c) {
		this.a = a;
		this.offset = offset;
		this.range = range;
		this.c = c;
	}

	@Override
	public void run() {
		final int start = offset * range;
		final int end = start + range;
		for (int i = start; i < end && i < a.length; i++) {
			min = Math.min(min, a[i]);
		}
		System.out.printf("Thread- %s - start - %d - end - %d - min - %d\n", Thread.currentThread().getName(), start,
				end, min);

		try {
			c.await(2L, TimeUnit.SECONDS);
		} catch (InterruptedException | BrokenBarrierException | TimeoutException e) {
			e.printStackTrace();
		}

	}
}