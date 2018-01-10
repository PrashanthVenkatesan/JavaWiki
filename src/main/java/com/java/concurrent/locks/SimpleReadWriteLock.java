package com.java.concurrent.locks;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class SimpleReadWriteLock {
	/**
	 * 
	 * It allows multiple threads to read a certain resource, but only one to write
	 * it, at a time ReadWriteLock actually internally keeps two Lock instances. One
	 * guarding read access, and one guarding write access.
	 */
	public static void main(String[] args) throws InterruptedException {
		ReadWriteLock r = new ReentrantReadWriteLock();
		new ReadThread(r).start(); // nothing to print
		new WriteThread(r).start(); // writes 10 numbers
		new ReadThread(r).start();
		Thread.sleep(1000);
		new WriteThread(r).start(); // write 10 numbers
		new ReadThread(r).start(); // read thread act simultaneously
		new ReadThread(r).start();
		Thread.sleep(1000);
		new WriteThread(r).start(); // write 10 numbers
		new ReadThread(r).start(); // read simultaneously
		new ReadThread(r).start(); // read simultaneously
	}

}

class Q {
	public static Queue<Integer> list = new LinkedList<>();
}

class ReadThread extends Thread {
	private ReadWriteLock l;

	public ReadThread(final ReadWriteLock l) {
		this.l = l;
	}

	@Override
	public void run() {
		l.readLock().lock();
		String str = Thread.currentThread().getName() + "   ";

		if (Q.list.isEmpty())
			str += "There is nothing to read";
		Iterator<Integer> it = Q.list.iterator();
		while (it.hasNext()) {
			str += it.next() + "  ";
		}
		System.out.println(str + "\n");
		l.readLock().unlock();
	}
}

class WriteThread extends Thread {
	private ReadWriteLock l;

	public WriteThread(final ReadWriteLock l) {
		this.l = l;
	}

	@Override
	public void run() {
		Random r = new Random();
		String str = Thread.currentThread().getName() + "   Writing\n";
		l.writeLock().lock();
		int i = 10;
		while (i-- > 0) {
			Q.list.add(r.nextInt(100));
		}
		System.out.println(str);
		l.writeLock().unlock();
	}

}