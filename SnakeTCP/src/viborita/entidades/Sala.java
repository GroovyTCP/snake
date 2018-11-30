package viborita.entidades;

import java.util.ArrayList;

public class Sala {

	public final static int CANT_MAX_JUGADORES = 4; 
	
	private String nombreSala;
	private ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
	private int cantJugadoresActuales = 0;
	private Usuario admin;
	
	public Sala(String nombreSala, Usuario admin) {
		this.nombreSala = nombreSala;
		this.admin = admin;
	}
	
}
