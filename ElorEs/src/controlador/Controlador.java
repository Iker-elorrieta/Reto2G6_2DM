package controlador;

import java.awt.Menu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JOptionPane;

import cliente.Cliente;
import modelo.Request;
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
		Request r = new Request("login");
		r.addDato("username", vistaLogin.getPanelLogin().getTextFieldUsuario().getText());
		r.addDato("password", vistaLogin.getPanelLogin().getTextFieldContrasena().getText());
		try {
			Request response = cliente.enviarRequest(r);
			if (response.getHeader().equals("login_correcto")) {
				usuario = (Users) response.getDato("usuario");
				if (usuario.getTipos().getId() != 3) {
					vistaLogin.getPanelLogin().getLblError().setText("Usuario no autorizado para usar la aplicación.");
				} else {
					JOptionPane.showMessageDialog(null, "Bienvenido. " + usuario.getNombre());
					vistaLogin.setVisible(false);
					vistaMenu.setVisible(true);
				}
			} else {
				String mensajeError = (String) response.getDato("mensaje");
				vistaLogin.getPanelLogin().getLblError().setText("Error: " + mensajeError);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	public void desconectar() {
		try {
			Request r = cliente.enviarRequest(new Request("logout"));
			String mensajeError = (String) r.getDato("mensaje");

			JOptionPane.showMessageDialog(null, r);
			vistaMenu.setVisible(false);
			vistaLogin.setVisible(true);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
