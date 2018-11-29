package viborita.app;

import viborita.servidor.ConfiguracionServidor;
import viborita.servidor.Servidor;

public class AppServidor {

	public static void main(String[] args) {
		Servidor sv = new Servidor(ConfiguracionServidor.PUERTO);
	}
	
}
