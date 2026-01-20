package modelo;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


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
		return this.estado;
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

	public String describirReunion() {
		String html = "<html><div style='line-height:1.2;'>";
		if (titulo != null) {
		    html += "<b>" + titulo + "</b>";
		}
		if (getUsersByProfesorId() != null) {
		    html += "<br/>" + getUsersByProfesorId().getNombre() + " " + getUsersByProfesorId().getApellidos();;
		}
		if (estado != null) {
		    if (getUsersByProfesorId() != null) {
		        html += " · ";
		    } else if (titulo != null) {
		        html += "<br/>";
		    }
		    html += estado;
		}
		if (aula != null) {
		    html += "<br/>" + aula;
		}
		html += "</div></html>";
		return html;
	}
}
