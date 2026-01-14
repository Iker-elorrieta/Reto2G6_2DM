package vista;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.JTextField;
import javax.swing.JPasswordField;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.ImageIcon;

public class Inicio extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel panelLogo;
	private PanelLogin panelLogin;
	/**
	 * Create the frame.
	 */
	public Inicio() {
		setTitle("Framework educativo - CIFP Elorrieta-Errekamari LHII");
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 460, 564);
		contentPane =  crearPanelconImagen("/Fondo.png");
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		panelLogo = crearPanelconImagen("/Elorrieta_White.png");
		panelLogo.setOpaque(false);
		panelLogo.setBounds(61, 36, 321, 95);
		contentPane.add(panelLogo);
		
		panelLogin = new PanelLogin();
		contentPane.add(panelLogin);
		
	}
	public static JPanel crearPanelconImagen(String rutaImagen) {
		return new JPanel() {
			private static final long serialVersionUID = 1L;
			private Image backgroundImage = new ImageIcon(Inicio.class.getResource(rutaImagen)).getImage();

			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
			}
		};
	}
	public void placeholder(String texto, Color color, JTextField textField) {
	    textField.setForeground(color);
	    if (textField.getText().isEmpty()) {
	        // Campo vacío: mostrar placeholder
	        textField.setText(texto);
	        textField.putClientProperty("placeholder", Boolean.TRUE);
	        if (textField instanceof JPasswordField) {
	            // Mostrar texto de placeholder para JPasswordField (no ocultar con asteriscos)
	            ((JPasswordField) textField).setEchoChar((char) 0);
	        }
	    } else {
	        // Campo ya contiene texto real
	        textField.putClientProperty("placeholder", Boolean.FALSE);
	        if (textField instanceof JPasswordField) {
	            ((JPasswordField) textField).setEchoChar((char) '*');
	        }
	    }
	    // Eliminamos listeners de foco previos para no acumular múltiples listeners iguales
	    FocusListener[] focusListeners = textField.getFocusListeners();
	    for (FocusListener listener : focusListeners) {
	        textField.removeFocusListener(listener);
	    }
	    // Añadimos listener que gestiona la aparición/desaparición del placeholder según el foco
	    textField.addFocusListener(new FocusAdapter() {
	        @Override
	        public void focusGained(FocusEvent e) {
	            if (textField.getText().equals(texto)) {
	                // Al recibir el foco, si está el placeholder, lo borramos para que el usuario escriba
	                textField.setText("");
	                textField.setForeground(Color.BLACK);
	                textField.putClientProperty("placeholder", Boolean.FALSE);
	                if (textField instanceof JPasswordField) {
	                    // Restauramos el echo char para ocultar la contraseña al escribir
	                    ((JPasswordField) textField).setEchoChar((char) '*');
	                }
	            }
	        }

	        @Override
	        public void focusLost(FocusEvent e) {
	            if (textField.getText().isEmpty()) {
	                // Si al perder foco el campo queda vacío, mostramos de nuevo el placeholder
	                textField.setForeground(color);
	                textField.setText(texto);
	                textField.putClientProperty("placeholder", Boolean.TRUE);
	                if (textField instanceof JPasswordField) {
	                    // Mostrar placeholder sin ocultación
	                    ((JPasswordField) textField).setEchoChar((char) 0);
	                }
	            } else {
	                // Si hay texto, aseguramos que el placeholder está desactivado
	                textField.putClientProperty("placeholder", Boolean.FALSE);
	                if (textField instanceof JPasswordField) {
	                    ((JPasswordField) textField).setEchoChar((char) '*');
	                }
	            }
	        }
	    });
	}
	public PanelLogin getPanelLogin() {
		return panelLogin;
	}
	public void setPanelLogin(PanelLogin panelLogin) {
		this.panelLogin = panelLogin;
	}
	
}
