package viborita.entidades;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import viborita.enums.EstadoUsuarioEnum;

import java.util.List;

import javax.persistence.Query;
import javax.swing.JOptionPane;

public class BaseDatos {
	
	private Configuration cfg;
	private SessionFactory factory;
	private Session session;
	
	public BaseDatos() {
		
		cfg = new Configuration();
		cfg.configure("hibernate.cfg.xml");
		
		factory = cfg.buildSessionFactory();
		session = factory.openSession();
		
	}
	
	public void close() {
		
		session.close();
		factory.close();	
		
	}
	
	public EstadoUsuarioEnum crearUsuario(Usuario u) {
		
		Transaction tx = session.beginTransaction();
		try {
			
			Query q = session.getNamedQuery("NomUsuario");
			@SuppressWarnings("unchecked")
			List<String> listUsuarios = q.getResultList();
			for(String n: listUsuarios) 
				if(n.compareTo(u.getUsuario()) == 0) {
					JOptionPane.showMessageDialog(null,"Nombre usuario esta en uso","Error nombre usuario",JOptionPane.ERROR_MESSAGE);
					tx.rollback();
					return EstadoUsuarioEnum.USUARIO_EXISTENTE;
				}
			
			if(u.getContrasenia().length() < 5) {
				JOptionPane.showMessageDialog(null,"Ingrese una contraseña de al menos 5 caracteres","Error contraseña",JOptionPane.ERROR_MESSAGE);
				tx.rollback();
				return EstadoUsuarioEnum.PW_MENOR_DE_CINCO_CHAR;
			}
			
			session.save(u);	
//			JOptionPane.showMessageDialog(null,"Usuario generado con exito","Usuario generado",JOptionPane.INFORMATION_MESSAGE);
			
			tx.commit();		
			
		} catch (HibernateException e2) {
			if (tx != null)
				tx.rollback();
			e2.printStackTrace();
		}
		
		return EstadoUsuarioEnum.REGISTRO_OK;
	}
	
	public EstadoUsuarioEnum validarUsuario(Usuario u) {
		
		if(u.getUsuario().length() == 0) {
			return EstadoUsuarioEnum.USUARIO_INVALIDO;
		}
		
		if(u.getContrasenia().length() < 5) {
			return EstadoUsuarioEnum.PW_MENOR_DE_CINCO_CHAR;
		}
		
		Transaction tx = session.beginTransaction();
		try {
			
			Query q = session.createQuery("Select u from Usuario u");
			@SuppressWarnings("unchecked")
			List<Usuario> listaUsuarios = q.getResultList();
			
			for(Usuario o : listaUsuarios) {
				if(u.getContrasenia().compareTo(o.getContrasenia()) == 0 && u.getUsuario().compareTo(o.getUsuario()) == 0) {
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
	
}
