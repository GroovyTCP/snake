package viborita.cliente;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import com.fasterxml.jackson.databind.ObjectMapper;
import viborita.entidades.PaqueteEnvio;
import viborita.entidades.PaqueteSalas;
import viborita.entidades.PaqueteUsuario;
import viborita.entidades.Usuario;
import viborita.enums.EstadoUsuarioEnum;
import viborita.interfaz.SalaInterfaz;
import viborita.interfaz.SalaV;

public class HiloCliente implements Runnable {

	Cliente cliente;
	DataOutputStream salida;
	DataInputStream entrada;
	SalaInterfaz sala;
	public static EstadoUsuarioEnum estadoUser;
	
	public HiloCliente() {

	}
	
	public HiloCliente(Cliente cli) {
		this.cliente = cli;
		try {
			this.salida = new DataOutputStream(this.cliente.getSocketCliente().getOutputStream());
			this.entrada = new DataInputStream(this.cliente.getSocketCliente().getInputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void run() {
		//this.recibirData();
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
			System.out.println("Error al obtener datos de entrada desde el servidor");
			e.printStackTrace();
		}
		
		boolean escuchando = true;
		
		while(escuchando) {
			try {
				if(entrada.available() != 0) {
					String jsonEntrada = entrada.readUTF();
					
					ObjectMapper om = new ObjectMapper();
					
					
					if(om.readValue(jsonEntrada, EstadoUsuarioEnum.class).equals(EstadoUsuarioEnum.LOGIN_OK)) {
						try {
							sala = new SalaInterfaz(null);
							sala.setVisible(true);
						} catch (UnsupportedAudioFileException | LineUnavailableException e) {
							e.printStackTrace();
						}
					}
					estadoUser = om.readValue(jsonEntrada, EstadoUsuarioEnum.class);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	
	
	public String getUsernameCliente() {
		return cliente.getUsername();
	}

	public void loguear(String paqueteDatos) {

		try {
			PaqueteEnvio pack = null;

			salida.writeUTF(paqueteDatos);
			String jsonEntrada = entrada.readUTF();
			
			ObjectMapper om = new ObjectMapper();
			pack = om.readValue(jsonEntrada, PaqueteEnvio.class);
			
			Usuario per = new Usuario(pack.getUsuario(), pack.getContrasenia());
			cliente.setUsername(per.getUsuario());
			
			estadoUser = pack.getAccionCliente();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	public void registrarUsuario(String paqueteDatos) {

		try {

			salida.writeUTF(paqueteDatos);
			
			String jsonEntrada = entrada.readUTF();
			//EstadoUsuarioEnum respuesta = null;
			
			ObjectMapper om = new ObjectMapper();
			estadoUser = om.readValue(jsonEntrada, EstadoUsuarioEnum.class);
			

		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	

	public PaqueteSalas actulizarSalas() {
		PaqueteSalas sala = null;
		try {

			PaqueteUsuario usuario = new PaqueteUsuario();
			usuario.setAccionCliente(EstadoUsuarioEnum.OBTENER_SALAS);
			salida.writeUTF(usuario.convertirDeObjAJSON());
			String jsonEntrada = entrada.readUTF();
			
			ObjectMapper om = new ObjectMapper();
			sala = om.readValue(jsonEntrada, PaqueteSalas.class);
			estadoUser = EstadoUsuarioEnum.REGISTRO_OK;
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return sala;
	}

	public void crearSala(String paqueteDatos) {
		try {

			salida.writeUTF(paqueteDatos);
			
			String jsonEntrada = entrada.readUTF();
			//EstadoUsuarioEnum respuesta = null;
			
			ObjectMapper om = new ObjectMapper();
			estadoUser = om.readValue(jsonEntrada, EstadoUsuarioEnum.class);
			

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// desconectar a un cliente de la sala
	public void desconectarDeSala(String string) {
		// TODO Auto-generated method stub
		
	}

	public void eliminar_sala(String paqueteDatos) {
		try {

			salida.writeUTF(paqueteDatos);
			String jsonEntrada = entrada.readUTF();
			
			ObjectMapper om = new ObjectMapper();
			estadoUser = om.readValue(jsonEntrada, EstadoUsuarioEnum.class);
			

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	
	
}
