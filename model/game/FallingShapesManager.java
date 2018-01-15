package model.game;

import java.util.ArrayList;

import model.creation.IShapesPool;
import model.creation.ShapesPool;
import model.shapes.Shape;
import model.strategy.MovingOnLeftShelfStrategy;
import model.strategy.MovingOnRightShelfStrategy;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class FallingShapesManager implements IFallingShapesManager {

	private ArrayList<Shape> drops;
	private IShapesPool shapesPool;
	private float maxY;
	private final int MAX_DROPS_SIZE = 30;
	private int difficultySpeed;
	private RandomSpeedGenerator randomSpeedGenerator;
	private final Logger log = LogManager.getLogger(getClass());

	public FallingShapesManager(final ArrayList<Shape> drops,
			final ArrayList<Class<Shape>> loadedShape, final float maxY) {
		this.drops = drops;
		shapesPool = ShapesPool.getInstance(loadedShape);
		this.maxY = maxY;
		randomSpeedGenerator = new RandomSpeedGenerator();
	}

	private Shape putNewShapeOnLeftShelf(final Shape newShape) {
		log.info("New shape was put on left shelf.");
		newShape.setX(0);
		newShape.setY(10);
		newShape.setMotionStrategy(new MovingOnLeftShelfStrategy(Math
				.abs(randomSpeedGenerator.getRandomSpeedX()) + difficultySpeed,
				randomSpeedGenerator.getRandomSpeedY() + difficultySpeed));
		return newShape;
	}

	private Shape putNewShapeOnRightShelf(final Shape newShape) {
		log.info("New shape was put on right shelf.");
		newShape.setX(1200 - newShape.getWidth());
		newShape.setY(10);
		newShape.setMotionStrategy(new MovingOnRightShelfStrategy(-Math
				.abs(randomSpeedGenerator.getRandomSpeedX()) - difficultySpeed,
				randomSpeedGenerator.getRandomSpeedY() + difficultySpeed));
		return newShape;
	}

	@Override
	public void setDifficultySpeed(final int difficulty) {
		difficultySpeed = difficulty;
	}

	public void setDrops(final ArrayList<Shape> drops) {
		this.drops = drops;
	}

	@Override
	public void update() {
		if (drops.size() < MAX_DROPS_SIZE) {
			drops.add(putNewShapeOnLeftShelf(shapesPool.acquireShape()));
			drops.add(putNewShapeOnRightShelf(shapesPool.acquireShape()));
		}

		for (int i = 0; i < drops.size(); ++i) {
			if (drops.get(i).getY() > maxY) {
				shapesPool.releaseShape(drops.get(i));
				drops.remove(i--);
			} else {
				drops.get(i).moveShape();
			}
		}
	}
}
