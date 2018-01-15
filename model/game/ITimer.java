package model.game;

import java.awt.Graphics2D;

public interface ITimer {

	public void draw(Graphics2D g);

	public long getCurrentTime();

	public int getDuration();

	public void setCurrentTime(long currentTime);

	public void setDuration(int duration);

}
