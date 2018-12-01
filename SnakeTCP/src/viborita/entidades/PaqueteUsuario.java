package viborita.entidades;

import java.io.Serializable;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import viborita.enums.EstadoUsuarioEnum;
import viborita.mapper.JSONMapperInterface;


//Para enviarle informacion al servidor
public class PaqueteUsuario implements JSONMapperInterface, Serializable {

	private static final long serialVersionUID = 1L;
	private String username;
	private String password;
	private String nombreSala;
	private String descSala;
	private EstadoUsuarioEnum accionCliente;


	@Override
	public String convertirDeObjAJSON() {
		ObjectMapper objM = new ObjectMapper();
		String json = "";
		try {
			json = objM.writeValueAsString((PaqueteUsuario)this);
		} catch (JsonProcessingException e) {
			System.out.println("Error al parsear object Usuario to JSON");
			e.printStackTrace();
		}
		return json;
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNombreSala() {
		return nombreSala;
	}

	public void setNombreSala(String nombreSala) {
		this.nombreSala = nombreSala;
	}

	public String getDescSala() {
		return descSala;
	}

	public void setDescSala(String descSala) {
		this.descSala = descSala;
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