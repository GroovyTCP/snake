package viborita.servidor.interpretesrequests;

import java.awt.Color;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import viborita.conexion.ServerRequest;
import viborita.conexion.ServerResponse;
import viborita.entidades.Cabeza;
import viborita.entidades.Direcciones;
import viborita.entidades.Mapa;
import viborita.entidades.Punto;
import viborita.entidades.Usuario;
import viborita.entidades.Vibora;
import viborita.enums.EstadoUsuarioEnum;

public class InterpreteRequestIniciarPartida extends InterpreteRequests {

	private List<Color> colores = new ArrayList<Color>();
	private List<Punto> direcciones = new ArrayList<Punto>();
	
	@Override
	public boolean soporta(ServerRequest request) {
		return validoParaPath(request, EstadoUsuarioEnum.INICIAR_PARTIDA);
	}

	@Override
	public ServerResponse procesar(ServerRequest request) throws IOException {
		this.colores.add(Color.RED);
		this.colores.add(Color.CYAN);
		this.colores.add(Color.WHITE);
		this.colores.add(Color.YELLOW);
		
		this.direcciones.add(Direcciones.ABAJO);
		this.direcciones.add(Direcciones.ARRIBA);
		this.direcciones.add(Direcciones.DERECHA);
		this.direcciones.add(Direcciones.IZQUIERDA);
		@SuppressWarnings("unchecked")
		Set<Usuario> usuarios = requestComo(request, Set.class);
		Vibora[] snakes = new Vibora[usuarios.size()];
		Vibora snake = new Vibora();
		for(int i = 0; i < usuarios.size(); i++) {
			snake = new Vibora(new Cabeza(new Punto(60, 50)), colores.get(i), direcciones.get(0));
			snakes[i] = snake;
		}
		Mapa game = new Mapa(snakes, 600, 500, 100);
		return new ServerResponse(200, om.writeValueAsString(game));
	}

}
