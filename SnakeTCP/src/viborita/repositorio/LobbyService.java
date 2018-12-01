package viborita.repositorio;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

import viborita.entidades.Sala;
import viborita.entidades.Usuario;

public class LobbyService {

	private static LobbyService lobbyService;

	protected Map<String, Sala> salas = new HashMap<>();

	public synchronized static LobbyService getInstance() {
		if (lobbyService == null)
			lobbyService = new LobbyService();
		return lobbyService;
	}

	public synchronized List<Sala> listarSalas() {
		return new ArrayList<>(salas.values());
	}

	public synchronized void crearSala(Sala nuevaSala) {
		validarNombreSala(nuevaSala);

		if (salas.containsKey(nuevaSala.getNombre()))
			throw new RuntimeException("Ya existe una sala con ese nombre");

		if (nuevaSala.getAdmin() == null)
			throw new RuntimeException("La nave necesita un capitan");

		if (nuevaSala.getUsuarios() == null)
			nuevaSala.setUsuarios(new HashSet<>());
		nuevaSala.getUsuarios().add(nuevaSala.getAdmin());

		salas.put(nuevaSala.getNombre(), nuevaSala);
	}

	public synchronized void unirseASala(Sala nuevaSala) {
		validarNombreSala(nuevaSala);
		String nombreSala = nuevaSala.getNombre();
		if (!salas.containsKey(nombreSala) || salas.get(nombreSala) == null)
			throw new RuntimeException("No se encuentra la sala seleccionada");

		Sala salaActiva = salas.get(nuevaSala.getNombre());
		if (salaActiva.getUsuarios().size() == Sala.CANT_MAX_JUGADORES)
			throw new RuntimeException("La sala seleccionada ya se encuentra llena");

		// para no agregar otro campo, mandamos al que se quiere unir como admin
		if (salaActiva.getAdmin() == null)
			throw new RuntimeException("Nadie quiere entrar, entonces nadie entra");
		// si el tipito quedo adentro, no hago nada pero tampoco tiro error
		if (!salaContieneUsuario(salaActiva, salaActiva.getAdmin())) {
			salaActiva.getUsuarios().add(nuevaSala.getAdmin());
		}
	}

	protected boolean salaContieneUsuario(Sala sala, Usuario usuario) {
		// Reviso los usuarios de la sala para ver si alguno coincide con el que
		// ya tengo
		// De la sala me quedo a los usuarios; de cada usuario me quedo con el
		// nombre;
		// pregunto si alguno de los nombres coincide con el que quiero
		return sala.getUsuarios().stream().map(Usuario::getUsuario).anyMatch(Predicate.isEqual(usuario.getUsuario()));
	}

	protected void validarNombreSala(Sala sala) {
		if (sala == null || sala.getNombre() == null || sala.getNombre().trim().equals(""))
			throw new NullPointerException("El nombre de la sala no puede estar vacio");
	}
}
