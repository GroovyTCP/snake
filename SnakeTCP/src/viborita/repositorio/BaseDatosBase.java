package viborita.repositorio;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
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

}
