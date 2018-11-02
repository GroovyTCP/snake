package viborita.entidades;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

public class Cliente {
	
	private int puerto;
	private String ip;
	
	public Cliente(String ip, int puerto) {
		this.ip = ip; 
		this.puerto = puerto;
		
		try {
			Socket cliente = new Socket(ip, puerto);
			
			DataInputStream entrada = new DataInputStream(cliente.getInputStream());
			
			System.out.println(entrada.readUTF());
			
			entrada.close();
			cliente.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public boolean loginCliente() {		
		return false;
	}
}
