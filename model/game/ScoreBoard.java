package model.game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;

import model.levels.ILevel;
import model.levels.Newbie;

public class ScoreBoard implements IScoreBoard {

	private final int playersCount;
	private int[] scores;
	private ILevel[] levels;
	private final Image[] playerImages;
	private final Font font;

	public ScoreBoard(final int playersCount, final Image[] playerImages) {
		this.playersCount = playersCount;
		scores = new int[playersCount + 1];
		levels = new ILevel[playersCount + 1];
		for (int i = 1; i <= playersCount; ++i) {
			levels[i] = new Newbie();
		}
		this.playerImages = new Image[playersCount + 1];
		for (int i = 1; i <= playersCount; ++i) {
			this.playerImages[i] = playerImages[i].getScaledInstance(50, 50,
					Image.SCALE_DEFAULT);
		}
		font = new Font("Garamond", Font.BOLD, 30);
	}

	@Override
	public void draw(final Graphics2D g) {
		g.setFont(font);
		int x = 1000;
		for (int i = 1; i <= playersCount; ++i) {
			g.drawImage(playerImages[i], 10 + x, 50, null);
			g.setColor(Color.MAGENTA);
			g.drawString(Integer.toString(scores[i]), 70 + x, 85);
			g.setColor(levels[i].getColor());
			g.drawString(levels[i].getName(), 90 + x, 85);
			x -= 1000;
		}
	}

	public ILevel[] getLevels() {
		return levels;
	}

	public int[] getScores() {
		return scores;
	}

	@Override
	public int getWinnerId() {
		int winnerId = 1;
		boolean draw = false;

		for (int i = 2; i <= playersCount; ++i) {
			if (scores[i] > scores[winnerId]) {
				winnerId = i;
				draw = false;
			} else if (scores[i] == scores[winnerId]) {
				draw = true;
			}
		}

		return draw ? 0 : winnerId;
	}

	public void setLevels(final ILevel[] levels) {
		this.levels = levels;
	}

	public void setScores(final int[] scores) {
		this.scores = scores;
	}

	@Override
	public void updatePlayerScore(final int id, final int score,
			final ILevel level) {
		scores[id] = score;
		levels[id] = level;
	}

}
