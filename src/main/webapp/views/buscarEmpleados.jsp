<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Buscar Empleados</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <style>
        /* Estilo para el botón Volver */
        .back-button {
            background-color: #007BFF;
            color: #fff;
            border: none;
            padding: 10px 20px;
            cursor: pointer;
            margin-top: 20px;
        }

        /* Estilo para el botón Volver al pasar el mouse sobre él */
        .back-button:hover {
            background-color: #0056b3;
        }
    </style>

    <script>
        // Función para ocultar el mensaje de error después de 3 segundos
        function hideErrorMessage() {
            var errorMessage = document.getElementById("error-message");
            if (errorMessage) {
                setTimeout(function () {
                    errorMessage.style.display = "none";
                }, 3000);
            }
        }

        // Llama a la función al cargar la página
        window.onload = hideErrorMessage;
    </script>
</head>
<body>
    <div class="container">
        <h1 class="mt-5">Buscar Empleados</h1>

        <% String errorMsg = (String) request.getAttribute("errorMsg"); %>
        <% if (errorMsg != null) { %>
            <div class="alert alert-danger mt-3" id="error-message">
                <strong>Error:</strong> <%= errorMsg %>
            </div>
        <% } %>
        
        <form class="mt-4" action="buscarEmpleados" method="post">
            <div class="form-group">
                <label for="criterio">Criterio de Búsqueda:</label>
                <select class="form-control" name="criterio" id="criterio">
                    <option value="dni">DNI</option>
                    <option value="nombre">Nombre</option>
                    <option value="categoria">Categoría</option>
                    <option value="anyos">Años</option>
                    <option value="sexo">Sexo</option>
                </select>
            </div>
            <div class="form-group">
                <label for="valor">Valor de Búsqueda:</label>
                <input type="text" class="form-control" name="valor" id="valor">
            </div>
            <button type="submit" class="btn btn-primary">Buscar Empleado</button>
        </form>
        <button class="back-button" onclick="goBack()">Volver</button>

        <script>
            function goBack() {
                window.history.back();
            }
        </script>
    </div>

    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.3/dist/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>


