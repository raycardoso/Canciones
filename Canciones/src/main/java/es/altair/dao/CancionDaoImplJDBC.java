package es.altair.dao;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import es.altair.bean.Artista;
import es.altair.bean.Cancion;
import es.altair.bean.Pais;

public class CancionDaoImplJDBC implements CancionDAO {

	public List<Cancion> listarTodas() {
		List<Cancion> canciones = new ArrayList<Cancion>();
		
		conexionDAO.abrirConexion();
		
		String query = "SELECT * FROM CANCIONES";
		try {
			Statement setencia = conexionDAO.getConexion().createStatement();
			
			ResultSet resultado =  setencia.executeQuery(query);
			
			while (resultado.next()) {
				Cancion can = new Cancion(resultado.getInt("idCancion"), 
						resultado.getString("nombreCancion"), 
						resultado.getString("duracion"), 
						resultado.getInt("idArtista")); 
				
				canciones.add(can);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		conexionDAO.cerrarConexion();				
		
		return canciones;
	}

	public boolean insertarCancion(Cancion a) {
			
			int numFilas = 0;
			
			conexionDAO.abrirConexion();
			
			String query ="INSERT INTO CANCIONES VALUES(null, ?, ?, ?)";
			
			try {
				PreparedStatement setencia = conexionDAO.getConexion().prepareStatement(query);
				
				setencia.setString(1, a.getNombreCancion());
				setencia.setString(2, a.getDuracion());
				setencia.setInt(3, a.getIdArtista());
				
				numFilas = setencia.executeUpdate();

				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			conexionDAO.cerrarConexion();
					
			if(numFilas == 0)
				return false;
			else
				return true;

	}

	public boolean actualizarDuracionCanciones(Cancion a, int pais1) {
		int numFilas = 0;
		
		conexionDAO.abrirConexion();

		String query ="UPDATE CANCIONES left join artistas on (canciones.idArtista = artistas.idArtista) SET duracion=?"
				+ " where idPais=? and idCancion=?";
		
		try {
			PreparedStatement setencia = conexionDAO.getConexion().prepareStatement(query);
			
			setencia.setString(1, a.getDuracion());
			setencia.setInt(2, pais1);
			setencia.setInt(3, a.getIdCancion()); 

			numFilas = setencia.executeUpdate();			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		conexionDAO.cerrarConexion();
		
		if(numFilas == 0)
			return false;
		else
			return true;
	}


	public Map<Integer, Cancion> ListaActualizar(int pais) {
		
		Map <Integer, Cancion> cancionesFin = new TreeMap<Integer, Cancion>();;
		conexionDAO.abrirConexion();
		
		try {
			CallableStatement call = conexionDAO.getConexion().prepareCall("{call mostrar_canciones_Pais(?)}");
			call.setInt(1,pais);
			ResultSet resultado = call.executeQuery();
			
			while(resultado.next()) {
				cancionesFin.put(resultado.getInt("idCancion"), new Cancion(resultado.getInt("idCancion"), resultado.getString("nombreCancion"), resultado.getString("duracion"), resultado.getInt("idArtista")));

			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		conexionDAO.cerrarConexion();
		
		return cancionesFin;
	}

	


}
