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


		modeloAlumnos = new DefaultTableModel(new String[] { "ID", "Avatar", "Nombre completo", "Ciclo", "Usuario",
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

	private static class PanelAvatarRenderer extends JPanel implements TableCellRenderer {
		private static final long serialVersionUID = 1L;
		private static final Image DEFAULT_AVATAR = new ImageIcon(Login.class.getResource("/avatar.png")).getImage();
		private static final Map<String, Image> IMAGE_CACHE = new HashMap<>();
		private static final int AVATAR_PADDING = 5;
		private Image currentImage = DEFAULT_AVATAR;
		private boolean selected;

		PanelAvatarRenderer() {
			setOpaque(false);
		}

		@Override
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
				int row, int column) {
			currentImage = cargarImagen(value);
			selected = isSelected;
			int size = Math.max(table.getRowHeight(), 44);
			setPreferredSize(new Dimension(size, size));
			return this;
		}

		@Override
		protected void paintComponent(Graphics g) {
			Graphics2D g2 = (Graphics2D) g.create();
			try {
				g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
				int baseSize = Math.min(getWidth(), getHeight());
				int diameter = Math.max(baseSize - (AVATAR_PADDING * 2), 20);
				int x = (getWidth() - diameter) / 2;
				int y = (getHeight() - diameter) / 2;
				RoundRectangle2D clip = new RoundRectangle2D.Float(x, y, diameter, diameter, diameter, diameter);
				g2.setClip(clip);
				if (currentImage != null) {
					g2.drawImage(currentImage, x, y, x + diameter, y + diameter, this);
				} else {
					g2.setColor(Color.LIGHT_GRAY);
					g2.fill(clip);
				}
				g2.setClip(null);
				if (selected) {
					g2.setColor(new Color(30, 42, 68, 140));
					g2.setStroke(new java.awt.BasicStroke(2f));
					g2.draw(clip);
				}
			} finally {
				g2.dispose();
			}
		}

		private Image cargarImagen(Object value) {
			if (!(value instanceof String)) {
				return DEFAULT_AVATAR;
			}
			String url = ((String) value).trim();
			if (url.isEmpty() || "null".equalsIgnoreCase(url)) {
				return DEFAULT_AVATAR;
			}
			return IMAGE_CACHE.computeIfAbsent(url, key -> {
				try {
					URI uri = new URI(key);
					URL remote = uri.toURL();
					return ImageIO.read(remote);
				} catch (Exception e) {
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
