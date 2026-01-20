package vista;

import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JButton;
import javax.swing.JLabel;

public class PanelGeneral extends JPanel {

	private static final long serialVersionUID = 1L;
	private Font fuenteBold = new Font("Raleway", Font.BOLD, 20);
	private TablaHorario panelHorarios;
	private TablaHorario panelReuniones;
	private JButton btnVerOtrosHorarios;
	private JButton btnOrganizarReuniones;

	/**
	 * Create the panel.
	 */
	public PanelGeneral() {
		setLayout(null);
		setPreferredSize(new Dimension(1394, 460));
		setBackground(Color.WHITE);

		// Panel contenedor para Mi Horario
		JPanel contenedorHorario = new JPanel() {
			private static final long serialVersionUID = 1L;
			
			@Override
			protected void paintComponent(Graphics g) {
				Graphics2D g2 = (Graphics2D) g.create();
				try {
					g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
					g2.setColor(Color.WHITE);
					g2.fillRoundRect(0, 0, getWidth(), getHeight(), 12, 12);
					g2.setColor(new Color(30, 42, 68, 20));
					g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 12, 12);
				} finally {
					g2.dispose();
				}
				super.paintComponent(g);
			}
		};
		contenedorHorario.setLayout(null);
		contenedorHorario.setOpaque(false);
		contenedorHorario.setBounds(10, 0, 682, 425);
		add(contenedorHorario);

		JLabel lblMiHorario = new JLabel("Mi horario");
		lblMiHorario.setFont(new Font("Dialog", Font.PLAIN, 26));
		lblMiHorario.setBounds(15, 12, 250, 40);
		contenedorHorario.add(lblMiHorario);

		btnVerOtrosHorarios = crearBotonOscuro("Ver otros horarios");
		btnVerOtrosHorarios.setBounds(462, 15, 205, 38);
		contenedorHorario.add(btnVerOtrosHorarios);

		panelHorarios = new TablaHorario();
		panelHorarios.setMode(TablaHorario.Mode.HORARIO);
		panelHorarios.setBounds(0, 60, 682, 365);
		contenedorHorario.add(panelHorarios);

		// Panel contenedor para Reuniones
		JPanel contenedorReuniones = new JPanel() {
			private static final long serialVersionUID = 1L;
			
			@Override
			protected void paintComponent(Graphics g) {
				Graphics2D g2 = (Graphics2D) g.create();
				try {
					g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
					g2.setColor(Color.WHITE);
					g2.fillRoundRect(0, 0, getWidth(), getHeight(), 12, 12);
					g2.setColor(new Color(30, 42, 68, 20));
					g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 12, 12);
				} finally {
					g2.dispose();
				}
				super.paintComponent(g);
			}
		};
		contenedorReuniones.setLayout(null);
		contenedorReuniones.setOpaque(false);
		contenedorReuniones.setBounds(702, 0, 682, 425);
		add(contenedorReuniones);

		JLabel lblMiHorario_1 = new JLabel("Reuniones esta semana");
		lblMiHorario_1.setFont(new Font("Dialog", Font.PLAIN, 26));
		lblMiHorario_1.setBounds(15, 12, 330, 40);
		contenedorReuniones.add(lblMiHorario_1);

		btnOrganizarReuniones = crearBotonOscuro("Organizar reuniones");
		btnOrganizarReuniones.setBounds(462, 15, 205, 38);
		contenedorReuniones.add(btnOrganizarReuniones);

		panelReuniones = new TablaHorario();
		panelReuniones.setMode(TablaHorario.Mode.REUNION);
		panelReuniones.setBounds(0, 60, 682, 365);
		contenedorReuniones.add(panelReuniones);
	}

	public JButton getBtnVerOtrosHorarios() {
		return btnVerOtrosHorarios;
	}

	public void setBtnVerOtrosHorarios(JButton btnVerOtrosHorarios) {
		this.btnVerOtrosHorarios = btnVerOtrosHorarios;
	}

	public JButton getBtnOrganizarReuniones() {
		return btnOrganizarReuniones;
	}

	public void setBtnOrganizarReuniones(JButton btnOrganizarReuniones) {
		this.btnOrganizarReuniones = btnOrganizarReuniones;
	}
	
	public TablaHorario getPanelHorarios() {
		return panelHorarios;
	}

	public TablaHorario getPanelReuniones() {
		return panelReuniones;
	}

	private JButton crearBotonOscuro(String texto) {
		JButton boton = new JButton(texto) {
			private static final long serialVersionUID = 1L;

			@Override
			protected void paintComponent(Graphics g) {
				Graphics2D g2 = (Graphics2D) g.create();
				try {
					g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
					g2.setColor(new Color(18, 26, 38, 35));
					g2.fillRoundRect(0, 0, getWidth(), getHeight(), 14, 14);
					g2.setColor(new Color(18, 26, 38, 140));
					g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 14, 14);
				} finally {
					g2.dispose();
				}
				super.paintComponent(g);
			}
		};
		boton.setFont(new Font("Raleway", Font.BOLD, 17));
		boton.setForeground(new Color(12, 20, 32));
		boton.setContentAreaFilled(false);
		boton.setBorderPainted(false);
		boton.setFocusPainted(false);
		return boton;
	}

	

}
