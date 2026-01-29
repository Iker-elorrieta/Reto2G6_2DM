package com.reto2.elorserv;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class HibernateUtil {

    private static Environment env;
    private static SessionFactory sessionFactory;

    @Autowired
    public HibernateUtil(Environment environment) {
        env = environment;
    }

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            sessionFactory = buildSessionFactory();
        }
        return sessionFactory;
    }

    private static SessionFactory buildSessionFactory() {
        try {
            Configuration cfg = new Configuration();
            cfg.configure("hibernate.cfg.xml");
            cfg.setProperty(
                "hibernate.connection.url",
                "jdbc:mysql://" +  env.getProperty("db.host") + ":" + env.getProperty("db.port") 
                + "/" + env.getProperty("db.name") 
               
            );
            cfg.setProperty("hibernate.connection.username", env.getProperty("db.user"));
          
            String DB_PASSWORD = env.getProperty("db.password");
            if (DB_PASSWORD != null&&!DB_PASSWORD.isEmpty() && !DB_PASSWORD.equals("null")) {
            cfg.setProperty("hibernate.connection.password", DB_PASSWORD);
            }

            return cfg.buildSessionFactory(
                    new StandardServiceRegistryBuilder()
                    .configure()
                        .applySettings(cfg.getProperties())
                        .build()
                );

        } catch (Exception e) {
            System.err.println("Error creando SessionFactory");
            e.printStackTrace();
            throw new ExceptionInInitializerError(e);
        }
    }
}
