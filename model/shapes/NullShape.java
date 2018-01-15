package model.shapes;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

public class NullShape extends Shape {

	public NullShape(final Color color) {
		super(color);
		h = 1;
		w = 25;
		frame = new Rectangle2D.Float(x, y, w, h);
	}

	@Override
	public Shape clone() {
		return new NullShape(color);
	}

	@Override
	public void draw(final Graphics2D g) {
	}

}
