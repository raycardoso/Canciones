package es.altair.dao;

import java.util.List;
import java.util.Map;

import es.altair.bean.Cancion;

public interface CancionDAO {

	public List<Cancion> listarTodas();

	public boolean insertarCancion(Cancion a);


	public boolean actualizarDuracionCanciones(Cancion a, int pais1);

	
	public Map <Integer, Cancion> ListaActualizar(int pais); 

	
	
}
