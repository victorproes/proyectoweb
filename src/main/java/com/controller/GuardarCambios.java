package com.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.NominaDAO;
import com.model.DatosNoCorrectoExcpetion;
import com.model.Empleado;

/**
 * Servlet implementation class GuardarCambios
 */
@WebServlet("/guardarCambios")
public class GuardarCambios extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GuardarCambios() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {
	    try {
	        String dni = request.getParameter("dni");
	        String nombre = request.getParameter("nombre");
	        int categoria = Integer.parseInt(request.getParameter("categoria"));
	        int anyos = Integer.parseInt(request.getParameter("anyos"));
	        String sexo = request.getParameter("sexo"); 

	        // Actualizar los datos del empleado en la base de datos
	        NominaDAO nominaDAO = new NominaDAO();
	        Empleado empleado = new Empleado(dni, nombre, sexo, categoria, anyos); // Incluye el campo de sexo
	        nominaDAO.modificarEmpleado(empleado);

	     // Guardar el objeto empleado en el alcance de solicitud
	        request.setAttribute("empleadoActualizado", empleado);
	        // Redirigir a la página de resultados de búsqueda u otra página
	        request.getRequestDispatcher("views/resultadoCambios.jsp").forward(request, response);
	    } catch (DatosNoCorrectoExcpetion e) {
	        // Manejar la excepción si se lanza
	        e.printStackTrace(); // O muestra un mensaje de error
	        response.sendRedirect("error.jsp"); // Redirige a la página de error
	       request.setAttribute("errorMessage", "Ocurrió un error en la operación.");
	        request.getRequestDispatcher("/views/error.jsp").forward(request, response);
	    }
	}


}
