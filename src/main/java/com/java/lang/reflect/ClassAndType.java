package com.java.lang.reflect;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Arrays;

public class ClassAndType {
	public static void main(String[] args) {
		Demo demo = new Demo(); // Object

		Class<?> c = demo.getClass();
		System.out.println(c.getName());
		java.lang.reflect.Type[] types = c.getGenericInterfaces();

		for (int i = 0; i < types.length; ++i) {
			Type t = types[i];
			if(t instanceof ParameterizedType) {
				System.out.println(((ParameterizedType) t).getRawType().getTypeName()); // Prints Comparable.class
				// since it takes parameter Integer
				System.out.println(Arrays.toString(((ParameterizedType) t).getActualTypeArguments()));
				// prints Integer
			}
			else {
				System.out.println(t.getTypeName());
			}
		}

	}
}

class Demo implements Comparable<Integer>, Serializable {

	@Override
	public int compareTo(Integer o) {
		return 0;
	}

}