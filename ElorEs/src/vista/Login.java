package vista;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class Login extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel panelLogo;
	private JTextField textFieldUsuario;
	private JPasswordField textFieldContrasena;
	private JButton btnIniciarSesion;
	private JLabel lblRegistrar;
	private JLabel lblError;

	/**
	 * Create the frame.
	 */
	public Login() {
		setTitle("Framework educativo - CIFP Elorrieta-Errekamari LHII");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 460, 564);
		setLocationRelativeTo(null);
		contentPane = crearPanelconImagen("/Fondo.png");
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		panelLogo = crearPanelconImagen("/Elorrieta_White.png");
		panelLogo.setOpaque(false);
		panelLogo.setBounds(61, 36, 321, 95);
		contentPane.add(panelLogo);

		JPanel panelLogin = new JPanel() {
			private static final long serialVersionUID = 1L;

			@Override
			protected void paintComponent(Graphics g) {
				Graphics2D g2 = (Graphics2D) g.create();
				g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
				g2.setColor(getBackground());
				g2.fillRoundRect(0, 0, getWidth(), getHeight(), 30, 30);
				g2.dispose();
			}
		};
		panelLogin.setBackground(Color.WHITE);
		panelLogin.setOpaque(false);
		panelLogin.setLayout(null);
		panelLogin.setBounds(38, 150, 374, 295);
		contentPane.add(panelLogin);
		JLabel lblLogin = new JLabel("Iniciar sesión");
		lblLogin.setFont(new Font("Raleway", Font.BOLD, 24));
		lblLogin.setHorizontalAlignment(JLabel.CENTER);
		lblLogin.setBounds(0, 0, 374, 43);
		panelLogin.add(lblLogin);

		JLabel lblUsuario = new JLabel("Nombre de usuario");
		lblUsuario.setFont(new Font("Noto Sans", Font.BOLD, 15));
		lblUsuario.setForeground(Color.BLACK);
		lblUsuario.setBounds(38, 71, 294, 14);
		panelLogin.add(lblUsuario);

		textFieldUsuario = new JTextField();
		textFieldUsuario.setFont(new Font("Raleway", Font.PLAIN, 15));
		textFieldUsuario.setBounds(38, 96, 294, 30);
		panelLogin.add(textFieldUsuario);
		textFieldUsuario.setColumns(10);

		JLabel lblContrasena = new JLabel("Contraseña");
		lblContrasena.setFont(new Font("Raleway", Font.BOLD, 15));
		lblContrasena.setForeground(Color.BLACK);
		lblContrasena.setBounds(38, 136, 294, 14);
		panelLogin.add(lblContrasena);

		textFieldContrasena = new JPasswordField();
		textFieldContrasena.setFont(new Font("Raleway", Font.PLAIN, 15));
		textFieldContrasena.setBounds(38, 161, 294, 30);
		panelLogin.add(textFieldContrasena);

		lblError = new JLabel("");
		lblError.setForeground(Color.RED);
		lblError.setFont(new Font("Raleway", Font.BOLD, 11));
		lblError.setBounds(38, 194, 294, 14);
		panelLogin.add(lblError);

		btnIniciarSesion = new JButton("Iniciar sesión");
		btnIniciarSesion.setFont(new Font("Raleway", Font.BOLD, 15));
		btnIniciarSesion.setForeground(Color.WHITE);
		btnIniciarSesion.setBackground(Color.decode("#0092A5"));
		btnIniciarSesion.setBounds(38, 219, 294, 30);
		panelLogin.add(btnIniciarSesion);

		lblRegistrar = new JLabel("Aplicación exclusiva para profesores");
		lblRegistrar.setFont(new Font("Raleway", Font.BOLD, 11));
		lblRegistrar.setForeground(Color.BLACK);
		lblRegistrar.setHorizontalAlignment(JLabel.CENTER);
		lblRegistrar.setBounds(38, 260, 294, 14);
		panelLogin.add(lblRegistrar);

	}

	public static JPanel crearPanelconImagen(String rutaImagen) {
		return new JPanel() {
			private static final long serialVersionUID = 1L;
			private Image backgroundImage = new ImageIcon(Login.class.getResource(rutaImagen)).getImage();

			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
			}
		};
	}

	public JButton getBtnIniciarSesion() {
		return btnIniciarSesion;
	}

	public JTextField getTextFieldUsuario() {
		return textFieldUsuario;
	}

	public JTextField getTextFieldContrasena() {
		return textFieldContrasena;
	}

	public JLabel getLblRegistrar() {
		return lblRegistrar;
	}

	public JLabel getLblError() {
		return lblError;
	}
}
