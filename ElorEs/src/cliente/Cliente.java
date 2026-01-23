package cliente;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;


public class Cliente {

    private Socket socket;
    private ObjectInputStream entrada;
    private ObjectOutputStream salida;
    
	private static int SOCKET_PORT = Integer.parseInt(System.getenv().getOrDefault("SOCKET_PORT", "5000"));
	private static String SOCKET_HOST = System.getenv().getOrDefault("SOCKET_HOST", "localhost");
	
    public Cliente() throws Exception {
        socket = new Socket(SOCKET_HOST, SOCKET_PORT);
        System.out.println("Conectado al servidor por socket");
        salida = new ObjectOutputStream(socket.getOutputStream());
        salida.flush();
        entrada = new ObjectInputStream(socket.getInputStream());
    }

    public synchronized Object enviarRequest(String header, ArrayList<Object> datos) throws Exception {
        salida.writeObject(header);
        salida.flush();
        for (Object dato : datos) {
			salida.writeObject(dato);
			salida.flush();
		}
        return (Object) entrada.readObject();
    }

    public void cerrar() throws Exception {
        socket.close();
    }
}
