package com.reto2.elorserv;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import modelo.Users;


// EJECUTAR UNA SOLA VEZ PARA HASHEAR USUARIOS AL IMPORTAR BD (directamente del Moodle)
public class HashUsuarios {
	public static void main(String[] args) {
		SessionFactory sesion = HibernateUtil.getSessionFactory();
		Session session = sesion.openSession();

		Transaction tx = session.beginTransaction();
		String hql = "from Users";
		List<Users> u = session.createQuery(hql, Users.class).list();
		for (Users usuario : u) {
			usuario.setUsername(Users.cifrar(usuario.getUsername(), Users.TIPO_SERVIDOR));
			usuario.setPassword(Users.cifrar(usuario.getPassword(), Users.TIPO_SERVIDOR));
			session.merge(usuario);
		}
		tx.commit();

	}

}
