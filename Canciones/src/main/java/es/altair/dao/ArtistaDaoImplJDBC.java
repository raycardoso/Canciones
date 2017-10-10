package es.altair.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;

import java.sql.Statement;

import es.altair.bean.Artista;
import es.altair.bean.Cancion;

public class ArtistaDaoImplJDBC implements ArtistaDAO {

	public List<Artista> listarTodos() {
		
		List<Artista> artistas = new ArrayList<Artista>();
		
		conexionDAO.abrirConexion();
		
		String query = "SELECT * FROM ARTISTAS";
		try {
			Statement setencia = conexionDAO.getConexion().createStatement();
			
			ResultSet resultado =  setencia.executeQuery(query);
			
			while (resultado.next()) {
				Artista art = new Artista(resultado.getInt("idArtista"), 
						resultado.getString("nombreArtista"), 
						resultado.getString("apellidos"), 
						resultado.getInt("idEstilo"), 
						resultado.getInt("edad"), 
						resultado.getInt("idPais"));
				
				artistas.add(art);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		conexionDAO.cerrarConexion();
		
		return artistas;
	}

	public boolean insertarArtista(Artista a) {
		
		int numFilas = 0;
		
		conexionDAO.abrirConexion();
		
		String query ="INSERT INTO ARTISTAS VALUES(null, ?, ?, ?, ?, ?)";
		
		try {
			PreparedStatement setencia = conexionDAO.getConexion().prepareStatement(query);
			
			setencia.setString(1, a.getNombreArtista());
			setencia.setString(2, a.getApellidos());
			setencia.setInt(3, a.getIdEstilo());
			setencia.setInt(4, a.getEdad());
			setencia.setInt(5, a.getIdPais());
			
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
	

	public boolean actualizarArtista(Artista a) {
		
		int numFilas = 0;
		
		conexionDAO.abrirConexion();
		
		String query ="UPDATE ARTISTAS SET nombreArtista=?, apellidos=?, idEstilo=?, edad=?, idPais=? WHERE idArtista = ?";
		
		try {
			PreparedStatement setencia = conexionDAO.getConexion().prepareStatement(query);
			
			setencia.setString(1, a.getNombreArtista());
			setencia.setString(2, a.getApellidos());
			setencia.setInt(3, a.getIdEstilo());
			setencia.setInt(4, a.getEdad());
			setencia.setInt(5, a.getIdPais());			
			setencia.setInt(6, a.getIdArtista());
			
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

	public Artista obtener(int idArt) {
		
		Artista a = null;
		
		conexionDAO.abrirConexion();
		
		String query = "SELECT * FROM ARTISTAS WHERE IDARTISTA = ?";
		
		try {
			PreparedStatement sentencia = conexionDAO.getConexion().prepareStatement(query);
			sentencia.setInt(1, idArt);
			ResultSet resultado = sentencia.executeQuery();
			while(resultado.next()) {
				a = new Artista(resultado.getInt("idArtista"), resultado.getString("nombreArtista"), resultado.getString("apellidos"),
						resultado.getInt("idEstilo"), resultado.getInt("edad"), resultado.getInt("idPais"));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		

		conexionDAO.cerrarConexion();

		return a;
	}

	public boolean borrarArtista(int artBorrar) {
	int numFilas = 0;
		
		conexionDAO.abrirConexion();
		
		String query ="DELETE FROM CANCIONES WHERE idArtista = ?";
		
		try {
			PreparedStatement setencia = conexionDAO.getConexion().prepareStatement(query);
			
			setencia.setInt(1, artBorrar);

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

	public List<Artista> mostrarArtistasEstilos(int estilo) {
		List<Artista> listArtistas = new ArrayList<Artista>();
		
		conexionDAO.abrirConexion();
		
		try {
			CallableStatement call = conexionDAO.getConexion().prepareCall("{call mostrar_artistas_estilo(?)}");
			call.setInt(1,estilo);
			ResultSet resultado = call.executeQuery();
			
			while(resultado.next()) {
				Artista a = new Artista(resultado.getInt("idArtista"), resultado.getString("nombreArtista"), resultado.getString("apellidos"),
						resultado.getInt("idEstilo"), resultado.getInt("edad"), resultado.getInt("idPais"));
				listArtistas.add(a);
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		conexionDAO.cerrarConexion();
		
		return listArtistas;
	}

	public List<Artista> mostrarArtistasCanciones(List<Artista> artistas) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Cancion> mostrarCancionesPorArtista(int artista) {
		List<Cancion> listCanciones = new ArrayList<Cancion>();
		
		conexionDAO.abrirConexion();
		
		try {
			CallableStatement call = conexionDAO.getConexion().prepareCall("{call mostrar_canciones_artista(?)}");
			call.setInt(1,artista);
			ResultSet resultado = call.executeQuery();
			
			while(resultado.next()) {
				Cancion b = new Cancion(resultado.getInt("idCancion"), resultado.getString("nombreCancion"), 
						resultado.getString("duracion"), resultado.getInt("idArtista"));
				listCanciones.add(b);
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		conexionDAO.cerrarConexion();
		
		return listCanciones;
	}

/*	public List<Artista> mostrarArtistasCanciones(int artistas, int canciones) {
		
		List<Artista> listArtistas1 = new ArrayList<Artista>();
		List<Cancion> listCanciones1 = new ArrayList<Cancion>();
		
		conexionDAO.abrirConexion();
		
		try {
			CallableStatement call = conexionDAO.getConexion().prepareCall("{call mostrar_artistasYcanciones(?,?)}");
			call.setInt(1,artistas);
			call.setInt(2, canciones);
			ResultSet resultado = call.executeQuery();
			
			while(resultado.next()) {
				Artista a = new Artista(resultado.getInt("idArtista"), resultado.getString("nombreArtista"), resultado.getString("apellidos"),
						resultado.getInt("idEstilo"), resultado.getInt("edad"), resultado.getInt("idPais"));
				Cancion b = new Cancion(resultado.getInt("idCancion"), resultado.getString("nombreCancion"), 
						resultado.getString("duracion"), resultado.getInt("idArtista"));
				listArtistas1.add(a);
				listCanciones1.add(b);
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		conexionDAO.cerrarConexion();
		
		return listArtistas1;
	}

	public List<Artista> mostrarArtistasCanciones(List<Artista> artistas) {
		
		List<Artista> listArtistas1 = new ArrayList<Artista>();
		List<Cancion> listCanciones1 = new ArrayList<Cancion>();
		
		conexionDAO.abrirConexion();
		
		try {
			CallableStatement call = conexionDAO.getConexion().prepareCall("{call mostrar_artistasYcanciones(?,?)}");
		//	call.getRef(artistas);
			ResultSet resultado = call.executeQuery();
			
			while(resultado.next()) {
				Artista a = new Artista(resultado.getInt("idArtista"), resultado.getString("nombreArtista"), resultado.getString("apellidos"),
						resultado.getInt("idEstilo"), resultado.getInt("edad"), resultado.getInt("idPais"));
				Cancion b = new Cancion(resultado.getInt("idCancion"), resultado.getString("nombreCancion"), 
						resultado.getString("duracion"), resultado.getInt("idArtista"));
				listArtistas1.add(a);
				listCanciones1.add(b);
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		conexionDAO.cerrarConexion();
		
		return listArtistas1;
	}    */


}
