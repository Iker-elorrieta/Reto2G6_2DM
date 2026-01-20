package modelo;

import java.sql.Timestamp;
import java.util.Locale;

public class Horarios implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private static final int MAX_MODULO_LENGTH = 18;
	private Integer id;
	private Users users;
	private Modulos modulos;
	private String dia;
	private byte hora;
	private String aula;
	private String observaciones;
	private Timestamp createdAt;
	private Timestamp updatedAt;

	public Horarios() {
	}

	public Horarios(Users users, Modulos modulos, String dia, byte hora) {
		this.users = users;
		this.modulos = modulos;
		this.dia = dia;
		this.hora = hora;
	}

	public Horarios(Users users, Modulos modulos, String dia, byte hora, String aula, String observaciones,
			Timestamp createdAt, Timestamp updatedAt) {
		this.users = users;
		this.modulos = modulos;
		this.dia = dia;
		this.hora = hora;
		this.aula = aula;
		this.observaciones = observaciones;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Users getUsers() {
		return this.users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}

	public Modulos getModulos() {
		return this.modulos;
	}

	public void setModulos(Modulos modulos) {
		this.modulos = modulos;
	}

	public String getDia() {
		return this.dia;
	}

	public void setDia(String dia) {
		this.dia = dia;
	}

	public byte getHora() {
		return this.hora;
	}

	public void setHora(byte hora) {
		this.hora = hora;
	}

	public String getAula() {
		return this.aula;
	}

	public void setAula(String aula) {
		this.aula = aula;
	}

	public String getObservaciones() {
		return this.observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
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
	public String describirModulo() {
		String nombreModulo = prepararModulo(getModulos() != null ? getModulos().getNombre() : null);
		String aula = limpiarTexto(getAula());
		boolean tieneModulo = nombreModulo != null;
		boolean tieneAula = aula != null;
		
		if (tieneModulo && tieneAula) {
			return envolverEnHtml("<b>" + nombreModulo + "</b> " + aula);
		}
		
		if (tieneModulo) {
			return envolverEnHtml("<b>" + nombreModulo + "</b>");
		}
		
		if (tieneAula) {
			return envolverEnHtml(aula);
		}
		
		return envolverEnHtml("<span style='color:#7A7A7A;'>Disponible</span>");
	}

	public int obtenerColumnaDia() {
		if (dia == null) {
			return -1;
		}
		
		String normalizado = dia.trim().toUpperCase();
		switch (normalizado) {
		case "LUNES":
			return 1;
		case "MARTES":
			return 2;
		case "MIERCOLES":
		case "MIÉRCOLES":
			return 3;
		case "JUEVES":
			return 4;
		case "VIERNES":
			return 5;
		default:
			return -1;
		}
	}

	private String prepararModulo(String valor) {
		if (valor == null) {
			return null;
		}
		String limpio = valor.trim();
		if (limpio.isEmpty()) {
			return null;
		}
		if (limpio.length() > MAX_MODULO_LENGTH && MAX_MODULO_LENGTH > 3) {
			limpio = limpio.substring(0, MAX_MODULO_LENGTH - 3) + "...";
		}
		return escaparHtml(limpio);
	}

	private String limpiarTexto(String valor) {
		if (valor == null) {
			return null;
		}
		String limpio = valor.trim();
		if (limpio.isEmpty()) {
			return null;
		}
		return escaparHtml(limpio);
	}

	private String escaparHtml(String texto) {
		return texto.replace("&", "&amp;").replace("<", "&lt;").replace(">", "&gt;");
	}

	private String envolverEnHtml(String contenido) {
		if (contenido == null || contenido.trim().isEmpty()) {
			return "";
		}
		return "<html><div style='line-height:1.2;'>" + contenido + "</div></html>";
	}

}
