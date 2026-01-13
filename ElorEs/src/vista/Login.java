package vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controlador.Controlador;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Login extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtUsuario;
	private JLabel lblContrasena;
	private JLabel lblUsuario;
	private JPasswordField passwordField;
	private JButton btnLogin;

	/**
	 * Create the frame.
	 */
	public Login() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 653, 469);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblUsuario = new JLabel("Usuario:");
		lblUsuario.setBounds(194, 151, 46, 14);
		contentPane.add(lblUsuario);
		
		lblContrasena = new JLabel("Contraseña:");
		lblContrasena.setBounds(194, 189, 75, 14);
		contentPane.add(lblContrasena);
		
		txtUsuario = new JTextField();
		txtUsuario.setBounds(293, 148, 148, 20);
		contentPane.add(txtUsuario);
		txtUsuario.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(293, 186, 148, 20);
		contentPane.add(passwordField);
		
		btnLogin = new JButton("Prueba Login");
	
		btnLogin.setBounds(328, 245, 89, 23);
		contentPane.add(btnLogin);

	}

	public JTextField getTxtUsuario() {
		return txtUsuario;
	}

	public void setTxtUsuario(JTextField txtUsuario) {
		this.txtUsuario = txtUsuario;
	}

	public JTextField getPasswordField() {
		return passwordField;
	}

	public void setPasswordField(JPasswordField passwordField) {
		this.passwordField = passwordField;
	}

	public JButton getBtnLogin() {
		return btnLogin;
	}

	public void setBtnLogin(JButton btnLogin) {
		this.btnLogin = btnLogin;
	}
	
	
}
