package viborita.app;

import java.io.IOException;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import viborita.entidades.Login;

public class AppCliente {

	public static void main(String[] args) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
		Login login = new Login();
		login.run();
	}

}
