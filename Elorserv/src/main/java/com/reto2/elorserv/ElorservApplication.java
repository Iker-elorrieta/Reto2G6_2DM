package com.reto2.elorserv;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ElorservApplication {

	public static void main(String[] args) {
		
		// iniciar la aplicacion de spring
		SpringApplication.run(ElorservApplication.class, args);
		
		// Iniciar el servidor de sockets
		try (ServerSocket serverSocket = new ServerSocket(5000)) {
			System.out.println("SOCKET SERVER: Servidor iniciado (P: 5000)");
			while (true) {
				Socket socket = serverSocket.accept();
				ObjectOutputStream salida = new ObjectOutputStream(socket.getOutputStream());
				salida.flush();
				ObjectInputStream entrada = new ObjectInputStream(socket.getInputStream());
				new SocketServer(entrada, salida).start();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
