package com.reto2.elorserv;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.springframework.beans.factory.annotation.Value;

public class HibernateUtil {
	private static final SessionFactory sessionFactory = buildSessionFactory();
	@Value("${db.host}")
	private static String DB_HOST;
	@Value("${db.port}")
	private static int DB_PORT;
	@Value("${db.name}")
	private static String DB_NAME;
	
	private static SessionFactory buildSessionFactory() {
		try {

			Configuration cfg = new Configuration();
			cfg.configure();
			cfg.setProperty("hibernate.connection.url","jdbc:mysql://" + DB_HOST+ ":" + DB_PORT + "/"
							+ DB_NAME + "?serverTimezone=UTC&useSSL=false");
			return cfg.buildSessionFactory(new StandardServiceRegistryBuilder().configure().build());

		} catch (Throwable ex) {

			System.err.println("Initial SessionFactory creation failed." + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}

	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}

}
