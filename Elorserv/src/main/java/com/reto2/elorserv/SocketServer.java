package com.reto2.elorserv;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import metodos.Usuario;
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
			System.out.println("Usuario conectado");
			while (true) {
				String request = (String) entrada.readObject();
				System.out.println("REQ: " + request);
				Object response = procesarRequest(request);
				salida.writeObject(response);
				salida.flush();
			}
		} catch (Exception e) {
			System.out.println("Usuario desconectado");
			e.printStackTrace();
		}
	}

	private Object procesarRequest(String header) {
		try {
			switch (header) {
			case "login":
				String username = (String) entrada.readObject();
				String contrasena = (String) entrada.readObject();
				return login(username, contrasena);
			case "get_usuario":
				return usuario.getUsuarioLogged();
			case "logout":
				usuario.cerrarSesion();
				return "Sesión cerrada correctamente";
			default:
				return "Request no reconocido";
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	private Object login(String username, String contrasena) {

		try {
			Users u = usuario.iniciarSesion(username, contrasena);
			if (u == null) {
				return "Credenciales inválidas";
			}
			return u;
		} catch (IllegalArgumentException e) {
			return e.getMessage();

		} catch (RuntimeException e) {
			return e.getMessage();
		}
	}
}
