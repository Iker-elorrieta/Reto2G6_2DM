package com.reto2.elorserv;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ElorservApplication implements CommandLineRunner {

	@Value("${socket.port}")
	private int socketPort;

	/*
	 * IMPORTANTE: importa elorserv.sql
	 */

	public static void main(String[] args) {
		SpringApplication.run(ElorservApplication.class, args);
	}

	@Override
	public void run(String... args) {

		try (ServerSocket serverSocket = new ServerSocket(socketPort)) {
			System.out.println("SOCKET SERVER: Servidor iniciado P:" + socketPort);
			while (true) {
				Socket socket = serverSocket.accept();
				ObjectOutputStream salida = new ObjectOutputStream(socket.getOutputStream());
				salida.flush();
				ObjectInputStream entrada = new ObjectInputStream(socket.getInputStream());
				new SocketServer(entrada, salida).start();
			}
		} catch (Exception e) {
			System.err.println("SOCKET SERVER: Error iniciando el servidor -> " + e.getMessage());
		}

	}

}
