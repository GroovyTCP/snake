package viborita.repositorio;

import java.util.List;

import javax.persistence.Query;
import javax.swing.JOptionPane;

import org.hibernate.HibernateException;
import org.hibernate.Transaction;

import viborita.entidades.Usuario;
import viborita.enums.EstadoUsuarioEnum;

public class BaseDatos extends BaseDatosBase {

	private static BaseDatos instancia;

	public EstadoUsuarioEnum crearUsuario(Usuario u) {

		Transaction tx = session.beginTransaction();
		try {

			Query q = session.getNamedQuery("NomUsuario");
			@SuppressWarnings("unchecked")
			List<String> listUsuarios = q.getResultList();
			for (String n : listUsuarios)
				if (n.compareTo(u.getUsuario()) == 0) {
					tx.rollback();
					return EstadoUsuarioEnum.USUARIO_EXISTENTE;
				}

			if (u.getContrasenia().length() < 5) {
				tx.rollback();
				return EstadoUsuarioEnum.PW_MENOR_DE_CINCO_CHAR;
			}
			
			if(u.getUsuario().isEmpty() || u.getContrasenia().isEmpty()) {
				tx.rollback();
				return EstadoUsuarioEnum.CAMPOS_VACIOS;
			}

			session.save(u);

			tx.commit();

		} catch (HibernateException e2) {
			if (tx != null)
				tx.rollback();
			e2.printStackTrace();
		}

		return EstadoUsuarioEnum.REGISTRO_OK;
	}

	public EstadoUsuarioEnum validarUsuario(Usuario u) {

		if (u.getUsuario().length() == 0) {
			return EstadoUsuarioEnum.USUARIO_INVALIDO;
		}

		if (u.getContrasenia().length() < 5) {
			return EstadoUsuarioEnum.PW_MENOR_DE_CINCO_CHAR;
		}

		Transaction tx = session.beginTransaction();
		try {

			Query q = session.createQuery("Select u from Usuario u");
			@SuppressWarnings("unchecked")
			List<Usuario> listaUsuarios = q.getResultList();

			for (Usuario o : listaUsuarios) {
				if (u.getContrasenia().compareTo(o.getContrasenia()) == 0 && u.getUsuario().compareTo(o.getUsuario()) == 0) {
					tx.commit();
					return EstadoUsuarioEnum.LOGIN_OK;
				}
			}
			tx.rollback();
		} catch (HibernateException e2) {
			if (tx != null)
				tx.rollback();
			e2.printStackTrace();
		}

		return EstadoUsuarioEnum.DATOS_INCORRECTOS;

	}

	public synchronized static BaseDatos getInstance() {
		if (instancia == null)
			instancia = new BaseDatos();
		return instancia;
	}

}
