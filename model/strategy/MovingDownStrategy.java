package model.strategy;

public class MovingDownStrategy implements IMovingStrategy {

	private final float xSpeed, ySpeed;

	public MovingDownStrategy(final float xSpeed, final float ySpeed) {
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
		return false;
	}

	@Override
	public float updateXPosition() {
		return xSpeed;
	}

	@Override
	public float updateYPosition() {
		return Math.abs(ySpeed);
	}
}
