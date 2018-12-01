package viborita.servidor.interpretesrequests;

import java.io.IOException;
import java.util.Map;

import viborita.conexion.ServerRequest;
import viborita.conexion.ServerResponse;
import viborita.conexion.UpdateVivora;
import viborita.entidades.Vibora;
import viborita.enums.EstadoUsuarioEnum;
import viborita.servidor.SnakePit;

public class InterpreteSerpiente extends InterpreteRequests {

	@Override
	public boolean soporta(ServerRequest request) {
		return validoParaPath(request, EstadoUsuarioEnum.SNAKE_PING);
	}

	@Override
	public ServerResponse procesar(ServerRequest request) throws IOException {
		UpdateVivora update = requestComo(request, UpdateVivora.class);

		Map<String, Vibora> viborasPorUsuario = SnakePit.getInstance().revolver(update);

		return new ServerResponse(200, om.writeValueAsString(viborasPorUsuario));
	}
}
