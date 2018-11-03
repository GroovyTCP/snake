package viborita.entidades;

import java.awt.BorderLayout;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import viborita.repositorio.UsuarioService;
import viborita.repositorio.impl.UsuarioServiceImpl;

public class Servidor {

	public static void main(String[] args) {
		MarcoServidor mimarco=new MarcoServidor();
		mimarco.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}	
	
	private int puerto;
	private Socket cliente;
	
	
	public Servidor(int puerto) {
		
		try {
			ServerSocket servidor = new ServerSocket(puerto);
			this.cliente = servidor.accept();
			servidor.close();
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

	public int getPuerto() {
		return puerto;
	}

	public void setPuerto(int puerto) {
		this.puerto = puerto;
	}
	
}

class MarcoServidor extends JFrame implements Runnable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 380699294292670345L;

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
			UsuarioServiceImpl usi = new UsuarioServiceImpl();
			Usuario user = usi.get(usuario);
			if(usuario != null) {
				//Debería devolver la response al cliente para saber que hacer
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
}