package com.reto2.elorserv;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.reto2.elorserv.modelo.Users;


// EJECUTAR UNA SOLA VEZ PARA HASHEAR USUARIOS AL IMPORTAR BD
public class HashUsuarios {
	public static void main(String[] args) {
		SessionFactory sesion = HibernateUtil.getSessionFactory();
		Session session = sesion.openSession();

		Transaction tx = session.beginTransaction();
		String hql = "from Users";
		List<Users> u = session.createQuery(hql, Users.class).list();
		for (Users usuario : u) {
			usuario.setUsername(usuario.cifrar(usuario.getUsername()));
			usuario.setPassword(usuario.cifrar(usuario.getPassword()));
			session.merge(usuario);
		}
		tx.commit();

	}

}
