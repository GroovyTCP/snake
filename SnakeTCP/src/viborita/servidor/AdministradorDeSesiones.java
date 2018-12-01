package viborita.servidor;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import viborita.entidades.Usuario;
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
	private Map<HiloServidor, Usuario> sesiones = new HashMap<>();
	private Map<Usuario, Set<HiloServidor>> sesionesPorUsuario = new HashMap<>();

	public synchronized Usuario obtenerUsuarioVinculado(HiloServidor hiloServidor) {
		return sesiones.get(hiloServidor);
	}

	public synchronized void desconectar(HiloServidor hiloServidor) {
		// obtiene el usuario vinculado al hilo
		Usuario usuarioVinculado = sesiones.get(hiloServidor);
		// desconecta el hilo
		sesiones.remove(hiloServidor);
		sesionesPorUsuario.get(usuarioVinculado).remove(hiloServidor);
		// se fija si el usuario tiene otro hilo
		// si no lo tiene, entonces deslogueo
		if (sesionesPorUsuario.get(usuarioVinculado).isEmpty()) {
			sesionesPorUsuario.remove(usuarioVinculado);
			desconectar(usuarioVinculado);
		}
	}

	public synchronized void desconectar(Usuario usuarioVinculado) {
		LobbyService.getInstance().dejarTodasLasSalas(usuarioVinculado);
	}

}
