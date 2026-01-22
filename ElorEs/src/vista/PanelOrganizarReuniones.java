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
	private JButton btnNuevaReunion;
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


		btnVolver =new JButton();
		btnVolver.setIcon(FontIcon.of(MaterialDesignC.CHEVRON_LEFT, 28, new Color(18, 26, 38)));
		btnVolver.setContentAreaFilled(false);
		btnVolver.setBorderPainted(false);
		btnVolver.setFocusPainted(false);
		btnVolver.setToolTipText("Volver");
		btnVolver.setBounds(10, 11, 25, 38);
		contenedorReuniones.add(btnVolver);

		JLabel lblTitulo = new JLabel("Organizar reuniones");
		lblTitulo.setFont(new Font("Raleway", Font.PLAIN, 26));
		lblTitulo.setBounds(45, 11, 330, 40);
		contenedorReuniones.add(lblTitulo);

		btnNuevaReunion = crearBotonNuevaReunion();
		btnNuevaReunion.setBounds(1140, 15, 220, 38);
		contenedorReuniones.add(btnNuevaReunion);

		modeloReuniones = new DefaultTableModel(new String[] { "ID", "Fecha", "Alumno",
			"Estado", "Título", "Asunto", "Aula", "Creado", "Actualizado" }, 0) {
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
		tableReuniones.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		tableReuniones.setDefaultEditor(Object.class, null);

		int[] columnWidths = { 0, 140, 200, 120, 130, 200, 80, 140, 140 };
		for (int i = 0; i < columnWidths.length && i < tableReuniones.getColumnCount(); i++) {
			tableReuniones.getColumnModel().getColumn(i).setPreferredWidth(columnWidths[i]);
		}
		tableReuniones.getColumnModel().getColumn(0).setMinWidth(0);
		tableReuniones.getColumnModel().getColumn(0).setMaxWidth(0);

		JScrollPane scrollPane = new JScrollPane(tableReuniones);
		scrollPane.setBounds(0, 60, 1374, 365);
		scrollPane.getViewport().setBackground(Color.WHITE);
		contenedorReuniones.add(scrollPane);
	}


	public JButton getBtnVolver() {
		return btnVolver;
	}

	public JButton getBtnNuevaReunion() {
		return btnNuevaReunion;
	}

	public void setBtnNuevaReunion(JButton btnNuevaReunion) {
		this.btnNuevaReunion = btnNuevaReunion;
	}

	public JTable getTableReuniones() {
		return tableReuniones;
	}

	public DefaultTableModel getModeloReuniones() {
		return modeloReuniones;
	}

	private JButton crearBotonNuevaReunion() {
		final Color verdeAccion = new Color(46, 204, 113);
		JButton boton = new JButton("Nueva reunión") {
			private static final long serialVersionUID = 1L;

			@Override
			protected void paintComponent(Graphics g) {
				Graphics2D g2 = (Graphics2D) g.create();
				try {
					g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
					g2.setColor(verdeAccion);
					g2.fillRoundRect(0, 0, getWidth(), getHeight(), 18, 18);
				} finally {
					g2.dispose();
				}
				super.paintComponent(g);
			}
		};
		boton.setFont(new Font("Raleway", Font.BOLD, 16));
		boton.setForeground(Color.WHITE);
		boton.setIcon(FontIcon.of(MaterialDesignC.CALENDAR_PLUS, 20, Color.WHITE));
		boton.setIconTextGap(10);
		boton.setFocusPainted(false);
		boton.setContentAreaFilled(false);
		boton.setBorderPainted(false);
		boton.setOpaque(false);
		return boton;
	}
}
