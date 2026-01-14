package socket;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import metodos.Usuario;
import modelo.Request;
import modelo.Response;
public class HiloServidor extends Thread {

    private ObjectInputStream entrada;
    private ObjectOutputStream salida;
    private Usuario usuario = new Usuario();

    public HiloServidor(ObjectInputStream entrada, ObjectOutputStream salida) {
        this.entrada = entrada;
        this.salida = salida;
    }

    @Override
    public void run() {
        try {
            while (true) {
                Request request = (Request) entrada.readObject();
                Response response = procesarRequest(request);
                salida.writeObject(response);
                salida.flush();
            }
        } catch (Exception e) {
            System.out.println("Cliente desconectado");
            e.printStackTrace();

        }
    }

    private Response procesarRequest(Request request) {
        switch (request.getHeader()) {

            case "login":
                String u = request.getParametro("username");
                String p = request.getParametro("password");
                String respuesta = usuario.iniciarSesion(u, p);
                System.out.println(respuesta);
                if (respuesta.equals("login_correcto")) {
                    Response ok = new Response("login_correcto");
                    ok.addDato("mensaje", "Bienvenido " + u);
                    return ok;
                } else {
                    Response err = new Response("login_error");
                    err.addDato("error", "Credenciales incorrectas");
                    return err;
                }
            case "get_usuario":
                Response ok = new Response("ok");
                ok.addDato("usuario",usuario.getUsuario());
                return ok;
            default:
                Response r = new Response("ERROR");
                r.addDato("mensaje", "Request no reconocido");
                return r;
        }
    }
}
