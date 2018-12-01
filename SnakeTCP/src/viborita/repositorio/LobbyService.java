package viborita.repositorio;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
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

	public void crearSala(Sala nuevaSala) {
		if (nuevaSala == null || nuevaSala.getNombre() == null || nuevaSala.getNombre().trim().equals(""))
			throw new NullPointerException("El nombre de la sala no puede estar vacio");

		if (salas.containsKey(nuevaSala.getNombre()))
			throw new RuntimeException("Ya existe una sala con ese nombre");

		if (nuevaSala.getAdmin() == null)
			throw new RuntimeException("La nave necesita un capitan");

		if (nuevaSala.getUsuarios() == null)
			nuevaSala.setUsuarios(new HashSet<>());
		nuevaSala.getUsuarios().add(nuevaSala.getAdmin());

		salas.put(nuevaSala.getNombre(), nuevaSala);
	}

}
