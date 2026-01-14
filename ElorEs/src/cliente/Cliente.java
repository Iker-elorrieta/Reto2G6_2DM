package cliente;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import modelo.Request;

public class Cliente {

    private Socket socket;
    private ObjectInputStream entrada;
    private ObjectOutputStream salida;

    public Cliente(String host, int puerto) throws Exception {
        socket = new Socket(host, puerto);
        salida = new ObjectOutputStream(socket.getOutputStream());
        salida.flush();

        entrada = new ObjectInputStream(socket.getInputStream());
    }

    public synchronized Request enviarRequest(Request request) throws Exception {
        salida.writeObject(request);
        salida.flush();
        return (Request) entrada.readObject();
    }

    public void cerrar() throws Exception {
        socket.close();
    }
}
