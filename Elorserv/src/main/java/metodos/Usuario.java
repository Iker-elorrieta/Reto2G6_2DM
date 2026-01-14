package metodos;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import modelo.Users;

public class Usuario {
	public Users usuario;

	public Users getUsuarioLogged() {
		return Users.getUsuarioPorID(this.usuario.getId());
	} 
	public Users getUsuarioPorID(int id) {
		return Users.getUsuarioPorID(id);
	}

	public Users iniciarSesion(String username, String contrasena) {
        if (username == null || username.trim().isEmpty()
                || contrasena == null || contrasena.trim().isEmpty()) {
            throw new IllegalArgumentException("Usuario o contraseña incorrectos");
        }
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            Users user = Users.getUsuarioUsernameContraseña(username, contrasena);
            if (user == null) {
                throw new RuntimeException("Credenciales inválidas");
            }
            return user;

        } finally {
            session.close();
        }
    }
	
	public Users registrarUsuario(String username, String contrasena, String email) {
		SessionFactory sesion = HibernateUtil.getSessionFactory();
		Session session = sesion.openSession();
		session.beginTransaction();
		
		Users nuevoUsuario = new Users();
		nuevoUsuario.setUsername(username);
		// contrasena = hashearContrasena(contrasena);
		nuevoUsuario.setPassword(contrasena);
		nuevoUsuario.setEmail(email);
		
		session.persist(nuevoUsuario);
		session.getTransaction().commit();
		session.close();
		sesion.close();
		return nuevoUsuario;
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
