package viborita.entidades;

import java.io.Serializable;
import java.util.ArrayList;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import viborita.enums.EstadoUsuarioEnum;
import viborita.mapper.JSONMapperInterface;

public class PaqueteSalas implements JSONMapperInterface, Serializable {

	
	private static final long serialVersionUID = 1L;
	
	private ArrayList<Sala> salasActivas = new ArrayList<>();
	private EstadoUsuarioEnum accionCliente;
	
	public PaqueteSalas() {
		
	}
	
	public PaqueteSalas(ArrayList<Sala> salasActivas) {
		this.salasActivas = salasActivas;
	}
	
	public PaqueteSalas(ArrayList<Sala> salasActivas, EstadoUsuarioEnum accionCliente) {
		this.salasActivas = salasActivas;
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



	public ArrayList<Sala> getSalasActivas() {
		return salasActivas;
	}

	public void setSalasActivas(ArrayList<Sala> salasActivas) {
		this.salasActivas = salasActivas;
	}

	public EstadoUsuarioEnum getAccionCliente() {
		return accionCliente;
	}

	public void setAccionCliente(EstadoUsuarioEnum accionCliente) {
		this.accionCliente = accionCliente;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}