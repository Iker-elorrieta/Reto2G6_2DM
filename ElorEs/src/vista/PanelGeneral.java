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

	/**
	 * Create the panel.
	 */
	public PanelGeneral() {
		setLayout(null);
		setPreferredSize(new Dimension(1394, 460));
		setBackground(Color.WHITE);

		// Panel contenedor para Mi Horario y reuniones
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
		contenedorHorario.setBounds(10, 0, 1374, 425);
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

		panelHorarios = new TablaHorario(new int[] { 90, 256, 256, 256, 256, 256 },true);
		panelHorarios.setBounds(0, 60, 1374, 365);
		contenedorHorario.add(panelHorarios);
	}
	
	public TablaHorario getPanelHorarios() {
		return panelHorarios;
	}
}
