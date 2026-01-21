package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.util.Locale;

import javax.swing.BorderFactory;
import javax.swing.JEditorPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

import modelo.Horarios;

public class TablaHorario extends JPanel {

	public enum Mode {
		HORARIO, REUNION
	}

	private static final long serialVersionUID = 1L;
	private static final Font HEADER_FONT = new Font("Raleway", Font.BOLD, 16);
	private static final Font CELL_FONT = new Font("Raleway", Font.PLAIN, 15);
	private static final int BASE_ROW_HEIGHT = 56;
	private static final int[] COLUMN_WIDTHS_HORARIO = { 60, 124, 124, 124, 124, 124 };
	private static final int[] COLUMN_WIDTHS_REUNION = { 120, 112, 112, 112, 112, 112 };

	private JTable table;
	private DefaultTableModel modelo;
	private JScrollPane scrollPane;
	private Mode mode = Mode.HORARIO;

	/**
	 * Create the panel.
	 */
	public TablaHorario() {
		setLayout(new BorderLayout());
		setOpaque(false);
		setPreferredSize(new Dimension(682, 365));
		modelo = crearModeloBase();
		table = new JTable(modelo) {
			private static final long serialVersionUID = 1L;

			@Override
			public String getToolTipText(MouseEvent event) {
				int row = rowAtPoint(event.getPoint());
				int column = columnAtPoint(event.getPoint());
				if (row < 0 || column < 0) {
					return null;
				}
				Object value = getValueAt(row, column);
				if (value instanceof Horarios) {
					return ((Horarios) value).getModulos().getNombre().trim()+" "+ ((Horarios) value).getAula();
				}
				if (value == null) {
					return null;
				}
				return limpiarHtml(value.toString());
			}
		};
		configurarTabla();
		scrollPane = new JScrollPane(table);
		scrollPane.setBorder(BorderFactory.createLineBorder(new Color(0xDADCE0)));
		scrollPane.getViewport().setBackground(Color.WHITE);
		add(scrollPane, BorderLayout.CENTER);
	}

	private DefaultTableModel crearModeloBase() {
		return new DefaultTableModel(new String[] { "Hora", "Lunes", "Martes", "Miércoles", "Jueves", "Viernes" },
				0);
	}

	private void configurarTabla() {
		table.setFont(CELL_FONT);
		table.setForeground(new Color(0x1F1F1F));
		table.getTableHeader().setFont(HEADER_FONT);
		table.getTableHeader().setBackground(new Color(0xF4F6F8));
		table.getTableHeader().setReorderingAllowed(false);
		table.setRowHeight(BASE_ROW_HEIGHT);
		table.setFillsViewportHeight(true);
		table.setShowGrid(true);
		table.setGridColor(new Color(0xE0E0E0));
		table.setSelectionBackground(new Color(0xD0D3D9));
		table.setSelectionForeground(new Color(0x1F1F1F));
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.setDefaultEditor(Object.class, null);
		table.setDefaultRenderer(Object.class, new MultiLineHtmlRenderer());
		configurarColumnas();
	}

	private void configurarColumnas() {
		for (int i = 0; i < table.getColumnCount(); i++) {
			TableColumn column = table.getColumnModel().getColumn(i);
			int width = obtenerAnchoColumna(i);
			column.setPreferredWidth(width);
			column.setMinWidth(width);
			if (i == 0) {
				column.setMaxWidth(width);
			}
		}
	}

	private void ajustarAlturaFilas() {
		int rows = table.getRowCount();
		if (rows <= 0) {
			table.setRowHeight(BASE_ROW_HEIGHT);
			return;
		}
		int headerHeight = table.getTableHeader().getPreferredSize().height;
		int disponible = Math.max(120, getPreferredSize().height - headerHeight - 10);
		int altura = Math.max(BASE_ROW_HEIGHT, disponible / rows);
		for (int row = 0; row < rows; row++) {
			table.setRowHeight(row, altura);
		}
	}

	public JTable getTable() {
		return table;
	}

	public DefaultTableModel getModelo() {
		return modelo;
	}

	public void actualizarModelo(DefaultTableModel nuevoModelo) {
		if (nuevoModelo == null) {
			nuevoModelo = crearModeloBase();
		}
		modelo = nuevoModelo;
		table.setModel(modelo);
		configurarTabla();
		ajustarAlturaFilas();
	}

	public void setModelo(DefaultTableModel modelo) {
		actualizarModelo(modelo);
	}

	public void setMode(Mode mode) {
		if (mode == null) {
			return;
		}
		this.mode = mode;
		configurarColumnas();
		ajustarAlturaFilas();
	}

	private int obtenerAnchoColumna(int index) {
		int[] widths = mode == Mode.REUNION ? COLUMN_WIDTHS_REUNION : COLUMN_WIDTHS_HORARIO;
		return widths[Math.min(index, widths.length - 1)];
	}

	private static class MultiLineHtmlRenderer extends JEditorPane implements TableCellRenderer {
		private static final long serialVersionUID = 1L;

		MultiLineHtmlRenderer() {
			setContentType("text/html");
			putClientProperty(JEditorPane.HONOR_DISPLAY_PROPERTIES, Boolean.TRUE);
			setEditable(false);
			setOpaque(true);
		}

		@Override
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
				int row, int column) {
			Object renderValue = value;
			Color backgroundColor = Color.WHITE;
			
			if (value instanceof Horarios) {
				renderValue = ((Horarios) value).describirModulo();
			} else if (value instanceof String && column > 0) {
				// Check if this might be a reunion cell by looking for HTML content
				String stringValue = (String) value;
				if (stringValue.toLowerCase(Locale.ROOT).contains("<b>")) {
					// Try to extract estado from the parent model to get color
					// We need to access the original Reuniones object through the controller
					// For now, we'll parse the estado from the HTML
					String estadoLower = extractEstadoFromHtml(stringValue);
					backgroundColor = getColorForEstado(estadoLower);
				}
			}
			
			String texto = renderValue == null ? "" : renderValue.toString();
			String normalizado = texto.trim().toLowerCase(Locale.ROOT);
			if (!normalizado.startsWith("<html")) {
				texto = "<html>" + texto.replace("\n", "<br/>") + "</html>";
			}
			setText(texto);
			setFont(table.getFont());
			setForeground(isSelected ? table.getSelectionForeground() : new Color(0x1F1F1F));
			setBackground(isSelected ? table.getSelectionBackground() : backgroundColor);
			setBorder(BorderFactory.createEmptyBorder(8, 12, 8, 12));
			return this;
		}
		
		private String extractEstadoFromHtml(String html) {
			// Extract estado from HTML - it's the first line after the title
			String[] parts = html.split("<br/>");
			if (parts.length > 1) {
				String estadoPart = parts[1].replaceAll("<[^>]+>", "").trim();
				return estadoPart.toLowerCase();
			}
			return "";
		}
		
		private Color getColorForEstado(String estadoLower) {
			switch (estadoLower) {
			case "aceptada":
				return new Color(200, 230, 201); // Verde pastel
			case "denegada":
				return new Color(255, 205, 210); // Rojo pastel
			case "pendiente":
				return new Color(255, 224, 130); // Amarillo/Naranja pastel
			case "conflicto":
				return new Color(224, 224, 224); // Gris
			default:
				return Color.WHITE;
			}
		}
	}

	private static String limpiarHtml(String texto) {
		if (texto == null) {
			return null;
		}
		String normalizado = texto.replace("<html>", "").replace("</html>", "");
		return normalizado.replace("<br>", "\n").replace("<br/>", "\n").replace("<br />", "\n").replaceAll("<[^>]+>", "").trim();
	}

}
