package es.altair.bean;

public class Artista {
	private int idArtista;
	private String nombreArtista;
	private String apellidos;
	private int idEstilo;
	private int edad;
	private int idPais;
	
	public Artista() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Artista(int idArtista, String nombreArtista, String apellidos, int idEstilo, int edad, int idPais) {
		super();
		this.idArtista = idArtista;
		this.nombreArtista = nombreArtista;
		this.apellidos = apellidos;
		this.idEstilo = idEstilo;
		this.edad = edad;
		this.idPais = idPais;
	}
	
	
	public Artista(String nombreArtista, String apellidos, int idEstilo, int edad, int idPais) {
		super();
		this.nombreArtista = nombreArtista;
		this.apellidos = apellidos;
		this.idEstilo = idEstilo;
		this.edad = edad;
		this.idPais = idPais;
	}

	public int getIdArtista() {
		return idArtista;
	}
	public void setIdArtista(int idArtista) {
		this.idArtista = idArtista;
	}
	public String getNombreArtista() {
		return nombreArtista;
	}
	public void setNombreArtista(String nombreArtista) {
		this.nombreArtista = nombreArtista;
	}
	public String getApellidos() {
		return apellidos;
	}
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	public int getIdEstilo() {
		return idEstilo;
	}
	public void setIdEstilo(int idEstilo) {
		this.idEstilo = idEstilo;
	}
	public int getEdad() {
		return edad;
	}
	public void setEdad(int edad) {
		this.edad = edad;
	}
	public int getIdPais() {
		return idPais;
	}
	public void setIdPais(int idPais) {
		this.idPais = idPais;
	}
	
	@Override
	public String toString() {
		return "Artista [idArtista=" + idArtista + ", nombreArtista=" + nombreArtista + ", apellidos=" + apellidos
				+ ", idEstilo=" + idEstilo + ", edad=" + edad + ", idPais=" + idPais + "]";
	}
	
	

	
}
