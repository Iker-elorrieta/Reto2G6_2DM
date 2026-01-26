package modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import cliente.Cliente;

public class Centros implements Serializable {

	private static final long serialVersionUID = 1L;
	public Integer CCEN;
	public String NOM;
	public String NOME;
	public String DGENRC;
	public String DGENRE;
	public String GENR;
	public Integer MUNI = 1;
	public String DMUNIC;
	public String DMUNIE;
	public String DTERRC;
	public String DTERRE;
	public Integer DEPE = 11;
	public String DTITUC;
	public String DTITUE;
	public String DOMI;
	public Integer CPOS;
	public Long TEL1;
	public Long TFAX;
	public String EMAIL;
	public String PAGINA;
	public Double COOR_X;
	public Double COOR_Y;
	public Double LATITUD;
	public Double LONGITUD;

	public Integer getCCEN() {
		return CCEN;
	}

	public void setCCEN(Integer cCEN) {
		CCEN = cCEN;
	}

	public String getNOM() {
		return NOM;
	}

	public void setNOM(String nOM) {
		NOM = nOM;
	}

	public String getNOME() {
		return NOME;
	}

	public void setNOME(String nOME) {
		NOME = nOME;
	}

	public String getDGENRC() {
		return DGENRC;
	}

	public void setDGENRC(String dGENRC) {
		DGENRC = dGENRC;
	}

	public String getDGENRE() {
		return DGENRE;
	}

	public void setDGENRE(String dGENRE) {
		DGENRE = dGENRE;
	}

	public String getGENR() {
		return GENR;
	}

	public void setGENR(String gENR) {
		GENR = gENR;
	}

	public Integer getMUNI() {
		return MUNI;
	}

	public void setMUNI(Integer mUNI) {
		MUNI = mUNI;
	}

	public String getDMUNIC() {
		return DMUNIC;
	}

	public void setDMUNIC(String dMUNIC) {
		DMUNIC = dMUNIC;
	}

	public String getDMUNIE() {
		return DMUNIE;
	}

	public void setDMUNIE(String dMUNIE) {
		DMUNIE = dMUNIE;
	}

	public String getDTERRC() {
		return DTERRC;
	}

	public void setDTERRC(String dTERRC) {
		DTERRC = dTERRC;
	}

	public String getDTERRE() {
		return DTERRE;
	}

	public void setDTERRE(String dTERRE) {
		DTERRE = dTERRE;
	}

	public Integer getDEPE() {
		return DEPE;
	}

	public void setDEPE(Integer dEPE) {
		DEPE = dEPE;
	}

	public String getDTITUC() {
		return DTITUC;
	}

	public void setDTITUC(String dTITUC) {
		DTITUC = dTITUC;
	}

	public String getDTITUE() {
		return DTITUE;
	}

	public void setDTITUE(String dTITUE) {
		DTITUE = dTITUE;
	}

	public String getDOMI() {
		return DOMI;
	}

	public void setDOMI(String dOMI) {
		DOMI = dOMI;
	}

	public Integer getCPOS() {
		return CPOS;
	}

	public void setCPOS(Integer cPOS) {
		CPOS = cPOS;
	}

	public Long getTEL1() {
		return TEL1;
	}

	public void setTEL1(Long tEL1) {
		TEL1 = tEL1;
	}

	public Long getTFAX() {
		return TFAX;
	}

	public void setTFAX(Long tFAX) {
		TFAX = tFAX;
	}

	public String getEMAIL() {
		return EMAIL;
	}

	public void setEMAIL(String eMAIL) {
		EMAIL = eMAIL;
	}

	public String getPAGINA() {
		return PAGINA;
	}

	public void setPAGINA(String pAGINA) {
		PAGINA = pAGINA;
	}

	public Double getCOOR_X() {
		return COOR_X;
	}

	public void setCOOR_X(Double cOOR_X) {
		COOR_X = cOOR_X;
	}

	public Double getCOOR_Y() {
		return COOR_Y;
	}

	public void setCOOR_Y(Double cOOR_Y) {
		COOR_Y = cOOR_Y;
	}

	public Double getLATITUD() {
		return LATITUD;
	}

	public void setLATITUD(Double lATITUD) {
		LATITUD = lATITUD;
	}

	public Double getLONGITUD() {
		return LONGITUD;
	}

	public void setLONGITUD(Double lONGITUD) {
		LONGITUD = lONGITUD;
	}

	@Override
	public String toString() {
		return "Centros [CCEN=" + CCEN + ", NOM=" + NOM + ", NOME=" + NOME + ", DGENRC=" + DGENRC + ", DGENRE=" + DGENRE
				+ ", GENR=" + GENR + ", MUNI=" + MUNI + ", DMUNIC=" + DMUNIC + ", DMUNIE=" + DMUNIE + ", DTERRC="
				+ DTERRC + ", DTERRE=" + DTERRE + ", DEPE=" + DEPE + ", DTITUC=" + DTITUC + ", DTITUE=" + DTITUE
				+ ", DOMI=" + DOMI + ", CPOS=" + CPOS + ", TEL1=" + TEL1 + ", TFAX=" + TFAX + ", EMAIL=" + EMAIL
				+ ", PAGINA=" + PAGINA + ", COOR_X=" + COOR_X + ", COOR_Y=" + COOR_Y + ", LATITUD=" + LATITUD
				+ ", LONGITUD=" + LONGITUD + "]";
	}

	public static List<Centros> getCentros(Cliente cliente) throws Exception {
		List<Centros> centros = new ArrayList<>();
		Object response = cliente.enviarRequest("get_centros", new ArrayList<>());
		if (response instanceof ArrayList<?> lista) {
			for (Object elemento : lista) {
				if (elemento instanceof Centros centro) {
					centros.add(centro);
				}
			}
		} else if (response instanceof String mensaje) {
			throw new Exception("Error al obtener centros: " + mensaje);

		}
		return centros;
	}

}
