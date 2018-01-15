package model.game;

public class RandomSpeedGenerator {

	public static final int speedLowerBound = 2;

	public float getRandomSpeedX() {
		return (float) (Math.random() * 10) + speedLowerBound;
	}

	public float getRandomSpeedY() {
		return (float) (Math.random() * 10) + speedLowerBound;
	}

}
