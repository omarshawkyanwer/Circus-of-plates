package model.game;

import java.util.ArrayList;
import java.util.Iterator;

import model.shapes.Shape;

public class StackOfShapes implements IShapesIterator {

	private ArrayList<Shape> shapes;

	public StackOfShapes () {
		shapes = new ArrayList<Shape>();
	}

	@Override
	public void addShape(final Shape s) {
		shapes.add(s);
	}

	@Override
	public Iterator<Shape> createIterator() {
		return shapes.iterator();
	}

	@Override
	public Shape getShape(final int index) {
		return shapes.get(index);
	}

	@Override
	public int getSize() {
		return shapes.size();
	}

	@Override
	public boolean isEmpty() {
		return shapes.isEmpty();
	}

	@Override
	public void removeShape(final int index) {
		shapes.remove(index);
	}

}
