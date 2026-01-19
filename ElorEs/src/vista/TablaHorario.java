package vista;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.Color;
import java.awt.Font;
import javax.swing.table.DefaultTableModel;

public class TablaHorario extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTable table;
	private DefaultTableModel modelo;
	private JScrollPane scrollPane;

	/**
	 * Create the panel.
	 */
	public TablaHorario() {
		setBackground(new Color(255, 128, 0));
		setLayout(null);
		modelo = new DefaultTableModel(new String[] { "Hora", "Lunes", "Martes", "Miercoles","Jueves","Viernes","Sábado","Domingo" }, 0);
		table = new JTable(modelo);
		table.setFont(new Font("Raleway", Font.PLAIN, 15));
		table.getTableHeader().setFont(new Font("Raleway", Font.PLAIN, 15));
		table.setRowHeight(25);
		// Desactivar ediciones
		table.setDefaultEditor(Object.class, null);
		scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 627, 341);
		add(scrollPane);
		scrollPane.setViewportView(table);
		scrollPane.getViewport().setBackground(Color.WHITE);
	}
	public DefaultTableModel getModelo() {
		return modelo;
	}
}
