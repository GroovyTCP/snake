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
			// Creo la nueva sala
			LobbyService.getInstance().crearSala(nuevaSala);
			// Listo todas las salas para actualizar la lista en el cliente
			List<Sala> salas = LobbyService.getInstance().listarSalas();
			return new ServerResponse(200, om.writeValueAsString(salas));
		} catch (Exception e) {
			// Fallo al crear la sala
			// Falto algun dato o el nombre ya existia
			return new ServerResponse(400, e.getMessage());
		}
	}

}
