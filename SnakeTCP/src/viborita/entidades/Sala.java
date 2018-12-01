package viborita.entidades;

import java.io.Serializable;
import java.util.ArrayList;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import viborita.mapper.JSONMapperInterface;

public class Sala implements JSONMapperInterface, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8053507170207388956L;

	public final static int CANT_MAX_JUGADORES = 4; 
	
	private String nombreSala;
	private ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
	private int cantJugadoresActuales = 0;
	private Usuario admin;
	
	public Sala(String nombreSala, Usuario admin) {
		this.nombreSala = nombreSala;
		this.admin = admin;
	}

	@Override
	public String convertirDeObjAJSON() {
		ObjectMapper objM = new ObjectMapper();
		String json = "";
		try {
			json = objM.writeValueAsString((Sala)this);
		} catch (JsonProcessingException e) {
			System.out.println("Error al parsear object Usuario to JSON");
			e.printStackTrace();
		}
		return json;
	}
	
	public String getNombreSala() {
		return nombreSala;
	}

	public void setNombreSala(String nombreSala) {
		this.nombreSala = nombreSala;
	}

	public ArrayList<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(ArrayList<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public int getCantJugadoresActuales() {
		return cantJugadoresActuales;
	}

	public void setCantJugadoresActuales(int cantJugadoresActuales) {
		this.cantJugadoresActuales = cantJugadoresActuales;
	}

	public Usuario getAdmin() {
		return admin;
	}

	public void setAdmin(Usuario admin) {
		this.admin = admin;
	}

	public static int getCantMaxJugadores() {
		return CANT_MAX_JUGADORES;
	}
	
	
	
}
