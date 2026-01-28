package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

import cliente.Cliente;
import modelo.Centros;
import modelo.Horarios;
import modelo.Reuniones;
import modelo.Users;
import vista.Login;
import vista.Menu;
import vista.AnadirReunion;
import vista.VerPerfil;

public class Controlador extends MouseAdapter implements ActionListener {

	// Vistas
	private Login vistaLogin;
	private Menu vistaMenu;
	private VerPerfil vistaPerfil;
	private AnadirReunion ventanaReunion;

	// Socket
	private Cliente cliente;

	// Usuario actual (profesor)
	private Users usuario;

	// Acciones posibles (usado en procesarAccion)
	private enum Accion {
		INICIAR_SESION, DESCONECTAR, VOLVER_MENU, VOLVER_MENUPAGINA, CONSULTAR_ALUMNOS, VER_HORARIOS,
		ORGANIZAR_REUNIONES, ABRIR_FORMULARIO_REUNION, ABRIR_PERFIL, HORARIO_PROFESOR_SELECCIONADO, CREAR_REUNION,
		ACEPTAR_REUNION, RECHAZAR_REUNION
	}

	// Constructor (ejecutado desde ElorEs.java)
	public Controlador(Login vistaLogin) {
		// Inicializar vistas y socket
		this.vistaLogin = vistaLogin;
		vistaMenu = new Menu();
		vistaPerfil = new VerPerfil();
		ventanaReunion = new AnadirReunion();

		// Configurar botones y listeners
		inicializarControlador();

		// Inicializar cliente socket
		inicializarCliente();
	}

	/**
	 * Inicializa la conexión del cliente con el servidor mediante sockets. Si la
	 * conexión falla, muestra un mensaje de error y cierra las vistas.
	 */
	private void inicializarCliente() {
		try {
			cliente = new Cliente();
		} catch (Exception e) {
			// Si no se puede conectar, cerrar la aplicación
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
		vistaLogin.getBtnIniciarSesion().setActionCommand(Accion.INICIAR_SESION.name());
		vistaLogin.getBtnIniciarSesion().addActionListener(this);

		vistaPerfil.getBtnDesconectar().setActionCommand(Accion.DESCONECTAR.name());
		vistaPerfil.getBtnDesconectar().addActionListener(this);

		vistaMenu.getPanelOrganizarReuniones().getBtnVolver().setActionCommand(Accion.VOLVER_MENU.name());
		vistaMenu.getPanelOrganizarReuniones().getBtnVolver().addActionListener(this);
		vistaMenu.getPanelOrganizarReuniones().getBtnNuevaReunion()
				.setActionCommand(Accion.ABRIR_FORMULARIO_REUNION.name());
		vistaMenu.getPanelOrganizarReuniones().getBtnNuevaReunion().addActionListener(this);

		vistaMenu.getPanelVerHorarios().getBtnVolver().setActionCommand(Accion.VOLVER_MENU.name());
		vistaMenu.getPanelVerHorarios().getBtnVolver().addActionListener(this);

		vistaMenu.getBtnVerOtrosHorarios().setActionCommand(Accion.VER_HORARIOS.name());
		vistaMenu.getBtnVerOtrosHorarios().addActionListener(this);

		vistaMenu.getBtnOrganizarReuniones().setActionCommand(Accion.ORGANIZAR_REUNIONES.name());
		vistaMenu.getBtnOrganizarReuniones().addActionListener(this);

		vistaMenu.getPanelVerHorarios().getTableProfesores().getSelectionModel().addListSelectionListener(event -> {
			if (event.getValueIsAdjusting()) {
				return;
			}
			procesarAccion(Accion.HORARIO_PROFESOR_SELECCIONADO);
		});
		vistaMenu.getPanelAlumnos().getBtnVolver().setActionCommand(Accion.VOLVER_MENU.name());
		vistaMenu.getPanelAlumnos().getBtnVolver().addActionListener(this);

		vistaMenu.getBtnConsultarAlumnos().setActionCommand(Accion.CONSULTAR_ALUMNOS.name());
		vistaMenu.getBtnConsultarAlumnos().addActionListener(this);

		vistaMenu.getPanelPerfil().addMouseListener(this);
		vistaMenu.getPanelLogo().addMouseListener(this);

		ventanaReunion.getBtnGuardar().setActionCommand(Accion.CREAR_REUNION.name());
		ventanaReunion.getBtnGuardar().addActionListener(this);

		vistaMenu.getPanelOrganizarReuniones().getBtnAceptar().setActionCommand(Accion.ACEPTAR_REUNION.name());
		vistaMenu.getPanelOrganizarReuniones().getBtnAceptar().addActionListener(this);
		vistaMenu.getPanelOrganizarReuniones().getBtnRechazar().setActionCommand(Accion.RECHAZAR_REUNION.name());
		vistaMenu.getPanelOrganizarReuniones().getBtnRechazar().addActionListener(this);
	}

	/**
	 * Procesa las acciones generadas por los eventos de la interfaz de usuario.
	 * Cada acción corresponde a una operación específica en la aplicación.
	 */
	private void procesarAccion(Accion accion) {
		switch (accion) {
		case INICIAR_SESION:
			login();
			break;
		case DESCONECTAR:
			usuario.desconectar(cliente);
			vistaMenu.setVisible(false);
			vistaPerfil.setVisible(false);
			vistaLogin.setVisible(true);
			break;
		case VOLVER_MENU:
			mostrarPanel(vistaMenu.getPanelGeneral(), "");
			break;
		case VOLVER_MENUPAGINA:
			vistaPerfil.setVisible(false);
			vistaMenu.setVisible(true);
			mostrarPanel(vistaMenu.getPanelGeneral(), "");
			break;
		case CONSULTAR_ALUMNOS:
			vistaMenu.setVisible(true);
			mostrarPanel(vistaMenu.getPanelAlumnos(), "ALUMNOS");
			actualizarTablaAlumnos();
			break;
		case VER_HORARIOS:
			mostrarPanel(vistaMenu.getPanelVerHorarios(), "HORARIOS");
			actualizarListaProfesores();
			break;
		case ORGANIZAR_REUNIONES:
			mostrarPanel(vistaMenu.getPanelOrganizarReuniones(), "REUNIONES");
			actualizarTablaReuniones();
			break;
		case ABRIR_FORMULARIO_REUNION:
			mostrarFormularioNuevaReunion();
			break;
		case ABRIR_PERFIL:
			vistaPerfil.setVisible(true);
			break;
		case HORARIO_PROFESOR_SELECCIONADO:
			mostrarHorarioProfesorSeleccionado();
			break;
		case CREAR_REUNION:
			crearReunion();
			break;
		case ACEPTAR_REUNION:
			actualizarEstadoReunionDesdeTabla(true);
			break;
		case RECHAZAR_REUNION:
			actualizarEstadoReunionDesdeTabla(false);
			break;
		default:
			break;
		}
	}

	// Redireccionar botones a procesarAccion
	@Override
	public void actionPerformed(ActionEvent e) {
		procesarAccion(Accion.valueOf(e.getActionCommand()));
	}

	// Redireccionar clicks en paneles/fotos a procesarAccion
	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == vistaMenu.getPanelPerfil()) {
			procesarAccion(Accion.ABRIR_PERFIL);
		} else if (e.getSource() == vistaMenu.getPanelLogo()) {
			procesarAccion(Accion.VOLVER_MENU);
		}
	}

	/**
	 * Muestra el panel especificado y oculta los demás paneles en la vista del
	 * menú. También actualiza el estado del menú según el panel mostrado.
	 */
	private void mostrarPanel(JPanel panel, String menu) {
		vistaMenu.getPanelGeneral().setVisible(false);
		vistaMenu.getPanelVerHorarios().setVisible(false);
		vistaMenu.getPanelOrganizarReuniones().setVisible(false);
		vistaMenu.getPanelAlumnos().setVisible(false);

		if (panel != null) {
			panel.setVisible(true);
		}
		vistaMenu.setEstadoMenu(menu == null ? "" : menu);
	}

	/**
	 * Maneja el proceso de inicio de sesión del usuario. Verifica las credenciales
	 * y, si son válidas, carga la información del usuario
	 */
	public void login() {
		try {
			Object response = Users.login(cliente, vistaLogin.getTextFieldUsuario().getText(),
					vistaLogin.getTextFieldContrasena().getText());
			if (response instanceof Users && response != null) {
				// Inicio de sesión exitoso
				usuario = (Users) response;
				vistaLogin.setVisible(false);
				vistaMenu.setVisible(true);
				mostrarPanel(vistaMenu.getPanelGeneral(), "");
				cargarDatosUsuario();
				actualizarTablaMiHorario();

			} else {
				String mensajeError = (String) response;
				vistaLogin.getLblError().setText("Error: " + mensajeError);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/*
	 * Carga los datos del usuario en la vista de perfil y menu.
	 */
	private void cargarDatosUsuario() {
		// Datos de menu
		vistaMenu.getLblNombreUsuario().setText(usuario.getNombre() + " " + usuario.getApellidos());
		vistaMenu.getLblRolUsuario().setText(usuario.getTipos().getName().substring(0, 1).toUpperCase()
				+ usuario.getTipos().getName().substring(1).toLowerCase());
		vistaMenu.cargarAvatar(usuario.getArgazkiaUrl());

		// Datos de ver perfil
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

	// Actualiza la tabla del horario del usuario actual, incluyendo las reuniones.
	private void actualizarTablaMiHorario() {
		try {
			DefaultTableModel modeloResultado = cargarModeloHorariosConReuniones(Horarios.getHorarios(cliente),
					Reuniones.getReunionesUsuario(cliente));
			vistaMenu.getPanelGeneral().getPanelHorarios().setModelo(modeloResultado);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(vistaMenu, "No se pudo cargar el horario: " + e.getMessage());
		}
	}

	// Actualiza la tabla de reuniones con la lista proporcionada
	private void actualizarTablaReuniones() {
		ArrayList<Reuniones> reuniones = Reuniones.getReunionesUsuario(cliente);
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
					dtf.format(reunion.getUpdatedAt().toLocalDateTime()), null, null });
		}
	}

	private void actualizarEstadoReunionDesdeTabla(boolean aceptar) {
		int filaVista = vistaMenu.getPanelOrganizarReuniones().getTableReuniones().getSelectedRow();
		if (filaVista < 0) {
			return;
		}

		int filaModelo = vistaMenu.getPanelOrganizarReuniones().getTableReuniones().convertRowIndexToModel(filaVista);
		DefaultTableModel modelo = vistaMenu.getPanelOrganizarReuniones().getModeloReuniones();

		int idReunion = ((Number) modelo.getValueAt(filaModelo, 0)).intValue();

		String estadoActual = Objects.toString(modelo.getValueAt(filaModelo, 3), "");
		String estadoDestino = obtenerEstadoDestino(aceptar, estadoActual);

		try {
			Reuniones respuesta = Reuniones.actualizarEstado(cliente, idReunion, estadoDestino);
			if (respuesta == null) {
				JOptionPane.showMessageDialog(vistaMenu, "No se pudo actualizar la reunión.");
				return;
			}
			actualizarTablaReuniones();
			actualizarTablaMiHorario();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(vistaMenu, "Error al actualizar la reunión: " + e.getMessage());
		}
	}

	private String obtenerEstadoDestino(boolean aceptar, String estadoActual) {
		String estadoNormalizado = estadoActual == null ? "" : estadoActual.trim().toUpperCase();
		if (aceptar) {
			return "ACEPTADA".equals(estadoNormalizado) ? "PENDIENTE" : "ACEPTADA";
		}
		return "DENEGADA".equals(estadoNormalizado) ? "PENDIENTE" : "DENEGADA";
	}

	// Actualiza la tabla de alumnos asignados al profesor actual
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
				modelo.addRow(new Object[] { alumno.getId(), alumno.getArgazkiaUrl(), nombreCompleto,
						alumno.getUsername(), alumno.getEmail(), alumno.getDni(), alumno.getDireccion(),
						alumno.getTelefono1(), alumno.getTelefono2() });
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(vistaMenu, "No se pudieron cargar los alumnos: " + e.getMessage());

		}
	}

	// Construye el modelo de horarios en una tabla a partir de la lista de horarios
	private DefaultTableModel cargarModeloHorarios(ArrayList<Horarios> horarios) {
		DefaultTableModel modeloTabla = new DefaultTableModel(
				new String[] { "", "Lunes", "Martes", "Miércoles", "Jueves", "Viernes" }, 0);
		Map<String, Object[]> filasPorHora = new TreeMap<>();
		for (Horarios horario : horarios) {
			/*
			 * Obtener o crear la fila correspondiente a la hora computeIfAbsent: si no
			 * existe la clave, crea una nueva fila Ejemplo: 8:00 se crea la fila si no
			 * existe
			 */
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

	// Construye el modelo de horarios en una tabla a partir de las listas de
	// horarios y reuniones
	private DefaultTableModel cargarModeloHorariosConReuniones(ArrayList<Horarios> horarios,
			ArrayList<Reuniones> reuniones) {
		DefaultTableModel modeloTabla = new DefaultTableModel(
				new String[] { "", "Lunes", "Martes", "Miércoles", "Jueves", "Viernes" }, 0);
		Map<String, Object[]> filasPorHora = new TreeMap<>();

		// Agregar horarios al modelo
		for (Horarios horario : horarios) {
			/*
			 * Obtener o crear la fila correspondiente a la hora computeIfAbsent: si no
			 * existe la clave, crea una nueva fila Ejemplo: 8:00 se crea la fila si no
			 * existe
			 */
			Object[] fila = filasPorHora.computeIfAbsent(horario.getHoraStr(), key -> crearFilaHorario(key));
			// Agregar el horario a la celda correspondiente
			agregarContenidoCelda(fila, horario.obtenerColumnaDia(), horario);
		}
		// Agregar reuniones al modelo
		for (Reuniones reunion : reuniones) {
			/*
			 * Obtener o crear la fila correspondiente a la hora computeIfAbsent: si no
			 * existe la clave, crea una nueva fila Ejemplo: 8:00 se crea la fila si no
			 * existe
			 */
			Object[] fila = filasPorHora.computeIfAbsent(reunion.obtenerHora(), key -> crearFilaHorario(key));
			// Agregar la reunión a la celda correspondiente
			agregarContenidoCelda(fila, reunion.obtenerColumnaDia(), reunion);
		}

		if (filasPorHora.isEmpty()) {
			return null;
		}
		for (Object[] fila : filasPorHora.values()) {
			modeloTabla.addRow(fila);
		}
		return modeloTabla;
	}

	/*
	 * Crea una nueva fila para el horario con la etiqueta de hora especificada. La
	 * fila tiene 6 columnas: una para la hora y cinco para los días de la semana.
	 */
	private Object[] crearFilaHorario(String etiquetaHora) {
		Object[] nuevaFila = new Object[6];
		nuevaFila[0] = etiquetaHora;
		return nuevaFila;
	}

	/*
	 * Agrega contenido a una celda de la fila en la columna especificada. Si la
	 * celda ya tiene contenido, lo convierte en una lista y agrega el nuevo
	 * contenido. Si la celda está vacía, simplemente asigna el nuevo contenido.
	 */
	private void agregarContenidoCelda(Object[] fila, int columna, Object contenido) {
		Object actual = fila[columna];

		/*
		 * Si la celda está vacía, asignar el nuevo contenido directamente Ejemplo: 1
		 * clase
		 */
		if (actual == null) {
			fila[columna] = contenido;
		} else if (actual instanceof List<?> lista) {
			/*
			 * Si ya es una lista, agregar el nuevo contenido a la lista existente
			 * Asegurarse de que la lista sea del tipo correcto Ejemplo: 1 clase y 2
			 * reuniones
			 */
			if (lista.isEmpty() || lista.get(0) instanceof Object) {
				List<Object> listaSegura = new ArrayList<>(lista.size());
				for (Object item : lista) {
					listaSegura.add(item);
				}
				listaSegura.add(contenido);
				fila[columna] = listaSegura;
			}
		} else {
			/*
			 * Si no es una lista, crear una nueva lista con el contenido actual y el nuevo
			 * Ejemplo: 1 clase y 1 reunión
			 */
			List<Object> combinados = new ArrayList<>();
			combinados.add(actual);
			combinados.add(contenido);
			fila[columna] = combinados;
		}
	}

	/*
	 * Actualiza la lista de profesores en el panel de ver horarios Funcion llamada
	 * desde procesarAccion accion VER_HORARIOS
	 */
	private void actualizarListaProfesores() {
		ArrayList<Users> profesores = usuario.getProfesores(cliente);
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

	// Muestra el horario del profesor seleccionado en la tabla
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

	/*
	 * Muestra el formulario para crear una nueva reunión.
	 */
	private void mostrarFormularioNuevaReunion() {
		List<Users> alumnos = usuario.getAlumnos(cliente);
		List<Centros> centros;
		try {
			centros = Centros.getCentros(cliente);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(vistaMenu, "No se pudieron cargar los centros: " + e.getMessage());
			return;
		}
		ventanaReunion.limpiarFormulario();
		ventanaReunion.setAlumnos(alumnos);
		ventanaReunion.setCentros(centros);
		ventanaReunion.setVisible(true);
	}

	/*
	 * Crea una nueva reunión con los datos del formulario y actualiza la tabla de
	 * reuniones si es exitoso.
	 */
	private void crearReunion() {
		try {
			Users alumno = (Users) ventanaReunion.getComboAlumnos().getSelectedItem();
			Centros centro = (Centros) ventanaReunion.getComboCentros().getSelectedItem();
			String titulo = ventanaReunion.getTxtTitulo().getText().trim();
			String asunto = ventanaReunion.getTxtAsunto().getText().trim();
			String aula = ventanaReunion.getTxtAula().getText().trim();
			Date fechaSeleccionada = ventanaReunion.getSelectorFecha().getDate();
			Date horaSeleccionada = (Date) ventanaReunion.getSelectorHora().getValue();

			if (alumno == null || centro == null || titulo.isEmpty() || asunto.isEmpty() || aula.isEmpty()
					|| fechaSeleccionada == null || horaSeleccionada == null) {
				ventanaReunion.getLblEstado().setText("Completa todos los campos antes de crear la reunión.");
				return;
			}

			LocalDate fecha = fechaSeleccionada.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			LocalTime hora = horaSeleccionada.toInstant().atZone(ZoneId.systemDefault()).toLocalTime();
			LocalDateTime fechaHora = LocalDateTime.of(fecha, hora.withSecond(0).withNano(0));
			Timestamp timestamp = Timestamp.valueOf(fechaHora);

			Reuniones nuevaReunion = new Reuniones(alumno, usuario, titulo, asunto, aula, timestamp, centro,
					centro.getCCEN());

			Object response = nuevaReunion.crearReunion(cliente);
			if (response instanceof Reuniones) {
				ventanaReunion.limpiarFormulario();
				actualizarTablaReuniones();
				actualizarTablaMiHorario();
				ventanaReunion.setVisible(false);
			} else if (response instanceof String mensaje) {
				ventanaReunion.getLblEstado().setText(mensaje);
			} else {
				ventanaReunion.getLblEstado().setText("No se pudo crear la reunión.");
			}
		} catch (Exception e) {
			ventanaReunion.getLblEstado().setText("Error: " + e.getMessage());
		}
	}

}
