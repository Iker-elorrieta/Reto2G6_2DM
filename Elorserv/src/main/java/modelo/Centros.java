package modelo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Centros implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    public Integer  CCEN;
    public String NOM;
    public String NOME;
    public String DGENRC;
    public String DGENRE;
    public String GENR;
    public Integer  MUNI = 1;
    public String DMUNIC;
    public String DMUNIE;
    public String DTERRC;
    public String DTERRE;
    public Integer  DEPE = 11;
    public String DTITUC;
    public String DTITUE;
    public String DOMI;
    public Integer  CPOS;
    public Long TEL1;
    public Long TFAX;
    public String EMAIL;
    public String PAGINA;
    public Double COOR_X;
    public Double COOR_Y;
    public Double LATITUD;
    public Double LONGITUD;
    
	private static String JSON_PATH = "EuskadiLatLon.json";

    
	
	public Centros() {
		super();
	}
	public Centros(int cCEN) {
		super();
		CCEN = cCEN;
	}

	public Integer getCCEN() {
		return CCEN;
	}

	public void setCCEN(Integer cCEN) {
		CCEN = cCEN;
	}


	/**
	 * Lee y parsea el fichero JSON con los centros (archivo en resources) y devuelve la lista.
	 */
	@JsonIgnore
	public static ArrayList<Centros> getAllCentros(){
		ArrayList<Centros> listaCentros = new ArrayList<>();
		File file = new File("files/"+JSON_PATH);
		Gson gson = new GsonBuilder().create();
		try (Reader reader = new FileReader(file)) {
			listaCentros =new ArrayList<Centros>(Arrays.asList(gson.fromJson(reader, Centros[].class)));
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		return listaCentros;
	}
	/**
	 * Busca en el JSON de centros y devuelve el Centro coincidente con este CCEN.
	 */
	@JsonIgnore
	public Centros getCentroById(){
		if(this.CCEN == null) {
			return null;
		}
		Centros centro = null;
		File file = new File("files/"+JSON_PATH);
		Gson gson = new GsonBuilder().create();
		try (Reader reader = new FileReader(file)) {
			ArrayList<Centros> centros =new ArrayList<Centros>(Arrays.asList(gson.fromJson(reader, Centros[].class)));
			for(Centros c: centros) { 
				if(c.getCCEN().equals(this.CCEN)) {
					centro = c;
					break;
				}
			}
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		return centro;
	}


}
