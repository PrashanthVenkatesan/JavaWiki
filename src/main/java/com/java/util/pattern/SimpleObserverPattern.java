package com.java.util.pattern;

import java.util.Observable;
import java.util.Observer;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SimpleObserverPattern {
	public static void main(String[] args) {
		ExecutorService e = Executors.newSingleThreadExecutor();
		ScoreBoard s = new ScoreBoard();
		Player p = new Player();
		p.addObserver(s);
		e.submit(p);
		e.shutdown();
	}
}

class ScoreBoard implements Observer {
	private Player player;

	@Override
	public void update(Observable o, Object arg) {
		boolean out = (boolean) arg;
		if (out) {
			System.out.println("Player got Out!!");
			return;
		}
		player = (Player) o;
		System.out.println("Player scored " + player.getScore());
	}

}

class Player extends Observable implements Runnable {
	private int score;

	@Override
	public void run() {
		int overs = 3;
		int balls = overs * 6;
		boolean out = true;
		while (balls-- > 0) {
			try {
				Random r = new Random();
				int sc = r.nextInt(8) - 1;
				setScore(sc);
				setChanged();
				if (sc < 0) {
					notifyObservers(out);
				} else {
					notifyObservers(!out);
				}
				Thread.sleep(1000);
			} catch (InterruptedException e) {

				e.printStackTrace();
			}
		}
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

}