package model.game;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import model.levels.ILevel;
import model.levels.Newbie;
import model.shapes.Shape;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import eg.edu.alexu.csd.oop.view.GameScreen;

public class Player implements IPlayer, KeyListener {

	private IPile leftPile, rightPile;
	private int id;
	private float x;
	private ILevel level;
	private final float boardWidth;
	private int score;
	private ArrayList<Shape> drops;
	private final float playerWidth;
	private boolean leftPressed, rightPressed;
	private GameScreen gameScreen;
	private Image image;
	private String name;
	private final Logger log = LogManager.getLogger(getClass());

	public Player(final int id, final float x, final float y,
			final float width, final float boardWidth,
			final ArrayList<Shape> drops, final GameScreen gameScreen) {
		this.gameScreen = gameScreen;
		this.drops = drops;
		this.level = new Newbie();
		this.id = id;
		this.x = x;
		leftPile = new Pile(y);
		rightPile = new Pile(y);
		playerWidth = width;
		this.boardWidth = boardWidth;
	}

	@Override
	public void drawPiles(final Graphics2D g) {
		leftPile.draw(g);
		rightPile.draw(g);
	}

	@Override
	public Image getImage() {
		return image;
	}

	@Override
	public IPile getLeftPile() {
		return leftPile;
	}

	@Override
	public ILevel getLevel() {
		return level;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public IPile getRightPile() {
		return rightPile;
	}

	@Override
	public int getScore() {
		return score;
	}

	@Override
	public float getX() {
		return x;
	}

	@Override
	public void keyPressed(final KeyEvent e) {
		if (id == 1) {
			switch (e.getKeyCode()) {
			case KeyEvent.VK_LEFT:
				log.info("Player " + id + " moved left.");
				leftPressed = true;
				break;
			case KeyEvent.VK_RIGHT:
				log.info("Player "+ id + " moved right.");
				rightPressed = true;
				break;
			}
		}

		else if (id == 2) {
			switch (e.getKeyChar()) {
			case 'a':
				log.info("Player " + id + " moved left.");
				leftPressed = true;
				break;
			case 'd':
				log.info("Player " + id + " moved right.");
				rightPressed = true;
				break;
			}
		}
		e.consume();
	}

	@Override
	public void keyReleased(final KeyEvent e) {
		if (id == 1) {
			switch (e.getKeyCode()) {
			case KeyEvent.VK_LEFT:
				leftPressed = false;
				break;
			case KeyEvent.VK_RIGHT:
				rightPressed = false;
				break;
			}
		}

		else if (id == 2) {
			switch (e.getKeyChar()) {
			case 'a':
				leftPressed = false;
				break;
			case 'd':
				rightPressed = false;
				break;
			}
		}
		e.consume();
	}

	@Override
	public void keyTyped(final KeyEvent e) {
		e.consume();
	}

	@Override
	public void reset(final int x, final int y, final int width) {
		this.x = x;
		this.level = new Newbie();
		score = 0;
		leftPile = new Pile(y);
		rightPile = new Pile(y);
	}

	@Override
	public void setDrops(final ArrayList<Shape> drops) {
		this.drops = drops;
	}

	@Override
	public void setImage(final Image playerImage) {
		image = playerImage;
	}

	@Override
	public void setLeftPile(final IPile leftPile) {
		this.leftPile = leftPile;
	}

	@Override
	public void setLevel(final ILevel level) {
		this.level = level;
	}

	@Override
	public void setName(final String name) {
		this.name = name;
	}

	@Override
	public void setRightPile(final IPile rightPile) {
		this.rightPile = rightPile;
	}

	@Override
	public void setScore(final int score) {
		this.score = score;
	}

	@Override
	public void setX(final float x) {
		this.x = x;
	}

	@Override
	public void update() {
		updatePosition();
		leftPile.notifyShapes(x);
		rightPile.notifyShapes(x + playerWidth - 30);
		updateScore();
		if (leftPile.reachedTop() || rightPile.reachedTop()) {
			gameScreen.endGame(id);
		}
	}

	private void updatePosition() {
		if (rightPressed) {
			x = Math.min(x + level.getSpeed(), boardWidth - playerWidth);
		}
		if (leftPressed) {
			x = Math.max(x - level.getSpeed(), 0);
		}
	}

	private void updateScore() {
		if (leftPile.update(drops)) {
			++score;
			log.info("Player " + id + " gained one point.");
		}
		if (rightPile.update(drops)) {
			log.info("Player " + id + " gained one point.");
			++score;
		}

		level = level.upgrade(score);
	}

}
