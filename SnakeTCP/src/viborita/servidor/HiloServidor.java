package viborita.servidor;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import viborita.cliente.ServerRequest;
import viborita.cliente.ServerResponse;
import viborita.entidades.BaseDatos;
import viborita.entidades.Usuario;
import viborita.enums.EstadoUsuarioEnum;
import viborita.mapper.JSONMapper;

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
		String svRequest;
		boolean conexionActiva = true;
		ObjectMapper om = new ObjectMapper();
		
		while(conexionActiva) {
			try {
				System.out.println("Estoy en el RUN de HiloServidor");
				svRequest = this.entrada.readUTF();
				ServerResponse svResponse = recibirDatos(svRequest);
				this.salida.writeUTF(om.writeValueAsString(svResponse));
			} catch (IOException e) {
				conexionActiva = false;
				e.printStackTrace();
			}
		}
	}

	private ServerResponse recibirDatos(String jsonEntrada) {
		Usuario user = new Usuario();
		ServerRequest sr = new ServerRequest();
		try {
			System.out.println("Convirtiendo json a USUARIO");
			ObjectMapper om = new ObjectMapper();
			sr = om.readValue(jsonEntrada, ServerRequest.class);
			user = om.readValue(sr.getBody(), Usuario.class);
		}catch (Exception e) {
			System.out.println("ERROR AL CONVERTIR JSON A USUARIO");
			e.printStackTrace();
		}
		
		ServerResponse sResponse = new ServerResponse();
		if(user.getAccionCliente().equals(EstadoUsuarioEnum.LOGIN)) {
			EstadoUsuarioEnum loginUsuarioEstado = bd.validarUsuario(user);
			if(!loginUsuarioEstado.equals(EstadoUsuarioEnum.LOGIN_OK)) {
				sResponse.setStatus(400);
			} else {
				sResponse.setStatus(200);
			}
			user.setAccionCliente(loginUsuarioEstado);
		} else if(user.getAccionCliente().equals(EstadoUsuarioEnum.REGISTRO)) {
			EstadoUsuarioEnum crearUsuarioEstado = bd.crearUsuario(user);
			if(!crearUsuarioEstado.equals(EstadoUsuarioEnum.REGISTRO_OK)) {
				sResponse.setStatus(400);
			} else {
				sResponse.setStatus(200);
			}
			user.setAccionCliente(crearUsuarioEstado);
		}
		
		sResponse.setBody(user.convertirDeObjAJSON());
		return sResponse;
	}

}
