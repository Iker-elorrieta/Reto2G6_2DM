package vista;

import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;

public class PanelReunionesTabla extends JPanel {

	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public PanelReunionesTabla() {
		setBackground(new Color(0, 0, 255));
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("TODO: TABLA REUNIONES DE HOY");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblNewLabel.setBounds(0, 0, 683, 395);
		add(lblNewLabel);
	}
}
