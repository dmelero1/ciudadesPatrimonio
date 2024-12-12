package Servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import Dao.DaoCiudad;
import Dao.DaoPunto;
import Dao.DaoRuta;
import Entidad.Ciudad;
import Entidad.Punto;
import Entidad.Ruta;

/**
 * Servlet implementation class Controller
 */
@WebServlet("/Controller")
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Controller() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<Ciudad> ciudades = null;
		ArrayList<Ciudad> ciudadesrutas = null;
		
		HttpSession session = request.getSession();
		String operacion = request.getParameter("operacion");
		
		switch (operacion) {
		case "iniciar": 
			// actuar en consecuencia
			try {
				ciudades = new DaoCiudad().getCiudades();
				ciudadesrutas = new DaoCiudad().getCiudadesConRutas();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			request.setAttribute("ciudades", ciudades);
			session.setAttribute("ciudadesrutas", ciudadesrutas);
			request.getRequestDispatcher("ciudad.jsp").forward(request, response);
			break;
		
		case "rutaelegida":
			String idciudad =request.getParameter("idciudad");
			ArrayList<Ruta> listaRutas=null;
			String nombreciudad=null;
			try {
				listaRutas = new DaoRuta().getRutas(Integer.parseInt(idciudad));
				nombreciudad = new DaoCiudad().getCiudad(Integer.parseInt(idciudad)).getNombre();
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			session.setAttribute("listarutas", listaRutas);
			session.setAttribute("idciudad", idciudad);
			session.setAttribute("ciudad", nombreciudad );
			request.getRequestDispatcher("ciudad.jsp").forward(request, response);
		break;
			
		case "ciudades":
			try {
				ciudades = new DaoCiudad().getCiudades();
				ciudadesrutas = new DaoCiudad().getCiudadesConRutas();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			request.setAttribute("ciudades", ciudades);
			session.setAttribute("ciudadesrutas", ciudadesrutas);
			session.removeAttribute("listarutas");
			request.getRequestDispatcher("ciudad.jsp").forward(request, response);
			break;
			
		case "rating":
			String puntos = request.getParameter("rating");
			String idruta = request.getParameter("idruta");
			
			Punto punto = new Punto(100, Integer.parseInt(idruta), Integer.parseInt(puntos));

			try {
				new DaoPunto().insertPunto(punto);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String msg = "Valorado con "+puntos+ " puntos";
			request.setAttribute("msg", msg);
			request.getRequestDispatcher("ciudad.jsp").forward(request, response);
			break;
			
		default:
			throw new IllegalArgumentException("Unexpected value: " + operacion);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
