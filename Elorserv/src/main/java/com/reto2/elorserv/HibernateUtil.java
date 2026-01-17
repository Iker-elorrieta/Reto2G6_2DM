package com.reto2.elorserv;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
	private static final SessionFactory sessionFactory = buildSessionFactory();
	private static String DB_HOST = System.getenv().getOrDefault("DB_HOST", "localhost");
	private static int DB_PORT = Integer.parseInt(System.getenv().getOrDefault("DB_PORT", "3306"));
	private static String DB_NAME = System.getenv().getOrDefault("DB_NAME", "elorserv");
	
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
