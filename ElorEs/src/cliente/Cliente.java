package cliente;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;


public class Cliente {

    private Socket socket; // socket de comunicacion
    private ObjectInputStream entrada;  // flujo de entrada
    private ObjectOutputStream salida;  // flujo de salida
    
    // Variables de entorno 
	private static int SOCKET_PORT = Integer.parseInt(System.getenv().getOrDefault("SOCKET_PORT", "5000"));
	private static String SOCKET_HOST = System.getenv().getOrDefault("SOCKET_HOST", "localhost");
	
    public Cliente() throws Exception {
    	// Establecer conexion con el servidor
        socket = new Socket(SOCKET_HOST, SOCKET_PORT);
        System.out.println("Conectado al servidor por socket");
        // Inicializar flujos de entrada y salida
        salida = new ObjectOutputStream(socket.getOutputStream());
        salida.flush();
        entrada = new ObjectInputStream(socket.getInputStream());
    }
    // Enviar request al servidor y recibir respuesta
    public synchronized Object enviarRequest(String header, ArrayList<Object> datos) throws Exception {
    	// Enviar header
    	salida.writeUTF(header);
        salida.flush();
        // Enviar datos
        for (Object dato : datos) {
        	// Escribir segun el tipo de dato
        	if(dato instanceof Integer) {
				salida.writeInt((Integer) dato);
			} else if(dato instanceof Byte) {
				salida.writeByte((Byte) dato);
			} else if(dato instanceof String) {
				salida.writeUTF((String) dato);
			} else {
				salida.writeObject(dato);				
			}
			salida.flush();
		}
        // Leer y devolver respuesta
        return (Object) entrada.readObject();
    }
    // Cerrar conexion
    public void cerrar() throws Exception {
        socket.close();
    }
}
