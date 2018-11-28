package viborita.cliente;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import com.fasterxml.jackson.databind.ObjectMapper;

import viborita.entidades.Usuario;

public class Cliente {

	private Socket cliente;
	private Usuario usuario;
	
	public Cliente(String ip, int puerto, Usuario usuario) {
		this.usuario = usuario;
		
		try {
			cliente = new Socket(ip, puerto);
			System.out.println("Conexion de usuario: " + usuario.getUsuario());
			recibirData();
			enviarData();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	private void enviarData() {
		try {
			String data = "usuario;"+usuario.getUsuario()+";pass;"+usuario.getContrasenia();
			new DataOutputStream(cliente.getOutputStream()).writeUTF(data);
			cliente.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void recibirData() {
		Thread hilo = new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					ObjectMapper om = new ObjectMapper();
					String json = om.writeValueAsString(usuario);
					new DataInputStream(cliente.getInputStream()).readUTF();
					System.out.println(json + "\n");
				} catch (Exception e) {
					System.err.println("Se ha desconectado cliente");
				}
			}
		});

		hilo.start();
	}
	
}
