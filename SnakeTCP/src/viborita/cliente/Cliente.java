package viborita.cliente;

import java.net.Socket;

public class Cliente {

	private Socket socketCliente;
	private String username;
	
	public Cliente(String ip, int puerto) {
		username = "";
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

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	
	
}
