package vista;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

public class Inicio extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel panelLogo;
	private PanelLogin panelLogin;
	/**
	 * Create the frame.
	 */
	public Inicio() {
		setTitle("Framework educativo - CIFP Elorrieta-Errekamari LHII");
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 460, 564);
		contentPane =  crearPanelconImagen("/Fondo.png");
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		panelLogo = crearPanelconImagen("/Elorrieta_White.png");
		panelLogo.setOpaque(false);
		panelLogo.setBounds(61, 36, 321, 95);
		contentPane.add(panelLogo);
		
		panelLogin = new PanelLogin();
		contentPane.add(panelLogin);
		
	}
	public static JPanel crearPanelconImagen(String rutaImagen) {
		return new JPanel() {
			private static final long serialVersionUID = 1L;
			private Image backgroundImage = new ImageIcon(Inicio.class.getResource(rutaImagen)).getImage();

			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
			}
		};
	}
	public PanelLogin getPanelLogin() {
		return panelLogin;
	}
	public void setPanelLogin(PanelLogin panelLogin) {
		this.panelLogin = panelLogin;
	}
	
}
