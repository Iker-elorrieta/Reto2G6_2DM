package com.reto2.elorserv;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

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
			login(request);
		case "get_usuario":
			Request ok = new Request("ok");
			ok.addDato("usuario", usuario.getUsuarioLogged());
			return ok;
		default:
			Request r = new Request("error");
			r.addDato("mensaje", "Request no reconocido");
			return r;
		}
	}

	private Request login(Request request) {
		String u = (String) request.getParametro("username");
		String p = (String) request.getParametro("password");
		try {
			Users user = usuario.iniciarSesion(u, p);
			Request ok = new Request("login_correcto");
			ok.addDato("usuario", user);
			return ok;
		} catch (IllegalArgumentException e) {
			Request err = new Request("error");
			err.addDato("mensaje", e.getMessage());
			return err;

		} catch (RuntimeException e) {
			Request err = new Request("error");
			err.addDato("mensaje", e.getMessage());
			return err;
		}

	}
}
