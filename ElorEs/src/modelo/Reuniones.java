package modelo;

import java.awt.Color;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import cliente.Cliente;

public class Reuniones implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Integer idReunion;
	private Users usersByAlumnoId;
	private Users usersByProfesorId;
	private String estado;
	private String estadoEus;
	private String idCentro;
	private String titulo;
	private String asunto;
	private String aula;
	private Timestamp fecha;
	private Timestamp createdAt;
	private Timestamp updatedAt;
	private Centros centro;

	public Reuniones() {
	}

	public Reuniones(Users usersByAlumnoId, Users usersByProfesorId, String estado, String estadoEus, String idCentro,
			String titulo, String asunto, String aula, Timestamp fecha, Timestamp createdAt, Timestamp updatedAt) {
		this.usersByAlumnoId = usersByAlumnoId;
		this.usersByProfesorId = usersByProfesorId;
		this.estado = estado;
		this.estadoEus = estadoEus;
		this.idCentro = idCentro;
		this.titulo = titulo;
		this.asunto = asunto;
		this.aula = aula;
		this.fecha = fecha;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}

	public Integer getIdReunion() {
		return this.idReunion;
	}

	public void setIdReunion(Integer idReunion) {
		this.idReunion = idReunion;
	}

	public Users getUsersByAlumnoId() {
		return this.usersByAlumnoId;
	}

	public void setUsersByAlumnoId(Users usersByAlumnoId) {
		this.usersByAlumnoId = usersByAlumnoId;
	}

	public Users getUsersByProfesorId() {
		return this.usersByProfesorId;
	}

	public void setUsersByProfesorId(Users usersByProfesorId) {
		this.usersByProfesorId = usersByProfesorId;
	}

	public String getEstado() {
		if (this.estado == null) {
			return "";
		}
		return estado.substring(0, 1).toUpperCase() + estado.substring(1).toLowerCase();
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getEstadoEus() {
		return this.estadoEus;
	}

	public void setEstadoEus(String estadoEus) {
		this.estadoEus = estadoEus;
	}

	public String getIdCentro() {
		return this.idCentro;
	}

	public void setIdCentro(String idCentro) {
		this.idCentro = idCentro;
	}

	public String getTitulo() {
		return this.titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getAsunto() {
		return this.asunto;
	}

	public void setAsunto(String asunto) {
		this.asunto = asunto;
	}

	public String getAula() {
		return this.aula;
	}

	public void setAula(String aula) {
		this.aula = aula;
	}

	public Timestamp getFecha() {
		return this.fecha;
	}

	public void setFecha(Timestamp fecha) {
		this.fecha = fecha;
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

	public Centros getCentro() {
		return centro;
	}

	public void setCentro(Centros centro) {
		this.centro = centro;
	}

	public static ArrayList<Reuniones> getReunionesSemanaActual(Cliente cliente) {
		try {
			Object response = cliente.enviarRequest("get_reuniones_semana", new ArrayList<>());
			if (response instanceof ArrayList<?>) {
				ArrayList<Reuniones> reuniones = new ArrayList<>();
				for (Object elemento : (ArrayList<?>) response) {
					if (elemento instanceof Reuniones) {
						reuniones.add((Reuniones) elemento);
					}
				}
				return reuniones;
			}
		} catch (Exception e) {
		}
		return new ArrayList<>();
	}

	public static ArrayList<Reuniones> getReunionesUsuario(Cliente cliente) {
		try {
			Object response = cliente.enviarRequest("get_reuniones", new ArrayList<>());
			if (response instanceof ArrayList<?>) {
				ArrayList<Reuniones> reuniones = new ArrayList<>();
				for (Object elemento : (ArrayList<?>) response) {
					if (elemento instanceof Reuniones) {
						reuniones.add((Reuniones) elemento);
					}
				}
				return reuniones;
			} else if (response instanceof String) {
			}
		} catch (Exception e) {
		}
		return new ArrayList<>();

	}

	public String obtenerHora() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
		LocalDateTime fecha = getFecha().toLocalDateTime();
		return formatter.format(fecha);
	}

	public int obtenerColumnaDia() {
		switch (getFecha().toLocalDateTime().getDayOfWeek()) {
		case MONDAY:
			return 1;
		case TUESDAY:
			return 2;
		case WEDNESDAY:
			return 3;
		case THURSDAY:
			return 4;
		case FRIDAY:
			return 5;
		default:
			return -1;
		}
	}


	public String describirReunion(boolean envolverHtml, boolean multilinea) {
		String contenido = construirDescripcionReunion(multilinea);
		return envolverHtml ? envolverEnHtml(contenido) : contenido;
	}

	private String construirDescripcionReunion(boolean multilinea) {
		StringBuilder html = new StringBuilder();
		String tituloReunion = "";
		if (asunto != null && !asunto.isEmpty()) {
			tituloReunion = asunto;
		} else if (titulo != null && !titulo.isEmpty()) {
			tituloReunion = titulo;
		}

		if (getUsersByAlumnoId() != null) {
			String nombreAlumno = getUsersByAlumnoId().getNombre() + " " + getUsersByAlumnoId().getApellidos();
			if (!tituloReunion.isEmpty()) {
				html.append("<b>").append(tituloReunion).append(" - ").append(nombreAlumno).append("</b>");
			} else {
				html.append("<b>").append(nombreAlumno).append("</b>");
			}
		} else if (!tituloReunion.isEmpty()) {
			html.append("<b>").append(tituloReunion).append("</b>");
		}

		if (estado != null && !estado.trim().isEmpty()) {
			appendSeparador(html, multilinea);
			html.append(getEstado());
		}
		if (aula != null && !aula.trim().isEmpty()) {
			appendSeparador(html, multilinea);
			html.append(aula);
		}
		if (html.length() == 0) {
			html.append("<span style='color:#7A7A7A;'>Reunión</span>");
		}
		return html.toString();
	}

	private void appendSeparador(StringBuilder html, boolean multilinea) {
		if (html.length() == 0) {
			return;
		}
		if (multilinea) {
			html.append("<br/>");
		} else {
			html.append(' ');
		}
	}

	private String envolverEnHtml(String contenido) {
		return "<html><div style='line-height:1.2;'>" + contenido + "</div></html>";
	}

	public Color getColorEstado() {
		if (estado == null) {
			return Color.WHITE;
		}
		switch (estado.toLowerCase().trim()) {
		case "aceptada":
			return new Color(200, 230, 201);
		case "denegada":
			return new Color(255, 205, 210);
		case "pendiente":
			return new Color(255, 224, 130);
		case "conflicto":
			return new Color(224, 224, 224);
		default:
			return Color.WHITE;
		}
	}
}
