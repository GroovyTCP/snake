package viborita.servidor.interpretesrequests;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import viborita.cliente.ServerRequest;
import viborita.cliente.ServerResponse;
import viborita.enums.EstadoUsuarioEnum;

public abstract class InterpreteRequests {

	protected ObjectMapper om = new ObjectMapper();

	public abstract boolean soporta(ServerRequest request);

	public abstract ServerResponse procesar(ServerRequest request) throws IOException;

	protected boolean validoParaPath(ServerRequest request, EstadoUsuarioEnum path) {
		return request != null && request.getPath() != null && request.getPath().equals(path.name());
	}

	protected <T> T requestComo(ServerRequest request, Class<T> clase) throws JsonParseException, JsonMappingException, IOException {
		return om.readValue(request.getBody(), clase);
	}
}
