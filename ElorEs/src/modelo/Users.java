package modelo;

import java.sql.Timestamp;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import cliente.Cliente;

public class Users implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Integer id;
	private Tipos tipos;
	private String email;
	private String username;
	private String password;
	private String nombre;
	private String apellidos;
	private String dni;
	private String direccion;
	private String telefono1;
	private String telefono2;
	private String argazkiaUrl;
	private Timestamp createdAt;
	private Timestamp updatedAt;
	private String cicloAsignado;

	public Users() {
	}

	public Users(Tipos tipos, String email, String username, String password) {
		this.tipos = tipos;
		this.email = email;
		this.username = username;
		this.password = password;
	}

	public Users(Tipos tipos, String email, String username, String password, String nombre, String apellidos,
			String dni, String direccion, String telefono1, String telefono2, String argazkiaUrl, Timestamp createdAt,
			Timestamp updatedAt) {
		this.tipos = tipos;
		this.email = email;
		this.username = username;
		this.password = password;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.dni = dni;
		this.direccion = direccion;
		this.telefono1 = telefono1;
		this.telefono2 = telefono2;
		this.argazkiaUrl = argazkiaUrl;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Tipos getTipos() {
		return this.tipos;
	}

	public void setTipos(Tipos tipos) {
		this.tipos = tipos;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return this.apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getDni() {
		return this.dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getDireccion() {
		return this.direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getTelefono1() {
		return this.telefono1;
	}

	public void setTelefono1(String telefono1) {
		this.telefono1 = telefono1;
	}

	public String getTelefono2() {
		return this.telefono2;
	}

	public void setTelefono2(String telefono2) {
		this.telefono2 = telefono2;
	}

	public String getArgazkiaUrl() {
		return this.argazkiaUrl;
	}

	public void setArgazkiaUrl(String argazkiaUrl) {
		this.argazkiaUrl = argazkiaUrl;
	}

	public Timestamp getCreatedAt() {
		return this.createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

	public Timestamp getUpdatedAt() {
		return this.updatedAt;
	}

	public void setUpdatedAt(Timestamp updatedAt) {
		this.updatedAt = updatedAt;
	}

	public String getCicloAsignado() {
		return cicloAsignado;
	}

	public void setCicloAsignado(String cicloAsignado) {
		this.cicloAsignado = cicloAsignado;
	}

	public static Object login(Cliente cliente, String username, String password) {
		ArrayList<Object> parametros = new ArrayList<>();
		parametros.add(username);
		parametros.add(password);
		try {
			return cliente.enviarRequest("login", parametros);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error al conectar con el servidor.");
			return null;
		}
	}

	public void desconectar(Cliente cliente) {
		try {
			cliente.enviarRequest("logout", new ArrayList<>());
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error al desconectar del servidor.");
		}
	}

	public ArrayList<Users> getProfesores(Cliente cliente) {
		try {
			Object response = cliente.enviarRequest("get_profesores", new ArrayList<>());
			if (response instanceof ArrayList<?>) {
				ArrayList<Users> profesores = new ArrayList<>();
				for (Object elemento : (ArrayList<?>) response) {
					if (elemento instanceof Users) {
						profesores.add((Users) elemento);
					}
				}
				return

				profesores;
			} else if (response instanceof String) {
			}
		} catch (Exception e) {
		}
		return new ArrayList<>();
	}

	public ArrayList<Users> getAlumnos(Cliente cliente) {
		ArrayList<Object> datos = new ArrayList<>();
		datos.add(getId());
		Object response;
		try {
			response = cliente.enviarRequest("get_alumnos", datos);

			if (response instanceof ArrayList<?>) {
				ArrayList<Users> alumnos = new ArrayList<>();
				for (Object elemento : (ArrayList<?>) response) {
					if (elemento instanceof Users) {
						alumnos.add((Users) elemento);
					}
				}
				return alumnos;

			} else if (response instanceof String) {
				JOptionPane.showMessageDialog(null, response);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ArrayList<>();
	}

	public Users getUsuarioLogged(Cliente cliente) {

		try {
			return (Users) cliente.enviarRequest("get_usuario", new ArrayList<>());

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}

}
