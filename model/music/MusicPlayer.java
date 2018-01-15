package model.music;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class MusicPlayer {

	public MusicPlayer(){
		try {
			AudioInputStream audioIn = AudioSystem.getAudioInputStream(getClass().getResource("/model/music/mario.wav"));
			Clip clip = AudioSystem.getClip();
			clip.open(audioIn);
			clip.loop(Clip.LOOP_CONTINUOUSLY);
		} catch (Exception e){
		}
	}

}
