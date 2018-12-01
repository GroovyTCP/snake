package viborita.repositorio;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import viborita.entidades.Sala;

public class LobbyService {

	private static LobbyService lobbyService;

	protected Map<String, Sala> salas = new HashMap<>();

	public List<Sala> listarSalas() {
		return new ArrayList<>(salas.values());
	}

	public synchronized static LobbyService getInstance() {
		if (lobbyService == null)
			lobbyService = new LobbyService();
		return lobbyService;
	}

}
