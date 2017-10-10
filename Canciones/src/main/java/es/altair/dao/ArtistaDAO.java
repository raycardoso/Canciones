package es.altair.dao;

import java.util.List;

import es.altair.bean.*;

public interface ArtistaDAO {
	
	public List<Artista> listarTodos();
	
	public boolean insertarArtista(Artista a);
	
	public boolean actualizarArtista(Artista a);

	public Artista obtener(int idArt);

	public boolean borrarArtista(int artBorrar);
	
	public List<Artista> mostrarArtistasEstilos(int estilo);
	
	public List<Artista> mostrarArtistasCanciones(List<Artista> artistas);

	public List<Cancion> mostrarCancionesPorArtista(int artista);

}
