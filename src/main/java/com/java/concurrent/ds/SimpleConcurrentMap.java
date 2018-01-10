package com.java.concurrent.ds;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class SimpleConcurrentMap {

	public static void main(String[] args) {
		ConcurrentMap<String, String> concurrentMap = new ConcurrentHashMap<String, String>();

		concurrentMap.put("key", "value");

		Object value = concurrentMap.get("key");
		System.out.println(value);
	}

}
