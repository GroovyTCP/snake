package viborita.servidor;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import viborita.repositorio.LobbyService;

public class AdministradorDeSesiones {

	private static AdministradorDeSesiones instance;

	public static synchronized AdministradorDeSesiones getInstance() {
		if (instance == null)
			instance = new AdministradorDeSesiones();
		return instance;
	}

	// los dos mapas tienen lo mismo entre si
	// pero es para acceder mas rapido en ambos sentidos
	private Map<Thread, String> sesiones = new HashMap<>();
	private Map<String, Set<Thread>> sesionesPorUsuario = new HashMap<>();

	public synchronized String obtenerUsuarioVinculado(Thread hiloServidor) {
		return sesiones.get(hiloServidor);
	}

	public synchronized void desconectar(Thread hiloServidor) {
		// obtiene el usuario vinculado al hilo
		String usuarioVinculado = sesiones.get(hiloServidor);
		// desconecta el hilo
		sesiones.remove(hiloServidor);

		if (usuarioVinculado != null) {
			sesionesPorUsuario.get(usuarioVinculado).remove(hiloServidor);
			// se fija si el usuario tiene otro hilo
			// si no lo tiene, entonces deslogueo
			if (sesionesPorUsuario.get(usuarioVinculado).isEmpty()) {
				sesionesPorUsuario.remove(usuarioVinculado);
				desconectar(usuarioVinculado);
			}
		}
	}

	public synchronized void desconectar(String usuarioVinculado) {
		LobbyService.getInstance().dejarTodasLasSalas(usuarioVinculado);
	}

	public void vincularUsuario(String usuario, Thread currentThread) {
		// vincula el hilo con el nombre de usuario
		sesiones.put(currentThread, usuario);
		// agrega el hilo a los hilos vinculados del usuario
		// podrian llegar a ser mas de uno
		if (!sesionesPorUsuario.containsKey(usuario))
			sesionesPorUsuario.put(usuario, new HashSet<>());
		sesionesPorUsuario.get(usuario).add(currentThread);
	}

}
