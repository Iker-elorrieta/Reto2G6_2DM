package modelo;

import java.sql.Timestamp;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import cliente.Cliente;

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
		if (this.aula == null) {
			return null;
		}
		return this.aula.replaceAll("(?i)aula\\s*", "").trim();
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
		return describirModulo(true);
	}

	public String describirModulo(boolean envolverHtml) {
		return describirModuloDetallado(true, envolverHtml);
	}

	public String describirModuloCompleto() {
		return describirModuloCompleto(true);
	}

	public String describirModuloCompleto(boolean envolverHtml) {
		return describirModuloDetallado(false, envolverHtml);
	}

	private String describirModuloDetallado(boolean recortarNombre, boolean envolverHtml) {
		String modulo = recortarNombre ? getNombreModuloCorto() : obtenerNombreModulo();
		String contenido = construirDescripcion(modulo);
		return envolverHtml ? envolverEnHtml(contenido) : contenido;
	}

	private String construirDescripcion(String modulo) {
		String aula = (getAula() != null) ? getAula().trim() : null;
		String ciclo = (getModulos() != null && getModulos().getCiclos() != null)
				? getModulos().getCiclos().getNombre().trim()
				: null;
		StringBuilder contenido = new StringBuilder();
		if (modulo != null && aula != null && ciclo != null) {
			contenido.append("<b>").append(modulo).append("</b> ").append(aula).append(" ").append(ciclo);
		} else if (modulo != null && aula != null) {
			contenido.append("<b>").append(modulo).append("</b> ").append(aula);
		} else if (modulo != null) {
			contenido.append("<b>").append(modulo).append("</b>");
		} else if (aula != null) {
			contenido.append(aula);
		} else {
			contenido.append("<span style='color:#7A7A7A;'>Disponible</span>");
		}
		return contenido.toString();
	}

	private String envolverEnHtml(String contenido) {
		return "<html><div style='line-height:1.2;'>" + contenido + "</div></html>";
	}

	private String getNombreModuloCorto() {
		String limpio = obtenerNombreModulo();
		if (limpio != null && limpio.length() > MAX_MODULO_LENGTH && MAX_MODULO_LENGTH > 3) {
			limpio = limpio.substring(0, MAX_MODULO_LENGTH - 3) + "...";
		}
		return limpio;
	}

	private String obtenerNombreModulo() {
		if (getModulos() == null || getModulos().getNombre() == null) {
			return null;
		}
		return getModulos().getNombre().trim();
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

	public static ArrayList<Horarios> getHorarios(Cliente cliente) {
		Object response;
		try {
			response = cliente.enviarRequest("get_horarios", new ArrayList<>());

			if (response instanceof ArrayList<?>) {
				ArrayList<Horarios> horarios = new ArrayList<>();
				for (Object elemento : (ArrayList<?>) response) {
					if (elemento instanceof Horarios) {
						horarios.add((Horarios) elemento);
					}
				}
				return horarios;

			} else if (response instanceof String) {
				JOptionPane.showMessageDialog(null, (String) response, "Error", JOptionPane.ERROR_MESSAGE);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ArrayList<>();
	}
	public static ArrayList<Horarios> getHorariosporUsuario(Cliente cliente, int usuario) {
		ArrayList<Object> datos = new ArrayList<>();
		datos.add(usuario);
		Object response;
		try {
			response = cliente.enviarRequest("get_horarios_id", datos);

			if (response instanceof ArrayList<?>) {
				ArrayList<Horarios> horarios = new ArrayList<>();
				for (Object elemento : (ArrayList<?>) response) {
					if (elemento instanceof Horarios) {
						horarios.add((Horarios) elemento);
					}
				}
				return horarios;

			} else if (response instanceof String) {
				JOptionPane.showMessageDialog(null, (String) response, "Error", JOptionPane.ERROR_MESSAGE);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ArrayList<>();
	}
	
	public static Horarios getPrimerHorarioDesdeLista(List<?> valores) {
		if (valores == null) {
			return null;
		}
		for (Object item : valores) {
			if (item instanceof Horarios) {
				return (Horarios) item;
			}
		}
		return null;
	}
	

	public String getHoraStr() {
		int horaNormalizada = Math.max(0, Math.min(23, 7+hora));
		return String.format("%02d:00", horaNormalizada);
	}

}
