package viborita.entidades;

import java.util.HashSet;
import java.util.Set;

public class Sala {

	public final static int CANT_MAX_JUGADORES = 4;

	private String nombre;
	private Set<Usuario> usuarios = new HashSet<>();
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

	public Set<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(Set<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public Usuario getAdmin() {
		return admin;
	}

	public void setAdmin(Usuario admin) {
		this.admin = admin;
	}

}
