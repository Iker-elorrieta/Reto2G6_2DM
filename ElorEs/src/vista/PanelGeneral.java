package vista;

import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;

public class PanelGeneral extends JPanel {

	private static final long serialVersionUID = 1L;
	private Font fuenteBold = new Font("Raleway", Font.BOLD, 20);
	private TablaHorario panelHorarios;
	private JButton btnVerOtrosHorarios;
	private JButton btnOrganizarReuniones;

	/**
	 * Create the panel.
	 */
	public PanelGeneral() {
		setLayout(null);
		JLabel lblMiHorario = new JLabel("Mi horario");
		lblMiHorario.setFont(new Font("Dialog", Font.PLAIN, 30));
		lblMiHorario.setBounds(0, 11, 160, 51);
		add(lblMiHorario);
		
		btnOrganizarReuniones = new JButton("Organizar reuniones");

		btnOrganizarReuniones.setForeground(Color.WHITE);
		btnOrganizarReuniones.setFont(fuenteBold);
		btnOrganizarReuniones.setBackground(Color.decode("#0092A5"));
		btnOrganizarReuniones.setBounds(389, 355, 251, 38);
		add(btnOrganizarReuniones);
		
		TablaHorario panelReunionesSemanal = new TablaHorario();
		panelReunionesSemanal.setBounds(0, 406, 637, 262);
		add(panelReunionesSemanal);
		
		JLabel lblMiHorario_1 = new JLabel("Reuniones esta semana");
		lblMiHorario_1.setFont(new Font("Dialog", Font.PLAIN, 30));
		lblMiHorario_1.setBounds(0, 345, 361, 51);
		add(lblMiHorario_1);
		
		btnVerOtrosHorarios = new JButton("Ver otros horarios");
		btnVerOtrosHorarios.setForeground(Color.WHITE);
		btnVerOtrosHorarios.setFont(new Font("Dialog", Font.BOLD, 20));
		btnVerOtrosHorarios.setBackground(Color.decode("#0092A5"));
		btnVerOtrosHorarios.setBounds(389, 21, 254, 38);
		add(btnVerOtrosHorarios);
		
		panelHorarios = new TablaHorario();
		panelHorarios.setLocation(0, 73);
		panelHorarios.setSize(637, 262);
		add(panelHorarios);
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

	

}
