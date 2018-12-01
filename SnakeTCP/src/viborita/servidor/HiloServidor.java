package viborita.servidor;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;
import viborita.entidades.BaseDatos;
import viborita.entidades.PaqueteEnvio;
import viborita.entidades.PaqueteSalas;
import viborita.entidades.PaqueteUsuario;
import viborita.entidades.Sala;
import viborita.entidades.Usuario;
import viborita.enums.EstadoUsuarioEnum;
import viborita.mapper.JSONMapper;
import viborita.mapper.JSONMapperInterface;

public class HiloServidor implements Runnable {

	private Socket socketCliente;
	private DataInputStream entrada;
	private DataOutputStream salida;
	private static BaseDatos bd = new BaseDatos();
	
	
	public static ArrayList<Sala> salasActivas = new ArrayList<>();
	public static ArrayList<String> salasNombresDisponibles = new ArrayList<String>();
	public static Map<String, Socket> mapConectados = new HashMap<>();
	//private static ArrayList<String> usuarioConectados = new ArrayList<>();
	
	public HiloServidor(Socket socket) {
		
		HiloServidor.salasActivas = new ArrayList<>();
		this.socketCliente = socket;
		
		try {
			entrada = new DataInputStream(socketCliente.getInputStream());
			salida = new DataOutputStream(socketCliente.getOutputStream());
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
			System.out.println("ERROR AL CONVERTIR JSON A PAQUETEUSUARIO");
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
				PaqueteSalas mensaje = new PaqueteSalas(salasActivas);
				return mensaje.convertirDeObjAJSON();
				
			}
			case CREAR_SALA:{
				String nombre = paquete.getNombreSala();
				for (Sala salaNombre : salasActivas) {
					if (nombre.equals(salaNombre.getNombreSala())) {
						return JSONMapper.fromObjectToJSON(EstadoUsuarioEnum.SALA_EXISTENTE); //FALTA la ventana, para avisar
					}
				}
				
				mapConectados.put(nombre, socketCliente);
				salasActivas.add(new Sala(nombre, paquete.getDescSala(), paquete.getUsername()));
				return JSONMapper.fromObjectToJSON(EstadoUsuarioEnum.TODO_OK);
				
			}
			case ELIMINAR_SALA:{
				if (salasActivas.size() == 0) {
					return null;
				}
				int indiceSala = 0;
				String nombredelasala = paquete.getNombreSala();
				
				for (int i = 0; i < salasActivas.size(); i++) 
					if(nombredelasala.equals(salasActivas.get(i).getNombreSala())) 
						indiceSala = i;

				salasActivas.remove(indiceSala);
				return JSONMapper.fromObjectToJSON(EstadoUsuarioEnum.TODO_OK);
				
			}
			case DESCONECTAR:{
				String jugador = paquete.getUsername();
				String sala_nombre = paquete.getNombreSala();
				for (Sala sala : salasActivas) {
					if (sala_nombre.equals(sala.getNombreSala())) {
						sala.eliminarJugador(jugador);
					}
				}
				return JSONMapper.fromObjectToJSON(EstadoUsuarioEnum.TODO_OK);
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
