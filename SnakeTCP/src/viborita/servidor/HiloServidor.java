package viborita.servidor;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import com.fasterxml.jackson.databind.ObjectMapper;

import viborita.entidades.BaseDatos;
import viborita.entidades.Usuario;
import viborita.enums.AccionClienteEnum;

public class HiloServidor implements Runnable {

	private Socket socketCliente;
	private DataInputStream entrada;
	private DataOutputStream salida;
	private BaseDatos bd = new BaseDatos();
	
	public HiloServidor(Socket socket) {
		this.socketCliente = socket;
		
		try {
			System.out.println("Leyendo datos desde servidor");
			entrada = new DataInputStream(socket.getInputStream());
			salida = new DataOutputStream(socket.getOutputStream());
		} catch (IOException e) {
			System.out.println("Error al obtener datos entrada y salida");
			e.printStackTrace();
		}
	}
	
	@Override
	public void run() {
		String jsonEntrada;
		boolean conexionActiva = true;
		
		while(conexionActiva) {
			try {
				System.out.println("Estoy en el RUN de HiloServidor");
				jsonEntrada = this.entrada.readUTF();
				String jsonSalida = recibirDatos(jsonEntrada);
				this.salida.writeUTF(jsonSalida);
			} catch (IOException e) {
				conexionActiva = false;
				e.printStackTrace();
			}
		}
	}

	private String recibirDatos(String jsonEntrada) {
		Usuario user = new Usuario();
		try {
			System.out.println("Convirtiendo json a USUARIO");
			ObjectMapper om = new ObjectMapper();
			user = om.readValue(jsonEntrada, Usuario.class);
		}catch (Exception e) {
			System.out.println("ERROR AL CONVERTIR JSON A USUARIO");
			e.printStackTrace();
		}
		
		
		if(user.getAccionCliente().equals(AccionClienteEnum.LOGIN)) {
			Boolean loginRegistro = bd.validarUsuario(user);
			return loginRegistro.toString();
		} else if(user.getAccionCliente().equals(AccionClienteEnum.REGISTRO)) {
			bd.crearUsuario(user);
		}
		
		return "false";
	}

}
