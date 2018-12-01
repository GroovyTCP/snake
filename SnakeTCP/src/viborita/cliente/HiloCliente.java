package viborita.cliente;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.function.Consumer;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import viborita.enums.EstadoUsuarioEnum;
import viborita.interfaz.SalaInterfaz;

public class HiloCliente extends Thread {

	Cliente cliente;
	DataOutputStream salida;
	SalaInterfaz sala;
	private DataInputStream entrada;
	private ObjectMapper objectMapper;
	public static EstadoUsuarioEnum estadoUser;

	public HiloCliente(Cliente cli) throws IOException {
		this.cliente = cli;
		connectSocket();
		this.objectMapper = new ObjectMapper();
	}

	@Override
	public void run() {
	}

	private void connectSocket() throws IOException {
		this.entrada = new DataInputStream(this.cliente.getSocketCliente().getInputStream());
		this.salida = new DataOutputStream(this.cliente.getSocketCliente().getOutputStream());
	}

	public synchronized void doRequest(ServerRequest request, Consumer<ServerResponse> callback) {
		this.doRequest(request, callback, null);
	}

	public synchronized void doRequest(ServerRequest request, Consumer<ServerResponse> callback, Consumer<Exception> errorCallback) {
		try {
			this.salida.writeUTF(objectMapper.writeValueAsString(request));
			callback.accept(this.recibirData(callback, errorCallback));
		} catch (Exception e) {
			if (errorCallback != null)
				errorCallback.accept(e);
			else {
				ServerResponse response = new ServerResponse();
				response.setStatus(500);
				try {
					response.setBody(objectMapper.writeValueAsString(e));
				} catch (JsonProcessingException e1) {
					response.setBody(e.getMessage());
				}
			}			
		}
	}

	public ServerResponse recibirData(Consumer<ServerResponse> callback, Consumer<Exception> errorCallback)
			throws JsonParseException, JsonMappingException, IOException {

		return this.objectMapper.readValue(entrada.readUTF(), ServerResponse.class);

	}

}
