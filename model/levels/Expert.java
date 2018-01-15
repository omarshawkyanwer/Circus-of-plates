package model.levels;

import java.awt.Color;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Expert implements ILevel {

	private static final float speed = 20;
	private static final int MAXScore = 4;
	private final Logger log = LogManager.getLogger(getClass());

	@Override
	public Color getColor() {
		return Color.BLUE;
	}

	@Override
	public String getName() {
		return "Expert";
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
		log.info("A player leveled up to Master.");
		return new Master();
	}

}
