package viborita.repositorio.impl;

import viborita.entidades.Usuario;
import viborita.repositorio.UsuarioService;

public class UsuarioServiceImpl implements UsuarioService{

	@Override
	public Usuario get(String usuario) {
		
		//Obtener usuario desde bd y si existe lo devuelvo, sino null
		
		Usuario user = new Usuario();
		user.setUsuario("admin");
		user.setContrasenia("admin");
		
		return user;
	}

}
