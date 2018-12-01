package viborita.servidor.interpretesrequests;

import java.io.IOException;

import viborita.cliente.ServerRequest;
import viborita.cliente.ServerResponse;
import viborita.entidades.BaseDatos;
import viborita.entidades.Usuario;
import viborita.enums.EstadoUsuarioEnum;

public class InterpreteRequestLogin extends InterpreteRequests {

	public boolean soporta(ServerRequest request) {
		return validoParaPath(request, EstadoUsuarioEnum.LOGIN);
	}

	@Override
	public ServerResponse procesar(ServerRequest request) throws IOException {
		Usuario usuario = requestComo(request, Usuario.class);
		EstadoUsuarioEnum estado = BaseDatos.getInstance().validarUsuario(usuario);
		if (estado == EstadoUsuarioEnum.LOGIN_OK) {
			return new ServerResponse(200, null);
		} else {
			return new ServerResponse(401, om.writeValueAsString(estado));
		}
	}

}
