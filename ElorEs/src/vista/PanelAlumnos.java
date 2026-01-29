package vista;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.geom.RoundRectangle2D;
import java.net.URI;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

import org.kordamp.ikonli.materialdesign2.MaterialDesignC;
import org.kordamp.ikonli.swing.FontIcon;

public class PanelAlumnos extends JPanel {

	private static final long serialVersionUID = 1L;
	private JButton btnVolver;
	private JButton btnMisAlumnos;
	private JTable tableAlumnos;
	private DefaultTableModel modeloAlumnos;

	public PanelAlumnos() {
		setLayout(null);
		setPreferredSize(new Dimension(1394, 460));
		setBackground(Color.WHITE);
		setVisible(false);

		JPanel contenedor = new JPanel() {
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
		contenedor.setLayout(null);
		contenedor.setOpaque(false);
		contenedor.setBounds(10, 0, 1374, 425);
		add(contenedor);

		btnVolver = crearBotonVolver();
		btnVolver.setBounds(10, 11, 25, 38);
		contenedor.add(btnVolver);

		JLabel lblTitulo = new JLabel("Mis alumnos");
		lblTitulo.setFont(new Font("Raleway", Font.PLAIN, 26));
		lblTitulo.setBounds(45, 11, 240, 40);
		contenedor.add(lblTitulo);


		modeloAlumnos = new DefaultTableModel(new String[] { "ID", "Avatar", "Nombre completo",  "Usuario",
				"Email", "DNI", "Dirección", "Teléfono 1", "Teléfono 2" }, 0) {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		tableAlumnos = new JTable(modeloAlumnos) {
			private static final long serialVersionUID = 1L;

			@Override
			public Class<?> getColumnClass(int column) {
				return column == 0 ? Integer.class : Object.class;
			}
		};
		tableAlumnos.setFont(new Font("Raleway", Font.PLAIN, 15));
		tableAlumnos.setForeground(new Color(0x1F1F1F));
		tableAlumnos.getTableHeader().setFont(new Font("Raleway", Font.BOLD, 16));
		tableAlumnos.getTableHeader().setBackground(new Color(0xF4F6F8));
		tableAlumnos.getTableHeader().setReorderingAllowed(false);
		tableAlumnos.setRowHeight(60);
		tableAlumnos.setShowGrid(true);
		tableAlumnos.setGridColor(new Color(0xE0E0E0));
		tableAlumnos.setSelectionBackground(new Color(0xD0D3D9));
		tableAlumnos.setSelectionForeground(new Color(0x1F1F1F));
		tableAlumnos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableAlumnos.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);
		tableAlumnos.setDefaultEditor(Object.class, null);
		tableAlumnos.setFillsViewportHeight(true);
		tableAlumnos.setBorder(BorderFactory.createEmptyBorder());

		if (tableAlumnos.getColumnCount() > 0) {
			tableAlumnos.getColumnModel().getColumn(0).setMinWidth(0);
			tableAlumnos.getColumnModel().getColumn(0).setMaxWidth(0);
		}
		if (tableAlumnos.getColumnCount() > 1) {
			int avatarSize = tableAlumnos.getRowHeight();
			TableColumn avatarColumn = tableAlumnos.getColumnModel().getColumn(1);
			avatarColumn.setMinWidth(avatarSize);
			avatarColumn.setMaxWidth(avatarSize);
			avatarColumn.setPreferredWidth(avatarSize);
			avatarColumn.setCellRenderer(new PanelAvatarRenderer());
		}

		JScrollPane scrollPane = new JScrollPane(tableAlumnos);
		scrollPane.setBounds(0, 60, 1374, 365);
		scrollPane.getViewport().setBackground(Color.WHITE);
		contenedor.add(scrollPane);
	}

	private JButton crearBotonVolver() {
		JButton boton = new JButton();
		boton.setIcon(FontIcon.of(MaterialDesignC.CHEVRON_LEFT, 28, new Color(18, 26, 38)));
		boton.setContentAreaFilled(false);
		boton.setBorderPainted(false);
		boton.setFocusPainted(false);
		boton.setToolTipText("Volver");
		return boton;
	}

	// SE USA SOLO EN LA COLUMNA DE AVATAR DE LA TABLA DE ALUMNOS
	private static class PanelAvatarRenderer extends JPanel implements TableCellRenderer {
		private static final long serialVersionUID = 1L;
		// Imagen por defecto usada cuando no hay avatar válido disponible
		private static final Image DEFAULT_AVATAR = new ImageIcon(Login.class.getResource("/avatar.png")).getImage();
		// Cache de imágenes por URL para evitar descargas repetidas
		private static final Map<String, Image> IMAGE_CACHE = new HashMap<>();
		private Image currentImage = DEFAULT_AVATAR;
		// Marca si la fila está seleccionada (se usa para dibujar borde de selección)
		private boolean selected;

		// Constructor: configura el panel para ser transparente
		PanelAvatarRenderer() {
			setOpaque(false);
		}

		@Override
		// Se invoca por la tabla para recuperar el componente que mostrará la celda
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
				int row, int column) {
			// Cargar la imagen correspondiente al valor de la celda
			currentImage = cargarImagen(value);
			// Guardar el estado de selección para uso en paintComponent
			selected = isSelected;
			// Ajustar tamaño preferido según la altura de fila (mínimo 44 px)
			int size = Math.max(table.getRowHeight(), 44);
			setPreferredSize(new Dimension(size, size));
			return this;
		}

		@Override
		// Pintado personalizado: dibuja la imagen recortada en forma circular y borde si está seleccionado
		protected void paintComponent(Graphics g) {
			Graphics2D g2 = (Graphics2D) g.create();
			try {
				// Activar antialiasing para suavizar dibujo
				g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
				// Calcular tamaño base disponible y diámetro del avatar (respetando padding)
				int baseSize = Math.min(getWidth(), getHeight());
				int diameter = Math.max(baseSize - (5 * 2), 20);
				// Centrar el avatar dentro del área del renderer
				int x = (getWidth() - diameter) / 2;
				int y = (getHeight() - diameter) / 2;
				// Crear recorte redondeado para el avatar (círculo)
				RoundRectangle2D clip = new RoundRectangle2D.Float(x, y, diameter, diameter, diameter, diameter);
				// Aplicar el clip para que la imagen quede recortada
				g2.setClip(clip);
				if (currentImage != null) {
					// Dibujar la imagen escalada dentro del clip circular
					g2.drawImage(currentImage, x, y, x + diameter, y + diameter, this);
				} else {
					// Si no hay imagen, rellenar con un gris claro
					g2.setColor(Color.LIGHT_GRAY);
					g2.fill(clip);
				}
				// Restablecer clip para poder dibujar el borde
				g2.setClip(null);
				if (selected) {
					// Si está seleccionado, dibujar un borde semitransparente alrededor
					g2.setColor(new Color(30, 42, 68, 140));
					g2.setStroke(new java.awt.BasicStroke(2f));
					g2.draw(clip);
				}
			} finally {
				// Liberar el Graphics2D creado
				g2.dispose();
			}
		}

		// Carga la imagen a partir del valor de la celda
		private Image cargarImagen(Object value) {
			// Si no es cadena, devolver la imagen por defecto
			if (!(value instanceof String)) {
				return DEFAULT_AVATAR;
			}
			// Normalizar la URL trimming espacios
			String url = ((String) value).trim();
			// Si la URL está vacía o contiene "null", usar la imagen por defecto
			if (url.isEmpty() || "null".equalsIgnoreCase(url)) {
				return DEFAULT_AVATAR;
			}
			// Usar la cache de imágenes; si no existe, intentar descargar la imagen remota
			return IMAGE_CACHE.computeIfAbsent(url, key -> {
				try {
					URI uri = new URI(key);
					URL remote = uri.toURL();
					return ImageIO.read(remote);
				} catch (Exception e) {
					// En caso de error, devolver la imagen por defecto
					return DEFAULT_AVATAR;
				}
			});
		}
	}



	public JButton getBtnVolver() {
		return btnVolver;
	}

	public JButton getBtnMisAlumnos() {
		return btnMisAlumnos;
	}

	public JTable getTableAlumnos() {
		return tableAlumnos;
	}

	public DefaultTableModel getModeloAlumnos() {
		return modeloAlumnos;
	}
}
