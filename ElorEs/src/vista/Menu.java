package vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;

public class Menu extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton btnDesconectar;
	private JButton btnReuniones;
	private JButton btnHorarios;
	private JButton btnAlumnos;
	private JButton btnPerfil;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Menu frame = new Menu();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Menu() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 618, 436);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		btnPerfil = new JButton("Consultar perfil");
		btnPerfil.setBounds(206, 138, 148, 23);
		contentPane.add(btnPerfil);
		
		btnAlumnos = new JButton("Consultar alumnos");
		btnAlumnos.setBounds(206, 193, 148, 23);
		contentPane.add(btnAlumnos);
		
		btnHorarios = new JButton("Consultar horarios");
		btnHorarios.setBounds(206, 250, 148, 23);
		contentPane.add(btnHorarios);
		
		btnReuniones = new JButton("Reuniones");
		btnReuniones.setBounds(206, 309, 148, 23);
		contentPane.add(btnReuniones);
		
		btnDesconectar = new JButton("Log out");
		btnDesconectar.setBounds(487, 36, 89, 23);
		contentPane.add(btnDesconectar);

	}
}
