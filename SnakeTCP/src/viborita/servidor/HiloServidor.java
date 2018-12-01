package viborita.servidor;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

import com.fasterxml.jackson.databind.ObjectMapper;

import viborita.entidades.BaseDatos;
import viborita.entidades.PaqueteEnvio;
import viborita.entidades.PaqueteSalas;
import viborita.entidades.PaqueteUsuario;
import viborita.entidades.Usuario;
import viborita.enums.EstadoUsuarioEnum;
import viborita.mapper.JSONMapper;

public class HiloServidor implements Runnable {

	private Socket socketCliente;
	private DataInputStream entrada;
	private DataOutputStream salida;
	private static BaseDatos bd = new BaseDatos();
	private static ArrayList<String> salasActivas;
	private static ArrayList<String> duenios;
	private static ArrayList<String> descripcion;
	
	public HiloServidor(Socket socket) {
		this.salasActivas = new ArrayList<>();
		this.duenios = new ArrayList<>();
		this.descripcion = new ArrayList<>();
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
		//Usuario user = new Usuario();
		PaqueteUsuario paquete = new PaqueteUsuario();
		try {
			System.out.println("Convirtiendo json a USUARIO");
			ObjectMapper om = new ObjectMapper();
			//user = om.readValue(jsonEntrada, Usuario.class);
			paquete = om.readValue(jsonEntrada, PaqueteUsuario.class);
		}catch (Exception e) {
			System.out.println("ERROR AL CONVERTIR JSON A USUARIO");
			e.printStackTrace();
		}
		
		switch (paquete.getAccionCliente()) {
			case LOGIN:{
				Usuario user = new Usuario(paquete.getUsername(), paquete.getPassword());
				EstadoUsuarioEnum loginUsuarioEstado = bd.validarUsuario(user);
				PaqueteEnvio mensaje = new PaqueteEnvio(user.getUsuario(), null,loginUsuarioEstado);
				return mensaje.convertirDeObjAJSON();
				
			}
			case REGISTRO:{
				Usuario user = new Usuario(paquete.getUsername(), paquete.getPassword());
				EstadoUsuarioEnum crearUsuarioEstado = bd.crearUsuario(user);
				return JSONMapper.fromObjectToJSON(crearUsuarioEstado);
				
			}
			case OBTENER_SALAS:{
				PaqueteSalas mensaje = new PaqueteSalas(this.salasActivas, this.duenios, EstadoUsuarioEnum.REGISTRO_OK);
				return mensaje.convertirDeObjAJSON();
				
			}
			case CREAR_SALA:{
				String nombre = paquete.getNombreSala();
				for (String salaNombre : salasActivas) {
					if (nombre.equals(salaNombre)) {
						return JSONMapper.fromObjectToJSON(EstadoUsuarioEnum.SALA_REPETIDA);
					}
				}
				salasActivas.add(nombre);
				duenios.add(paquete.getUsername());
				descripcion.add(paquete.getDescSala());
				return JSONMapper.fromObjectToJSON(EstadoUsuarioEnum.CREACION_OK);
				
			}
			case ELIMINAR_SALA:{
				int indiceSala = salasActivas.indexOf(paquete.getNombreSala());
				System.out.println(indiceSala + " indice sala");
				salasActivas.remove(indiceSala);
				duenios.remove(indiceSala);
				descripcion.remove(indiceSala);
				return JSONMapper.fromObjectToJSON(EstadoUsuarioEnum.CREACION_OK);
				
			}
			default:
				break;
		}
//		if(user.getAccionCliente().equals(EstadoUsuarioEnum.LOGIN)) {
//			EstadoUsuarioEnum loginUsuarioEstado = bd.validarUsuario(user);
//			PaqueteEnvio mensaje = new PaqueteEnvio(user.getUsuario(), user.getContrasenia(),loginUsuarioEstado);///JSONMapper.fromObjectToJSON(loginUsuarioEstado);//paqueteEnvio (Usuario,pedido,respuesta,salas)
//			return mensaje.convertirDeObjAJSON();
//		} else if(user.getAccionCliente().equals(EstadoUsuarioEnum.REGISTRO)) {
//			EstadoUsuarioEnum crearUsuarioEstado = bd.crearUsuario(user);
//			return JSONMapper.fromObjectToJSON(crearUsuarioEstado);
//		}else if(user.getAccionCliente().equals(EstadoUsuarioEnum.OBTENER_SALAS)) {
//			Usuario per = new Usuario("room","numero1");
//			per.setAccionCliente(EstadoUsuarioEnum.REGISTRO_OK);
//			
//					this.salasActivas.add("Asgard");
//					this.duenios.add("Odin");
//			PaqueteSalas mensaje = new PaqueteSalas(this.salasActivas, this.duenios, EstadoUsuarioEnum.REGISTRO_OK);///JSONMapper.fromObjectToJSON(loginUsuarioEstado);//paqueteEnvio (Usuario,pedido,respuesta,salas)
//			return mensaje.convertirDeObjAJSON();
//		}
		
		return jsonEntrada;
	}

}
