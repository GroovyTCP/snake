package viborita.servidor.interpretesrequests;

import java.io.IOException;

import viborita.cliente.ServerRequest;
import viborita.cliente.ServerResponse;
import viborita.entidades.BaseDatos;
import viborita.entidades.Usuario;
import viborita.enums.EstadoUsuarioEnum;

public class InterpreteRequestRegistro extends InterpreteRequests {

	@Override
	public boolean soporta(ServerRequest request) {
		return validoParaPath(request, EstadoUsuarioEnum.REGISTRO);
	}

	@Override
	public ServerResponse procesar(ServerRequest request) throws IOException {
		Usuario usuario = requestComo(request, Usuario.class);
		EstadoUsuarioEnum crearUsuarioEstado = BaseDatos.getInstance().crearUsuario(usuario);

		if (crearUsuarioEstado.equals(EstadoUsuarioEnum.REGISTRO_OK)) {
			return new ServerResponse(200, null);
		} else {
			return new ServerResponse(400, om.writeValueAsString(crearUsuarioEstado));
		}
	}
}
