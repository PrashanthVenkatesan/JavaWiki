package com.java.concurrent.ds;

import java.util.concurrent.Exchanger;

public class SimpleExchanger {

	/**
	 * Practical use case:
	 * One thread can fill the list and other thread can consume and read the list
	 * then they both can exchange their lists and do same process
	 * */
	public static void main(String[] args) {
		// Is a rendezvous point where two threads can exchange objects
		Exchanger<String> e = new Exchanger<>();
		new Worker2("Seller", "Home", e).start();
		new Worker2("Buyer", "Money", e).start();
	}

}

class Worker2 extends Thread {
	private String entity;
	private Exchanger<String> e;
	private String name;

	public Worker2(String name, String entity, Exchanger<String> e) {
		super();
		this.entity = entity;
		this.e = e;
		this.name = name;
	}

	public void run() {
		try {
			String previousEntity = entity;
			entity = e.exchange(entity);
			System.out.printf("Thread-%s - Exchanged %s for %s\n", name, previousEntity, entity);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}