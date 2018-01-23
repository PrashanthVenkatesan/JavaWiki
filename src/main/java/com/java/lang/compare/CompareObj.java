package com.java.lang.compare;

import java.util.Comparator;

public class CompareObj {

	public static void main(String[] args) {
		// Compare two integer objects
		Integer testData = 10;
		Integer numberLessThantestData = 9;
		Integer numberGreaterThantestData = 14;
		Integer numberEqualstestData = 10;

		// Using Comparable
		Comparable<Integer> integerCompare = (Comparable<Integer>) testData;
		System.out.println(integerCompare.compareTo(numberLessThantestData)); // Prints 1
		System.out.println(integerCompare.compareTo(numberGreaterThantestData)); // Prints -1
		System.out.println(integerCompare.compareTo(numberEqualstestData)); // Prints 0

		// Using Comparator
		Comparator<Integer> intCompare = new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				int difference = o2 - o1;
				if (difference < 0)
					return 1;
				else if (difference > 0)
					return -1;
				else
					return 0;
			}
		};
		System.out.println(intCompare.compare(testData, numberLessThantestData)); // Prints 1
		System.out.println(intCompare.compare(testData, numberGreaterThantestData)); // Prints -1
		System.out.println(intCompare.compare(testData, numberEqualstestData)); // Prints 0
	}
}
