package model.snapshot;

import java.util.ArrayList;

import model.game.IPile;
import model.levels.ILevel;
import model.shapes.Shape;

public class Memento {

	private ArrayList<Shape> drops;

	//timer
	private long currentTime;
	private int duration;

	//score board
	private int[] scores;
	private ILevel[] levels;

	//players
	private String playerOneName, playerTwoName;
	private float playerOneX, playerTwoX;
	private IPile playerOneLeftPile, playerOneRightPile;
	private IPile playerTwoLeftPile, playerTwoRightPile;
	private ILevel playerOneLevel, playerTwoLevel;
	private int playerOneScore, playerTwoScore;

	public Memento(final ArrayList<Shape> drops, final long currentTime, final int duration,
			final int[] scores, final ILevel[] levels, final String playerOneName,
			final String playerTwoName, final float playerOneX, final float playerTwoX,
			final IPile playerOneLeftPile, final IPile playerOneRightPile,
			final IPile playerTwoLeftPile, final IPile playerTwoRightPile,
			final ILevel playerOneLevel, final ILevel playerTwoLevel, final int playerOneScore,
			final int playerTwoScore) {
		this.drops = drops;
		this.currentTime = currentTime;
		this.duration = duration;
		this.scores = scores;
		this.levels = levels;
		this.playerOneName = playerOneName;
		this.playerTwoName = playerTwoName;
		this.playerOneX = playerOneX;
		this.playerTwoX = playerTwoX;
		this.playerOneLeftPile = playerOneLeftPile;
		this.playerOneRightPile = playerOneRightPile;
		this.playerTwoLeftPile = playerTwoLeftPile;
		this.playerTwoRightPile = playerTwoRightPile;
		this.playerOneLevel = playerOneLevel;
		this.playerTwoLevel = playerTwoLevel;
		this.playerOneScore = playerOneScore;
		this.playerTwoScore = playerTwoScore;
	}

	public long getCurrentTime() {
		return currentTime;
	}

	public ArrayList<Shape> getDrops() {
		return drops;
	}

	public int getDuration() {
		return duration;
	}

	public ILevel[] getLevels() {
		return levels;
	}

	public IPile getPlayerOneLeftPile() {
		return playerOneLeftPile;
	}

	public ILevel getPlayerOneLevel() {
		return playerOneLevel;
	}

	public String getPlayerOneName() {
		return playerOneName;
	}

	public IPile getPlayerOneRightPile() {
		return playerOneRightPile;
	}

	public int getPlayerOneScore() {
		return playerOneScore;
	}

	public float getPlayerOneX() {
		return playerOneX;
	}

	public IPile getPlayerTwoLeftPile() {
		return playerTwoLeftPile;
	}

	public ILevel getPlayerTwoLevel() {
		return playerTwoLevel;
	}

	public String getPlayerTwoName() {
		return playerTwoName;
	}

	public IPile getPlayerTwoRightPile() {
		return playerTwoRightPile;
	}

	public int getPlayerTwoScore() {
		return playerTwoScore;
	}

	public float getPlayerTwoX() {
		return playerTwoX;
	}

	public int[] getScores() {
		return scores;
	}

}
