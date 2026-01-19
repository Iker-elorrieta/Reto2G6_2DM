package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import cliente.Cliente;
import modelo.Horarios;
import modelo.Users;
import vista.Inicio;
import vista.PantallaMenu;
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
		
		vistaMenu.getBtnDesconectar().setActionCommand("DESCONECTAR");
		vistaMenu.getBtnDesconectar().addActionListener(this);
		
		vistaMenu.getPanelOrganizarReuniones().getBtnVolver().setActionCommand("VOLVER_MENU");
		vistaMenu.getPanelOrganizarReuniones().getBtnVolver().addActionListener(this);
		
		vistaMenu.getPanelVerHorarios().getBtnVolver().setActionCommand("VOLVER_MENU");
		vistaMenu.getPanelVerHorarios().getBtnVolver().addActionListener(this);
		
		vistaMenu.getPanelGeneral().getBtnVerOtrosHorarios().setActionCommand("VER_HORARIOS");
		vistaMenu.getPanelGeneral().getBtnVerOtrosHorarios().addActionListener(this);
		
		vistaMenu.getPanelGeneral().getBtnOrganizarReuniones().setActionCommand("ORGANIZAR_REUNIONES");
		vistaMenu.getPanelGeneral().getBtnOrganizarReuniones().addActionListener(this);
		
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
			break;
		case "ORGANIZAR_REUNIONES":
			vistaMenu.getPanelGeneral().setVisible(false);
			vistaMenu.getPanelOrganizarReuniones().setVisible(true);
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
		if (e.getSource() == vistaMenu.getPanelPerfil() ) {
			actionPerformed(new ActionEvent(vistaMenu.getPanelAvatar(), ActionEvent.ACTION_PERFORMED,
					"ABRIR_PERFIL"));
		} 
	}
	
	public void login() {
		ArrayList<Object> datos = new ArrayList<>();
		datos.add(vistaLogin.getPanelLogin().getTextFieldUsuario().getText());
		datos.add(vistaLogin.getPanelLogin().getTextFieldContrasena().getText());

		try {
			Object response = cliente.enviarRequest("login",datos);
			if (response instanceof Users && response != null) {
				usuario = (Users) response;
				
				if (usuario.getTipos().getId() != 3) {
					vistaLogin.getPanelLogin().getLblError().setText("Usuario no autorizado para usar la aplicación.");
				} else {
					JOptionPane.showMessageDialog(null, "¡Bienvenido, " + usuario.getNombre()+"!");
					vistaLogin.setVisible(false);
					vistaMenu.setVisible(true);
					vistaMenu.getLblNombreUsuario().setText(usuario.getNombre()+" "+usuario.getApellidos());
					vistaMenu.getLblRolUsuario().setText(usuario.getTipos().getName().substring(0, 1).toUpperCase() + usuario.getTipos().getName().substring(1).toLowerCase());
					vistaMenu.cargarAvatar(usuario.getArgazkiaUrl());
					cargarHorariosUsuario();
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
			cliente.enviarRequest("logout",new ArrayList<>());

			vistaMenu.setVisible(false);
			vistaLogin.setVisible(true);
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	public void cargarDatosUsuario(Users usuario) {
		vistaPerfil.getLblNombreUsuario().setText(usuario.getNombre() + " " + usuario.getApellidos());
		vistaPerfil.getLblRolUsuario().setText(usuario.getTipos().getName().substring(0, 1).toUpperCase() + usuario.getTipos().getName().substring(1).toLowerCase());
		vistaPerfil.getLblUsuario().setText(usuario.getUsername());
		vistaPerfil.getLblEmail().setText(usuario.getEmail());
		vistaPerfil.getLblTelefono1().setText(usuario.getTelefono1());
		vistaPerfil.getLblTelefono2().setText(usuario.getTelefono2());
		vistaPerfil.getLblDireccion().setText(usuario.getDireccion());
		vistaPerfil.getLblDNI().setText(usuario.getDni());		
		
		vistaPerfil.cargarAvatar(usuario.getArgazkiaUrl());
	}

	private void cargarHorariosUsuario() {
		try {
			Object response = cliente.enviarRequest("get_horarios", new ArrayList<>());
			if (response instanceof ArrayList<?>) {
				ArrayList<Horarios> horarios = new ArrayList<>();
				for (Object elemento : (ArrayList<?>) response) {
					if (elemento instanceof Horarios) {
						horarios.add((Horarios) elemento);
					}
				}
				actualizarTablaHorarios(horarios);
			} else if (response instanceof String) {
				JOptionPane.showMessageDialog(vistaMenu, response);
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(vistaMenu, "No se pudo cargar el horario: " + e.getMessage());
		}
	}

	private void actualizarTablaHorarios(ArrayList<Horarios> horarios) {
		DefaultTableModel modeloTabla = vistaMenu.getPanelGeneral().getPanelHorarios().getModelo();
		modeloTabla.setRowCount(0);
		
		if (horarios == null || horarios.isEmpty()) {
			return;
		}
		
		Map<Byte, String[]> filasPorHora = new TreeMap<>();
		for (Horarios horario : horarios) {
			if (horario == null) {
				continue;
			}
			Byte bloque = Byte.valueOf(horario.getHora());
			String[] fila = filasPorHora.computeIfAbsent(bloque, key -> crearFilaBase(key.byteValue()));
			int columna = obtenerColumnaDia(horario.getDia());
			if (columna == -1) {
				continue;
			}
			fila[columna] = describirModulo(horario);
		}
		
		for (String[] fila : filasPorHora.values()) {
			modeloTabla.addRow(fila);
		}
	}

	private String[] crearFilaBase(byte bloqueHora) {
		String[] fila = new String[8];
		fila[0] = formatearBloqueHoraria(bloqueHora);
		return fila;
	}

	private String formatearBloqueHoraria(byte bloqueHora) {
		return String.format(Locale.ROOT, "Tramo %d", bloqueHora);
	}

	private int obtenerColumnaDia(String dia) {
		if (dia == null) {
			return -1;
		}
		
		String normalizado = dia.trim().toUpperCase(Locale.ROOT);
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
		case "SABADO":
		case "SÁBADO":
			return 6;
		case "DOMINGO":
			return 7;
		default:
			return -1;
		}
	}

	private String describirModulo(Horarios horario) {
		String nombreModulo = null;
		if (horario.getModulos() != null) {
			nombreModulo = horario.getModulos().getNombre();
		}
		String aula = horario.getAula();
		boolean tieneModulo = nombreModulo != null && !nombreModulo.trim().isEmpty();
		boolean tieneAula = aula != null && !aula.trim().isEmpty();
		
		if (tieneModulo && tieneAula) {
			return nombreModulo.trim() + " - " + aula.trim();
		}
		
		if (tieneModulo) {
			return nombreModulo.trim();
		}
		
		if (tieneAula) {
			return aula.trim();
		}
		
		return "Disponible";
	}
}
