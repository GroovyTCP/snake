package viborita.servidor.interpretesrequests;

import java.io.IOException;

import viborita.conexion.ServerRequest;
import viborita.conexion.ServerResponse;
import viborita.entidades.Usuario;
import viborita.enums.EstadoUsuarioEnum;
import viborita.repositorio.BaseDatos;
import viborita.servidor.AdministradorDeSesiones;

public class InterpreteRequestLogin extends InterpreteRequests {

	public boolean soporta(ServerRequest request) {
		return validoParaPath(request, EstadoUsuarioEnum.LOGIN);
	}

	@Override
	public ServerResponse procesar(ServerRequest request) throws IOException {
		Usuario usuario = requestComo(request, Usuario.class);
		EstadoUsuarioEnum estado = BaseDatos.getInstance().validarUsuario(usuario);
		if (estado == EstadoUsuarioEnum.LOGIN_OK) {
			AdministradorDeSesiones.getInstance().vincularUsuario(usuario.getUsuario(), Thread.currentThread());
			return new ServerResponse(200, null);
		} else {
			return new ServerResponse(401, om.writeValueAsString(estado));
		}
	}

}
