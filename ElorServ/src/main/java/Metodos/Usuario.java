package Metodos;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Modelo.Users;
@RestController
@RequestMapping("/usuarios")
public class Usuario {
	public Users usuario;

	public void registro() {
	}
	
	@PostMapping
	public String iniciarSesion(String username, String contrasena) {
		String respuesta = "";
		if (username.trim().isEmpty() || contrasena.trim().isEmpty()) {
			respuesta = "Error: Usuario o contraseña incorrectos.";
		} else {
			SessionFactory sesion = HibernateUtil.getSessionFactory();
			Session session = sesion.openSession();
			contrasena = hashearContrasena(contrasena);
			String hql = "from users where username = ?1 and contrasena = ?2";
			Query<Users> q = session.createQuery(hql, Users.class);
			q.setParameter(1, username);
			q.setParameter(2, contrasena);
			Users fila = q.uniqueResult();
			if (fila != null) {
				respuesta = "Error: Usuario o contraseña incorrectos.";
			} else {
				usuario = fila;
				respuesta = "login_correcto";
			}
		}
		return respuesta;
	}

	public String hashearContrasena(String contrasena) {
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
