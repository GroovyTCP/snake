package viborita.servidor;

import java.util.Map;

import viborita.conexion.UpdateVivora;
import viborita.entidades.Vibora;

public class SnakePit {

	private static SnakePit snakePit;

	private Map<String, Map<String, Vibora>> viborasPorSalaPorUsuario;

	public static synchronized SnakePit getInstance() {
		if (snakePit == null)
			snakePit = new SnakePit();
		return snakePit;
	}

	public synchronized Map<String, Vibora> revolver(UpdateVivora update) {
		if (update == null || update.getSala() == null)
			throw new RuntimeException("Debe ingresar a una sala para poder jugar");

		Map<String, Vibora> viborasEnLaSala = viborasPorSalaPorUsuario.get(update.getSala());

		// si la vibora es null, entonces solo devuelvo pero no updateo
		if (update.getVibora() != null) {
			// actualiza el estado de la vibora en base a lo que nos avise el
			// usuario, y devuelve todas las vivoras que haya en el pozo para la
			// sala actual
			String usuarioAsociado = update.getUsername();
			if (usuarioAsociado == null)
				throw new RuntimeException("Una serpiente salvaje aparece");

			viborasEnLaSala.put(usuarioAsociado, update.getVibora());
		}

		return viborasEnLaSala;
	}

}
