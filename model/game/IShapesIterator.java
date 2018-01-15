package model.game;

import java.util.Iterator;

import model.shapes.Shape;

public interface IShapesIterator {

	public void addShape(Shape s);

	public Iterator<Shape> createIterator();

	public Shape getShape(final int index);

	public int getSize();

	public boolean isEmpty();

	public void removeShape(final int index);

}
