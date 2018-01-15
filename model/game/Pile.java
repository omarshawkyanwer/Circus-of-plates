package model.game;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Iterator;

import model.shapes.NullShape;
import model.shapes.Shape;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Pile implements IPile {

	private StackOfShapes stackOfShapes;
	private float topY;
	private int size;
	private final Logger log = LogManager.getLogger(getClass());

	public Pile(final float initialY) {
		stackOfShapes = new StackOfShapes();
		size = 1;
		topY = initialY;
		Shape s = new NullShape(Color.WHITE);
		s.setY(initialY);
		stackOfShapes.addShape(s);

	}

	private boolean checkLastThree() {
		if (stackOfShapes.getSize() < 3) {
			return false;
		}

		return stackOfShapes.getShape(size - 1).getColor()
				.equals(stackOfShapes.getShape(size - 2).getColor())
				&& stackOfShapes.getShape(size - 2).getColor()
						.equals(stackOfShapes.getShape(size - 3).getColor());
	}

	@Override
	public void draw(final Graphics2D g) {
		Iterator<Shape> it = stackOfShapes.createIterator();
		while (it.hasNext()) {
			it.next().draw(g);
		}
	}

	@Override
	public void notifyShapes(final float newX) {
		Iterator<Shape> it = stackOfShapes.createIterator();
		while (it.hasNext()) {
			it.next().updatePosition(newX);
		}
	}

	@Override
	public boolean reachedTop() {
		return topY < 0;
	}

	@Override
	public void register(final Shape s) {
		s.setY(topY - s.getHeight());
		stackOfShapes.addShape(s);
		topY -= s.getHeight();
		++size;
	}

	@Override
	public void unregister(final int numberOfUnregisters) {
		for (int i = 0; i < numberOfUnregisters; ++i) {
			topY += stackOfShapes.getShape(size - 1).getHeight();
			stackOfShapes.removeShape(--size);
		}
	}

	@Override
	public boolean update(final ArrayList<Shape> drops) {
		for (int i = 0; i < drops.size(); ++i) {
			if (drops.get(i).collide(
					stackOfShapes.getShape(size - 1).getFrame())) {
				log.info("A falling shape intersected with the top of a pile.");
				register(drops.get(i));
				drops.remove(i);
				if (checkLastThree()) {
					unregister(3);
					return true;
				}
				return false;
			}
		}

		return false;
	}

}
