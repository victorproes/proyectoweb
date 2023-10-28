<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Buscar Salario de Empleado</title>
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
</head>
<div class="container">
    <h1 class="mt-5">Buscar Salario de Empleado</h1>

    <% String mensaje = (String) request.getAttribute("mensaje"); %>
    <div id="mensaje-error" class="alert alert-danger mt-3" style="<% if (mensaje == null) { %>display: none;<% } %>">
        <strong>Error:</strong> <%= mensaje %>
    </div>
    
    <!-- Formulario para buscar salario -->
    <form class="mt-4" action="buscarSalario" method="post">
        <div class="form-group">
            <label for="dni">DNI del Empleado:</label>
            <input type="text" class="form-control" name="dni" id="dni">
        </div>
        <button type="submit" class="btn btn-primary">Buscar Salario</button>
    </form>
    
    <button class="back-button" onclick="goBack()">Volver</button>
</div>

<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.3/dist/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

<script>
    function goBack() {
        window.history.back();
    }

    // Ocultar el mensaje de error después de 3 segundos
    var mensajeError = document.getElementById("mensaje-error");
    if (mensajeError.style.display !== "none") {
        setTimeout(function() {
            mensajeError.style.display = "none";
        }, 3000); // 3000 milisegundos (3 segundos)
    }
</script>
</body>

</html>

