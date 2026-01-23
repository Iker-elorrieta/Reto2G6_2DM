package ElorEs;

import controlador.Controlador;
import vista.Login;

public class ElorEs {
	  public static void main(String[] args) {
	        try {
	            Login ventanaLogin = new Login();
	            ventanaLogin.setVisible(true);
	            new Controlador(ventanaLogin);
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
}
