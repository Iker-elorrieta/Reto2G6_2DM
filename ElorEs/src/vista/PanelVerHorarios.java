package vista;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JButton;
import javax.swing.JLabel;

import org.kordamp.ikonli.materialdesign2.MaterialDesignC;
import org.kordamp.ikonli.swing.FontIcon;

public class PanelVerHorarios extends JPanel {

	private static final long serialVersionUID = 1L;
	private TablaHorario panelHorarios;
	private JTable tableProfesores;
	private JButton btnVolver;
	private DefaultTableModel modeloProfesores;
	/**
	 * Create the panel.
	 */

	public PanelVerHorarios() {
		setLayout(null);
		setPreferredSize(new Dimension(1394, 460));
		setBackground(Color.WHITE);
		setVisible(false);

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
		contenedorHorario.setBounds(702, 0, 682, 425);
		add(contenedorHorario);

		JLabel lblMiHorario = new JLabel("Horario");
		lblMiHorario.setFont(new Font("Raleway", Font.PLAIN, 26));
		lblMiHorario.setBounds(15, 12, 250, 40);
		contenedorHorario.add(lblMiHorario);



		panelHorarios = new TablaHorario();
		panelHorarios.setMode(TablaHorario.Mode.HORARIO);
		panelHorarios.setBounds(0, 60, 682, 365);
		contenedorHorario.add(panelHorarios);

		// Panel contenedor para Reuniones
		JPanel contenedorProfesores = new JPanel() {
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
		contenedorProfesores.setLayout(null);
		contenedorProfesores.setOpaque(false);
		contenedorProfesores.setBounds(10, 0, 682, 425);
		add(contenedorProfesores);

		JLabel lblMiHorario_1 = new JLabel("Selecciona un profesor");
		lblMiHorario_1.setFont(new Font("Raleway", Font.PLAIN, 26));
		lblMiHorario_1.setBounds(45, 11, 330, 40);
		contenedorProfesores.add(lblMiHorario_1);

	

		modeloProfesores = new DefaultTableModel(
				new String[] {"ID", "Nombre" }, 0);
		tableProfesores = new JTable(modeloProfesores);
		tableProfesores.setFont(new Font("Raleway", Font.PLAIN, 15));
		tableProfesores.setForeground(new Color(0x1F1F1F));
		tableProfesores.getTableHeader().setFont(new Font("Raleway", Font.BOLD, 16));
		tableProfesores.getTableHeader().setBackground(new Color(0xF4F6F8));
		tableProfesores.getTableHeader().setReorderingAllowed(false);
		tableProfesores.setRowHeight(30);
		tableProfesores.setFillsViewportHeight(true);
		tableProfesores.setShowGrid(true);
		tableProfesores.setGridColor(new Color(0xE0E0E0));
		tableProfesores.setSelectionBackground(new Color(0xD0D3D9));
		tableProfesores.setSelectionForeground(new Color(0x1F1F1F));
		tableProfesores.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableProfesores.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		tableProfesores.setDefaultEditor(Object.class, null);

		JScrollPane scrollPane= new JScrollPane();
		scrollPane.setBounds(0, 60, 682, 365);
		scrollPane.getViewport().setBackground(Color.WHITE);
		contenedorProfesores.add(scrollPane);
		scrollPane.setViewportView(tableProfesores);
		ocultarColumnaId();

		

		btnVolver =new JButton();
		btnVolver.setIcon(FontIcon.of(MaterialDesignC.CHEVRON_LEFT, 28, new Color(18, 26, 38)));
		btnVolver.setContentAreaFilled(false);
		btnVolver.setBorderPainted(false);
		btnVolver.setFocusPainted(false);
		btnVolver.setToolTipText("Volver");
		btnVolver.setBounds(10, 11, 25, 38);
		contenedorProfesores.add(btnVolver);

	}


	private void ocultarColumnaId() {
		if (tableProfesores.getColumnModel().getColumnCount() > 1) {
			tableProfesores.removeColumn(tableProfesores.getColumnModel().getColumn(0));
		}
	}

	public JTable getTableProfesores() {
		return tableProfesores;
	}

	public void setTableProfesores(JTable tableProfesores) {
		this.tableProfesores = tableProfesores;
	}
	
	public DefaultTableModel getModeloProfesores() {
		return modeloProfesores;
	}

	public void setModeloProfesores(DefaultTableModel modeloProfesores) {
		this.modeloProfesores = modeloProfesores;
	}

	public JButton getBtnVolver() {
		return btnVolver;
	}

	public void setBtnVolver(JButton btnVolver) {
		this.btnVolver = btnVolver;
	}

	public TablaHorario getPanelHorarios() {
		return panelHorarios;
	}

	public void setPanelHorarios(TablaHorario panelHorarios) {
		this.panelHorarios = panelHorarios;
	}
	
}
