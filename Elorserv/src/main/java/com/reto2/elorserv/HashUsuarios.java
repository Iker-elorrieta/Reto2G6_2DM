package com.reto2.elorserv;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import metodos.HibernateUtil;
import modelo.Users;

// EJECUTAR UNA SOLA VEZ PARA HASHEAR USUARIOS AL IMPORTAR BD
public class HashUsuarios {
	public static void main(String[] args) {
		SessionFactory sesion = HibernateUtil.getSessionFactory();
		Session session = sesion.openSession();

		Transaction tx = session.beginTransaction();
		String hql = "from Users";
		List<Users> u = session.createQuery(hql, Users.class).list();
		for (Users usuario : u) {
			usuario.setUsername(hashear(usuario.getUsername()));
			usuario.setPassword(hashear(usuario.getPassword()));
			session.merge(usuario);
		}
		tx.commit();

	}

	public static String hashear(String contrasena) {
		try {
			MessageDigest md = MessageDigest.getInstance("SHA");
			byte bytes[] = contrasena.getBytes();
			md.update(bytes);

			byte resumenBytes[] = md.digest();
			contrasena = new String(resumenBytes);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return contrasena;
	}

}
