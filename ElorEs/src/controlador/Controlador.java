package controlador;

import java.awt.Menu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import cliente.Cliente;
import modelo.Request;
import modelo.Response;
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
		r.addParametro("username", vistaLogin.getTxtUsuario().getText());
		r.addParametro("password", vistaLogin.getPasswordField().getText());
		try {
			Response response = cliente.enviarRequest(r);
			if (response.getHeader().equals("login_correcto")) {
				usuario = new Users();
				usuario = usuario.getUsuarioLogged(cliente);
				JOptionPane.showMessageDialog(null, "Bienvenido. " + usuario.getNombre());
				vistaLogin.setVisible(false);
				vistaMenu.setVisible(true);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
