package vista;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.Date;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import com.toedter.calendar.JDateChooser;

import modelo.Centros;
import modelo.Users;

public class VentanaAnadirReunion extends JFrame {

    private static final long serialVersionUID = 1L;

    private JPanel contentPane;
    private JPanel panelFormulario;

    private JComboBox<Users> comboAlumno;
    private JComboBox<Centros> comboCentros;
    private JTextField txtTitulo;
    private JTextArea txtAsunto;
    private JTextField txtAula;
    private JDateChooser selectorFecha;
    private JSpinner selectorHora;
    private JButton btnGuardar;
    private JLabel lblEstado;
    
    
    public VentanaAnadirReunion() {
        setTitle("Anadir reunion - CIFP Elorrieta-Errekamari LHII");
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 690, 560);
        setLocationRelativeTo(null);

        contentPane = Login.crearPanelconImagen("/Fondo.png");
        contentPane.setBorder(new EmptyBorder(20, 20, 20, 20));
        contentPane.setLayout(null);
        setContentPane(contentPane);

        panelFormulario = crearPanelRedondeado();
        panelFormulario.setOpaque(false);
        panelFormulario.setBackground(new Color(255, 255, 255, 230));
        panelFormulario.setLayout(null);
        panelFormulario.setBounds(20, 20, 633, 476);
        contentPane.add(panelFormulario);

        JLabel lblTitulo = new JLabel("Añadir reunion");
        lblTitulo.setFont(new Font("Raleway", Font.BOLD, 26));
        lblTitulo.setHorizontalAlignment(JLabel.CENTER);
        lblTitulo.setBounds(0, 15, 641, 32);
        panelFormulario.add(lblTitulo);

        JLabel lblAlumno = new JLabel("Alumno");
        lblAlumno.setFont(new Font("Raleway", Font.BOLD, 14));
        lblAlumno.setBounds(40, 70, 260, 18);
        panelFormulario.add(lblAlumno);

        comboAlumno = new JComboBox<>();
        comboAlumno.setFont(new Font("Raleway", Font.PLAIN, 14));
        comboAlumno.setBounds(40, 95, 260, 32);
        comboAlumno.setRenderer(new UsuarioRenderer());
        panelFormulario.add(comboAlumno);

        JLabel lblCentro = new JLabel("Centro");
        lblCentro.setFont(new Font("Raleway", Font.BOLD, 14));
        lblCentro.setBounds(40, 138, 260, 18);
        panelFormulario.add(lblCentro);

        comboCentros = new JComboBox<>();
        comboCentros.setFont(new Font("Raleway", Font.PLAIN, 14));
        comboCentros.setBounds(40, 163, 260, 32);
        comboCentros.setRenderer(new CentroRenderer());
        panelFormulario.add(comboCentros);

        JLabel lblAula = new JLabel("Aula");
        lblAula.setFont(new Font("Raleway", Font.BOLD, 14));
        lblAula.setBounds(340, 70, 260, 18);
        panelFormulario.add(lblAula);

        txtAula = new JTextField();
        txtAula.setFont(new Font("Raleway", Font.PLAIN, 14));
        txtAula.setBounds(340, 95, 260, 32);
        panelFormulario.add(txtAula);

        JLabel lblFecha = new JLabel("Fecha y hora");
        lblFecha.setFont(new Font("Raleway", Font.BOLD, 14));
        lblFecha.setBounds(340, 138, 260, 18);
        panelFormulario.add(lblFecha);

        selectorFecha = new JDateChooser();
        selectorFecha.setDate(new Date());
        selectorFecha.setDateFormatString("dd/MM/yyyy");
        selectorFecha.setFont(new Font("Raleway", Font.PLAIN, 14));
        selectorFecha.setBounds(340, 163, 157, 32);
        panelFormulario.add(selectorFecha);

        selectorHora = new JSpinner(new SpinnerDateModel());
        selectorHora.setFont(new Font("Raleway", Font.PLAIN, 14));
        selectorHora.setBounds(496, 163, 104, 32);
        selectorHora.setEditor(new JSpinner.DateEditor(selectorHora, "HH:mm"));
        panelFormulario.add(selectorHora);

        JLabel lblTituloReunion = new JLabel("Titulo");
        lblTituloReunion.setFont(new Font("Raleway", Font.BOLD, 14));
        lblTituloReunion.setBounds(40, 211, 580, 18);
        panelFormulario.add(lblTituloReunion);

        txtTitulo = new JTextField();
        txtTitulo.setFont(new Font("Raleway", Font.PLAIN, 14));
        txtTitulo.setBounds(40, 236, 560, 32);
        panelFormulario.add(txtTitulo);

        JLabel lblAsunto = new JLabel("Asunto");
        lblAsunto.setFont(new Font("Raleway", Font.BOLD, 14));
        lblAsunto.setBounds(40, 279, 580, 18);
        panelFormulario.add(lblAsunto);

        txtAsunto = new JTextArea();
        txtAsunto.setWrapStyleWord(true);
        txtAsunto.setLineWrap(true);
        txtAsunto.setFont(new Font("Raleway", Font.PLAIN, 14));

        JScrollPane scrollAsunto = new JScrollPane(txtAsunto);
        scrollAsunto.setBounds(40, 310, 560, 80);
        panelFormulario.add(scrollAsunto);

        lblEstado = new JLabel("");
        lblEstado.setHorizontalAlignment(SwingConstants.CENTER);
        lblEstado.setFont(new Font("Raleway", Font.PLAIN, 13));
        lblEstado.setForeground(new Color(0xB20000));
        lblEstado.setBounds(40, 402, 560, 18);
        panelFormulario.add(lblEstado);

        btnGuardar = new JButton("Guardar");
        btnGuardar.setFont(new Font("Raleway", Font.BOLD, 14));
        btnGuardar.setBackground(Color.decode("#0092A5"));
        btnGuardar.setForeground(Color.WHITE);
        btnGuardar.setBounds(40, 416, 560, 34);
        panelFormulario.add(btnGuardar);
    }

    private JPanel crearPanelRedondeado() {
        return new JPanel() {
            private static final long serialVersionUID = 1L;

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(getBackground());
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 30, 30);
                g2.dispose();
            }
        };
    }

    public void setCentros(List<Centros> centros) {
        DefaultComboBoxModel<Centros> model = new DefaultComboBoxModel<>();
        if (centros != null) {
            for (Centros centro : centros) {
                model.addElement(centro);
            }
        }
        comboCentros.setModel(model);
    }

    public void setAlumnos(List<Users> alumnos) {
        DefaultComboBoxModel<Users> model = new DefaultComboBoxModel<>();
        if (alumnos != null) {
            for (Users alumno : alumnos) {
                model.addElement(alumno);
            }
        }
        comboAlumno.setModel(model);
    }

    public JComboBox<Centros> getComboCentros() {
        return comboCentros;
    }

    public JComboBox<Users> getComboAlumnos() {
        return comboAlumno;
    }

    public JTextField getTxtTitulo() {
        return txtTitulo;
    }

    public JTextArea getTxtAsunto() {
        return txtAsunto;
    }

    public JTextField getTxtAula() {
        return txtAula;
    }

    public JDateChooser getSelectorFecha() {
        return selectorFecha;
    }

    public JSpinner getSelectorHora() {
        return selectorHora;
    }

    public JButton getBtnGuardar() {
        return btnGuardar;
    }
    
    
    public JLabel getLblEstado() {
		return lblEstado;
	}

	public void setLblEstado(JLabel lblEstado) {
		this.lblEstado = lblEstado;
	}

	public void limpiarFormulario() {
		comboAlumno.setSelectedIndex(-1);
		comboCentros.setSelectedIndex(-1);
		txtTitulo.setText("");
		txtAsunto.setText("");
		txtAula.setText("");
		selectorFecha.setDate(new Date());
		selectorHora.setValue(new Date());
		lblEstado.setText("");
	}


    private static class CentroRenderer extends DefaultListCellRenderer {
        private static final long serialVersionUID = 1L;

        @Override
        public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected,
                boolean cellHasFocus) {
            super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
            if (value instanceof Centros centro) {
                String nombre = centro.getNOM() != null ? centro.getNOM() : "Centro";
                Integer codigo = centro.getCCEN();
                setText((codigo != null ? codigo + " - " : "") + nombre);
            } else {
                setText("Selecciona un centro");
            }
            return this;
        }
    }

    private static class UsuarioRenderer extends DefaultListCellRenderer {
        private static final long serialVersionUID = 1L;

        @Override
        public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected,
                boolean cellHasFocus) {
            super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
            if (value instanceof Users usuario) {
                String nombre = usuario.getNombre() != null ? usuario.getNombre() : "";
                String apellidos = usuario.getApellidos() != null ? usuario.getApellidos() : "";
                setText((nombre + " " + apellidos).trim());
            } else {
                setText("Selecciona");
            }
            return this;
        }
    }
}
