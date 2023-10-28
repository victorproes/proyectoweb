<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Menú de Nómina</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <!-- Estilos personalizados -->
    <style>
        body {
            background-color: #f4f4f4;
        }

        .menu-container {
            text-align: center;
            padding: 20px;
        }

        h1 {
            background-color: #007BFF;
            color: #fff;
            padding: 20px;
        }

        .menu-list {
            list-style: none;
            padding: 0;
        }

        .menu-list li {
            margin-bottom: 10px;
        }
    </style>
</head>
<body>
    <div class="container">
        <div class="menu-container">
            <h1>Menú de Nómina</h1>
            <ul class="menu-list">
                <li>
                    <a href="empresa" class="btn btn-primary btn-lg">Mostrar Empleados</a>
                </li>
                <li>
                    <a href="buscarSalario" class="btn btn-primary btn-lg">Buscar Salario por DNI</a>
                </li>
                <li>
                    <a href="buscarEmpleados" class="btn btn-primary btn-lg">Buscar Empleados</a>
                </li>
            </ul>
        </div>
    </div>
    
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.3/dist/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
