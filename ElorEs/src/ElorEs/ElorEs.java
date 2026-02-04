package ElorEs;

import controlador.Controlador;
import vista.Login;

public class ElorEs {
	  public static void main(String[] args) {
	        try {
	        	// Crear y mostrar la ventana de login
	            Login ventanaLogin = new Login();
	            ventanaLogin.setVisible(true);
	            // Crear el controlador y pasarle la ventana de login
	            new Controlador(ventanaLogin);
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
}
