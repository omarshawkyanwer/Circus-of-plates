package model.creation;

import java.awt.Color;

import model.shapes.Shape;

public interface IShapeFactory {

	public Shape getShape(int type, Color shapeColor);

}
