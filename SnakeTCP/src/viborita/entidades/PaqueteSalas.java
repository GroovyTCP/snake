package viborita.entidades;

import java.io.Serializable;
import java.util.ArrayList;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import viborita.enums.EstadoUsuarioEnum;
import viborita.mapper.JSONMapperInterface;

public class PaqueteSalas implements JSONMapperInterface, Serializable {

	
	private static final long serialVersionUID = 7065266977120038678L;
	
	private ArrayList<String> salasActivas;
	private ArrayList<String> usuarioDuenio;
	private EstadoUsuarioEnum accionCliente;
	
	public PaqueteSalas() {
		
	}
	
	public PaqueteSalas(ArrayList<String> salasActivas, ArrayList<String> usuarioDuenio) {
		this.salasActivas = salasActivas;
		this.usuarioDuenio = usuarioDuenio;
	}
	
	public PaqueteSalas(ArrayList<String> salasActivas, ArrayList<String> usuarioDuenio, EstadoUsuarioEnum accionCliente) {
		this.salasActivas = salasActivas;
		this.usuarioDuenio = usuarioDuenio;
		this.accionCliente = accionCliente;
	}
	
	@Override
	public String convertirDeObjAJSON() {
		ObjectMapper objM = new ObjectMapper();
		String json = "";
		try {
			json = objM.writeValueAsString((PaqueteSalas)this);
		} catch (JsonProcessingException e) {
			System.out.println("Error al parsear object Usuario to JSON");
			e.printStackTrace();
		}
		return json;
	}



	public ArrayList<String> getSalasActivas() {
		return salasActivas;
	}

	public void setSalasActivas(ArrayList<String> salasActivas) {
		this.salasActivas = salasActivas;
	}

	public EstadoUsuarioEnum getAccionCliente() {
		return accionCliente;
	}

	public void setAccionCliente(EstadoUsuarioEnum accionCliente) {
		this.accionCliente = accionCliente;
	}

	public ArrayList<String> getUsuarioDuenio() {
		return usuarioDuenio;
	}

	public void setUsuarioDuenio(ArrayList<String> usuarioDuenio) {
		this.usuarioDuenio = usuarioDuenio;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}