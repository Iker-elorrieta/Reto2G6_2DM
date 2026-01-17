package com.reto2.elorserv;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import com.reto2.elorserv.modelo.Users;


public class SocketServer extends Thread {

	private ObjectInputStream entrada;
	private ObjectOutputStream salida;
	private Users usuario = null;

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
		Object response = null;
		try {
			switch (header) {
			case "login":
				String username = (String) entrada.readObject();
				String contrasena = (String) entrada.readObject();
				response = login(username, contrasena);
				break;
			case "get_usuario":
				response = usuario.getUsuarioPorID();
				break;
			case "logout":
				usuario = null;
				response = "Sesión cerrada correctamente";
				break;
			default:
				response = "Request no reconocido";
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return response;
	}

	private Object login(String username, String contrasena) {

		try {
			
			Users u = new Users(username,contrasena).iniciarSesion();
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
