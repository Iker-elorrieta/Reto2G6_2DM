package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import cliente.Cliente;
import modelo.Users;
import vista.Inicio;
import vista.PantallaMenu;

public class Controlador implements ActionListener {
	private Inicio vistaLogin;
	private Cliente cliente;
	private Users usuario;
	private PantallaMenu vistaMenu;

	public Controlador(Inicio vistaLogin) {
		this.vistaLogin = vistaLogin;
		vistaMenu = new PantallaMenu();
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
		default:
			break;
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
			String r = (String) cliente.enviarRequest("logout",new ArrayList<>());

			vistaMenu.setVisible(false);
			vistaLogin.setVisible(true);
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
