package viborita.servidor;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

import viborita.entidades.Hilo;
import viborita.entidades.Usuario;
import viborita.repositorio.impl.UsuarioServiceImpl;

public class Servidor {

	private static final int CONEXIONES_SIMULTANEAS = 10;
	
	private ArrayList<Socket> clientes;
	private Socket cliente;
	
	public Servidor(int puerto) {
		try {
			ServerSocket servidor = new ServerSocket(puerto);
			System.out.println("Esperando conexiones");
			
			clientes = new ArrayList<Socket>();
			for(int i = 0; i < CONEXIONES_SIMULTANEAS; i++) {
				cliente = servidor.accept();
				System.out.println("Cliente " + (i+1) + " conectado.");
				clientes.add(cliente);
				enviarData();
			}
			System.out.println("Cierro sv");
			servidor.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void enviarData() {
		Thread hilo = new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
					Socket clienteHilo = cliente;
					ObjectMapper om = new ObjectMapper();
					String data = new DataInputStream(clienteHilo.getInputStream()).readUTF();
					Usuario user = om.readValue(data, Usuario.class);

					if(validarUsuario(user.getUsuario(), user.getContrasenia())) {
						new DataOutputStream(clienteHilo.getOutputStream()).writeUTF("true");
					} else {
						new DataOutputStream(clienteHilo.getOutputStream()).writeUTF("false");
						clientes.remove(clienteHilo);
						clienteHilo.close();
						System.err.println("Cliente se desconecto.");
					}
				} catch (Exception e) {
					System.err.println("Se perdio la conexión con el cliente.");
				}
			}

			private boolean validarUsuario(String usuario, String pw) {
				UsuarioServiceImpl us = new UsuarioServiceImpl();
				Usuario user = us.get(usuario);
				
				if(user != null && user.getContrasenia().equals(pw)) {
					return true;
				} 
				
				return false;
			}
		});
		
		hilo.start();
	}

//	public static void main(String[] args) {
//		Servidor sv = new Servidor(8000);
//	}
}
