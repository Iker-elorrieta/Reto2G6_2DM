package vista;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.geom.RoundRectangle2D;
import java.awt.event.ActionEvent;

public class PantallaMenu extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton btnDesconectar;
	private JButton btnReuniones;
	private JButton btnHorarios;
	private JButton btnAlumnos;
	private JButton btnPerfil;
	private Font fuenteBold = new Font("Raleway", Font.BOLD, 20);


	/**
	 * Create the frame.
	 */
	public PantallaMenu() {
		setTitle("Framework educativo - CIFP Elorrieta-Errekamari LHII");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 1000, 650);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		btnPerfil = new JButton("Consultar perfil");
		btnPerfil.setBounds(556, 112, 148, 23);
		contentPane.add(btnPerfil);
		
		btnAlumnos = new JButton("Consultar alumnos");
		btnAlumnos.setBounds(556, 146, 148, 23);
		contentPane.add(btnAlumnos);
		
		btnHorarios = new JButton("Consultar horarios");
		btnHorarios.setBounds(556, 180, 148, 23);
		contentPane.add(btnHorarios);
		
		btnReuniones = new JButton("Reuniones");
		btnReuniones.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnReuniones.setBounds(556, 214, 148, 23);
		contentPane.add(btnReuniones);
		
		JPanel panelIzquierda = new JPanel() {
			private static final long serialVersionUID = 1L;
			private Image backgroundImage;
			{
				backgroundImage = new ImageIcon(Inicio.class.getResource("/Fondo.png")).getImage();
				setOpaque(false); // hacer el panel transparente
			}

			@Override
			protected void paintComponent(Graphics g) {
				Graphics2D g2 = (Graphics2D) g.create();
				try {
					g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
					int arc = 30;
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

		panelIzquierda.setBorder(null);
		panelIzquierda.setBounds(10, 10, 304, 590);
		panelIzquierda.setLayout(null);
		panelIzquierda.setOpaque(false);
		contentPane.add(panelIzquierda);
		
		btnDesconectar = new JButton("Desconectar");
		btnDesconectar.setForeground(new Color(0, 0, 0));
		btnDesconectar.setFont(fuenteBold);
		btnDesconectar.setBackground(new Color(255, 255, 255));
		btnDesconectar.setBounds(10, 540, 284, 38);
		panelIzquierda.add(btnDesconectar);

	

		JPanel panelLogo = new JPanel() {
			private static final long serialVersionUID = 1L;
			private Image backgroundImage = new ImageIcon(Inicio.class.getResource("/Elorrieta_White.png")).getImage();

			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
			}
		};
		panelLogo.setBounds(30, 10, 230, 95);
		panelLogo.setOpaque(false);
		panelIzquierda.add(panelLogo);

	}


	public JButton getBtnDesconectar() {
		return btnDesconectar;
	}


	public void setBtnDesconectar(JButton btnDesconectar) {
		this.btnDesconectar = btnDesconectar;
	}
	
	
}
