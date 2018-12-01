package viborita.entidades;

import java.io.Serializable;
import java.util.ArrayList;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import viborita.mapper.JSONMapperInterface;

public class Sala implements JSONMapperInterface, Serializable {

	private static final long serialVersionUID = -8053507170207388956L;

	public final static int CANT_MAX_JUGADORES = 4; 
	
	private String nombreSala;
	private String descripcion;
	private ArrayList<String> usuarios = new ArrayList<String>();
	private int cantJugadoresActuales = 0;
	private String admin;
	
	public Sala() {
		
	}
	
	public Sala(String nombreSala, String descripcion, String admin) {
		this.nombreSala = nombreSala;
		this.admin = admin;
		this.setDescripcion(descripcion);
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

	public ArrayList<String> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(ArrayList<String> usuarios) {
		this.usuarios = usuarios;
	}

	public int getCantJugadoresActuales() {
		return cantJugadoresActuales;
	}

	public void setCantJugadoresActuales(int cantJugadoresActuales) {
		this.cantJugadoresActuales = cantJugadoresActuales;
	}

	public String getAdmin() {
		return admin;
	}

	public void setAdmin(String admin) {
		this.admin = admin;
	}

	public static int getCantMaxJugadores() {
		return CANT_MAX_JUGADORES;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public void eliminarJugador(String name) {
		this.usuarios.remove(name);
	}
	
	public void agregarJugador(String name) {
		this.usuarios.add(name);
	}
	
}
