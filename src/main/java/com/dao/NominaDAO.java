package com.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.conexion.Conexion;
import com.model.DatosNoCorrectoExcpetion;
import com.model.Empleado;
import com.model.Nomina;

public class NominaDAO {
	
	public static boolean validarDNI(String dni) {
        return dni.matches("^\\d{8}[A-Z]$");
    }

	public List<Empleado> obtenerEmpleadosDesdeBD() throws DatosNoCorrectoExcpetion {
 	    List<Empleado> empleados = new ArrayList<>();

 	    Conexion conexionDB = new Conexion();
 	    Connection conexion = null; // Declarar la conexión fuera del bloque try

 	    try {
 	        conexionDB.conectar();
 	        conexion = conexionDB.getConexion();
 	        String sql = "SELECT * FROM empleados";

 	        try (PreparedStatement stmt = conexion.prepareStatement(sql);
 	             ResultSet rs = stmt.executeQuery()) {
 	            while (rs.next()) {
 	                String nombre = rs.getString("nombre");
 	                String dni = rs.getString("dni");
 	                String sexo = rs.getString("sexo");
 	                int categoria = rs.getInt("categoria");
 	                int anyos = rs.getInt("anyos");

 	                Empleado empleado = new Empleado(dni, nombre, sexo, categoria, anyos);
 	                empleados.add(empleado);
 	            }
 	        }
 	    } catch (SQLException e) {
 	        e.printStackTrace();
 	        throw new DatosNoCorrectoExcpetion("Error al obtener empleados desde la base de datos");
 	    } finally {
 	    	
 	        // Asegúrate de cerrar la conexión en el bloque finally
 	        if (conexion != null) {
 	            try {
 	                conexion.close();
 	            } catch (SQLException e) {
 	                e.printStackTrace();
 	            }
 	        }
 	    }

 	    return empleados;
 	}
	
	public Double obtenerSalarioEmpleado(String dni) throws DatosNoCorrectoExcpetion {
	    Conexion conexionDB = new Conexion();
	    Connection conexion = null;

	    try {
	        conexionDB.conectar();
	        conexion = conexionDB.getConexion();
	        String consulta = "SELECT sueldo FROM nomina WHERE id_empleado = (SELECT dni FROM empleados WHERE dni = ?)";
	        try (PreparedStatement preparedStatement = conexion.prepareStatement(consulta)) {
	            preparedStatement.setString(1, dni);
	            try (ResultSet resultSet = preparedStatement.executeQuery()) {
	                if (resultSet.next()) {
	                    // Si se encontró un salario en la base de datos, regresamos el valor
	                    double salario = resultSet.getDouble("sueldo");
	                    return salario;
	                } else {
	                    // Si no se encontró un salario, regresamos null
	                    return null;
	                }
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        throw new DatosNoCorrectoExcpetion("Error al obtener el salario del empleado desde la base de datos");
	    } finally {
	        if (conexion != null) {
	            try {
	                conexion.close();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
	    }
	}

	
	public List<Empleado> buscarEmpleadosPorDato(String datoBusqueda) throws DatosNoCorrectoExcpetion {
	    List<Empleado> empleados = new ArrayList<>();

	    Conexion conexionDB = new Conexion();
	    Connection conexion = null;

	    try {
	        conexionDB.conectar();
	        conexion = conexionDB.getConexion();

	        String consulta = "SELECT dni, nombre, sexo, categoria, anyos FROM empleados " +
	                          "WHERE dni LIKE ? OR nombre LIKE ?"; // Puedes agregar más campos a la consulta

	        try (PreparedStatement preparedStatement = conexion.prepareStatement(consulta)) {
	            preparedStatement.setString(1, "%" + datoBusqueda + "%");
	            preparedStatement.setString(2, "%" + datoBusqueda + "%");

	            try (ResultSet resultSet = preparedStatement.executeQuery()) {
	                while (resultSet.next()) {
	                    String dni = resultSet.getString("dni");
	                    String nombre = resultSet.getString("nombre");
	                    String sexo = resultSet.getString("sexo");
	                    int categoria = resultSet.getInt("categoria");
	                    int anyos = resultSet.getInt("anyos");

	                    Empleado empleado = new Empleado(dni, nombre, sexo, categoria, anyos);
	                    empleados.add(empleado);
	                }
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        throw new DatosNoCorrectoExcpetion("Error al buscar empleados en la base de datos");
	    } finally {
	        if (conexion != null) {
	            try {
	                conexion.close();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
	    }

	    return empleados;
	}

	public Empleado recuperarDatosEmpleadoPorDNI(String dni) throws DatosNoCorrectoExcpetion {
	    Conexion conexionDB = new Conexion();
	    Connection conexion = null;

	    try {
	        conexionDB.conectar();
	        conexion = conexionDB.getConexion();

	        String consulta = "SELECT dni, nombre, sexo, categoria, anyos FROM empleados WHERE dni = ?";

	        try (PreparedStatement preparedStatement = conexion.prepareStatement(consulta)) {
	            preparedStatement.setString(1, dni);

	            try (ResultSet resultSet = preparedStatement.executeQuery()) {
	                if (resultSet.next()) {
	                    String nombre = resultSet.getString("nombre");
	                    String sexo = resultSet.getString("sexo");
	                    int categoria = resultSet.getInt("categoria");
	                    int anyos = resultSet.getInt("anyos");

	                    return new Empleado(dni, nombre, sexo, categoria, anyos);
	                } else {
	                    throw new DatosNoCorrectoExcpetion("No se encontró el empleado con el DNI proporcionado.");
	                }
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        throw new DatosNoCorrectoExcpetion("Error al recuperar los datos del empleado desde la base de datos");
	    } finally {
	        if (conexion != null) {
	            try {
	                conexion.close();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
	    }
	}
	

	
	public List<Empleado> buscarEmpleadosPorDNI(String dni) throws DatosNoCorrectoExcpetion {
	    List<Empleado> empleados = new ArrayList<>();
	    Connection conexion = null;

	    try {
	        Conexion conexionDB = new Conexion();
	        conexionDB.conectar();
	        conexion = conexionDB.getConexion();

	        String consulta = "SELECT dni, nombre, sexo, categoria, anyos FROM empleados WHERE dni = ?";

	        try (PreparedStatement preparedStatement = conexion.prepareStatement(consulta)) {
	            preparedStatement.setString(1, dni);

	            try (ResultSet resultSet = preparedStatement.executeQuery()) {
	                while (resultSet.next()) {
	                    String nombre = resultSet.getString("nombre");
	                    String sexo = resultSet.getString("sexo");
	                    int categoria = resultSet.getInt("categoria");
	                    int anyos = resultSet.getInt("anyos");

	                    Empleado empleado = new Empleado(dni, nombre, sexo, categoria, anyos);
	                    empleados.add(empleado);
	                }
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        throw new DatosNoCorrectoExcpetion("Error al buscar empleados por DNI");
	    } finally {
	    }

	    return empleados;
	}
	
	public List<Empleado> buscarEmpleadosPorNombre(String nombre) throws DatosNoCorrectoExcpetion {
	    List<Empleado> empleados = new ArrayList<>();
	    Connection conexion = null;

	    try {
	        Conexion conexionDB = new Conexion();
	        conexionDB.conectar();
	        conexion = conexionDB.getConexion();

	        String consulta = "SELECT dni, nombre, sexo, categoria, anyos FROM empleados WHERE nombre = ?";

	        try (PreparedStatement preparedStatement = conexion.prepareStatement(consulta)) {
	            preparedStatement.setString(1, nombre);

	            try (ResultSet resultSet = preparedStatement.executeQuery()) {
	                while (resultSet.next()) {
	                    String dni = resultSet.getString("dni");
	                    String sexo = resultSet.getString("sexo");
	                    int categoria = resultSet.getInt("categoria");
	                    int anyos = resultSet.getInt("anyos");

	                    Empleado empleado = new Empleado(dni, nombre, sexo, categoria, anyos);
	                    empleados.add(empleado);
	                }
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        throw new DatosNoCorrectoExcpetion("Error al buscar empleados por DNI");
	    } finally {
	    }

	    return empleados;
	}

	
	public List<Empleado> buscarEmpleadosPorCategoria(int categoria) throws DatosNoCorrectoExcpetion {
	    List<Empleado> empleados = new ArrayList<>();
	    Connection conexion = null;

	    try {
	    	Conexion conexionDB = new Conexion();
	        conexionDB.conectar();
	        conexion = conexionDB.getConexion();
	        String consulta = "SELECT * FROM empleados WHERE categoria = ?";
	        
	        try (PreparedStatement preparedStatement = conexion.prepareStatement(consulta)) {
	            preparedStatement.setInt(1, categoria);
	            
	            try (ResultSet resultSet = preparedStatement.executeQuery()) {
	                while (resultSet.next()) {
	                    String dni = resultSet.getString("dni");
	                    String nombre = resultSet.getString("nombre");
	                    String sexo = resultSet.getString("sexo");
	                    int anyos = resultSet.getInt("anyos");
	                    
	                    Empleado empleado = new Empleado(dni, nombre, sexo, categoria, anyos);
	                    empleados.add(empleado);
	                }
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        throw new DatosNoCorrectoExcpetion("Error al buscar empleados por categoría");
	    } finally {
	    }

	    return empleados;
	}

	
	public List<Empleado> buscarEmpleadosPorAnyos(int anyos) throws DatosNoCorrectoExcpetion {
	    List<Empleado> empleados = new ArrayList<>();
	    Connection conexion = null;

	    try {
	    	Conexion conexionDB = new Conexion();
	        conexionDB.conectar();
	        conexion = conexionDB.getConexion();
	        String consulta = "SELECT * FROM empleados WHERE anyos = ?";
	        
	        try (PreparedStatement preparedStatement = conexion.prepareStatement(consulta)) {
	            preparedStatement.setInt(1, anyos);
	            
	            try (ResultSet resultSet = preparedStatement.executeQuery()) {
	                while (resultSet.next()) {
	                    String dni = resultSet.getString("dni");
	                    String nombre = resultSet.getString("nombre");
	                    String sexo = resultSet.getString("sexo");
	                    int categoria = resultSet.getInt("categoria");
	                    
	                    Empleado empleado = new Empleado(dni, nombre, sexo, categoria, anyos);
	                    empleados.add(empleado);
	                }
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        throw new DatosNoCorrectoExcpetion("Error al buscar empleados por años");
	    } finally {
	    }

	    return empleados;
	}
	
	public List<Empleado> buscarEmpleadosPorSexo(String sexo) throws DatosNoCorrectoExcpetion {
	    List<Empleado> empleados = new ArrayList<>();
	    Connection conexion = null;

	    try {
	    	Conexion conexionDB = new Conexion();
	        conexionDB.conectar();
	        conexion = conexionDB.getConexion();
	        String consulta = "SELECT dni, nombre, sexo, categoria, anyos FROM empleados WHERE sexo = ?";
	        
	        try (PreparedStatement preparedStatement = conexion.prepareStatement(consulta)) {
	            preparedStatement.setString(1, sexo);

	            try (ResultSet resultSet = preparedStatement.executeQuery()) {
	                while (resultSet.next()) {
	                    String dni = resultSet.getString("dni");
	                    String nombre = resultSet.getString("nombre");
	                    int categoria = resultSet.getInt("categoria");
	                    int anyos = resultSet.getInt("anyos");

	                    Empleado empleado = new Empleado(dni, nombre, sexo, categoria, anyos);
	                    empleados.add(empleado);
	                }
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        throw new DatosNoCorrectoExcpetion("Error al buscar empleados por sexo");
	    } finally {
	    }

	    return empleados;
	}

	

	// Método para actualizar un empleado en la base de datos
	public boolean actualizarEmpleado(Empleado empleado)throws DatosNoCorrectoExcpetion  {
	    // Obtener una conexión a la base de datos (debes implementar esta parte)
	    Connection conexion = null;

	    try {
	        Conexion conexionDB = new Conexion();
	        conexionDB.conectar();
	        conexion = conexionDB.getConexion();            
	        String sql = "UPDATE empleados SET nombre = ?, categoria = ?, anyos = ?, sexo = ? WHERE dni = ?";

	        try (PreparedStatement statement = conexion.prepareStatement(sql)) {
	            statement.setString(1, empleado.getNombre());
	            statement.setInt(2, empleado.getCategoria());
	            statement.setInt(3, empleado.getAnyos());
	            statement.setString(4, empleado.getSexo()); // Agregar el campo sexo
	            statement.setString(5, empleado.getDni());

	            int filasActualizadas = statement.executeUpdate();

	            // Si se actualizó al menos una fila, consideramos que fue exitoso
	            return filasActualizadas > 0;
	        }
	    } catch (SQLException e) {
	        // Manejar excepciones si ocurre un error en la base de datos
	        e.printStackTrace();
	        return false; // Retornar false en caso de error
	    } finally {
	        // Cerrar la conexión (debes implementar esta parte)
	        if (conexion != null) {
	            try {
	                conexion.close();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
	    }
	}
	
	public static void modificarEmpleado(Empleado empleado) throws DatosNoCorrectoExcpetion {
        String URL = "jdbc:mysql://localhost:3306/nomina";
        String USUARIO = "root";
        String CONTRASEÑA = "123456";

        try (Connection conn = DriverManager.getConnection(URL, USUARIO, CONTRASEÑA)) {
            // Inicia una transacción ya que realizarás múltiples actualizaciones
            conn.setAutoCommit(false);

            // Primero, actualiza los datos del empleado en la tabla "empleados"
            String sqlUpdateEmpleado = "UPDATE empleados SET nombre = ?, sexo = ?, categoria = ?, anyos = ? WHERE dni = ?";
            try (PreparedStatement stmtUpdateEmpleado = conn.prepareStatement(sqlUpdateEmpleado)) {
                stmtUpdateEmpleado.setString(1, empleado.getNombre());
                stmtUpdateEmpleado.setString(2, empleado.getSexo());
                stmtUpdateEmpleado.setInt(3, empleado.getCategoria());
                stmtUpdateEmpleado.setInt(4, empleado.getAnyos());
                stmtUpdateEmpleado.setString(5, empleado.getDni());
                stmtUpdateEmpleado.executeUpdate();
            }

            // Luego, actualiza el sueldo en la tabla "nominas" basado en la nueva categoría y años trabajados
            String sqlUpdateSueldo = "UPDATE nomina SET sueldo = ? WHERE id_empleado= (SELECT dni FROM empleados WHERE dni = ?)";
            try (PreparedStatement stmtUpdateSueldo = conn.prepareStatement(sqlUpdateSueldo)) {
                int nuevoSueldo = Nomina.sueldo(empleado); // Utiliza el método sueldo de la clase Nomina
                stmtUpdateSueldo.setInt(1, nuevoSueldo);
                stmtUpdateSueldo.setString(2, empleado.getDni());
                stmtUpdateSueldo.executeUpdate();
            }

            // Realiza el commit para confirmar las actualizaciones
            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DatosNoCorrectoExcpetion("Error al modificar los datos del empleado en la base de datos");
        }
    }
	



}
