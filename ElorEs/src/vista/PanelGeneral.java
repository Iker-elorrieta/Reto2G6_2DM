package vista;

import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JLabel;

import org.kordamp.ikonli.materialdesign2.MaterialDesignC;
import org.kordamp.ikonli.swing.FontIcon;

public class PanelGeneral extends JPanel {

	private static final long serialVersionUID = 1L;
	private TablaHorario panelHorarios;
	private TablaHorario panelReuniones;
	private JLabel lblMensajeVacioReuniones;

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

		JLabel lblIconoHorario = new JLabel();
		lblIconoHorario.setIcon(FontIcon.of(MaterialDesignC.CLOCK_OUTLINE, 28, new Color(255, 159, 64)));
		lblIconoHorario.setBounds(15, 12, 32, 40);
		contenedorHorario.add(lblIconoHorario);

		JLabel lblMiHorario = new JLabel("Mi horario");
		lblMiHorario.setFont(new Font("Raleway", Font.PLAIN, 26));
		lblMiHorario.setForeground(new Color(255, 159, 64));
		lblMiHorario.setBounds(52, 12, 250, 40);
		contenedorHorario.add(lblMiHorario);

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

		JLabel lblIconoReuniones = new JLabel();
		lblIconoReuniones.setIcon(FontIcon.of(MaterialDesignC.CALENDAR_CLOCK, 28, new Color(153, 102, 255)));
		lblIconoReuniones.setBounds(15, 12, 32, 40);
		contenedorReuniones.add(lblIconoReuniones);

		JLabel lblMiHorario_1 = new JLabel("Reuniones esta semana");
		lblMiHorario_1.setFont(new Font("Raleway", Font.PLAIN, 26));
		lblMiHorario_1.setForeground(new Color(153, 102, 255));
		lblMiHorario_1.setBounds(52, 12, 330, 40);
		contenedorReuniones.add(lblMiHorario_1);

		lblMensajeVacioReuniones = new JLabel("No tienes reuniones esta semana");
		lblMensajeVacioReuniones.setFont(new Font("Raleway", Font.PLAIN, 18));
		lblMensajeVacioReuniones.setForeground(new Color(120, 120, 120));
		lblMensajeVacioReuniones.setHorizontalAlignment(JLabel.CENTER);
		lblMensajeVacioReuniones.setBounds(0, 150, 682, 100);
		lblMensajeVacioReuniones.setVisible(false);
		contenedorReuniones.add(lblMensajeVacioReuniones);

		panelReuniones = new TablaHorario();
		panelReuniones.setMode(TablaHorario.Mode.REUNION);
		panelReuniones.setBounds(0, 60, 682, 365);
		contenedorReuniones.add(panelReuniones);
	}
	
	public TablaHorario getPanelHorarios() {
		return panelHorarios;
	}

	public TablaHorario getPanelReuniones() {
		return panelReuniones;
	}

	public JLabel getLblMensajeVacioReuniones() {
		return lblMensajeVacioReuniones;
	}

	

}
