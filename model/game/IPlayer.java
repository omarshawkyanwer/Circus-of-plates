package model.game;

import java.awt.Graphics2D;
import java.awt.Image;
import java.util.ArrayList;

import model.levels.ILevel;
import model.shapes.Shape;

public interface IPlayer {

	public void drawPiles(Graphics2D g);

	public Image getImage();

	public IPile getLeftPile();

	public ILevel getLevel();

	public String getName();

	public IPile getRightPile();

	public int getScore();

	public float getX();

	public void reset(int x, int y, int width);

	public void setDrops(ArrayList<Shape> drops);

	public void setImage(Image playerImage);

	public void setLeftPile(IPile leftPile);

	public void setLevel(ILevel level);

	public void setName(String name);

	public void setRightPile(IPile rightPile);

	public void setScore(int score);

	public void setX(float x);

	public void update();

}
