package model.game;

import java.awt.Graphics2D;

import model.levels.ILevel;

public interface IScoreBoard {

	public void draw(Graphics2D g);

	public ILevel[] getLevels();

	public int[] getScores();

	public int getWinnerId();

	public void setLevels(ILevel[] levels);

	public void setScores(int[] scores);

	public void updatePlayerScore(final int id, final int score, ILevel level);

}
