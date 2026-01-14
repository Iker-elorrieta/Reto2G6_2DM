package vista;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class PanelLogin extends JPanel {
	
	private static final long serialVersionUID = 1L;
	private JTextField textField;
	private JPasswordField textFieldContrasena;
	private JButton btnIniciarSesion;
	private JLabel lblRegistrar;
	private JLabel lblError;
	private JPanel contentPane;
	
	public PanelLogin() {
		setBackground(new Color(255, 255, 255));
		setOpaque(false);
		setBounds(38, 150, 374, 295);
		setLayout(null);

        contentPane = new JPanel();
        contentPane.setOpaque(false);
        contentPane.setLayout(null);
        contentPane.setBounds(0, 0, 374, 295);
        add(contentPane);

		JLabel lblNewLabel = new JLabel("Nombre de usuario");
		lblNewLabel.setFont(new Font("Noto Sans", Font.BOLD, 15));
		lblNewLabel.setForeground(new Color(0, 0, 0));
		lblNewLabel.setBounds(38, 71, 294, 14);
		contentPane.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setFont(new Font("Raleway", Font.PLAIN, 15));
		textField.setBounds(38, 96, 294, 30);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblContrasena = new JLabel("Contraseña");
		lblContrasena.setFont(new Font("Raleway", Font.BOLD, 15));
		lblContrasena.setForeground(new Color(0, 0, 0));
		lblContrasena.setBounds(38, 136, 294, 14);
		contentPane.add(lblContrasena);
		
		textFieldContrasena = new JPasswordField();
		textFieldContrasena.setFont(new Font("Raleway", Font.PLAIN, 15));
		textFieldContrasena.setBounds(38, 161, 294, 30);
		contentPane.add(textFieldContrasena);
		
		lblRegistrar = new JLabel("Aplicación exclusiva para profesores");
		lblRegistrar.setFont(new Font("Raleway", Font.BOLD, 11));
		lblRegistrar.setForeground(new Color(0, 0, 0));
		lblRegistrar.setHorizontalAlignment(JLabel.CENTER);
		lblRegistrar.setBounds(38, 260, 294, 14);
		contentPane.add(lblRegistrar);
		
		btnIniciarSesion = new JButton("Iniciar sesión");
		btnIniciarSesion.setFont(new Font("Raleway", Font.BOLD, 15));
		btnIniciarSesion.setForeground(new Color(255, 255, 255));
		btnIniciarSesion.setBackground(Color.decode("#0092A5"));
		btnIniciarSesion.setBounds(38, 219, 294, 30);
		contentPane.add(btnIniciarSesion);
		
		JLabel lblLogin = new JLabel("Iniciar sesión");
		lblLogin.setFont(new Font("Raleway", Font.BOLD, 24));
		lblLogin.setBackground(Color.WHITE);
		lblLogin.setOpaque(false);
		lblLogin.setHorizontalAlignment(JLabel.CENTER);
		lblLogin.setBounds(0, 0, 374, 43);
		contentPane.add(lblLogin);
		
		lblError = new JLabel("");
		lblError.setForeground(new Color(255, 0, 0));
		lblError.setBounds(38, 194, 294, 14);
		lblError.setFont(new Font("Raleway", Font.BOLD, 11));

		contentPane.add(lblError);
		

	}
	
	@Override
	protected void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g.create();
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2.setColor(getBackground());
		g2.fillRoundRect(0, 0, getWidth(), getHeight(), 30, 30);
		g2.dispose();
	}
	/************** Getters **************/
	public JTextField getTextFieldUsuario() {
		return textField;
	}
	
	public JLabel getLblError() {
		return lblError;
	}

	public void setLblError(JLabel lblError) {
		this.lblError = lblError;
	}

	public JTextField getTextFieldContrasena() {
		return textFieldContrasena;
	}
	
	public JButton getBtnIniciarSesion() {
		return btnIniciarSesion;
	}
	
	public JLabel getLblRegistrar() {
		return lblRegistrar;
	}

	public JPanel getContentPane() {
		return contentPane;
	}
}