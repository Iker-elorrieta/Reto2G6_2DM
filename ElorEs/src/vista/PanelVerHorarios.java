package vista;

import javax.swing.JPanel;
import javax.swing.JButton;

public class PanelVerHorarios extends JPanel {

	private static final long serialVersionUID = 1L;
	private JButton btnVolver;

	/**
	 * Create the panel.
	 */
	public PanelVerHorarios() {
		setLayout(null);
		setVisible(false);
		btnVolver = new JButton("Volver");
		btnVolver.setBounds(10, 11, 89, 23);
		add(btnVolver);

	}

	public JButton getBtnVolver() {
		return btnVolver;
	}

	public void setBtnVolver(JButton btnVolver) {
		this.btnVolver = btnVolver;
	}
	
}
