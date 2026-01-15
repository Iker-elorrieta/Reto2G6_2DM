package modelo;

public class Tipos implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Integer id;
	private String name;
	private String nameEu;

	public Tipos() {
	}

	public Tipos(String name) {
		this.name = name;
	}

	public Tipos(String name, String nameEu) {
		this.name = name;
		this.nameEu = nameEu;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNameEu() {
		return this.nameEu;
	}

	public void setNameEu(String nameEu) {
		this.nameEu = nameEu;
	}

}
