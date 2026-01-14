package controlador;

import java.awt.Menu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import cliente.Cliente;
import modelo.Request;
import modelo.Users;
import vista.Login;
import vista.PantallaMenu;

public class Controlador implements ActionListener {
	private Login vistaLogin;
	private Cliente cliente;
	private Users usuario;
	private PantallaMenu vistaMenu;

	public Controlador(Login vistaLogin) {
		this.vistaLogin = vistaLogin;
		inicializarControlador();
		try {
			cliente = new Cliente("localhost", 5000);
		} catch (Exception e) {
			e.printStackTrace();
		}
		vistaMenu = new PantallaMenu();
	}

	/**
	 * Inicializa y registra todos los listeners y valores iniciales de los paneles.
	 * Aquí se asocian comandos de acción y manejadores a botones y componentes.
	 */
	private void inicializarControlador() {
		vistaLogin.getBtnLogin().setActionCommand("INICIAR_SESION");
		vistaLogin.getBtnLogin().addActionListener(this);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		final String cmd = e.getActionCommand();

		switch (cmd) {
		case "INICIAR_SESION":
			login();
			break;

		default:
			break;
		}

	}

	public void login() {
		Request r = new Request("login");
		r.addDato("username", vistaLogin.getTxtUsuario().getText());
		r.addDato("password", vistaLogin.getPasswordField().getText());
		try {
			Request response = cliente.enviarRequest(r);
			if (response.getHeader().equals("login_correcto")) {
				usuario =  (Users) response.getDato("usuario");
				JOptionPane.showMessageDialog(null, "Bienvenido. " + usuario.getNombre());
				vistaLogin.setVisible(false);
				vistaMenu.setVisible(true);
			}else {
				String mensajeError = (String) response.getDato("mensaje");
				JOptionPane.showMessageDialog(null, "Error al iniciar sesión: " + mensajeError);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
