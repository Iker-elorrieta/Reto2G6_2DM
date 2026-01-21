package modelo;

import java.sql.Timestamp;

public class Horarios implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private static final int MAX_MODULO_LENGTH = 13;
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
		String modulo = moduloTrim();
		return construirDescripcion(modulo);
	}

	private String construirDescripcion(String modulo) {
		String aula = (getAula() != null)? getAula().trim():null;
		String contenido = "<html><div style='line-height:1.2;'>";
		if (modulo != null && aula != null) {
			contenido += "<b>" + modulo + "</b> " + aula;
		} else if (modulo != null) {
			contenido += "<b>" + modulo + "</b>";
		} else if (aula != null) {
			contenido += aula;
		} else {
			contenido += "<span style='color:#7A7A7A;'>Disponible</span>";
		}
		contenido += "</div></html>";
		return contenido;
	}
	private String moduloTrim() {
		String limpio = getModulos().getNombre().trim();
		if (limpio != null && limpio.length() > MAX_MODULO_LENGTH && MAX_MODULO_LENGTH > 3) {
			limpio = limpio.substring(0, MAX_MODULO_LENGTH - 3) + "...";
		}
		return limpio;
	}



	public int obtenerColumnaDia() {
		switch (dia.trim().toUpperCase()) {
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



}
