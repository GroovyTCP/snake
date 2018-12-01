package viborita.servidor;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import viborita.entidades.Usuario;
import viborita.conexion.ServerRequest;
import viborita.conexion.ServerResponse;

public class HiloServidor extends Thread {

	protected Socket socketCliente;
	protected DataInputStream entrada;
	protected DataOutputStream salida;

	private SistemaInterpreteRequests interpreteRequests;

	ObjectMapper om = new ObjectMapper();

	public HiloServidor(Socket socket, SistemaInterpreteRequests interpreteRequests) {
		this.socketCliente = socket;
		this.interpreteRequests = interpreteRequests;

		try {
			System.out.println("Leyendo datos desde servidor");
			this.entrada = new DataInputStream(socket.getInputStream());
			this.salida = new DataOutputStream(socket.getOutputStream());
		} catch (IOException e) {
			System.out.println("Error al obtener datos entrada y salida");
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		boolean conexionActiva = true;
		while (conexionActiva) {
			try {
				System.out.println("Estoy en el RUN de HiloServidor");
				ServerRequest svRequest = leerRequest(this.entrada);
				System.out.println("Se recibio una request : " + svRequest.getPath());
				ServerResponse svResponse = procesarRequest(svRequest);
				escribirRespuesta(svResponse);
			} catch (SocketException e) {
				// Desconexion por corte de socket
				conexionActiva = false;
				processUserDisconnection();
			} catch (IOException e) {
				conexionActiva = false;
				e.printStackTrace();
			}
		}
	}

	private ServerRequest leerRequest(DataInputStream entrada) throws JsonParseException, JsonMappingException, IOException {
		String request = this.entrada.readUTF();
		return this.om.readValue(request, ServerRequest.class);
	}

	public ServerResponse procesarRequest(ServerRequest request) throws IOException {
		return this.interpreteRequests.interpretar(request);
	}

	public void escribirRespuesta(ServerResponse response) throws JsonProcessingException, IOException {
		this.salida.writeUTF(om.writeValueAsString(response));
	}

	private void processUserDisconnection() {
		Usuario usuarioVinculado = AdministradorDeSesiones.getInstance().obtenerUsuarioVinculado(this);
		if (usuarioVinculado != null) {
			System.out.println("[WARN] Se desconecto un hilo, asociado al usuario " + usuarioVinculado.getUsuario());
			AdministradorDeSesiones.getInstance().desconectar(this);
		} else {
			System.out.println("[WARN] Se desconecto un hilo. El hilo no poseia un usuario asociado.");
		}
	}

}
