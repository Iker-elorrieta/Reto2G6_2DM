package vista;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.geom.RoundRectangle2D;
import java.net.URI;
import java.net.URL;

import javax.swing.JLabel;

public class VerPerfil extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel panelAvatar;
	private JLabel lblNombreUsuario;
	private JLabel lblRolUsuario;
	private JLabel lblUsuarioTitulo;
	private JLabel lblCorreoElectrnico;
	private JLabel lblEmail;
	private JLabel lblDNI;
	private JLabel lblDNITitulo;
	private JLabel lblDireccionTitulo;
	private JLabel lblDireccion;
	private JLabel lblTelefono1Titulo;
	private JLabel lblTelefono1;
	private JLabel lblTelefono2Titulo;
	private JLabel lblTelefono2;
	private JPanel panel;
	private JLabel lblUsuario;
	private JButton btnDesconectar;
	private Font fuenteBold = new Font("Raleway", Font.BOLD, 20);


	public VerPerfil() {
		setTitle("Consultar perfil - CIFP Elorrieta-Errekamari LHII");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 460, 411);
		setLocationRelativeTo(null);
		setResizable(false);

		contentPane = Inicio.crearPanelconImagen("/Fondo.png");
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		panel = new JPanel() {
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
		panel.setBounds(10, 32, 424, 324);
		contentPane.add(panel);
		panel.setLayout(null);
		
		lblRolUsuario = new JLabel("RolUsuario");
		lblRolUsuario.setBounds(70, 41, 316, 20);
		panel.add(lblRolUsuario);
		lblRolUsuario.setFont(new Font("Raleway", Font.PLAIN, 15));
		
		lblNombreUsuario = new JLabel("Nombre Usuario");
		lblNombreUsuario.setBounds(70, 21, 316, 20);
		lblNombreUsuario.setFont(new Font("Raleway", Font.BOLD, 15));
		panel.add(lblNombreUsuario);
		
		lblUsuarioTitulo = new JLabel("Usuario:");
		lblUsuarioTitulo.setFont(new Font("Raleway", Font.BOLD, 15));
		lblUsuarioTitulo.setBounds(10, 89, 72, 20);
		panel.add(lblUsuarioTitulo);
		
		lblUsuario = new JLabel("NOMBRE");
		lblUsuario.setFont(new Font("Raleway", Font.PLAIN, 15));
		lblUsuario.setBounds(81, 89, 333, 20);
		panel.add(lblUsuario);
		
		lblCorreoElectrnico = new JLabel("Correo electrónico:");
		lblCorreoElectrnico.setFont(new Font("Raleway", Font.BOLD, 15));
		lblCorreoElectrnico.setBounds(10, 120, 160, 20);
		panel.add(lblCorreoElectrnico);
		
		lblEmail = new JLabel("NOMBRE");
		lblEmail.setFont(new Font("Raleway", Font.PLAIN, 15));
		lblEmail.setBounds(164, 120, 250, 20);
		panel.add(lblEmail);
		
		lblDNITitulo = new JLabel("DNI:");
		lblDNITitulo.setFont(new Font("Raleway", Font.BOLD, 15));
		lblDNITitulo.setBounds(10, 151, 41, 20);
		panel.add(lblDNITitulo);
		
		lblDNI = new JLabel("NOMBRE");
		lblDNI.setFont(new Font("Raleway", Font.PLAIN, 15));
		lblDNI.setBounds(49, 151, 365, 20);
		panel.add(lblDNI);
		
		lblDireccionTitulo = new JLabel("Dirección:");
		lblDireccionTitulo.setFont(new Font("Raleway", Font.BOLD, 15));
		lblDireccionTitulo.setBounds(10, 182, 85, 20);
		panel.add(lblDireccionTitulo);
		
		lblDireccion = new JLabel("NOMBRE");
		lblDireccion.setFont(new Font("Dialog", Font.PLAIN, 15));
		lblDireccion.setBounds(91, 182, 323, 20);
		panel.add(lblDireccion);
		
		lblTelefono1Titulo = new JLabel("Teléfono 1:");
		lblTelefono1Titulo.setFont(new Font("Raleway", Font.BOLD, 15));
		lblTelefono1Titulo.setBounds(10, 213, 100, 20);
		panel.add(lblTelefono1Titulo);
		
		lblTelefono1 = new JLabel("NOMBRE");
		lblTelefono1.setFont(new Font("Raleway", Font.PLAIN, 15));
		lblTelefono1.setBounds(107, 213, 307, 20);
		panel.add(lblTelefono1);
		
		lblTelefono2Titulo = new JLabel("Teléfono 2:");
		lblTelefono2Titulo.setFont(new Font("Raleway", Font.BOLD, 15));
		lblTelefono2Titulo.setBounds(10, 244, 100, 20);
		panel.add(lblTelefono2Titulo);
		
		lblTelefono2 = new JLabel("NOMBRE");
		lblTelefono2.setFont(new Font("Raleway", Font.PLAIN, 15));
		lblTelefono2.setBounds(107, 244, 307, 20);
		panel.add(lblTelefono2);
	
		btnDesconectar = new JButton("Desconectar");
		btnDesconectar.setForeground(new Color(0, 0, 0));
		btnDesconectar.setFont(fuenteBold);
		btnDesconectar.setBackground(new Color(255, 255, 255));
		btnDesconectar.setBounds(10, 275, 404, 38);
		panel.add(btnDesconectar);
		

	}

	public JButton getBtnDesconectar() {
		return btnDesconectar;
	}


	public void setBtnDesconectar(JButton btnDesconectar) {
		this.btnDesconectar = btnDesconectar;
	}

	public void cargarAvatar(String argazkiaUrl) {
		panelAvatar = new JPanel() {
			    private static final long serialVersionUID = 1L;
			    private Image backgroundImage;

			    {
			        try {
			            if (argazkiaUrl  != null && !argazkiaUrl.isBlank() && !argazkiaUrl.equals("null")) {
			                URI uri = new URI(argazkiaUrl);
			                URL url = uri.toURL();
			                backgroundImage = ImageIO.read(url);		
			               
			            } else {
			                backgroundImage = new ImageIcon(Inicio.class.getResource("/avatar.png")).getImage();
			            }
			        } catch (Exception e) {
			            e.printStackTrace();
			            backgroundImage = new ImageIcon(Inicio.class.getResource("/avatar.png")).getImage();
			        }
			        setOpaque(false); // hacer el panel transparente
			    }

			    @Override
			    protected void paintComponent(Graphics g) {
			        Graphics2D g2 = (Graphics2D) g.create();
			        try {
			            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			            int arc = 90;
			            RoundRectangle2D round = new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), arc, arc);
			            g2.setClip(round);
			            if (backgroundImage != null) {
			                g2.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
			            } else {
			                g2.setColor(getBackground());
			                g2.fill(round);
			            }
			            super.paintComponent(g2);
			        } finally {
			            g2.dispose();
			        }
			    }
			};
			panelAvatar.setBounds(10, 11, 50, 50);
			panelAvatar.setOpaque(false);
			panel.add(panelAvatar);
			panel.revalidate();
			panel.repaint();

	}
	

	public JLabel getLblUsuario() {
		return lblUsuario;
	}

	public void setLblUsuario(JLabel lblUsuario) {
		this.lblUsuario = lblUsuario;
	}

	public JLabel getLblNombreUsuario() {
		return lblNombreUsuario;
	}

	public void setLblNombreUsuario(JLabel lblNombreUsuario) {
		this.lblNombreUsuario = lblNombreUsuario;
	}

	public JLabel getLblRolUsuario() {
		return lblRolUsuario;
	}

	public void setLblRolUsuario(JLabel lblRolUsuario) {
		this.lblRolUsuario = lblRolUsuario;
	}

	public JLabel getLblEmail() {
		return lblEmail;
	}

	public void setLblEmail(JLabel lblEmail) {
		this.lblEmail = lblEmail;
	}

	public JLabel getLblDNI() {
		return lblDNI;
	}

	public void setLblDNI(JLabel lblDNI) {
		this.lblDNI = lblDNI;
	}

	public JLabel getLblDireccion() {
		return lblDireccion;
	}

	public void setLblDireccion(JLabel lblDireccion) {
		this.lblDireccion = lblDireccion;
	}

	public JLabel getLblTelefono1() {
		return lblTelefono1;
	}

	public void setLblTelefono1(JLabel lblTelefono1) {
		this.lblTelefono1 = lblTelefono1;
	}

	public JLabel getLblTelefono2() {
		return lblTelefono2;
	}

	public void setLblTelefono2(JLabel lblTelefono2) {
		this.lblTelefono2 = lblTelefono2;
	}


}
