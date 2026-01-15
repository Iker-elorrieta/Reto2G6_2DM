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
import java.awt.geom.RoundRectangle2D;
import java.net.URI;
import java.net.URL;
import javax.swing.JLabel;

public class PantallaMenu extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton btnDesconectar;
	private JButton btnPerfil;
	private Font fuenteBold = new Font("Raleway", Font.BOLD, 20);
	private PanelHorario panelHorarios;
	private JLabel lblNombreUsuario;
	private JLabel lblRolUsuario;
	private JPanel panelAvatar;
	private JPanel panelIzquierda;


	/**
	 * Create the frame.
	 */
	public PantallaMenu() {
		setTitle("Framework educativo - CIFP Elorrieta-Errekamari LHII");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 1000, 717);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
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
		
		btnPerfil = new JButton("Consultar perfil");
		btnPerfil.setBounds(104, 268, 148, 23);
		panelIzquierda.add(btnPerfil);
		
		JButton btnConsultarAlumnos = new JButton("Consultar alumnos");
		btnConsultarAlumnos.setForeground(Color.BLACK);
		btnConsultarAlumnos.setFont(new Font("Dialog", Font.BOLD, 20));
		btnConsultarAlumnos.setBackground(Color.WHITE);
		btnConsultarAlumnos.setBounds(10, 559, 284, 38);
		panelIzquierda.add(btnConsultarAlumnos);
		
		lblNombreUsuario = new JLabel("Nombre Usuario");
		lblNombreUsuario.setForeground(new Color(255, 255, 255));
		lblNombreUsuario.setFont(new Font("Dialog", Font.BOLD, 15));
		lblNombreUsuario.setBounds(70, 498, 160, 23);
		panelIzquierda.add(lblNombreUsuario);
		
		lblRolUsuario = new JLabel("RolUsuario");
		lblRolUsuario.setForeground(new Color(255, 255, 255));
		lblRolUsuario.setFont(new Font("Dialog", Font.PLAIN, 15));
		lblRolUsuario.setBounds(70, 521, 160, 23);
		panelIzquierda.add(lblRolUsuario);
		panelHorarios = new PanelHorario();
		panelHorarios.setLocation(334, 71);
		panelHorarios.setSize(637, 262);
		contentPane.add(panelHorarios);
		
		JLabel lblMiHorario = new JLabel("Mi horario");
		lblMiHorario.setFont(new Font("Dialog", Font.PLAIN, 30));
		lblMiHorario.setBounds(337, 10, 160, 51);
		contentPane.add(lblMiHorario);
		
		JButton btnOrganizarReuniones = new JButton("Organizar reuniones");

		btnOrganizarReuniones.setForeground(Color.WHITE);
		btnOrganizarReuniones.setFont(fuenteBold);
		btnOrganizarReuniones.setBackground(Color.decode("#0092A5"));
		btnOrganizarReuniones.setBounds(720, 354, 251, 38);
		contentPane.add(btnOrganizarReuniones);
		
		PanelReunionesTabla panelReunionesSemanal = new PanelReunionesTabla();
		panelReunionesSemanal.setBounds(331, 405, 637, 262);
		contentPane.add(panelReunionesSemanal);
		
		JLabel lblMiHorario_1 = new JLabel("Reuniones esta semana");
		lblMiHorario_1.setFont(new Font("Dialog", Font.PLAIN, 30));
		lblMiHorario_1.setBounds(334, 344, 361, 51);
		contentPane.add(lblMiHorario_1);
		
		JButton btnVerOtrosHorarios = new JButton("Ver otros horarios");
		btnVerOtrosHorarios.setForeground(Color.WHITE);
		btnVerOtrosHorarios.setFont(new Font("Dialog", Font.BOLD, 20));
		btnVerOtrosHorarios.setBackground(Color.decode("#0092A5"));
		btnVerOtrosHorarios.setBounds(720, 20, 254, 38);
		contentPane.add(btnVerOtrosHorarios);
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
	
	public void cargarAvatar(String argazkiaUrl) {
		System.out.println("cargarAvatar: " + argazkiaUrl);
		panelAvatar = new JPanel() {
			    private static final long serialVersionUID = 1L;
			    private Image backgroundImage;

			    {
			        try {
			            if (argazkiaUrl  != null && !argazkiaUrl.isBlank()) {
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
			panelAvatar.setBounds(10, 498, 50, 50);
			panelAvatar.setOpaque(false);
			panelIzquierda.add(panelAvatar);

	}
}
