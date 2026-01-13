package modelo;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Response implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String header;
    private Map<String, Object> datos;
    
    public Response(String header) {
        this.header = header;
        this.datos = new HashMap<>();
    }
    
    public void addDato(String clave, Object valor) {
        datos.put(clave, valor);
    }
    
    public String getHeader() {
        return header;
    }
    
    public Map<String, Object> getDatos() {
        return datos;
    }
    
    public Object getDato(String clave) {
        return datos.get(clave);
    }
}
