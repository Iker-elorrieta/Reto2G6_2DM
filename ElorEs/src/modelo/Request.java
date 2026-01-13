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
    private Map<String, String> parametros;
    
    public Request(String header) {
        this.header = header;
        this.parametros = new HashMap<>();
    }
    
    public void addParametro(String clave, String valor) {
        parametros.put(clave, valor);
    }
    
    public String getHeader() {
        return header;
    }
    
    public Map<String, String> getParametros() {
        return parametros;
    }
    
    public String getParametro(String clave) {
        return parametros.get(clave);
    }
}
