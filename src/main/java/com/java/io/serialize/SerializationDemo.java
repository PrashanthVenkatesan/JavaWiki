package com.java.io.serialize;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class SerializationDemo {
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		Person p = new Person("Java");

		// Saving of object in a file
		FileOutputStream file = new FileOutputStream("test.ser");
		ObjectOutputStream out = new ObjectOutputStream(file);

		out.writeObject(p);
		out.flush();
		out.close();
		file.close();
		System.out.println(p.name); // prints Java
		p = null; // Let GC clears it out
		System.out.println(p); // null

		// Reading the object from a file
		FileInputStream file1 = new FileInputStream("test.ser");
		ObjectInputStream in = new ObjectInputStream(file1);
		p = (Person) in.readObject();
		System.out.println(p.name); // prints Java

		in.close();
		file1.close();
	}
}

class Person implements Serializable {
	private static final long serialVersionUID = 2558923546466054505L;
	String name;
	transient int age = 1; // Non - serializable

	public Person(final String name) {
		this.name = name;
	}

	public Person() {
	}

	private void writeObject(java.io.ObjectOutputStream s) throws java.io.IOException {
		System.out.println("Using customised writeObject");
		s.defaultWriteObject();
		s.writeObject(name);
	}

	private void readObject(java.io.ObjectInputStream s) throws java.io.IOException, ClassNotFoundException {
		System.out.println("Using customised readObject");
		s.defaultReadObject();
		this.name = (String) s.readObject();
	}
}