package model.levels;

import java.awt.Color;

public class Master implements ILevel {

	private static final float speed = 30;

	@Override
	public Color getColor() {
		return Color.ORANGE;
	}

	@Override
	public String getName() {
		return "Master";
	}

	@Override
	public float getSpeed() {
		return speed;
	}

	@Override
	public ILevel upgrade(final int score) {
		return this;
	}

}
