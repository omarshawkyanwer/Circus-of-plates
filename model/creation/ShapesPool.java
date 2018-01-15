package model.creation;

import java.util.ArrayList;

import model.shapes.Shape;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ShapesPool implements IShapesPool {

	private static ShapesPool poolObject;

	public static ShapesPool getInstance(
			final ArrayList<Class<Shape>> loadedShape) {
		if (poolObject == null) {
			poolObject = new ShapesPool(loadedShape);
		}
		return poolObject;
	}

	private IRandomShapeCreator randomShapeGenerator;
	private ArrayList<Shape> reusableShapes;
	private final Logger log = LogManager.getLogger(getClass());

	private ShapesPool(final ArrayList<Class<Shape>> loadedShape) {
		reusableShapes = new ArrayList<Shape>();
		randomShapeGenerator = new RandomShapeCreator(loadedShape);
	}

	@Override
	public Shape acquireShape() {
		log.info("Shape acquired from pool.");
		Shape toBeGiven = null;
		if (hasShape()) {
			toBeGiven = reusableShapes.get(0);
			reusableShapes.remove(0);
		} else {
			toBeGiven = randomShapeGenerator.getRandomShape();
		}
		return toBeGiven;
	}

	@Override
	public boolean hasShape() {
		return reusableShapes.size() > 0;
	}

	@Override
	public void releaseShape(final Shape releasedShape) {
		log.info("Shape released to pool.");
		reusableShapes.add(releasedShape);
	}
}
