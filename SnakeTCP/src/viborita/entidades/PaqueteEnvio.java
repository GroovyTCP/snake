package viborita.entidades;

import java.io.Serializable;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import viborita.enums.EstadoUsuarioEnum;
import viborita.mapper.JSONMapperInterface;

public class PaqueteEnvio implements JSONMapperInterface, Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3676514245879764079L;
	private String usuario;
	private String contrasenia;
	private EstadoUsuarioEnum accionCliente;
	
	public PaqueteEnvio() {
		
	}
	
	public PaqueteEnvio(String usuario, String pass) {
		this.usuario = usuario;
		this.contrasenia = pass;
	}
	
	public PaqueteEnvio(String usuario, String pass, EstadoUsuarioEnum accionCliente) {
		this.usuario = usuario;
		this.contrasenia = pass;
		this.accionCliente = accionCliente;
	}
	
	@Override
	public String convertirDeObjAJSON() {
		ObjectMapper objM = new ObjectMapper();
		String json = "";
		try {
			json = objM.writeValueAsString((PaqueteEnvio)this);
		} catch (JsonProcessingException e) {
			System.out.println("Error al parsear object Usuario to JSON");
			e.printStackTrace();
		}
		return json;
	}
	
	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getContrasenia() {
		return contrasenia;
	}

	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}

	public EstadoUsuarioEnum getAccionCliente() {
		return accionCliente;
	}

	public void setAccionCliente(EstadoUsuarioEnum accionCliente) {
		this.accionCliente = accionCliente;
	}
	
}