package vista;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Cursor;
import java.awt.geom.RoundRectangle2D;
import java.net.URI;
import java.net.URL;
import javax.swing.JLabel;

public class PantallaMenu extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton btnDesconectar;
	private Font fuenteBold = new Font("Raleway", Font.BOLD, 20);
	private JLabel lblNombreUsuario;
	private JLabel lblRolUsuario;
	private JPanel panelAvatar;
	private JPanel panelIzquierda;
	private PanelGeneral panelGeneral;
	private PanelVerHorarios panelVerHorarios;
	private PanelOrganizarReuniones panelOrganizarReuniones;
	private JPanel panelPerfil;
	private JButton btnConsultarAlumnos;


	/**
	 * Create the frame.
	 */
	public PantallaMenu() {
		setTitle("Framework educativo - CIFP Elorrieta-Errekamari LHII");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 1000, 717);
		setLayout(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		panelPerfil = new JPanel();
		panelIzquierda = new JPanel() {
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
		panelIzquierda.setBounds(10, 10, 304, 657);
		panelIzquierda.setLayout(null);
		panelIzquierda.setOpaque(false);
		contentPane.add(panelIzquierda);
		
		panelPerfil.setOpaque(false);
		panelPerfil.setBounds(0, 492, 304, 56);
		panelPerfil.setLayout(null);
		panelIzquierda.add(panelPerfil);
		
		btnDesconectar = new JButton("Desconectar");
		btnDesconectar.setForeground(new Color(0, 0, 0));
		btnDesconectar.setFont(fuenteBold);
		btnDesconectar.setBackground(new Color(255, 255, 255));
		btnDesconectar.setBounds(10, 608, 284, 38);
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
		
		btnConsultarAlumnos = new JButton("Consultar alumnos");
		btnConsultarAlumnos.setForeground(Color.BLACK);
		btnConsultarAlumnos.setFont(new Font("Dialog", Font.BOLD, 20));
		btnConsultarAlumnos.setBackground(Color.WHITE);
		btnConsultarAlumnos.setBounds(10, 559, 284, 38);
		panelIzquierda.add(btnConsultarAlumnos);
		

		
		panelGeneral = new PanelGeneral();
		panelGeneral.setBounds(324, 10, 650, 657);
		contentPane.add(panelGeneral);
		
		panelVerHorarios = new PanelVerHorarios();
		panelVerHorarios.setBounds(324, 10, 650, 657);
		contentPane.add(panelVerHorarios);
		
		panelOrganizarReuniones = new PanelOrganizarReuniones();
		panelOrganizarReuniones.setBounds(324, 10, 650, 657);
		contentPane.add(panelOrganizarReuniones);
		
		
		lblNombreUsuario = new JLabel("Nombre Usuario");
		lblNombreUsuario.setForeground(new Color(255, 255, 255));
		lblNombreUsuario.setFont(new Font("Dialog", Font.BOLD, 15));
		lblNombreUsuario.setBounds(69, 11, 115, 20);
		panelPerfil.add(lblNombreUsuario);
		
		lblRolUsuario = new JLabel("RolUsuario");
		lblRolUsuario.setForeground(new Color(255, 255, 255));
		lblRolUsuario.setFont(new Font("Dialog", Font.PLAIN, 15));
		lblRolUsuario.setBounds(69, 31, 72, 20);
		panelPerfil.add(lblRolUsuario);
		

	}

	public PanelGeneral getPanelGeneral() {
		return panelGeneral;
	}

	public void setPanelGeneral(PanelGeneral panelGeneral) {
		this.panelGeneral = panelGeneral;
	}

	public PanelVerHorarios getPanelVerHorarios() {
		return panelVerHorarios;
	}

	public void setPanelVerHorarios(PanelVerHorarios panelVerHorarios) {
		this.panelVerHorarios = panelVerHorarios;
	}

	public PanelOrganizarReuniones getPanelOrganizarReuniones() {
		return panelOrganizarReuniones;
	}
	public void setPanelOrganizarReuniones(PanelOrganizarReuniones panelOrganizarReuniones) {
		this.panelOrganizarReuniones = panelOrganizarReuniones;
	}

	public JPanel getPanelAvatar() {
		return panelAvatar;
	}


	public void setPanelAvatar(JPanel panelAvatar) {
		this.panelAvatar = panelAvatar;
	}


	public JButton getBtnDesconectar() {
		return btnDesconectar;
	}


	public void setBtnDesconectar(JButton btnDesconectar) {
		this.btnDesconectar = btnDesconectar;
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
	
	
	
	public JButton getBtnConsultarAlumnos() {
		return btnConsultarAlumnos;
	}

	public void setBtnConsultarAlumnos(JButton btnConsultarAlumnos) {
		this.btnConsultarAlumnos = btnConsultarAlumnos;
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
			panelAvatar.setBounds(10, 7, 50, 50);
			panelAvatar.setOpaque(false);
			panelAvatar.setCursor(new Cursor(Cursor.HAND_CURSOR));
			panelPerfil.add(panelAvatar);
		    panelPerfil.revalidate();
		    panelPerfil.repaint();

	}
	
	public JPanel getPanelPerfil() {
		return panelPerfil;
	}
}
