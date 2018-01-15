package model.levels;

import java.awt.Color;

public interface ILevel {

	public Color getColor();

	public String getName();

	public float getSpeed();

	public ILevel upgrade(int score);

}
