package socket;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import Metodos.Usuario;

public class HiloServidor extends Thread {

	private Socket cliente;
	private DataOutputStream dos;
	private DataInputStream dis;

	private Usuario usuario;

	public HiloServidor(Socket cliente) {
		super();
		this.cliente = cliente;
		try {
			dos = new DataOutputStream(cliente.getOutputStream());
			dis = new DataInputStream(cliente.getInputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
		usuario = new Usuario();

	}

	@Override
	public void run() {
		String req = "";
		try {
			while (true) {
				req = recibirMensaje();
				String metodo = req.split(" :: ")[0];
				switch (metodo) {
				case "login":
					String username = req.split(" :: ")[1];
					String contrasena = req.split(" :: ")[2];
					String respuesta = usuario.iniciarSesion(username, contrasena);
					if (respuesta.equals("login_correcto")) {
						enviarMensaje("Login correcto. Bienvenido " + usuario.usuario.getUsername());
					} else {
						enviarMensaje(respuesta);
					}
					break;
				default:
					enviarMensaje("metodo_invalido");
				}
			}
		} catch (Exception e) {
			System.out.println("Conexión cerrada.");
		}

	}

	private void enviarMensaje(String mensaje) throws IOException {
		dos.writeUTF(mensaje);
	}

	private String recibirMensaje() throws IOException {
		return dis.readUTF();
	}

}
