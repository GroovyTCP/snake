package viborita.app;

import java.io.IOException;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import viborita.cliente.Cliente;
import viborita.cliente.HiloCliente;
import viborita.entidades.Login;
import viborita.servidor.ConfiguracionServidor;

public class AppCliente {

	public static void main(String[] args) throws UnsupportedAudioFileException, IOException, LineUnavailableException {

		Cliente cliente = new Cliente(ConfiguracionServidor.HOST, ConfiguracionServidor.PUERTO);
		HiloCliente connectionThread = new HiloCliente(cliente);
		connectionThread.start();

		Login login = new Login(connectionThread);
		login.run();
	}

}
