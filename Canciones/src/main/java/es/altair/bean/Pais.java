package es.altair.bean;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import es.altair.dao.conexionDAO;

public class Pais {

	private int idPais;
	private String pais;
	
	public Pais() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Pais(int idPais, String pais) {
		super();
		this.idPais = idPais;
		this.pais = pais;
	}

	public int getIdPais() {
		return idPais;
	}

	public void setIdPais(int idPais) {
		this.idPais = idPais;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}
	
	
	public List<Pais> listarTodos() {
		List<Pais> paises = new ArrayList<Pais>();
		
		conexionDAO.abrirConexion();
		
		String query = "SELECT * FROM PAISES";
		try {
			Statement setencia = conexionDAO.getConexion().createStatement();
			
			ResultSet resultado =  setencia.executeQuery(query);
			
			while (resultado.next()) {
				Pais pp = new Pais(resultado.getInt("idPais"), 
						resultado.getString("pais")); 
				
				paises.add(pp);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		conexionDAO.cerrarConexion();				
		
		return paises;
	}
	

	@Override
	public String toString() {
		return "Pais [idPais=" + idPais + ", pais=" + pais + "]";
	}
	
	
	
}
