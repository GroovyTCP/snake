package viborita.entidades;

import java.awt.BorderLayout;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class Servidor {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		MarcoServidor mimarco=new MarcoServidor();
		
		mimarco.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
	}	
	
	private int puerto;
	
	public Servidor(int puerto) {
		
		try {
			ServerSocket servidor = new ServerSocket(puerto);			
			System.out.println("SERVER INICIADO - Esperando conexiones de clientes ...");
			
			for(int i = 1; i <= 3; i++) {
				Socket cliente = servidor.accept();				
				System.out.println("Se conecto el cliente " + i);				
				DataOutputStream salida = new DataOutputStream(cliente.getOutputStream());				
				salida.writeUTF("Hola cliente " + i);				
				salida.close();
				cliente.close();
			}
			
			servidor.close();			
			System.out.println("SERVER TERMINADO");
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}

class MarcoServidor extends JFrame {
	
	public MarcoServidor(){
		
		setBounds(1200,300,280,350);				
			
		JPanel milamina= new JPanel();
		
		milamina.setLayout(new BorderLayout());
		
		areatexto=new JTextArea();
		
		milamina.add(areatexto,BorderLayout.CENTER);
		
		add(milamina);
		
		setVisible(true);
		
		}
	
	private	JTextArea areatexto;
}