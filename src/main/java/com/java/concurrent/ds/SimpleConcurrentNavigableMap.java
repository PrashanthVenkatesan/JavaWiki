package com.java.concurrent.ds;

import java.util.concurrent.ConcurrentNavigableMap;
import java.util.concurrent.ConcurrentSkipListMap;

public class SimpleConcurrentNavigableMap {

	public static void main(String[] args) {
		// java.util.NavigableMap with support for concurrent access, and which has
		// concurrent access enabled for its submaps

		// The headMap(T toKey) method returns a view of the map containing the keys
		// which are strictly less than the given key.
		// tailMap(T fromKey) method returns a view of the map containing the keys which
		// are greater than or equal to the given fromKey
		// The subMap() method returns a view of the original map which contains all
		// keys from (including), to (excluding) two keys given as parameters to the
		// method
		ConcurrentNavigableMap<Integer, String> map = new ConcurrentSkipListMap<Integer, String>();

		map.putIfAbsent(1, "hi");
		map.putIfAbsent(2, "hello");
		map.putIfAbsent(3, "how");

		System.out.println(map.headMap(2).toString()); // prints key < 2
		System.out.println(map.tailMap(2).toString()); // prints key >= 2
		System.out.println(map.subMap(2, 3).toString()); // prints 2 <= key < 3
	}
}
