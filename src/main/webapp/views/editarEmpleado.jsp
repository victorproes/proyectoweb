<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Editar Empleado</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <style>
    /* Estilo para el título h1 */
h1 {
    color: #333;
    text-align: center;
}

/* Estilo para el formulario */
.form-group {
    margin-bottom: 20px;
}

/* Estilo para el botón */
.btn-primary {
    background-color: #007BFF;
    border-color: #007BFF;
}

.btn-primary:hover {
    background-color: #0056b3;
    border-color: #0056b3;
    color: #fff;
}

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
<body>
    <div class="container">
        <h1 class="mt-5">Editar Empleado</h1>
        <form action="guardarCambios" method="post">
            <div class="form-group">
                <label for="dni">DNI:</label>
                <input type="text" name="dni" value="${empleado.dni}" class="form-control" readonly>
            </div>
            <div class="form-group">
                <label for="nombre">Nombre:</label>
                <input type="text" name="nombre" value="${empleado.nombre}" class="form-control">
            </div>
            <div class="form-group">
                <label for="sexo">Sexo:</label>
                <input type="text" name="sexo" value="${empleado.sexo}" class="form-control">
            </div>
            <div class="form-group">
                <label for="categoria">Categoría:</label>
                <input type="number" name="categoria" value="${empleado.categoria}" class="form-control">
            </div>
            <div class="form-group">
                <label for="anyos">Años Trabajados:</label>
                <input type="number" name="anyos" value="${empleado.anyos}" class="form-control">
            </div>
            <div class="form-group">
                <button type="submit" class="btn btn-primary">Guardar Cambios</button>
            </div>
        </form>
               <button class="back-button" onclick="goBack()">Volver</button>

	<script>
		function goBack() {
			window.history.back();
		}
	</script>
    </div>
</body>
</html>

