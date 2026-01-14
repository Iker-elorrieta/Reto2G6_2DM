package com.reto2.elorserv;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import metodos.Usuario;
import modelo.Request;
import modelo.Users;

public class SocketServer extends Thread {

	private ObjectInputStream entrada;
	private ObjectOutputStream salida;
	private Usuario usuario = new Usuario();

	public SocketServer(ObjectInputStream entrada, ObjectOutputStream salida) {
		this.entrada = entrada;
		this.salida = salida;
	}

	@Override
	public void run() {
		try {
			while (true) {
				Request request = (Request) entrada.readObject();
				Request response = procesarRequest(request);
				salida.writeObject(response);
				salida.flush();
			}
		} catch (Exception e) {
			System.out.println("Cliente desconectado");
			e.printStackTrace();

		}
	}

	private Request procesarRequest(Request request) {
		switch (request.getHeader()) {
		case "login":
			return login(request);
		case "get_usuario":
			return new Request("ok","usuario", usuario.getUsuarioLogged());	
		case "logout":
			usuario.cerrarSesion();
			return new Request("ok","mensaje", "Sesión cerrada correctamente");
		default:
			return new Request("error","mensaje", "Request no reconocido");
		}
	}

	private Request login(Request request) {
		String u = (String) request.getParametro("username");
		String p = (String) request.getParametro("password");
		try {
			Users user = usuario.iniciarSesion(u, p);
			if (user != null) {
				return new Request("login_correcto","usuario",user);
			} else {
				return new Request("error","mensaje","Usuario o contraseña incorrectos");
			}
		} catch (IllegalArgumentException e) {
			return new Request("error","mensaje", e.getMessage());

		} catch (RuntimeException e) {
			return new Request("error","mensaje", e.getMessage());
		}

	}
}
