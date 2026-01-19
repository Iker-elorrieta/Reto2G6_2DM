package vista;

import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;

public class TablaHorario extends JPanel {

	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public TablaHorario() {
		setBackground(new Color(255, 128, 0));
		setLayout(null);
		JLabel lblNewLabel = new JLabel("TODO: TABLA HORARIO/REUNIONES");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblNewLabel.setBounds(0, 0, 683, 395);
		add(lblNewLabel);

	}
}
