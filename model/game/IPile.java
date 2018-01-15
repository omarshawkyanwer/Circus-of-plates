package model.game;

import java.awt.Graphics2D;
import java.util.ArrayList;

import model.shapes.Shape;

public interface IPile {

	public void draw(Graphics2D g);

	public void notifyShapes(float newX);

	public boolean reachedTop();

	public void register(Shape s);

	public void unregister(int numberOfUnregisters);

	public boolean update(ArrayList<Shape> drops);

}
