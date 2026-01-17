package cliente;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;


public class Cliente {

    private Socket socket;
    private ObjectInputStream entrada;
    private ObjectOutputStream salida;

    public Cliente(String host, int puerto) throws Exception {
        socket = new Socket(host, puerto);
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
