package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.time.format.DateTimeFormatter;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import cliente.Cliente;
import modelo.Horarios;
import modelo.Reuniones;
import modelo.Users;
import vista.Inicio;
import vista.PantallaMenu;
import vista.TablaHorario;
import vista.VerAlumnos;
import vista.VerPerfil;

public class Controlador extends MouseAdapter implements ActionListener {
	private Inicio vistaLogin;
	private Cliente cliente;
	private Users usuario;
	private PantallaMenu vistaMenu;
	private VerAlumnos vistaAlumnos;
	private VerPerfil vistaPerfil;

	public Controlador(Inicio vistaLogin) {
		this.vistaLogin = vistaLogin;
		vistaMenu = new PantallaMenu();
		vistaAlumnos = new VerAlumnos();
		vistaPerfil = new VerPerfil();
		inicializarControlador();
		try {
			cliente = new Cliente("localhost", 5000);
		} catch (Exception e) {
			vistaLogin.dispose();
			vistaMenu.dispose();
			JOptionPane.showMessageDialog(null, "Error al conectar con el servidor: " + e.getMessage());
			e.printStackTrace();
		}
	}

	/**
	 * Inicializa y registra todos los listeners y valores iniciales de los paneles.
	 * Aquí se asocian comandos de acción y manejadores a botones y componentes.
	 */
	private void inicializarControlador() {
		vistaLogin.getPanelLogin().getBtnIniciarSesion().setActionCommand("INICIAR_SESION");
		vistaLogin.getPanelLogin().getBtnIniciarSesion().addActionListener(this);

		vistaPerfil.getBtnDesconectar().setActionCommand("DESCONECTAR");
		vistaPerfil.getBtnDesconectar().addActionListener(this);

		vistaMenu.getPanelOrganizarReuniones().getBtnVolver().setActionCommand("VOLVER_MENU");
		vistaMenu.getPanelOrganizarReuniones().getBtnVolver().addActionListener(this);

		vistaMenu.getPanelVerHorarios().getBtnVolver().setActionCommand("VOLVER_MENU");
		vistaMenu.getPanelVerHorarios().getBtnVolver().addActionListener(this);

		vistaMenu.getPanelGeneral().getBtnVerOtrosHorarios().setActionCommand("VER_HORARIOS");
		vistaMenu.getPanelGeneral().getBtnVerOtrosHorarios().addActionListener(this);

		vistaMenu.getPanelGeneral().getBtnOrganizarReuniones().setActionCommand("ORGANIZAR_REUNIONES");
		vistaMenu.getPanelGeneral().getBtnOrganizarReuniones().addActionListener(this);

		vistaMenu.getPanelVerHorarios().getTableProfesores().getSelectionModel()
		.addListSelectionListener(event -> {
			if (event.getValueIsAdjusting()) {
				return;
			}
			mostrarHorarioProfesorSeleccionado();
		});
		vistaAlumnos.getBtnVolver().setActionCommand("VOLVER_MENUPAGINA");
		vistaAlumnos.getBtnVolver().addActionListener(this);

		vistaMenu.getBtnConsultarAlumnos().setActionCommand("CONSULTAR_ALUMNOS");
		vistaMenu.getBtnConsultarAlumnos().addActionListener(this);

		vistaMenu.getPanelPerfil().addMouseListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		final String cmd = e.getActionCommand();

		switch (cmd) {
		case "INICIAR_SESION":
			login();
			break;
		case "DESCONECTAR":
			vistaMenu.setVisible(false);
			vistaLogin.setVisible(true);
			break;
		case "VOLVER_MENU":
			vistaMenu.getPanelVerHorarios().setVisible(false);
			vistaMenu.getPanelOrganizarReuniones().setVisible(false);
			vistaMenu.getPanelGeneral().setVisible(true);
			break;
		case "VOLVER_MENUPAGINA":
			vistaAlumnos.setVisible(false);
			vistaPerfil.setVisible(false);
			vistaMenu.setVisible(true);
			break;
		case "CONSULTAR_ALUMNOS":
			vistaMenu.setVisible(false);
			vistaAlumnos.setVisible(true);
			break;
		case "VER_HORARIOS":
			vistaMenu.getPanelGeneral().setVisible(false);
			vistaMenu.getPanelVerHorarios().setVisible(true);
			cargarListaProfesores();
			break;
		case "ORGANIZAR_REUNIONES":
			vistaMenu.getPanelGeneral().setVisible(false);
			vistaMenu.getPanelOrganizarReuniones().setVisible(true);
			cargarReunionesUsuario();
			break;
		case "ABRIR_PERFIL":
			System.out.println("Abriendo perfil de usuario...");
			cargarDatosUsuario(usuario);
			vistaPerfil.setVisible(true);
			break;
		default:
			break;
		}

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == vistaMenu.getPanelPerfil()) {
			actionPerformed(new ActionEvent(vistaMenu.getPanelAvatar(), ActionEvent.ACTION_PERFORMED, "ABRIR_PERFIL"));
		}
	}

	public void login() {
		ArrayList<Object> datos = new ArrayList<>();
		datos.add(vistaLogin.getPanelLogin().getTextFieldUsuario().getText());
		datos.add(vistaLogin.getPanelLogin().getTextFieldContrasena().getText());

		try {
			Object response = cliente.enviarRequest("login", datos);
			if (response instanceof Users && response != null) {
				usuario = (Users) response;

				if (usuario.getTipos().getId() != 3) {
					vistaLogin.getPanelLogin().getLblError().setText("Usuario no autorizado para usar la aplicación.");
				} else {
					JOptionPane.showMessageDialog(null, "¡Bienvenido, " + usuario.getNombre() + "!");
					vistaLogin.setVisible(false);
					vistaMenu.setVisible(true);
					vistaMenu.getLblNombreUsuario().setText(usuario.getNombre() + " " + usuario.getApellidos());
					vistaMenu.getLblRolUsuario().setText(usuario.getTipos().getName().substring(0, 1).toUpperCase()
						+ usuario.getTipos().getName().substring(1).toLowerCase());
					vistaMenu.cargarAvatar(usuario.getArgazkiaUrl());
					cargarHorariosPorUsuario(usuario.getId(), vistaMenu.getPanelGeneral().getPanelHorarios());
					cargarReunionesUsuario();
				}
			} else {
				String mensajeError = (String) response;
				vistaLogin.getPanelLogin().getLblError().setText("Error: " + mensajeError);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void desconectar() {
		try {
			cliente.enviarRequest("logout", new ArrayList<>());

			vistaMenu.setVisible(false);
			vistaLogin.setVisible(true);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void cargarDatosUsuario(Users usuario) {
		vistaPerfil.getLblNombreUsuario().setText(usuario.getNombre() + " " + usuario.getApellidos());
		vistaPerfil.getLblRolUsuario().setText(usuario.getTipos().getName().substring(0, 1).toUpperCase()
				+ usuario.getTipos().getName().substring(1).toLowerCase());
		vistaPerfil.getLblUsuario().setText(usuario.getUsername());
		vistaPerfil.getLblEmail().setText(usuario.getEmail());
		vistaPerfil.getLblTelefono1().setText(usuario.getTelefono1());
		vistaPerfil.getLblTelefono2().setText(usuario.getTelefono2());
		vistaPerfil.getLblDireccion().setText(usuario.getDireccion());
		vistaPerfil.getLblDNI().setText(usuario.getDni());
		vistaPerfil.cargarAvatar(usuario.getArgazkiaUrl());
	}


	private void cargarHorariosPorUsuario(int userId, TablaHorario tablaDestino) {
		if (tablaDestino == null) {
			return;
		}
		try {
			ArrayList<Object> datos = new ArrayList<>();
			datos.add(userId);
			Object response = cliente.enviarRequest("get_horarios_id", datos);
			if (response instanceof ArrayList<?>) {
				ArrayList<Horarios> horarios = new ArrayList<>();
				for (Object elemento : (ArrayList<?>) response) {
					if (elemento instanceof Horarios) {
						horarios.add((Horarios) elemento);
					}
				}
				tablaDestino.actualizarModelo(cargarModeloHorarios(horarios));
			} else if (response instanceof String) {
				JOptionPane.showMessageDialog(vistaMenu, response);
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(vistaMenu, "No se pudo cargar el horario: " + e.getMessage());
		}
	}

	private void cargarReunionesUsuario() {
		try {
			Object response = cliente.enviarRequest("get_reuniones", new ArrayList<>());
			if (response instanceof ArrayList<?>) {
				ArrayList<Reuniones> reuniones = new ArrayList<>();
				for (Object elemento : (ArrayList<?>) response) {
					if (elemento instanceof Reuniones) {
						reuniones.add((Reuniones) elemento);
					}
				}
				vistaMenu.getPanelGeneral().getPanelReuniones().actualizarModelo(cargarModeloReuniones(reuniones));
				actualizarTablaReunionesOrganizador(reuniones);
			} else if (response instanceof String) {
				JOptionPane.showMessageDialog(vistaMenu, response);
				actualizarTablaReunionesOrganizador(null);
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(vistaMenu, "No se pudieron cargar las reuniones: " + e.getMessage());
			actualizarTablaReunionesOrganizador(null);
		}
	}

	private void actualizarTablaReunionesOrganizador(List<Reuniones> reuniones) {
		DefaultTableModel modelo = vistaMenu.getPanelOrganizarReuniones().getModeloReuniones();
		if (modelo == null) {
			return;
		}
		modelo.setRowCount(0);
		if (reuniones == null || reuniones.isEmpty()) {
			return;
		}
		for (Reuniones reunion : reuniones) {
			DateTimeFormatter dtf= DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
			modelo.addRow(new Object[] { reunion.getIdReunion(),reunion.getUsersByAlumnoId().getNombre() + " " +reunion.getUsersByAlumnoId().getApellidos(),
					reunion.getUsersByProfesorId().getNombre() + " " +reunion.getUsersByProfesorId().getApellidos(), reunion.getEstado(),
					reunion.getTitulo(), reunion.getAsunto(), reunion.getAula(), dtf.format(reunion.getFecha().toLocalDateTime()),
					 dtf.format(reunion.getCreatedAt().toLocalDateTime()),  dtf.format(reunion.getUpdatedAt().toLocalDateTime())  });
		}
	}


	private DefaultTableModel cargarModeloHorarios(ArrayList<Horarios> horarios) {
		DefaultTableModel modeloTabla = new DefaultTableModel(
				new String[] { "", "Lunes", "Martes", "Miércoles", "Jueves", "Viernes" }, 0);
		if (horarios == null) {
			return null;
		}
		Map<Byte, Object[]> filasPorHora = new TreeMap<>();
		for (Horarios horario : horarios) {
			if (horario == null) {
				continue;
			}
			// Se obtiene la fila correspondiente a ese bloque horario
	        // Si no existe, se crea una nueva fila con 6 columnas
			Object[] fila = filasPorHora.computeIfAbsent(Byte.valueOf(horario.getHora()), key -> {
				Object[] nuevaFila = new Object[6];
				nuevaFila[0] = key + "ª";
				return nuevaFila;
			});
			int columna = horario.obtenerColumnaDia();
			if (columna == -1) {
				continue;
			}
				fila[columna] = horario;
		}

		for (Object[] fila : filasPorHora.values()) {
			modeloTabla.addRow(fila);
		}
		
		return modeloTabla;
	}

	private DefaultTableModel cargarModeloReuniones(ArrayList<Reuniones> reuniones) {
		DefaultTableModel modeloTabla = new DefaultTableModel(
				new String[] { "", "Lunes", "Martes", "Miércoles", "Jueves", "Viernes" }, 0);
		if (reuniones == null) {
			return null;
		}
		Map<String, String[]> filasPorHora = new TreeMap<>();
		for (Reuniones reunion : reuniones) {
			// Se obtiene la fila correspondiente a esa hora
	        // Si no existe, se crea una nueva fila con 6 columnas
			String[] fila = filasPorHora.computeIfAbsent(reunion.obtenerHora(), key -> {
				String[] nuevaFila = new String[6];
				nuevaFila[0] = key;
				return nuevaFila;
			});
			int columna = reunion.obtenerColumnaDia();
			if (columna == -1) {
				continue;
			}
			fila[columna] = reunion.describirReunion();
		}

		if (filasPorHora.isEmpty()) {
			return null;
		}

		for (String[] fila : filasPorHora.values()) {
			modeloTabla.addRow(fila);
		}
		return modeloTabla;
	}
	
	private void cargarListaProfesores() {
		try {
			Object response = cliente.enviarRequest("get_profesores", new ArrayList<>());
			if (response instanceof ArrayList<?>) {
				ArrayList<Users> profesores = new ArrayList<>();
				for (Object elemento : (ArrayList<?>) response) {
					if (elemento instanceof Users) {
						profesores.add((Users) elemento);
					}
				}
				vistaMenu.getPanelVerHorarios().getModeloProfesores().setRowCount(0);
				for (Users profesor : profesores) {
					vistaMenu.getPanelVerHorarios().getModeloProfesores()
							.addRow(new Object[] { profesor.getId(),profesor.getNombre() + " " + profesor.getApellidos() });
				}
				vistaMenu.getPanelVerHorarios().getTableProfesores().clearSelection();
				vistaMenu.getPanelVerHorarios().getPanelHorarios().actualizarModelo(null);
				vistaMenu.getPanelVerHorarios().getTableProfesores().repaint();
				vistaMenu.getPanelVerHorarios().getTableProfesores().revalidate();
				
			} else if (response instanceof String) {
				JOptionPane.showMessageDialog(vistaMenu, response);
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(vistaMenu, "No se pudo cargar la lista de profesores: " + e.getMessage());
		}
	}
	private void mostrarHorarioProfesorSeleccionado() {
		int selectedRow = vistaMenu.getPanelVerHorarios().getTableProfesores().getSelectedRow();
		if (selectedRow < 0) {
			return;
		}
		int modelRow = vistaMenu.getPanelVerHorarios().getTableProfesores().convertRowIndexToModel(selectedRow);
		int profesorId = (int) vistaMenu.getPanelVerHorarios().getModeloProfesores().getValueAt(modelRow, 0);
		cargarHorariosPorUsuario(profesorId, vistaMenu.getPanelVerHorarios().getPanelHorarios());
	}
}
