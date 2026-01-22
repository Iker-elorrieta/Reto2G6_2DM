package com.reto2.elorserv;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.SocketException;

import modelo.Centros;
import modelo.Horarios;
import modelo.Reuniones;
import modelo.Users;

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
		} catch (SocketException e) {
			System.out.println("Usuario desconectado");
		}
		catch (Exception e) {
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
				Users usuarioAutenticado = new Users(username, contrasena).iniciarSesion();
				if (usuarioAutenticado != null) {
					this.usuario = usuarioAutenticado; // Asignar el usuario autenticado
				}
				response = usuarioAutenticado;
				break;
			case "get_usuario":
				response = usuario.getUsuarioPorID();
				break;
			case "logout":
				usuario = null;
				response = "Sesión cerrada correctamente";
				break;
			case "get_reuniones":
				response = Reuniones.getReunionesByUserID(usuario.getId());
				break;
			case "get_reuniones_semana":
				response = Reuniones.getReunionesByUserIDSemanaActual(usuario.getId());
				break;
			case "update_reunion":
				int reunionId = (int) entrada.readObject();
				String nuevoEstado = (String) entrada.readObject();
				response = new Reuniones(reunionId).cambiarEstadoReunion(Reuniones.EstadoReunion.valueOf(nuevoEstado));
				break;
			case "crear_reunion":
				Reuniones nuevaReunion = (Reuniones) entrada.readObject();
				nuevaReunion.setUsersByAlumnoId(usuario);
				response = nuevaReunion.crearReunion();
				break;
			case "get_centros":
				response = Centros.getAllCentros();
				break;
			case "get_horarios":
				System.out.println(usuario.getId());
				response = Horarios.getHorariosByUserId(usuario.getId());
				break;
			case "get_horarios_id":
				int userId = (int) entrada.readObject();
				response = Horarios.getHorariosByUserId(userId);
				break;
			case "get_profesores":
				response = Users.getUsersByTipo("profesor");
				break;
			case "get_alumnos":
				int profesorId = (int) entrada.readObject();
				response =Users.getAlumnosbyProfesorID(profesorId);
				break;
			default:
				response = "Request no reconocido";
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			response = e.getMessage();

		} catch (RuntimeException e) {
			response = e.getMessage();
		}
		return response;
	}
}
