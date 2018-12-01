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

	public synchronized void unirseASala(Sala salaAUnirse) {
		Sala salaActiva = obtenerSalaActiva(salaAUnirse);
		if (salaActiva.getUsuarios().size() == Sala.CANT_MAX_JUGADORES)
			throw new RuntimeException("La sala seleccionada ya se encuentra llena");

		// para no agregar otro campo, mandamos al que se quiere unir como admin
		if (salaActiva.getAdmin() == null)
			throw new RuntimeException("Nadie quiere entrar, entonces nadie entra");
		// si el tipito quedo adentro, no hago nada pero tampoco tiro error
		if (!salaContieneUsuario(salaActiva, salaAUnirse.getAdmin())) {
			salaActiva.getUsuarios().add(salaAUnirse.getAdmin());
		}
	}

	public synchronized void dejarSala(Sala sala) {
		Sala salaActiva = obtenerSalaActiva(sala);

		if (salaContieneUsuario(salaActiva, sala.getAdmin())) {
			// Remuevo al usuario que coincida con el nombre solicitado
			salaActiva.getUsuarios().removeIf(usuario -> usuario.getUsuario().equals(sala.getAdmin().getUsuario()));
		}
		// Si se quiere ir y ya no esta, no hago nada
	}

	public synchronized void dejarTodasLasSalas(Usuario usuario) {
		// el usuario provisto abandona todas las salas en las que se encuentre
		for (Sala sala : salas.values()) {
			// Creo una "peticion de abandono"
			// para poder re-usar el otro metodo
			Sala abandono = new Sala(sala.getNombre(), usuario);
			dejarSala(abandono);
		}
	}

	private Sala obtenerSalaActiva(Sala sala) {
		// Valida que exista una sala, y devuelve la sala en el servidor
		validarNombreSala(sala);
		String nombreSala = sala.getNombre();
		if (!salas.containsKey(nombreSala) || salas.get(nombreSala) == null)
			throw new RuntimeException("No se encuentra la sala seleccionada");

		return salas.get(nombreSala);
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
