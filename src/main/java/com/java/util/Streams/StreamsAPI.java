package com.java.util.Streams;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.LongConsumer;

public class StreamsAPI {
	// Find the strictly increasing triplets using Streams APi
	public static void main(String[] args) {
		AtomicInteger i = new AtomicInteger();
		Queue<Long> queue = new LinkedList<Long>();
		AtomicLong min = new AtomicLong(Long.MAX_VALUE);
		LongConsumer printTriplets = x -> {

			// First Number in the stream
			if (min.get() == Long.MAX_VALUE) {
				min.set(x);
				queue.add(x);
				i.incrementAndGet();
			} else if (x > min.get()) {
				if (i.incrementAndGet() == 3) {
					// Found Triplets
					queue.add(x);
					i.getAndDecrement();
					System.out.println(queue.toString());
					queue.poll();
					min.set(x);
				} else {
					// Gathering Triplets
					queue.add(x);
					min.set(x);
				}
			} else {
				// Resetting the previous uncompleted triplets
				min.set(x);
				queue.removeAll(queue);
				queue.add(x);
				i.getAndSet(1);
			}
		};

		Random r = new Random();
		r.longs(1000, 100, 200)
		 // LongStream.of(100,101,102,102,102,103,104,100,103,107)
		 .forEach(printTriplets);
	}

}
