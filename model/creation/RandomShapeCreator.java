package model.creation;

import java.awt.Color;
import java.util.ArrayList;

import model.shapes.Shape;

public class RandomShapeCreator implements IRandomShapeCreator {

	private int colorsCount;
	private ArrayList<Color> shapesColors;
	private IShapeFactory shapesFactory;
	private int numberOFLoadedShapes;

	public RandomShapeCreator(final ArrayList<Class<Shape>> loadedShapes) {
		shapesFactory = new ShapesFactory(loadedShapes);
		numberOFLoadedShapes = loadedShapes.size();
		fillColorsArrayList();
	}

	private void fillColorsArrayList() {
		shapesColors = new ArrayList<Color>();
		shapesColors.add(Color.red);
		shapesColors.add(Color.green);
		shapesColors.add(Color.blue);
		shapesColors.add(Color.yellow);
		colorsCount = shapesColors.size();
	}

	public Color getRandomColor() {
		return shapesColors.get((int) (Math.random() * colorsCount));
	}

	@Override
	public Shape getRandomShape() {
		return shapesFactory.getShape(getRandomType(numberOFLoadedShapes), getRandomColor());
	}

	public int getRandomType(final int numberOfLoadedShapes) {
		return (int) (Math.random() * numberOfLoadedShapes);
	}
}
