package model.strategy;


public class MovingOnLeftShelfStrategy implements IMovingStrategy {
	private static final float shelfEndX = 350;
	private final float xSpeed, ySpeed;

	public MovingOnLeftShelfStrategy(final float xSpeed, final float ySpeed) {
		this.xSpeed = xSpeed;
		this.ySpeed = ySpeed;
	}

	@Override
	public float getXSpeed() {
		return xSpeed;
	}

	@Override
	public float getYSpeed() {
		return ySpeed;
	}

	@Override
	public boolean reachedEnd(final float currentXPosition) {
		return Float.compare(shelfEndX, currentXPosition) == -1;
	}

	@Override
	public float updateXPosition() {
		return xSpeed;
	}

	@Override
	public float updateYPosition() {
		return 0;
	}
}
