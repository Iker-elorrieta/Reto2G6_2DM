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

import org.kordamp.ikonli.materialdesign2.MaterialDesignC;
import org.kordamp.ikonli.swing.FontIcon;

public class Menu extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblNombreUsuario;
	private JLabel lblRolUsuario;
	private JPanel panelAvatar;
	private JPanel panelIzquierda;
	private PanelGeneral panelGeneral;
	private PanelHorariosProfesores panelVerHorarios;
	private PanelOrganizarReuniones panelOrganizarReuniones;
	private PanelAlumnos panelAlumnos;
	private JPanel panelPerfil;
	private JPanel panelLogo;
	private JButton btnConsultarAlumnos;
	private JButton btnVerOtrosHorarios;
	private JButton btnOrganizarReuniones;
	private String estadoMenu = "";


	/**
	 * Create the frame.
	 */
	public Menu() {
		setResizable(false);
		setTitle("Framework educativo - CIFP Elorrieta-Errekamari LHII");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 1429, 595);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		setBackground(Color.WHITE);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		panelPerfil = new JPanel() {
			private static final long serialVersionUID = 1L;
			
			@Override
			protected void paintComponent(Graphics g) {
				Graphics2D g2 = (Graphics2D) g.create();
				try {
					g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
					g2.setColor(new Color(255, 255, 255, 20));
				g2.fillRoundRect(0, 0, getWidth(), getHeight(), 25, 25);
				g2.setColor(new Color(255, 255, 255, 51));
				g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 25, 25);
					super.paintComponent(g2);
				} finally {
					g2.dispose();
				}
			}
		};
		panelIzquierda = Login.crearPanelconImagen("/Fondo.png");

		panelIzquierda.setBorder(null);
		panelIzquierda.setBounds(0, 0, 1413, 100);
		panelIzquierda.setLayout(null);
		panelIzquierda.setOpaque(false);
		contentPane.add(panelIzquierda);
		
		panelPerfil.setOpaque(false);
		panelPerfil.setBounds(1180, 21, 215, 60);
		panelPerfil.setLayout(null);
		panelIzquierda.add(panelPerfil);
	

		panelLogo = new JPanel() {
			private static final long serialVersionUID = 1L;
			private Image backgroundImage = new ImageIcon(Login.class.getResource("/Elorrieta_White.png")).getImage();

			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
			}
		};
		panelLogo.setBounds(22, 21, 230, 60);
		panelLogo.setOpaque(false);
		panelIzquierda.add(panelLogo);
		
		btnConsultarAlumnos = crearBotonMenu("Alumnos", "ALUMNOS");
		btnConsultarAlumnos.setBounds(275, 35, 150, 34);
		panelIzquierda.add(btnConsultarAlumnos);

		btnVerOtrosHorarios = crearBotonMenu("Horarios", "HORARIOS");
		btnVerOtrosHorarios.setBounds(435, 35, 150, 34);
		panelIzquierda.add(btnVerOtrosHorarios);

		btnOrganizarReuniones = crearBotonMenu("Reuniones", "REUNIONES");
		btnOrganizarReuniones.setBounds(595, 35, 150, 34);
		panelIzquierda.add(btnOrganizarReuniones);
		

		
		panelGeneral = new PanelGeneral();
		panelGeneral.setBounds(10, 120, 1394, 460);
		contentPane.add(panelGeneral);
		
		panelVerHorarios = new PanelHorariosProfesores();
		panelVerHorarios.setBounds(10, 120, 1394, 460);
		contentPane.add(panelVerHorarios);
		
		panelOrganizarReuniones = new PanelOrganizarReuniones();
		panelOrganizarReuniones.setBounds(10, 120, 1394, 460);
		contentPane.add(panelOrganizarReuniones);

		panelAlumnos = new PanelAlumnos();
		panelAlumnos.setBounds(10, 120, 1394, 460);
		contentPane.add(panelAlumnos);

		panelGeneral.setVisible(true);
		panelVerHorarios.setVisible(false);
		panelOrganizarReuniones.setVisible(false);
		panelAlumnos.setVisible(false);
		
		
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
		
		JLabel lblIconoFlecha = new JLabel();
		lblIconoFlecha.setIcon(FontIcon.of(MaterialDesignC.CHEVRON_DOWN, 18, Color.WHITE));
		lblIconoFlecha.setBounds(185, 21, 24, 24);
		panelPerfil.add(lblIconoFlecha);
		

	}
	

	public PanelGeneral getPanelGeneral() {
		return panelGeneral;
	}

	public void setPanelGeneral(PanelGeneral panelGeneral) {
		this.panelGeneral = panelGeneral;
	}

	public PanelHorariosProfesores getPanelVerHorarios() {
		return panelVerHorarios;
	}

	public void setPanelVerHorarios(PanelHorariosProfesores panelVerHorarios) {
		this.panelVerHorarios = panelVerHorarios;
	}

	public PanelOrganizarReuniones getPanelOrganizarReuniones() {
		return panelOrganizarReuniones;
	}
		public PanelAlumnos getPanelAlumnos() {
			return panelAlumnos;
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

	public JButton getBtnVerOtrosHorarios() {
		return btnVerOtrosHorarios;
	}

	public JButton getBtnOrganizarReuniones() {
		return btnOrganizarReuniones;
	}

	public JPanel getPanelLogo() {
		return panelLogo;
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
			                backgroundImage = new ImageIcon(Login.class.getResource("/avatar.png")).getImage();
			            }
			        } catch (Exception e) {
			            e.printStackTrace();
			            backgroundImage = new ImageIcon(Login.class.getResource("/avatar.png")).getImage();
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

	public void setEstadoMenu(String estadoMenu) {
		String nuevoEstado = estadoMenu == null ? "" : estadoMenu;
		switch (nuevoEstado) {
		case "ALUMNOS":
		case "HORARIOS":
		case "REUNIONES":
			this.estadoMenu = nuevoEstado;
			break;
		default:
			this.estadoMenu = "";
			break;
		}
		if (btnConsultarAlumnos != null) {
			btnConsultarAlumnos.repaint();
		}
		if (btnVerOtrosHorarios != null) {
			btnVerOtrosHorarios.repaint();
		}
		if (btnOrganizarReuniones != null) {
			btnOrganizarReuniones.repaint();
		}
	}

	private JButton crearBotonMenu(String texto, String estadoAsociado) {
		JButton boton = new JButton() {
			private static final long serialVersionUID = 1L;

			@Override
			protected void paintComponent(Graphics g) {
				Graphics2D g2 = (Graphics2D) g.create();
				try {
					g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
					boolean activo = estadoAsociado.equals(estadoMenu);
					Color colorFondo = activo ? new Color(242, 194, 35, 46) : new Color(255, 255, 255, 20);
					Color colorBorde = activo ? new Color(242, 194, 35, 153) : new Color(255, 255, 255, 51);
					g2.setColor(colorFondo);
					g2.fillRoundRect(0, 0, getWidth(), getHeight(), 25, 25);
					g2.setColor(colorBorde);
					g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 25, 25);

				} finally {
					g2.dispose();
				}
				super.paintComponent(g);
			}
		};
		String textoMayusculas = texto.toUpperCase();
		boton.setText("<html><span style='letter-spacing: 0.24em;'>" + textoMayusculas + "</span></html>");
		boton.setContentAreaFilled(false);
		boton.setBorderPainted(false);
		boton.setFocusPainted(false);
		boton.setForeground(Color.WHITE);
		boton.setFont(new Font("Dialog", Font.PLAIN, 16));
		return boton;
	}
}
