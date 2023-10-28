package com.conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    // Configura la URL de conexión, el usuario y la contraseña de la base de datos
    private static final String URL = "jdbc:mariadb://localhost:3306/nomina";
    private static final String USUARIO = "root";
    private static final String CONTRASEÑA = "123456";

    private Connection conexion;

    public Conexion() {
        try {
            // Cargar el controlador JDBC
            Class.forName("org.mariadb.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException("No se puede cargar el controlador JDBC");
        }
    }

    public void conectar() {
        try {
            // Establecer la conexión a la base de datos
            conexion = DriverManager.getConnection(URL, USUARIO, CONTRASEÑA);
            System.out.println("Conexión exitosa a la base de datos.");
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error al conectar a la base de datos");
        }
    }

    public void desconectar() {
        try {
            if (conexion != null) {
                conexion.close();
                System.out.println("Conexión cerrada correctamente.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error al cerrar la conexión");
        }
    }

    public Connection getConexion() {
        return conexion;
    }
}
