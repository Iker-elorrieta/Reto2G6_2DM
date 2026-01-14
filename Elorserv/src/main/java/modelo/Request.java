package modelo;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Request implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String header;
    private Map<String, Object> datos;
    
    public Request(String header) {
        this.header = header;
        this.datos = new HashMap<>();
    }
    
    public Request(String header, String clave1, Object valor1) {
		this.header = header;
        this.datos = new HashMap<>();
		this.addDato(clave1, valor1);
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
    public Object getParametro(String clave) {
        return datos.get(clave);
    }
    
    public Object getDato(String clave) {
        return datos.get(clave);
    }
    
}
