package com.reto2.elorserv;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ElorservApplication {

	private static int SOCKET_PORT = Integer.parseInt(System.getenv().getOrDefault("SOCKET_PORT","5000"));
	private static int API_PORT = Integer.parseInt(System.getenv().getOrDefault("API_PORT","8080"));

	/*
	 * IMPORTANTE:
	 * TRAS IMPORTAR LA BD POR PRIMERA VEZ, HAY QUE EJECUTAR HASHUSUARIOS PARA HASHEAR LOS USUARIOS
	 * 
	*/

	public static void main(String[] args) {
		// iniciar la aplicacion de spring
		SpringApplication app = new SpringApplication(ElorservApplication.class);
		app.setDefaultProperties(
		    Map.of("server.port", API_PORT)
		);
		app.run(args);
		
		// Iniciar el servidor de sockets
		try (ServerSocket serverSocket = new ServerSocket(SOCKET_PORT)) {
			System.out.println("SOCKET SERVER: Servidor iniciado P:"+SOCKET_PORT);
			while (true) {
				Socket socket = serverSocket.accept();
				ObjectOutputStream salida = new ObjectOutputStream(socket.getOutputStream());
				salida.flush();
				ObjectInputStream entrada = new ObjectInputStream(socket.getInputStream());
				new SocketServer(entrada, salida).start();
			}
		} catch (Exception e) {
			//e.printStackTrace();
		}
	}


}

