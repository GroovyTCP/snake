package viborita.cliente;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import viborita.entidades.Usuario;
import viborita.interfaz.SalaInterfaz;

public class HiloCliente implements Runnable {

	Cliente cliente;
	DataOutputStream salida;
	SalaInterfaz sala;
	
	public HiloCliente() {

	}
	
	public HiloCliente(Cliente cli) {
		this.cliente = cli;
		try {
			this.salida = new DataOutputStream(this.cliente.getSocketCliente().getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void run() {
		this.recibirData();
	}
	
	public void enviarData(String json) {
		try {
			this.salida.writeUTF(json);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void recibirData() {
		DataInputStream entrada = null;
		try {
			entrada = new DataInputStream(this.cliente.getSocketCliente().getInputStream());
		} catch (IOException e) {
			System.out.println("Error al obtener datos de entrada hacia el cliente");
			e.printStackTrace();
		}
		
		boolean escuchando = true;
		
		while(escuchando) {
			try {
				if(entrada.available() != 0) {
					String jsonEntrada = entrada.readUTF();
					if(jsonEntrada.equals("true")) {
						try {
							sala = new SalaInterfaz();
							sala.setVisible(true);
						} catch (UnsupportedAudioFileException | LineUnavailableException e) {
							e.printStackTrace();
						}
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}

}
