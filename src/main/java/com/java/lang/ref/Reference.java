package com.java.lang.ref;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;

public class Reference {
	public static void main(String[] args) throws InterruptedException {
		// Strong Reference
		// Never includes this object for GC
		Integer a = 10;

		// Soft Reference
		// Includes for GC only when memory need by JVM
		// After making b as null, b is eligible for GC but only called when out of
		// memory
		Integer b = 10;
		SoftReference<Integer> softb = new SoftReference<Integer>(b);

		// Weak Reference
		// GC won’t wait until it needs memory , it will immediately remove reference
		// in next GC cycle
		Integer c = 10;
		WeakReference<Integer> softc = new WeakReference<Integer>(c);

		// Phantom Reference
		// Phantom references are objects that can be collected whenever the programmer
		// likes
		ReferenceQueue<? super Integer> queue = new ReferenceQueue<>();
		Integer d = new Integer(10);
		PhantomReference<Integer> softd = new PhantomReference<Integer>(d, queue);
		d = null;
		System.out.println(queue.remove(100)); // mostly prints null

		Runtime.getRuntime().gc(); // Force GC

		java.lang.ref.Reference<?> ref = queue.remove(1);
		System.out.println(ref); // prints phantom reference
		ref.clear();
	}
}
