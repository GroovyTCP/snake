package viborita.servidor.interpretesrequests;

import java.io.IOException;
import java.util.List;

import viborita.cliente.ServerRequest;
import viborita.cliente.ServerResponse;
import viborita.entidades.Sala;
import viborita.enums.EstadoUsuarioEnum;
import viborita.repositorio.LobbyService;

public class InterpreterRequestLobby extends InterpreteRequests {

	@Override
	public boolean soporta(ServerRequest request) {
		return validoParaPath(request, EstadoUsuarioEnum.LOBBY);
	}

	@Override
	public ServerResponse procesar(ServerRequest request) throws IOException {
		List<Sala> salas = LobbyService.getInstance().listarSalas();
		return new ServerResponse(200, om.writeValueAsString(salas));
	}

}
