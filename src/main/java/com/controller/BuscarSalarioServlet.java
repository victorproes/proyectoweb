	package com.controller;
	
	
	import java.io.IOException;
	import javax.servlet.ServletException;
	import javax.servlet.annotation.WebServlet;
	import javax.servlet.http.HttpServlet;
	import javax.servlet.http.HttpServletRequest;
	import javax.servlet.http.HttpServletResponse;
	
	import com.dao.NominaDAO;
	import com.model.DatosNoCorrectoExcpetion;
	
	@WebServlet("/buscarSalario")
	public class BuscarSalarioServlet extends HttpServlet {
	    private static final long serialVersionUID = 1L;
	
	    public BuscarSalarioServlet() {
	        super();
	    }
	
	    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	        // Muestra la página de búsqueda de salario
	        request.getRequestDispatcher("/views/buscarSalario.jsp").forward(request, response);
	    }
	
	    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	        String dni = request.getParameter("dni");

	        try {
	            NominaDAO nominaDAO = new NominaDAO();
	            Double salario = nominaDAO.obtenerSalarioEmpleado(dni);

	            if (salario != null) {
	                request.setAttribute("salario", salario);
	                request.getRequestDispatcher("/views/mostrarSalario.jsp").forward(request, response);
	            } else {
	                // Indica que el DNI no tiene salario registrado
	                request.setAttribute("mensaje", "El DNI proporcionado no tiene salario registrado o no se encuentra en la base de datos.");
	                request.getRequestDispatcher("/views/buscarSalario.jsp").forward(request, response);
	            }
	        } catch (DatosNoCorrectoExcpetion e) {
	            // Maneja la excepción 
	            e.printStackTrace();
	        }
	    }


	
	}
