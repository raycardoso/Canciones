package es.altair.bean;

public class Cancion {
	
	private int idCancion;
	private String nombreCancion;
	private String duracion;
	private int idArtista;
	
	public Cancion() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Cancion(int idCancion, String nombreCancion, String duracion, int idArtista) {
		super();
		this.idCancion = idCancion;
		this.nombreCancion = nombreCancion;
		this.duracion = duracion;
		this.idArtista = idArtista;
	}
	
	

	public Cancion(String nombreCancion, String duracion, int idArtista) {
		super();
		this.nombreCancion = nombreCancion;
		this.duracion = duracion;
		this.idArtista = idArtista;
	}

	public int getIdCancion() {
		return idCancion;
	}

	public void setIdCancion(int idCancion) {
		this.idCancion = idCancion;
	}

	public String getNombreCancion() {
		return nombreCancion;
	}

	public void setNombreCancion(String nombreCancion) {
		this.nombreCancion = nombreCancion;
	}

	public String getDuracion() {
		return duracion;
	}

	public void setDuracion(String duracion) {
		this.duracion = duracion;
	}

	public int getIdArtista() {
		return idArtista;
	}

	public void setIdArtista(int idArtista) {
		this.idArtista = idArtista;
	}

	
	@Override
	public String toString() {
		return "Cancion [idCancion=" + idCancion + ", nombreCancion=" + nombreCancion + ", duracion=" + duracion
				+ ", idArtista=" + idArtista + "]";
	}
	

}
