package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

import cliente.Cliente;
import modelo.Horarios;
import modelo.Reuniones;
import modelo.Users;
import vista.Login;
import vista.Menu;
import vista.VerPerfil;

public class Controlador extends MouseAdapter implements ActionListener {
	private Login vistaLogin;
	private Cliente cliente;
	private Users usuario;
	private Menu vistaMenu;
	private VerPerfil vistaPerfil;

	public Controlador(Login vistaLogin) {
		this.vistaLogin = vistaLogin;
		vistaMenu = new Menu();
		vistaPerfil = new VerPerfil();
		inicializarControlador();
		try {
			cliente = new Cliente();
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
		vistaLogin.getBtnIniciarSesion().setActionCommand("INICIAR_SESION");
		vistaLogin.getBtnIniciarSesion().addActionListener(this);

		vistaPerfil.getBtnDesconectar().setActionCommand("DESCONECTAR");
		vistaPerfil.getBtnDesconectar().addActionListener(this);

		vistaMenu.getPanelOrganizarReuniones().getBtnVolver().setActionCommand("VOLVER_MENU");
		vistaMenu.getPanelOrganizarReuniones().getBtnVolver().addActionListener(this);

		vistaMenu.getPanelVerHorarios().getBtnVolver().setActionCommand("VOLVER_MENU");
		vistaMenu.getPanelVerHorarios().getBtnVolver().addActionListener(this);

		vistaMenu.getBtnVerOtrosHorarios().setActionCommand("VER_HORARIOS");
		vistaMenu.getBtnVerOtrosHorarios().addActionListener(this);

		vistaMenu.getBtnOrganizarReuniones().setActionCommand("ORGANIZAR_REUNIONES");
		vistaMenu.getBtnOrganizarReuniones().addActionListener(this);

		vistaMenu.getPanelVerHorarios().getTableProfesores().getSelectionModel().addListSelectionListener(event -> {
			if (event.getValueIsAdjusting()) {
				return;
			}
			mostrarHorarioProfesorSeleccionado();
		});
		vistaMenu.getPanelAlumnos().getBtnVolver().setActionCommand("VOLVER_MENU");
		vistaMenu.getPanelAlumnos().getBtnVolver().addActionListener(this);

		vistaMenu.getBtnConsultarAlumnos().setActionCommand("CONSULTAR_ALUMNOS");
		vistaMenu.getBtnConsultarAlumnos().addActionListener(this);

		vistaMenu.getPanelPerfil().addMouseListener(this);
		vistaMenu.getPanelLogo().addMouseListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		final String cmd = e.getActionCommand();

		switch (cmd) {
		case "INICIAR_SESION":
			login();
			break;
		case "DESCONECTAR":
			usuario.desconectar(cliente);
			vistaMenu.setVisible(false);
			vistaLogin.setVisible(true);
			break;
		case "VOLVER_MENU":
			mostrarPanelPrincipal(vistaMenu.getPanelGeneral(), "");
			break;
		case "VOLVER_MENUPAGINA":
			vistaPerfil.setVisible(false);
			vistaMenu.setVisible(true);
			mostrarPanelPrincipal(vistaMenu.getPanelGeneral(), "");
			break;
		case "CONSULTAR_ALUMNOS":
			vistaMenu.setVisible(true);
			mostrarPanelPrincipal(vistaMenu.getPanelAlumnos(), "ALUMNOS");
			actualizarTablaAlumnos();
			break;
		case "VER_HORARIOS":
			mostrarPanelPrincipal(vistaMenu.getPanelVerHorarios(), "HORARIOS");
			actualizarListaProfesores(usuario.getProfesores(cliente));
			break;
		case "ORGANIZAR_REUNIONES":
			mostrarPanelPrincipal(vistaMenu.getPanelOrganizarReuniones(), "REUNIONES");
			actualizarTablaReuniones(Reuniones.getReunionesUsuario(cliente));
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
		} else if (e.getSource() == vistaMenu.getPanelLogo()) {
			actionPerformed(new ActionEvent(vistaMenu, ActionEvent.ACTION_PERFORMED, "VOLVER_MENU"));
		}
	}
	
	private void mostrarPanelPrincipal(JPanel panel, String menu) {
		vistaMenu.getPanelGeneral().setVisible(false);
		vistaMenu.getPanelVerHorarios().setVisible(false);
		vistaMenu.getPanelOrganizarReuniones().setVisible(false);
		vistaMenu.getPanelAlumnos().setVisible(false);

		if (panel != null) {
			panel.setVisible(true);
		}
		vistaMenu.setEstadoMenu(menu == null ? "" : menu);
	}

	public void login() {
		try {
			Object response = Users.login(cliente, vistaLogin.getTextFieldUsuario().getText(), vistaLogin.getTextFieldContrasena().getText());
			if (response instanceof Users && response != null) {
				usuario = (Users) response;

				if (usuario.getTipos().getId() != 3) {
					vistaLogin.getLblError().setText("Usuario no autorizado para usar la aplicación.");
				} else {
					JOptionPane.showMessageDialog(null, "¡Bienvenido, " + usuario.getNombre() + "!");
					vistaLogin.setVisible(false);
					vistaMenu.setVisible(true);
					mostrarPanelPrincipal(vistaMenu.getPanelGeneral(), "");
					vistaMenu.getLblNombreUsuario().setText(usuario.getNombre() + " " + usuario.getApellidos());
					vistaMenu.getLblRolUsuario().setText(usuario.getTipos().getName().substring(0, 1).toUpperCase()
							+ usuario.getTipos().getName().substring(1).toLowerCase());
					vistaMenu.cargarAvatar(usuario.getArgazkiaUrl());
					actualizarTablaMiHorario();
					actualizarTablaReuniones(Reuniones.getReunionesUsuario(cliente));
				}
			} else {
				String mensajeError = (String) response;
				vistaLogin.getLblError().setText("Error: " + mensajeError);
			}
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
	
	private void actualizarTablaMiHorario() {
		try {
			DefaultTableModel modeloResultado = cargarModeloHorariosConReuniones(Horarios.getHorarios(cliente),
					Reuniones.getReunionesUsuario(cliente));
			vistaMenu.getPanelGeneral().getPanelHorarios().setModelo(modeloResultado);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(vistaMenu, "No se pudo cargar el horario: " + e.getMessage());
		}
	}
	
	private void actualizarTablaReuniones(List<Reuniones> reuniones) {
		DefaultTableModel modelo = vistaMenu.getPanelOrganizarReuniones().getModeloReuniones();
		if (modelo == null) {
			return;
		}
		modelo.setRowCount(0);
		if (reuniones == null || reuniones.isEmpty()) {
			return;
		}
		for (Reuniones reunion : reuniones) {
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
			modelo.addRow(new Object[] { reunion.getIdReunion(), dtf.format(reunion.getFecha().toLocalDateTime()),
					reunion.getUsersByAlumnoId().getNombre() + " " + reunion.getUsersByAlumnoId().getApellidos(),
					reunion.getEstado(), reunion.getTitulo(), reunion.getAsunto(), reunion.getAula(),
					dtf.format(reunion.getCreatedAt().toLocalDateTime()),
					dtf.format(reunion.getUpdatedAt().toLocalDateTime()) });
		}
	}


	private void actualizarTablaAlumnos() {
		if (usuario == null || vistaMenu.getPanelAlumnos() == null) {
			return;
		}
		try {
			DefaultTableModel modelo = vistaMenu.getPanelAlumnos().getModeloAlumnos();
			modelo.setRowCount(0);
			ArrayList<Users> alumnos = usuario.getAlumnos(cliente);
			for (Users alumno : alumnos) {
				if (alumno == null) {
					continue;
				}
				String nombre = alumno.getNombre() == null ? "" : alumno.getNombre();
				String apellidos = alumno.getApellidos() == null ? "" : alumno.getApellidos();
				String nombreCompleto = (nombre + " " + apellidos).trim();
				String ciclo = alumno.getCicloAsignado() == null ? "" : alumno.getCicloAsignado();
				modelo.addRow(new Object[] { alumno.getId(), alumno.getArgazkiaUrl(), nombreCompleto, ciclo,
						alumno.getUsername(), alumno.getEmail(), alumno.getDni(), alumno.getDireccion(),
						alumno.getTelefono1(), alumno.getTelefono2() });
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(vistaMenu, "No se pudieron cargar los alumnos: " + e.getMessage());

		}
	}

	private DefaultTableModel cargarModeloHorarios(ArrayList<Horarios> horarios) {
		DefaultTableModel modeloTabla = new DefaultTableModel(
				new String[] { "", "Lunes", "Martes", "Miércoles", "Jueves", "Viernes" }, 0);
		if (horarios == null) {
			return null;
		}
		Map<String, Object[]> filasPorHora = new TreeMap<>();
		for (Horarios horario : horarios) {
			if (horario == null) {
				continue;
			}
			Object[] fila = filasPorHora.computeIfAbsent(horario.getHoraStr(), key -> crearFilaHorario(key));
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

	private DefaultTableModel cargarModeloHorariosConReuniones(ArrayList<Horarios> horarios,
			ArrayList<Reuniones> reuniones) {
		DefaultTableModel modeloTabla = new DefaultTableModel(
				new String[] { "", "Lunes", "Martes", "Miércoles", "Jueves", "Viernes" }, 0);
		Map<String, Object[]> filasPorHora = new TreeMap<>();

		for (Horarios horario : horarios) {
			if (horario == null) {
				continue;
			}
			Object[] fila = filasPorHora.computeIfAbsent(horario.getHoraStr(), key -> crearFilaHorario(key));
			int columna = horario.obtenerColumnaDia();
			if (columna == -1) {
				continue;
			}
			agregarContenidoCelda(fila, columna, horario);
		}

		for (Reuniones reunion : reuniones) {
			if (reunion == null) {
				continue;
			}
			String etiquetaHora = reunion.obtenerHora();
			Object[] fila = filasPorHora.computeIfAbsent(etiquetaHora, key -> crearFilaHorario(key));
			int columna = reunion.obtenerColumnaDia();
			if (columna == -1) {
				continue;
			}
			agregarContenidoCelda(fila, columna, reunion);
		}

		if (filasPorHora.isEmpty()) {
			return null;
		}
		for (Object[] fila : filasPorHora.values()) {
			modeloTabla.addRow(fila);
		}
		return modeloTabla;
	}

	private Object[] crearFilaHorario(String etiquetaHora) {
		Object[] nuevaFila = new Object[6];
		nuevaFila[0] = etiquetaHora;
		return nuevaFila;
	}


	private void agregarContenidoCelda(Object[] fila, int columna, Object contenido) {
	    if (fila == null || columna <= 0 || columna >= fila.length || contenido == null) {
	        return;
	    }

	    Object actual = fila[columna];

	    if (actual == null) {
	        fila[columna] = contenido;
	    } else if (actual instanceof List<?> lista) {
	        if (lista.isEmpty() || lista.get(0) instanceof Object) {
	           List<Object> listaSegura = new ArrayList<>(lista.size());
	            for (Object item : lista) {
	                listaSegura.add(item);
	            }
	            listaSegura.add(contenido);
	            fila[columna] = listaSegura;
	        } 
	    } else {
	        List<Object> combinados = new ArrayList<>();
	        combinados.add(actual);
	        combinados.add(contenido);
	        fila[columna] = combinados;
	    }
	}

	private void actualizarListaProfesores(ArrayList<Users> profesores) {
		vistaMenu.getPanelVerHorarios().getModeloProfesores().setRowCount(0);
		for (Users profesor : profesores) {
			vistaMenu.getPanelVerHorarios().getModeloProfesores()
					.addRow(new Object[] { profesor.getId(), profesor.getNombre() + " " + profesor.getApellidos() });
		}
		vistaMenu.getPanelVerHorarios().getTableProfesores().clearSelection();
		vistaMenu.getPanelVerHorarios().getPanelHorarios().setModelo(null);
		vistaMenu.getPanelVerHorarios().getTableProfesores().repaint();
		vistaMenu.getPanelVerHorarios().getTableProfesores().revalidate();
	}

	private void mostrarHorarioProfesorSeleccionado() {
		int selectedRow = vistaMenu.getPanelVerHorarios().getTableProfesores().getSelectedRow();
		if (selectedRow < 0) {
			return;
		}
		int modelRow = vistaMenu.getPanelVerHorarios().getTableProfesores().convertRowIndexToModel(selectedRow);
		int profesorId = (int) vistaMenu.getPanelVerHorarios().getModeloProfesores().getValueAt(modelRow, 0);

		try {
			ArrayList<Horarios> horarios = Horarios.getHorariosporUsuario(cliente, profesorId);
			DefaultTableModel modeloResultado = cargarModeloHorarios(horarios);
			vistaMenu.getPanelVerHorarios().getPanelHorarios().setModelo(modeloResultado);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(vistaMenu, "No se pudo cargar el horario: " + e.getMessage());
		}
	}
}
