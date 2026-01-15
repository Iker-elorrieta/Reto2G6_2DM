package metodos;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.util.ArrayList;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import modelo.Tipos;
import modelo.Users;

public class Usuario {
	public Users usuario;

	public Users getUsuarioLogged() {
		return Users.getUsuarioPorID(this.usuario.getId());
	} 
	public Users getUsuarioPorID(int id) {
		return Users.getUsuarioPorID(id);
	}
	public ArrayList<Users> getAllUsuarios() {
		return Users.getAllUsuarios();
	}
	
	public boolean cerrarSesion() {
		usuario = null;
		return true;
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
	// Tipos tipo,String email, String username, String contrasena,String nombre, String apellidos, String dni, String direccion, String telefono1, String telefono2
	public Users crearUsuario(Users user) {
	    SessionFactory sesion = HibernateUtil.getSessionFactory();
	    try (Session session = sesion.openSession()) {
	        Transaction tx = session.beginTransaction();

	     
	        // Hash de contraseña
	        user.setUsername(Users.hashear(user.getUsername()));
	        user.setPassword(Users.hashear(user.getPassword()));
	        // Timestamps
	        Timestamp now = new Timestamp(System.currentTimeMillis());
	        user.setCreatedAt(now);
	        user.setUpdatedAt(now);
	        Users u = user;
	        Tipos t = new Tipos(u.getTipos().getId(), u.getTipos().getName(),u.getTipos().getNameEu());
	        session.persist(new Users(u.getId(),u.getEmail(), u.getUsername(), u.getPassword(), u.getNombre(), u.getApellidos(), u.getDni(),
					u.getDireccion(), u.getTelefono1(), u.getTelefono2(), u.getArgazkiaUrl(), u.getCreatedAt(),
					u.getUpdatedAt(),t));
	        tx.commit();
	        return user;
	    } catch (Exception e) {
	        e.printStackTrace();  // <--- Mira exactamente el error aquí
	        throw e;
	    }
	}

}
