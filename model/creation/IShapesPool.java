package model.creation;

import model.shapes.Shape;

public interface IShapesPool {

	public Shape acquireShape();

	public boolean hasShape();

	public void releaseShape(Shape releasedShape);

}
