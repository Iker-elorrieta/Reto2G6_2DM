package socket;

import java.io.IOException;
import java.net.ServerSocket;

public class Servidor {

	public static void main(String[] args) {
		try (ServerSocket serverSocket = new ServerSocket(5000)) {
			System.out.println("Servidor iniciado.");
			while (true) {
				try {
					new HiloServidor(serverSocket.accept()).start();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();

		}

	}

}
