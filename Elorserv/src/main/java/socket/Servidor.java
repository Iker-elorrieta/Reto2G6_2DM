package socket;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;


public class Servidor {

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(5000)) {
            System.out.println("Servidor iniciado");

            while (true) {
                Socket socket = serverSocket.accept();

                ObjectOutputStream salida =
                        new ObjectOutputStream(socket.getOutputStream());
                salida.flush();

                ObjectInputStream entrada =
                        new ObjectInputStream(socket.getInputStream());

                new HiloServidor(entrada, salida).start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

