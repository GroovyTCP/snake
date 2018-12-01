package viborita.entidades;

import java.util.ArrayList;

public class Sala {

	public final static int CANT_MAX_JUGADORES = 4;

	private String nombre;
	private ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
	private Usuario admin;

	public Sala(String nombreSala, Usuario admin) {
		this.nombre = nombreSala;
		this.admin = admin;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public ArrayList<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(ArrayList<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public Usuario getAdmin() {
		return admin;
	}

	public void setAdmin(Usuario admin) {
		this.admin = admin;
	}

}
