package es.altair.bean;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import es.altair.dao.conexionDAO;

public class Estilo {
	
	private int idEstilo;
	private String estilo;
	
	public Estilo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Estilo(int idEstilo, String estilo) {
		super();
		this.idEstilo = idEstilo;
		this.estilo = estilo;
	}
	
	

	public Estilo(String estilo) {
		super();
		this.estilo = estilo;
	}

	public int getIdEstilo() {
		return idEstilo;
	}

	public void setIdEstilo(int idEstilo) {
		this.idEstilo = idEstilo;
	}

	public String getEstilo() {
		return estilo;
	}

	public void setEstilo(String estilo) {
		this.estilo = estilo;
	}
	
	
	public List<Estilo> listarTodos() {
		List<Estilo> estilos = new ArrayList<Estilo>();
		
		conexionDAO.abrirConexion();
		
		String query = "SELECT * FROM ESTILOS";
		try {
			Statement setencia = conexionDAO.getConexion().createStatement();
			
			ResultSet resultado =  setencia.executeQuery(query);
			
			while (resultado.next()) {
				Estilo est = new Estilo(resultado.getInt("idEstilo"), 
						resultado.getString("estilo")); 
				
				estilos.add(est);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		conexionDAO.cerrarConexion();				
		
		return estilos;
	}
	
	
	public boolean insertarEstilo(Estilo a) {
		
		int numFilas = 0;
		
		conexionDAO.abrirConexion();
		
		String query ="INSERT INTO ESTILOS VALUES(null, ?)";
		
		try {
			PreparedStatement setencia = conexionDAO.getConexion().prepareStatement(query);
			
			setencia.setString(1, a.getEstilo());
			
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

	

	@Override
	public String toString() {
		return "Estilo [idEstilo=" + idEstilo + ", estilo=" + estilo + "]";
	}
	
	

}
