package vista;

import javax.swing.AbstractCellEditor;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.Objects;

import javax.swing.JLabel;

import org.kordamp.ikonli.materialdesign2.MaterialDesignC;
import org.kordamp.ikonli.swing.FontIcon;

public class PanelOrganizarReuniones extends JPanel {

	private static final long serialVersionUID = 1L;
	private JButton btnVolver;
	private JButton btnNuevaReunion;
	private JTable tableReuniones;
	private DefaultTableModel modeloReuniones;

	private JButton btnAceptar;
	private JButton btnRechazar;
	private static final Color COLOR_PENDIENTE = new Color(255, 224, 130);

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

		btnVolver = new JButton();
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
		final Color verdeAccion = new Color(46, 204, 113);
		btnNuevaReunion = new JButton("Nueva reunión") {
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
		btnNuevaReunion.setFont(new Font("Raleway", Font.BOLD, 16));
		btnNuevaReunion.setForeground(Color.WHITE);
		btnNuevaReunion.setIcon(FontIcon.of(MaterialDesignC.CALENDAR_PLUS, 20, Color.WHITE));
		btnNuevaReunion.setIconTextGap(10);
		btnNuevaReunion.setFocusPainted(false);
		btnNuevaReunion.setContentAreaFilled(false);
		btnNuevaReunion.setBorderPainted(false);
		btnNuevaReunion.setOpaque(false);
		btnNuevaReunion.setBounds(1140, 15, 220, 38);
		contenedorReuniones.add(btnNuevaReunion);

		modeloReuniones = new DefaultTableModel(new String[] { "ID", "Fecha", "Alumno", "Estado", "Título", "Asunto",
				"Aula", "Creado", "Actualizado", "", "" }, 0) {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return column >= getColumnCount() - 2;
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

		int[] columnWidths = { 0, 140, 200, 120, 130, 200, 80, 140, 140, 110, 120 };
		for (int i = 0; i < columnWidths.length && i < tableReuniones.getColumnCount(); i++) {
			tableReuniones.getColumnModel().getColumn(i).setPreferredWidth(columnWidths[i]);
		}
		tableReuniones.getColumnModel().getColumn(0).setMinWidth(0);
		tableReuniones.getColumnModel().getColumn(0).setMaxWidth(0);

		int aceptarColumnIndex = modeloReuniones.getColumnCount() - 2;
		int rechazarColumnIndex = modeloReuniones.getColumnCount() - 1;

		btnAceptar = new JButton();
		btnRechazar = new JButton();

		tableReuniones.getColumnModel().getColumn(aceptarColumnIndex)
				.setCellRenderer(new BotonAccionRenderer("Aceptar", new Color(46, 204, 113)));

		tableReuniones.getColumnModel().getColumn(aceptarColumnIndex)
				.setCellEditor(new BotonAccionEditor(btnAceptar, "Aceptar", new Color(46, 204, 113)));

		tableReuniones.getColumnModel().getColumn(rechazarColumnIndex)
				.setCellRenderer(new BotonAccionRenderer("Rechazar", new Color(231, 76, 60)));

		tableReuniones.getColumnModel().getColumn(rechazarColumnIndex)
				.setCellEditor(new BotonAccionEditor(btnRechazar, "Rechazar", new Color(231, 76, 60)));

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

	public JButton getBtnAceptar() {
		return btnAceptar;
	}

	public void setBtnAceptar(JButton btnAceptar) {
		this.btnAceptar = btnAceptar;
	}

	public JButton getBtnRechazar() {
		return btnRechazar;
	}

	public void setBtnRechazar(JButton btnRechazar) {
		this.btnRechazar = btnRechazar;
	}

	// Renderer para mostrar un botón de acción en la celda (ej. Aceptar/Rechazar)
	private static class BotonAccionRenderer extends JButton implements TableCellRenderer {
		// Versión serializada (buena práctica)
		private static final long serialVersionUID = 1L;
		// Texto que se mostrará en el botón (establecido en el constructor)
		private final String texto;
		private final Color colorBase;

		// Constructor: configura apariencia base del botón
		BotonAccionRenderer(String texto, Color color) {
			// Guardar texto para mostrar
			this.texto = texto;
			this.colorBase = color;
			// Permitir que el botón pinte su propio fondo
			setOpaque(true);
			// Texto siempre en blanco para contraste con el fondo de color
			setForeground(Color.WHITE);
			// Fondo del botón según el color de acción (verde/rojo)
			setBackground(color);
			// Fuente
			setFont(new Font("Raleway", Font.BOLD, 14));
			// No dibujar el 'focus' por defecto
			setFocusPainted(false);
		}

		@Override
		// Devuelve el botón configurado que actuará como renderer
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
				int row, int column) {
			// Establecer texto y devolver el propio botón
			// Si esta aceptada actualmente poner el boton pendiente

			int modelRow = table.convertRowIndexToModel(row);
			TableModel model = table.getModel();
			String estado = Objects.toString(model.getValueAt(modelRow, 3), "");

	
				   if ("ACEPTADA".equalsIgnoreCase(estado) || "DENEGADA".equalsIgnoreCase(estado)) {
					   JPanel panelVacio = new JPanel();
					   panelVacio.setOpaque(false);
					   return panelVacio;
				   } else {
					   setText(texto);
					   setBackground(colorBase);
					   return this;
				   }
		}
	}

	// BOTONES ACEPTAR O RECHAZAR
	private static class BotonAccionEditor extends AbstractCellEditor implements TableCellEditor {
		private static final long serialVersionUID = 1L;
		// Botón real que se insertará en la celda mientras esté en modo edición
		private final JButton button;
		// Color de fondo usado para el botón
		private final Color background;
		// Texto mostrado en el botón
		private final String texto;
		private JTable tabla;

		// Constructor: recibe el JButton externo, el texto y el color a usar
		BotonAccionEditor(JButton button, String texto, Color background) {
			this.texto = texto;
			this.background = background;
			this.button = button;
			// Configurar apariencia del botón reutilizado
			button.setForeground(Color.WHITE);
			button.setFont(new Font("Raleway", Font.BOLD, 14));
			button.setFocusPainted(false);
			button.setContentAreaFilled(true);
			button.setOpaque(true);
			button.setBackground(background);
			button.addActionListener(e -> {
				fireEditingStopped();
				
				tabla.repaint();
			}
			);
		}

		@Override
		// Ajustar el botón justo antes de mostrarlo en la celda
		public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row,
				int column) {
			   this.tabla = table;
			   int modelRow = table.convertRowIndexToModel(row);
			   TableModel model = table.getModel();
			   String estado = Objects.toString(model.getValueAt(modelRow, 3), "");
			   if ("ACEPTADA".equalsIgnoreCase(estado) || "DENEGADA".equalsIgnoreCase(estado)) {
				   JPanel panelVacio = new JPanel();
				   panelVacio.setOpaque(false);
				   return panelVacio;
			   } else {
				   button.setText(texto);
				   button.setBackground(background);
				   return button;
			   }
		}

		@Override
		// Deshabilitar la edición
		public Object getCellEditorValue() {
			return null;
		}

	}
}
