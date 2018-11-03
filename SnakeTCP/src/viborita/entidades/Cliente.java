package viborita.entidades;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Cliente {
	
	private int puerto;
	private String ip;
	private Socket cliente;
	
	public Cliente(String ip, int puerto) {
		this.ip = ip; 
		this.puerto = puerto;
		
		try {
			Socket cliente = new Socket(ip, puerto);
			this.cliente = cliente;
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public boolean loginCliente(String usuario, String pass) {
		
		try {
			DataOutputStream salida = new DataOutputStream(this.cliente.getOutputStream());
			salida.writeUTF(usuario);
			salida.writeUTF(pass);
			salida.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return false;
	}
}
