package viborita.entidades;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Musica {

	private Clip clip;
	
	public Musica(String pathCancion) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
		File cancion = new File("recursos\\soundtrack\\"+pathCancion);
		AudioInputStream audio = AudioSystem.getAudioInputStream(cancion);
		clip = AudioSystem.getClip();
		clip.open(audio);	
	}
	
	public void reproducir() {
		this.clip.stop();
		this.clip.flush();
		this.clip.setMicrosecondPosition(0);
		
		this.clip.loop(Clip.LOOP_CONTINUOUSLY);
		
	}
	
	public void detener() {
		this.clip.stop();
		this.clip.flush();
	}
	
}
