package model.game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import eg.edu.alexu.csd.oop.view.GameScreen;

public class Timer implements ITimer {

	private int duration;
	private long currentTime;
	private Font font;
	private GameScreen gameScreen;

	public Timer(final int duration, final GameScreen gameScreen) {
		this.duration = duration;
		this.gameScreen = gameScreen;
		currentTime = System.currentTimeMillis() / 1000;
		font = new Font("Garamond", Font.BOLD, 30);
	}

	@Override
	public void draw(final Graphics2D g) {
		if (duration == 0) {
			endGame();
		}

		if (duration >= 60) {
			g.setColor(Color.GREEN);
		} else if (duration >= 20) {
			g.setColor(Color.YELLOW);
		} else {
			g.setColor(Color.RED);
		}
		g.setFont(font);
		g.drawString(getTime(), 580, 40);

		if (currentTime != System.currentTimeMillis() / 1000) {
			--duration;
			currentTime = System.currentTimeMillis() / 1000;
		}
	}

	private void endGame() {
		gameScreen.endGame(0);
	}

	public long getCurrentTime() {
		return currentTime;
	}

	public int getDuration() {
		return duration;
	}

	private String getTime() {
		String minute = Integer.toString(duration / 60);
		if (minute.length() < 2) {
			minute = "0" + minute;
		}

		String second = Integer.toString(duration % 60);
		if (second.length() < 2) {
			second = "0" + second;
		}

		return minute + ":" + second;
	}

	public void setCurrentTime(final long currentTime) {
		this.currentTime = currentTime;
	}

	public void setDuration(final int duration) {
		this.duration = duration;
	}

}
