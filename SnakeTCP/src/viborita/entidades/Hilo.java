package viborita.entidades;

import java.net.Socket;

public class Hilo implements Runnable {

	private Socket cliente;
	
	public Hilo(Socket cliente) {
		this.cliente = cliente;
	}
	
	@Override
	public void run() {
		
	}

}
