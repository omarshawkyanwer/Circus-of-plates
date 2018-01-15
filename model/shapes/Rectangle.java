package model.shapes;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

public class Rectangle extends Shape {

	private final Rectangle2D rectangle;

	public Rectangle(final Color color) {
		super(color);
		w = 40;
		h = 10;
		rectangle = new Rectangle2D.Float(x, y, w, h);
		frame = new Rectangle2D.Float(x, y, w, h);
	}

	@Override
	public Shape clone() {
		Rectangle clone = new Rectangle(color);
		clone.setX(x);
		clone.setY(y);
		clone.setMotionStrategy(getMovingStrategy());
		return clone;
	}

	@Override
	public void draw(final Graphics2D g) {
		rectangle.setFrame(x, y, w, h);
		g.setColor(color);
		g.fill(rectangle);
	}

}
