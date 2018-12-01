package viborita.repositorio;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public abstract class BaseDatosBase {

	protected Configuration cfg;
	protected SessionFactory factory;
	protected Session session;

	public BaseDatosBase() {

		cfg = new Configuration();
		cfg.configure("hibernate.cfg.xml");

		factory = cfg.buildSessionFactory();
		session = factory.openSession();
	}

	public void close() {

		session.close();
		factory.close();

	}

	@SuppressWarnings("unchecked")
	protected <T> List<T> buscarTodos(Class<T> clase, String query) {
		
		Transaction tx = session.beginTransaction();
		
		try {
			
			Query q = session.createQuery(query, clase);
			return q.getResultList();
			
		} catch (HibernateException hibernateException) {
			
			if (tx != null)
				tx.rollback();
			
			throw new RuntimeException(hibernateException);
		}
	}

}
