package modelo;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
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

	public static final String AES_TRANSFORMATION = "AES/ECB/PKCS5Padding";
	public static final int AES_KEY_LENGTH = 16;
	public static final String CLIENT_ENV_KEY = System.getenv().getOrDefault("ELOR_CLIENT_AES_KEY", "CLNTS3CR3T202601");

	/**
	 * Envía una petición de inicio de sesión al servidor. Devuelve el objeto Users
	 * si el login es exitoso, o un mensaje de error (STRING) en caso contrario.
	 * Antes de enviar, cifra las credenciales con la clave cliente (cliente->servidor).
	 */
	public static Object login(Cliente cliente, String username, String password) {
		ArrayList<Object> parametros = new ArrayList<>();
		// Normalizar username como hace el servidor/DB
		parametros.add(cifrarCliente(username.trim().toLowerCase()));
		parametros.add(cifrarCliente(password));
		try {
			return cliente.enviarRequest("login", parametros);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error al conectar con el servidor.");
			return null;
		}
	}

	/**
	 * Cifra con la clave cliente (cliente -> servidor)
	 */
	public static String cifrarCliente(String valor) {
		if (valor == null) return null;
		try {
			Cipher cipher = Cipher.getInstance(AES_TRANSFORMATION);
			cipher.init(Cipher.ENCRYPT_MODE, keyFromString(CLIENT_ENV_KEY));
			byte[] cifrado = cipher.doFinal(valor.getBytes(StandardCharsets.UTF_8));
			return Base64.getEncoder().encodeToString(cifrado);
		} catch (Exception e) {
			throw new RuntimeException("Error cifrando valor", e);
		}
	}

	public static String descifrarCliente(String valor) {
		if (valor == null) return null;
		try {
			Cipher cipher = Cipher.getInstance(AES_TRANSFORMATION);
			cipher.init(Cipher.DECRYPT_MODE, keyFromString(CLIENT_ENV_KEY));
			byte[] decodificado = Base64.getDecoder().decode(valor);
			byte[] descifrado = cipher.doFinal(decodificado);
			return new String(descifrado, StandardCharsets.UTF_8);
		} catch (Exception e) {
			throw new RuntimeException("Error descifrando valor", e);
		}
	}

	private static SecretKeySpec keyFromString(String clave) {
		byte[] keyBytes = clave.getBytes(StandardCharsets.UTF_8);
		byte[] normalized = new byte[AES_KEY_LENGTH];
		for (int i = 0; i < AES_KEY_LENGTH; i++) {
			normalized[i] = i < keyBytes.length ? keyBytes[i] : 0;
		}
		return new SecretKeySpec(normalized, "AES");
	}

	/**
	 * Envía una petición de cierre de sesión (logout) al servidor
	 */
	public void desconectar(Cliente cliente) {
		try {
			cliente.enviarRequest("logout", new ArrayList<>());
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error al desconectar del servidor.");
		}
	}

	/**
	 * Solicita la lista de profesores al servidor.
	 */
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

	/**
	 * Solicita la lista de alumnos al servidor.
	 */
	public ArrayList<Users> getAlumnos(Cliente cliente) {
		Object response;
		try {
			response = cliente.enviarRequest("get_alumnos", new ArrayList<>());

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

	/**
	 * Obtiene los datos del usuario actualmente autenticado en el servidor.
	 */
	public Users getUsuarioLogged(Cliente cliente) {
		try {
			return (Users) cliente.enviarRequest("get_usuario", new ArrayList<>());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}

}
