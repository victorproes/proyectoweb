package com.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.NominaDAO;
import com.model.DatosNoCorrectoExcpetion;
import com.model.Empleado;

/**
 * Servlet implementation class NominaController
 */
@WebServlet("/empresa")
public class NominaController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NominaController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            NominaDAO nominaDAO = new NominaDAO();
            List<Empleado> empleados = nominaDAO.obtenerEmpleadosDesdeBD();

            // Guarda la lista de empleados en el atributo de la solicitud
            request.setAttribute("empleados", empleados);

            // Redirige a la página JSP para mostrar la información
            request.getRequestDispatcher("/views/nomina.jsp").forward(request, response);
        } catch (DatosNoCorrectoExcpetion e) {
            e.printStackTrace(); // Maneja la excepción 
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
