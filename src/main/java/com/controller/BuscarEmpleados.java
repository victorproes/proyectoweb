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
 * Servlet implementation class BuscarEmpleados
 */
@WebServlet("/buscarEmpleados")
public class BuscarEmpleados extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BuscarEmpleados() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Redirige a la página con el formulario de búsqueda
        request.getRequestDispatcher("/views/buscarEmpleados.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    // Obtiene los parámetros del formulario de búsqueda
	    String criterio = request.getParameter("criterio");
	    String valor = request.getParameter("valor");
	    String errorMsg = null; // Variable para almacenar mensajes de error
	    String previousPage = "/views/buscarEmpleados.jsp"; // Página anterior

	    try {
	        // Crea una instancia de NominaDAO 
	        NominaDAO nominaDAO = new NominaDAO();

	        // Realiza validaciones específicas
	        if ("anyos".equals(criterio)) {
	            // Verifica que "valor" sea un número entero no negativo
	            int anyos = Integer.parseInt(valor);
	            if (anyos < 0) {
	                errorMsg = "Años no puede ser negativo";
	            }
	        } else if ("dni".equals(criterio)) {
	            // Verifica que "valor" sea un DNI válido
	            if (!valor.matches("[0-9]{8}[A-Z]")) {
	                errorMsg = "DNI inválido, El DNI debe ser 8 digitos y una letra en mayuscula";
	            }
	        } else if ("sexo".equals(criterio)) {
	            // Verifica que "valor" sea "M", "O" o "F"
	            if (!valor.equals("M") && !valor.equals("F") && !valor.equals("O")) {
	                errorMsg = "Sexo debe ser M, F o O";
	            }
	        }

	        // Si se encontró un error, almacena el mensaje y la página anterior
	        if (errorMsg != null) {
	            request.setAttribute("errorMsg", errorMsg);
	            request.getRequestDispatcher(previousPage).forward(request, response);
	            return; // Termina la ejecución del método
	        }
	      
	        // Realiza la búsqueda en la base de datos según el criterio y valor
	        List<Empleado> empleados = null;
	        if ("dni".equals(criterio)) {
	            // Buscar por DNI
	            empleados = nominaDAO.buscarEmpleadosPorDNI(valor);
	        } else if ("nombre".equals(criterio)) {
	            // Buscar por Nombre
	            empleados = nominaDAO.buscarEmpleadosPorNombre(valor);
	        } else if ("categoria".equals(criterio)) {
	            // Buscar por Categoría
	            empleados = nominaDAO.buscarEmpleadosPorCategoria(Integer.parseInt(valor));
	        } else if ("anyos".equals(criterio)) {
	            // Buscar por Años
	            empleados = nominaDAO.buscarEmpleadosPorAnyos(Integer.parseInt(valor));
	        } else if ("sexo".equals(criterio)) {
	            // Buscar por Sexo
	            empleados = nominaDAO.buscarEmpleadosPorSexo(valor);
	        }

	        // Establece los resultados de la búsqueda como un atributo para la vista
	        request.setAttribute("empleados", empleados);

	        // Redirige a la página de resultados
	        request.getRequestDispatcher("/views/resultadoBusqueda.jsp").forward(request, response);
	    } catch (DatosNoCorrectoExcpetion e) {
	        e.printStackTrace(); // Maneja la excepción 
	    }
	}

}
