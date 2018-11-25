package viborita.entidades;

public class Usuario {

	private String nombreYApellido;
	private String usuario;
	private String contrasenia;
	
	public Usuario() {
		
	}

	public Usuario(String usuario, String pass) {
		this.usuario = usuario;
		this.contrasenia = pass;
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

}
