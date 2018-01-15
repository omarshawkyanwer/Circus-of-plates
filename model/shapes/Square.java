package model.shapes;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

public class Square extends Shape {

	private Rectangle2D square;

	public Square(final Color color) {
		super(color);
		w = 20;
		h = 20;
		square = new Rectangle2D.Float(x, y, w, h);
		frame = new Rectangle2D.Float(x, y, w, h);
	}

	@Override
	public Shape clone() {
		Square clone = new Square(color);
		clone.setX(x);
		clone.setY(y);
		clone.setMotionStrategy(getMovingStrategy());
		return clone;
	}

	@Override
	public void draw(final Graphics2D g) {
		square.setFrame(x, y, w, h);
		g.setColor(color);
		g.fill(square);
	}
}
