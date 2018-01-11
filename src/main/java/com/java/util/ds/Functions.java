package com.java.util.ds;

import java.util.function.BiFunction;
import java.util.function.Function;

public class Functions {

	private static Function<Integer, Integer> increment = number -> number + 2;
	private static Function<Integer, Integer> power = number -> (int) Math.pow(number, 2);
	private static BiFunction<Integer, Integer, Integer> add = (input1, input2) -> input1 + input2;
	private static BiFunction<Integer, Integer, Integer> multiply = (input1, input2) -> input1 * input2;

	public static void main(String[] args) {
		int x = 4;
		int y = 5;
		// Equation : x + 2 = 6
		System.out.println(increment.apply(x));

		// Equation : x^2 + 2 = 18
		System.out.println(increment.compose(power).apply(x));

		// Equation : (x + 2)^2 = 36
		System.out.println(increment.andThen(power).apply(x));

		// Equation : (x * y) ^ 2 = 400
		System.out.println(multiply.andThen(power).apply(x, y));

		// Equation : ((x + y) + 2)^2 = 121
		System.out.println(add.andThen(increment).andThen(power).apply(x, y));
	}

}
