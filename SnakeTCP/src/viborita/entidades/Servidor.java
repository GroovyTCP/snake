package viborita.entidades;

import java.awt.BorderLayout;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class Servidor {

	public static void main(String[] args) {
		MarcoServidor mimarco=new MarcoServidor();
		mimarco.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}	
	
	private int puerto;
	private Socket cliente;
	
	
	public Servidor(int puerto) {
		
		try {
			System.out.println("SERVER INICIADO - Esperando conexiones de clientes ...");
			ServerSocket servidor = new ServerSocket(puerto);
			this.cliente = servidor.accept();
			servidor.close();
			System.out.println("SERVER TERMINADO");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Socket getCliente() {
		return cliente;
	}

	public void setCliente(Socket cliente) {
		this.cliente = cliente;
	}
	
}

class MarcoServidor extends JFrame implements Runnable {
	
	public MarcoServidor(){
		
			setBounds(1200,300,280,350);				
				
			JPanel milamina= new JPanel();
			
			milamina.setLayout(new BorderLayout());
			
			areatexto=new JTextArea();
			
			milamina.add(areatexto,BorderLayout.CENTER);
			
			add(milamina);
			
			setVisible(true);
			
			Thread hilo = new Thread(this);
			
			hilo.start();
		
		}
	
	private	JTextArea areatexto;

	@Override
	public void run() {
		
		System.out.println("Server escuchando");
		
		Servidor servidor = new Servidor(8080);
		
		DataInputStream entrada;
		try {
			entrada = new DataInputStream(servidor.getCliente().getInputStream());
			String usuario = entrada.readUTF();
			areatexto.append(usuario + "\n");
			String pass = entrada.readUTF();
			areatexto.append(pass);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
}