package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import Conexion.Conexion;
import Entidad.Punto;

public class DaoPunto {
	
	public void insertPunto(Punto punto) throws SQLException{
		Connection con  = new Conexion().getConexion();
		String ordenSQL;
		ordenSQL = "INSERT INTO PUNTO VALUES(?,?,?)";
		PreparedStatement st = con.prepareStatement(ordenSQL);
		st.setInt(1, punto.getId());
		st.setInt(2, punto.getRuta());
		st.setInt(3, punto.getPuntos());
		st.executeUpdate();
		st.close();
	}

}