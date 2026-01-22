package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.util.Arrays;
import java.util.List;
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
import modelo.Reuniones;

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
	private int[] customColumnWidths;
	private boolean mostrarModuloCompleto;

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
				if (value instanceof List<?>) {
					return TablaHorario.this.describirListaTooltip((List<?>) value);
				} else if (value instanceof Horarios) {
					return TablaHorario.this.construirTooltipHorario((Horarios) value);
				} else if (value instanceof Reuniones) {
					return TablaHorario.this.construirTooltipReunion((Reuniones) value);
				}
				if (value == null) {
					return null;
				}
				return value.toString();
			}
		};
		configurarTabla();
		scrollPane = new JScrollPane(table);
		scrollPane.setBorder(BorderFactory.createLineBorder(new Color(0xDADCE0)));
		scrollPane.getViewport().setBackground(Color.WHITE);
		add(scrollPane, BorderLayout.CENTER);
	}

	private DefaultTableModel crearModeloBase() {
		return new DefaultTableModel(new String[] { "", "Lunes", "Martes", "Miércoles", "Jueves", "Viernes" },
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
		table.setRowSelectionAllowed(false);
		table.setColumnSelectionAllowed(false);
		table.setCellSelectionEnabled(false);
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
			column.setMaxWidth(width);
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


	public void setModelo(DefaultTableModel modelo) {
		if (modelo == null) {
			modelo = crearModeloBase();
		}
		table.setModel(modelo);
		configurarTabla();
		ajustarAlturaFilas();
	}

	public void setCustomColumnWidths(int[] widths) {
		if (widths == null) {
			customColumnWidths = null;
		} else {
			customColumnWidths = Arrays.copyOf(widths, widths.length);
		}
		if (table != null && table.getColumnModel() != null) {
			configurarColumnas();
		}
	}

	public void setMode(Mode mode) {
		if (mode == null) {
			return;
		}
		this.mode = mode;
		configurarColumnas();
		ajustarAlturaFilas();
	}

	public void setMostrarModuloCompleto(boolean mostrarModuloCompleto) {
		this.mostrarModuloCompleto = mostrarModuloCompleto;
		if (table != null) {
			table.repaint();
		}
	}

	private int obtenerAnchoColumna(int index) {
		if (customColumnWidths != null && index < customColumnWidths.length) {
			return customColumnWidths[index];
		}
		int[] widths = mode == Mode.REUNION ? COLUMN_WIDTHS_REUNION : COLUMN_WIDTHS_HORARIO;
		return widths[Math.min(index, widths.length - 1)];
	}

	private String construirTooltipHorario(Horarios horario) {
		if (horario == null || horario.getModulos() == null || horario.getModulos().getNombre() == null) {
			return "Clase";
		}
		String modulo = horario.getModulos().getNombre().trim();
		String aula = horario.getAula();
		StringBuilder sb = new StringBuilder("Módulo: ").append(modulo);
		if (aula != null && !aula.trim().isEmpty()) {
			sb.append(" | Aula: ").append(aula.trim());
		}
		return sb.toString();
	}

	private String construirTooltipReunion(Reuniones reunion) {
		if (reunion == null) {
			return "Reunión";
		}
		String titulo = reunion.getTitulo() != null ? reunion.getTitulo().trim() : "";
		String asunto = reunion.getAsunto() != null ? reunion.getAsunto().trim() : "";
		String alumno = "";
		if (reunion.getUsersByAlumnoId() != null) {
			String nombre = reunion.getUsersByAlumnoId().getNombre();
			String apellidos = reunion.getUsersByAlumnoId().getApellidos();
			alumno = ((nombre == null ? "" : nombre) + " " + (apellidos == null ? "" : apellidos)).trim();
		}
		StringBuilder sb = new StringBuilder();
		if (!titulo.isEmpty()) {
			sb.append("Título: ").append(titulo);
		}
		if (!asunto.isEmpty()) {
			if (sb.length() > 0) {
				sb.append(" | ");
			}
			sb.append("Asunto: ").append(asunto);
		}
		if (!alumno.isEmpty()) {
			if (sb.length() > 0) {
				sb.append(" | ");
			}
			sb.append("Alumno: ").append(alumno);
		}
		return sb.length() > 0 ? sb.toString() : "Reunión";
	}

	private String describirListaTooltip(List<?> valores) {
		if (valores == null) {
			return null;
		}
		Horarios primerHorario = null;
		Reuniones primerReunion = null;
		for (Object item : valores) {
			if (primerHorario == null && item instanceof Horarios) {
				primerHorario = (Horarios) item;
			} else if (primerReunion == null && item instanceof Reuniones) {
				primerReunion = (Reuniones) item;
			}
		}
		StringBuilder sb = new StringBuilder();
		if (primerHorario != null) {
			sb.append(construirTooltipHorario(primerHorario));
		}
		if (primerReunion != null) {
			if (sb.length() > 0) {
				sb.append("\n");
			}
			sb.append(construirTooltipReunion(primerReunion));
		}
		return sb.length() > 0 ? sb.toString() : null;
	}

	private class MultiLineHtmlRenderer extends JEditorPane implements TableCellRenderer {
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
			String texto = "";
			Color backgroundColor = Color.WHITE;

			if (value instanceof List<?>) {
				texto = procesarLista((List<?>) value);
				backgroundColor = obtenerColorDesdeLista((List<?>) value);
			} else if (value instanceof Horarios) {
				texto = obtenerDescripcionHorario((Horarios) value, true, false);
			} else if (value instanceof Reuniones) {
				Reuniones reunion = (Reuniones) value;
				backgroundColor = reunion.getColorEstado();
				texto = reunion.describirReunion(true,false);
			} else if (value != null) {
				texto = asegurarHtml(value.toString());
			}

			setText(texto);
			setFont(table.getFont());
			setForeground(isSelected ? table.getSelectionForeground() : new Color(0x1F1F1F));
			setBackground(isSelected ? table.getSelectionBackground() : backgroundColor);
			setBorder(null);
			return this;
		}

		private String procesarLista(List<?> valores) {
			Horarios primerHorario = null;
			Reuniones primerReunion = null;
			for (Object item : valores) {
				if (primerHorario == null && item instanceof Horarios) {
					primerHorario = (Horarios) item;
				} else if (primerReunion == null && item instanceof Reuniones) {
					primerReunion = (Reuniones) item;
				}
				if (primerHorario != null && primerReunion != null) {
					break;
				}
			}

			if (primerHorario != null && primerReunion != null) {
				String moduloContenido = obtenerDescripcionHorario(primerHorario, false, true);
				String reunionContenido = primerReunion.describirReunion(false, false);
				return "<html><div style='line-height:1.2;'>" +
					"<div>" + moduloContenido + "</div>" +
					"<div style='margin-top:4px;'>" + reunionContenido + "</div>" +
					"</div></html>";
			}
			if (primerHorario != null) {
				return obtenerDescripcionHorario(primerHorario, true, false);
			}
			if (primerReunion != null) {
				return primerReunion.describirReunion(true,false);
			}
			return "";
		}

		private String obtenerDescripcionHorario(Horarios horario, boolean envolverHtml, boolean combinadoConReunion) {
			if (horario == null) {
				return "";
			}
			boolean usarDescripcionCompleta = mostrarModuloCompleto && !combinadoConReunion;
			if (usarDescripcionCompleta) {
				return horario.describirModuloCompleto(envolverHtml);
			}
			return horario.describirModulo(envolverHtml);
		}

		private Color obtenerColorDesdeLista(List<?> valores) {
			if (valores == null) {
				return Color.WHITE;
			}
			for (Object item : valores) {
				if (item instanceof Reuniones) {
					return ((Reuniones) item).getColorEstado();
				}
			}
			return Color.WHITE;
		}

		private String asegurarHtml(String texto) {
			if (texto == null || texto.isBlank()) {
				return "";
			}
			String normalizado = texto.trim().toLowerCase(Locale.ROOT);
			if (normalizado.startsWith("<html")) {
				return texto;
			}
			return "<html>" + escapeHtml(texto).replace("\n", "<br/>") + "</html>";
		}

		private String escapeHtml(String texto) {
			if (texto == null) {
				return "";
			}
			return texto.replace("&", "&amp;")
					.replace("<", "&lt;")
					.replace(">", "&gt;")
					.replace("\"", "&quot;")
					.replace("'", "&#39;");
		}
	}

}
