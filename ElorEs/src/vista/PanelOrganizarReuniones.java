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

public class PanelOrganizarReuniones extends JPanel {

	private static final long serialVersionUID = 1L;
	private JButton btnVolver;
	private JTable tableReuniones;
	private DefaultTableModel modeloReuniones;

	public PanelOrganizarReuniones() {
		setLayout(null);
		setPreferredSize(new Dimension(1394, 460));
		setBackground(Color.WHITE);
		setVisible(false);

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
		contenedorReuniones.setBounds(10, 0, 1374, 425);
		add(contenedorReuniones);

		btnVolver = crearBotonVolver();
		btnVolver.setBounds(15, 15, 48, 36);
		contenedorReuniones.add(btnVolver);

		JLabel lblTitulo = new JLabel("Organizar reuniones");
		lblTitulo.setFont(new Font("Raleway", Font.PLAIN, 26));
		lblTitulo.setBounds(73, 15, 400, 36);
		contenedorReuniones.add(lblTitulo);

		modeloReuniones = new DefaultTableModel(new String[] { "ID", "Alumno", "Profesor", "Estado",
				 "Título", "Asunto", "Aula", "Fecha", "Creado", "Actualizado" }, 0) {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		tableReuniones = new JTable(modeloReuniones);
		tableReuniones.setFont(new Font("Raleway", Font.PLAIN, 15));
		tableReuniones.setForeground(new Color(0x1F1F1F));
		tableReuniones.getTableHeader().setFont(new Font("Raleway", Font.BOLD, 16));
		tableReuniones.getTableHeader().setBackground(new Color(0xF4F6F8));
		tableReuniones.getTableHeader().setReorderingAllowed(false);
		tableReuniones.setRowHeight(28);
		tableReuniones.setShowGrid(true);
		tableReuniones.setGridColor(new Color(0xE0E0E0));
		tableReuniones.setSelectionBackground(new Color(0xD0D3D9));
		tableReuniones.setSelectionForeground(new Color(0x1F1F1F));
		tableReuniones.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableReuniones.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		tableReuniones.setDefaultEditor(Object.class, null);

		int[] columnWidths = { 60, 160, 160, 120, 120, 180, 220, 90, 140, 140, 140 };
		for (int i = 0; i < columnWidths.length && i < tableReuniones.getColumnCount(); i++) {
			tableReuniones.getColumnModel().getColumn(i).setPreferredWidth(columnWidths[i]);
		}

		JScrollPane scrollPane = new JScrollPane(tableReuniones);
		scrollPane.setBounds(0, 70, 1374, 355);
		scrollPane.getViewport().setBackground(Color.WHITE);
		contenedorReuniones.add(scrollPane);
	}

	private JButton crearBotonVolver() {
		JButton boton = new JButton();
		boton.setIcon(FontIcon.of(MaterialDesignC.CHEVRON_LEFT, 26, new Color(18, 26, 38)));
		boton.setContentAreaFilled(false);
		boton.setBorderPainted(false);
		boton.setFocusPainted(false);
		boton.setToolTipText("Volver");
		return boton;
	}

	public JButton getBtnVolver() {
		return btnVolver;
	}

	public JTable getTableReuniones() {
		return tableReuniones;
	}

	public DefaultTableModel getModeloReuniones() {
		return modeloReuniones;
	}
}
