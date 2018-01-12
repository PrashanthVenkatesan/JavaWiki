package com.java.util.ds;

import java.util.Optional;
import java.util.OptionalDouble;
import java.util.OptionalInt;
import java.util.OptionalLong;

public class OptionalReturn {

	public static void main(String[] args) {
		System.out.println(add(5, 6));
		System.out.println(add(7, 6));
		System.out.println(add(5l, 6l));
		System.out.println(add(7l, 6l));
		System.out.println(add(5.0d, 6.0d));
		System.out.println(add(7.0d, 6.0d));
		System.out.println(validate(5, 6));
		System.out.println(validate(7, 6));
	}

	private static OptionalInt add(int a, int b) {
		if (a > b) {
			return OptionalInt.of(a + b);
		}
		return OptionalInt.empty();
	}

	private static OptionalLong add(long a, long b) {
		if (a > b) {
			return OptionalLong.of(a + b);
		}
		return OptionalLong.empty();
	}

	private static OptionalDouble add(double a, double b) {
		if (a > b) {
			return OptionalDouble.of(a + b);
		}
		return OptionalDouble.empty();
	}

	private static Optional<Boolean> validate(int a, int b) {
		Boolean b1 = null;
		if (a > b) {
			b1 = new Boolean(true);
		}
		return Optional.ofNullable(b1);
	}
}
