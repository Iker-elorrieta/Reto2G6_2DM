package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import vista.Login;

public class Controlador implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Login login = new Login();
		login.setVisible(true);
		
	}
	
	public String mandarDatos(String usuario, String contrasena) {
		// Aquí puedes implementar la lógica para manejar el usuario
		return "login :: " + usuario + " :: " + contrasena;
	}	

}
