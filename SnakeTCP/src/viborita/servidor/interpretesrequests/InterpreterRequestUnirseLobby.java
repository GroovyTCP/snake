package viborita.servidor.interpretesrequests;

import java.io.IOException;
import java.util.List;

import viborita.cliente.ServerRequest;
import viborita.cliente.ServerResponse;
import viborita.entidades.Sala;
import viborita.enums.EstadoUsuarioEnum;
import viborita.repositorio.LobbyService;

public class InterpreterRequestUnirseLobby extends InterpreteRequests {

	@Override
	public boolean soporta(ServerRequest request) {
		return validoParaPath(request, EstadoUsuarioEnum.CREAR_LOBBY);
	}

	@Override
	public ServerResponse procesar(ServerRequest request) throws IOException {
		Sala nuevaSala = requestComo(request, Sala.class);
		try {
			// Me uno a la sala
			LobbyService.getInstance().unirseASala(nuevaSala);
			// Listo todas las salas para actualizar la lista en el cliente
			List<Sala> salas = LobbyService.getInstance().listarSalas();
			return new ServerResponse(200, om.writeValueAsString(salas));
		} catch (Exception e) {
			// Fallo al unirme a la sala
			// Puede que ya este completa
			return new ServerResponse(400, e.getMessage());
		}
	}

}
