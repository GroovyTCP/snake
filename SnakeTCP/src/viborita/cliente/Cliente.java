package viborita.cliente;

import java.net.Socket;

public class Cliente {

	private Socket socketCliente;
	
	public Cliente(String ip, int puerto) {
		try {
			socketCliente = new Socket(ip, puerto);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Socket getSocketCliente() {
		return socketCliente;
	}

	public void setSocketCliente(Socket socketCliente) {
		this.socketCliente = socketCliente;
	}
	
}
