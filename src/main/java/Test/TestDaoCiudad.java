package Test;

import java.sql.SQLException;
import java.util.ArrayList;

import Dao.DaoCiudad;
import Entidad.Ciudad;

public class TestDaoCiudad {
	public static void main(String[] args) {
		testListCiudades();
	}
	
	public static void testListCiudades() {
		DaoCiudad dao = new DaoCiudad();
		try {
			ArrayList<Ciudad>ciudades=dao.getCiudades();
			for(Ciudad c: ciudades) {
				System.out.println(c.toString());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	public static void testListCiudadRuta() {
		DaoCiudad dao = new DaoCiudad();
		try {
			ArrayList<Ciudad>ciudadesruta=dao.getCiudadesConRutas();
			for(Ciudad c: ciudadesruta) {
				System.out.println(c.toString());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
