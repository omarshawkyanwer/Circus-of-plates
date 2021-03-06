package model.levels;

import java.awt.Color;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Newbie implements ILevel {

	private static final float speed = 10;
	private static final int MAXScore = 2;
	private final Logger log = LogManager.getLogger(getClass());

	@Override
	public Color getColor() {
		return Color.GRAY;
	}

	@Override
	public String getName() {
		return "Newbie";
	}

	@Override
	public float getSpeed() {
		return speed;
	}

	@Override
	public ILevel upgrade(final int score) {
		if(score <= MAXScore) {
			return this;
		}
		log.info("A player leveled up to Expert.");
		return new Expert();
	}

}
