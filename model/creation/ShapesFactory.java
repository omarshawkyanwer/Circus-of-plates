package model.creation;

import java.awt.Color;
import java.util.ArrayList;

import model.shapes.Shape;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ShapesFactory implements IShapeFactory {

	private ArrayList<Class<Shape>> loadedShapes;
	private final Logger log = LogManager.getLogger(getClass());

	public ShapesFactory(final ArrayList<Class<Shape>> loadedShapes) {
		this.loadedShapes = loadedShapes;
	}

	@Override
	public Shape getShape(final int type, final Color shapeColor) {
		log.info("New shape created.");
		try {
			return (Shape) loadedShapes.get(type).getConstructors()[0]
					.newInstance(shapeColor);
		} catch (Exception e) {
			return null;
		}
	}
}
