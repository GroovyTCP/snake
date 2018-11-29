package viborita.servidor;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Servidor {

	private ArrayList<Socket> clientes;
	private Socket clienteSocket;
	
	public Servidor(int puerto) {
		ServerSocket servidor = null;
		try {
			servidor = new ServerSocket(puerto);
			System.out.println("Esperando conexiones");
			clientes = new ArrayList<Socket>();
			
			while(true) {
				clienteSocket = servidor.accept();
				System.out.println("Cliente " + clienteSocket.getInetAddress().getHostName() + " conectado.");
				clientes.add(clienteSocket);
				HiloServidor sv = new HiloServidor(clienteSocket);
				Thread hsv = new Thread(sv);
				hsv.start();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			System.out.println("Cierro sv");
			try {
				servidor.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
