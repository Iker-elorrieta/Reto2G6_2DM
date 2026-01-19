package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import cliente.Cliente;
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
		vistaPerfil.getLblEmail().setText(usuario.getEmail());
		vistaPerfil.getLblTelefono1().setText(usuario.getTelefono1());
		vistaPerfil.getLblTelefono2().setText(usuario.getTelefono2());
		vistaPerfil.getLblDireccion().setText(usuario.getDireccion());
		vistaPerfil.getLblDNI().setText(usuario.getDni());		
		
		vistaPerfil.cargarAvatar(usuario.getArgazkiaUrl());
	}
}
