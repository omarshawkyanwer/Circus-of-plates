package model.shapes;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

import model.strategy.IMovingStrategy;
import model.strategy.MovingDownStrategy;

public abstract class Shape implements Cloneable {

	protected float x, y, w, h;
	protected Color color;
	protected Rectangle2D frame;
	protected IMovingStrategy movingType;

	public Shape(final Color color) {
		this.color = color;
	}

	@Override
	public abstract Shape clone();

	public boolean collide(final Rectangle2D frame) {
		return this.frame.intersects(frame);
	}

	public abstract void draw(Graphics2D g);

	public Color getColor() {
		return color;
	}

	public Rectangle2D getFrame() {
		return frame;
	}

	public float getHeight() {
		return h;
	}

	public IMovingStrategy getMovingStrategy() {
		return movingType;
	}

	public float getWidth() {
		return w;
	}

	public float getX() {
		return x;
	}

	public float getY() {
		return y;
	}

	public void moveShape() {
		this.x += movingType.updateXPosition();
		this.y += movingType.updateYPosition();
		if (this.movingType.reachedEnd(this.getX())) {
			movingType = new MovingDownStrategy(this.movingType.getXSpeed(),
					this.movingType.getYSpeed());
		}
		frame.setFrame(x, y, w, h);
	}

	public void setMotionStrategy(final IMovingStrategy newStrategy) {
		movingType = newStrategy;
	}

	public void setX(final float x) {
		this.x = x;
	}

	public void setY(final float y) {
		this.y = y;
	}

	public void updatePosition(final float x) {
		this.x = x;
		frame.setFrame(x, y, w, h);
	}
}
