package model.shapes;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

public class Plate extends Shape {

	public Plate(final Color color) {
		super(color);
		w = 45;
		h = 10;
		frame = new Rectangle2D.Float(x, y, w, h);
	}

	@Override
	public Shape clone() {
		Plate clone = new Plate(color);
		clone.setX(x);
		clone.setY(y);
		clone.setMotionStrategy(getMovingStrategy());
		return clone;
	}

	@Override
	public void draw(final Graphics2D g) {
		int[] xPoints = { (int) x, (int) (x + w), (int) (x + 30),
				(int) (x + 15) };
		int[] yPoints = { (int) y, (int) y, (int) (y + h), (int) (y + h) };
		g.setColor(color);
		g.fillPolygon(xPoints, yPoints, 4);
	}

}
