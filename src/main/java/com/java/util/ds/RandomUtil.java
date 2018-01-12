package com.java.util.ds;

import java.util.Random;

public class RandomUtil {
	public static void main(String[] args) {
		Random r = new Random();

		// generate infinite stream of numbers
		/** r.ints().asLongStream().forEach(x -> System.out.println(x)); */

		// generate stream of 5 numbers
		r.ints(5).asLongStream().forEach(x -> System.out.println(x));

		// generate infinite of numbers in range 0 to 10
		/** r.ints(0, 10).asLongStream().forEach(x -> System.out.println(x)); */

		// generate stream of 5 numbers in range 0 to 10
		r.ints(5, 0, 10).asLongStream().forEach(x -> System.out.println(x));

		// Similarly same set of API's available for long and double streams
	}
}
