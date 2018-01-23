package com.java.util.ds;

import java.util.Map;
import java.util.WeakHashMap;

public class WeakMap {

	public static void main(String[] args) throws InterruptedException {
		Map<Object, String> listenerRegistry = new WeakHashMap<>();

		Object o1 = new Object();
		Object o2 = new Object();
		Object o3 = new Object();
		Object o4 = new Object();

		// Usage guideline:
		// https://stackoverflow.com/questions/33050021/can-i-use-weakhashmap-instead-of-hashmap

		listenerRegistry.put(o1, "First");
		listenerRegistry.put(o2, "Second");
		listenerRegistry.put(o3, "Third");
		listenerRegistry.put(o4, "Fourth");

		// WeakHashMap remove the entry from the map as soon as the key Object is called
		// for gc.
		// Map implementation that allows GC to automatically delete unused objects

		System.out.println(listenerRegistry.size());
		System.out.println(listenerRegistry.toString());

		o1 = null;
		System.gc();  // Force JVM to GC
		Thread.sleep(1000);
		System.out.println(listenerRegistry.size());  // Now size will be 3
		// System.out.println(listenerRegistry.toString());
	}

}
