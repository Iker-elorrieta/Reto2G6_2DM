package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.util.List;

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
	private static final long serialVersionUID = 1L;

	private static final int FILA_ALTURA = 56;
	private JTable table;
	private DefaultTableModel modelo;
	private DefaultTableModel modeloInicial = new DefaultTableModel(
			new String[] { "", "Lunes", "Martes", "Miércoles", "Jueves", "Viernes" }, 0);
	private int[] columnasWidths;
	private boolean mostrarModuloCompleto;

	public TablaHorario(int[] columnasWidths, boolean mostrarModuloCompleto) {
		this.modelo = modeloInicial;
		this.columnasWidths = columnasWidths;
		this.mostrarModuloCompleto = mostrarModuloCompleto;
		setLayout(new BorderLayout());
		setOpaque(false);
		setPreferredSize(new Dimension(682, 365));
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
					Horarios primerHorario = Horarios.getPrimerHorarioDesdeLista((List<?>) value);
					Reuniones primerReunion = Reuniones.getPrimeraReunionDesdeLista((List<?>) value);
					if (primerReunion == null) {
					return primerHorario.getTooltip();
					}					
					return primerHorario.getTooltip() + "   Reunión:"
							+ primerReunion.getTooltip();
				} else if (value instanceof Horarios) {
					return ((Horarios) value).getTooltip();
				} else if (value instanceof Reuniones) {
					return ((Reuniones) value).getTooltip();
				}
				if (value == null) {
					return null;
				}
				return value.toString();
			}
		};
		configurarTabla();
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBorder(BorderFactory.createLineBorder(new Color(0xDADCE0)));
		scrollPane.getViewport().setBackground(Color.WHITE);
		add(scrollPane, BorderLayout.CENTER);
	}

	private void configurarTabla() {
		table.setFont(new Font("Raleway", Font.PLAIN, 15));
		table.setForeground(new Color(0x1F1F1F));
		table.getTableHeader().setFont(new Font("Raleway", Font.BOLD, 16));
		table.getTableHeader().setBackground(new Color(0xF4F6F8));
		table.getTableHeader().setReorderingAllowed(false);
		table.setRowHeight(FILA_ALTURA);
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
		table.setDefaultRenderer(Object.class, new HorarioReunionesHTMLRenderer());
		for (int i = 0; i < table.getColumnCount(); i++) {
			TableColumn column = table.getColumnModel().getColumn(i);
			int width = columnasWidths[i];
			column.setPreferredWidth(width);
			column.setMinWidth(width);
			column.setMaxWidth(width);
		}
	}

	public void setModelo(DefaultTableModel modelo) {
		if (modelo == null) {
			modelo = modeloInicial;
		}
		table.setModel(modelo);
		configurarTabla();
		// Ajustar altura de filas
		int rows = table.getRowCount();
		if (rows <= 0) {
			table.setRowHeight(FILA_ALTURA);
			return;
		}
		int headerHeight = table.getTableHeader().getPreferredSize().height;
		int disponible = Math.max(120, getPreferredSize().height - headerHeight - 10);
		int altura = Math.max(FILA_ALTURA, disponible / rows);
		for (int row = 0; row < rows; row++) {
			table.setRowHeight(row, altura);
		}
	}

	// Renderer HTML para celdas de horario que pueden contener Horarios y/o Reuniones
	private class HorarioReunionesHTMLRenderer extends JEditorPane implements TableCellRenderer {
		private static final long serialVersionUID = 1L;

		// Constructor: configuración inicial del JEditorPane para HTML
		HorarioReunionesHTMLRenderer() {
			// Indicar que el contenido es HTML para permitir formato
			setContentType("text/html");
			// Respetar las propiedades de fuente del sistema para buen renderizado
			putClientProperty(JEditorPane.HONOR_DISPLAY_PROPERTIES, Boolean.TRUE);
			// No editable: solo se usa para mostrar contenido
			setEditable(false);
			// Opaque true para que el background se pinte correctamente
			setOpaque(true);
		}

		@Override
		// Devuelve el componente configurado para la celda concreta
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
				int row, int column) {
			// Texto HTML que se mostrará dentro del editor
			String texto = "";
			// Color de fondo por defecto blanco
			Color backgroundColor = Color.WHITE;

			// Si la celda contiene una lista, combinar el primer Horario y la primera Reunión si existen
			if (value instanceof List<?>) {
				texto = htmlCombinado((List<?>) value);
				// Si hay reunión, usar su color de estado como fondo
				Reuniones primeraReunion = Reuniones.getPrimeraReunionDesdeLista((List<?>) value);
				if (primeraReunion != null)
					backgroundColor = primeraReunion.getColorEstado();
			// Si contiene solo un Horarios, obtener su HTML 
			} else if (value instanceof Horarios) {
				texto = ((Horarios) value).getModuloHtml(!mostrarModuloCompleto, true);
			// Si contiene una Reunión, usar su color y descripción en HTML
			} else if (value instanceof Reuniones) {
				Reuniones reunion = (Reuniones) value;
				backgroundColor = reunion.getColorEstado();
				texto = reunion.getDescripcionHtml(true, false);
			// Si es otro tipo de objeto, usar su toString
			} else if (value != null) {
				texto = value.toString();
			}

			// Aplicar texto, fuente y colores según selección
			setText(texto);
			setFont(table.getFont());
			setForeground(isSelected ? table.getSelectionForeground() : new Color(0x1F1F1F));
			setBackground(isSelected ? table.getSelectionBackground() : backgroundColor);
			setBorder(null);
			return this;
		}

		// Combina HTML de Horario y Reunión cuando la celda contiene ambos valores en una lista
		private String htmlCombinado(List<?> valores) {
			// Tomar el primer Horario y la primera Reunión de la lista
			Horarios primerHorario = Horarios.getPrimerHorarioDesdeLista(valores);
			Reuniones primerReunion = Reuniones.getPrimeraReunionDesdeLista(valores);
			// Si no hay reunión, devolver solo el HTML del módulo
			if (primerReunion == null) {
				return primerHorario.getModuloHtml(mostrarModuloCompleto, false);
			} else if (primerHorario == null) {
				return primerReunion.getDescripcionHtml(false, false);
			}
			// Si hay ambos, construir un bloque HTML con módulo arriba y reunión abajo
			String moduloContenido = primerHorario.getModuloHtml(mostrarModuloCompleto, false);
			String reunionContenido = primerReunion.getDescripcionHtml(false, false);
			return "<html><div style='line-height:1.2;'>" + "<div>" + moduloContenido + "</div>"
					+ "<div style='margin-top:4px;'>" + reunionContenido + "</div>" + "</div></html>";
		}

	}

}
