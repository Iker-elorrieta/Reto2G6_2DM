package ElorEs;

import controlador.Controlador;
import vista.Inicio;

public class ElorEs {
	  public static void main(String[] args) {
	        try {
	            Inicio ventanaLogin = new Inicio();
	            ventanaLogin.setVisible(true);
	            new Controlador(ventanaLogin);
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
}
