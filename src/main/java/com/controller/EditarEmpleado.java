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
import com.model.Nomina;

@WebServlet("/editarEmpleado")
public class EditarEmpleado extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public EditarEmpleado() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            // Recuperar el DNI del empleado a editar desde los parámetros
            String dni = request.getParameter("dni");

            // Obtener el empleado de la base de datos según el DNI
            NominaDAO nominaDAO = new NominaDAO();
            Empleado empleado = (Empleado) request.getAttribute("empleadoActualizado");

            if (empleado == null) {
                // Si no hay datos actualizados, recupera el empleado por DNI como lo hacías antes
                empleado = nominaDAO.recuperarDatosEmpleadoPorDNI(dni);
            }

            if (empleado != null) {
                // Calcular el sueldo actual del empleado
                int sueldo = Nomina.sueldo(empleado);

                // Establecer el objeto empleado y sueldo en el alcance de solicitud
                request.setAttribute("empleado", empleado);
                request.setAttribute("sueldo", sueldo);

                // Redirigir a la página de edición
                request.getRequestDispatcher("/views/editarEmpleado.jsp").forward(request, response);
            } else {
                // Manejar el caso en el que no se encontró el empleado
                response.sendRedirect("/views/error.jsp");
            }
        } catch (DatosNoCorrectoExcpetion e) {
            // Manejar la excepción si se lanza
            e.printStackTrace(); // O muestra un mensaje de error
	        request.getRequestDispatcher("/views/error.jsp").forward(request, response);
        }
    }

}

 