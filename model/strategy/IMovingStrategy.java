package model.strategy;

public interface IMovingStrategy {

	public float getXSpeed();

	public float getYSpeed();

	public boolean reachedEnd(float curXPosition);

	public float updateXPosition();

	public float updateYPosition();
}
