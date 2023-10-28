<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Resultado de Cambios</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            text-align: center;
        }

        h1 {
            background-color: #007BFF;
            color: #fff;
            padding: 20px;
        }

        table {
            width: 80%;
            margin: 20px auto;
            border-collapse: collapse;
        }

        th, td {
            padding: 10px;
            border: 1px solid #ddd;
        }

        th {
            background-color: #007BFF;
            color: #fff;
        }

        tr:nth-child(even) {
            background-color: #f2f2f2;
        }

        a {
            text-decoration: none;
            background-color: #007BFF;
            color: #fff;
            padding: 10px 20px;
            border-radius: 5px;
            display: block;
            margin: 20px auto;
            width: 200px;
        }

        a:hover {
            background-color: #0056b3;
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
    <h1>Resultado de Cambios</h1>
    <p>Los cambios se han guardado correctamente. Aquí están los datos actualizados del empleado:</p>

    <table border="1">
        <tr>
            <th>DNI</th>
            <th>Nombre</th>
            <th>Sexo</th>
            <th>Categoría</th>
            <th>Años Trabajados</th>
        </tr>
       
            <tr>
                <td>${empleadoActualizado.dni}</td>
                <td>${empleadoActualizado.nombre}</td>
                <td>${empleadoActualizado.sexo}</td>
                <td>${empleadoActualizado.categoria}</td>
                <td>${empleadoActualizado.anyos}</td>
            </tr>
      
    </table>
    
    <a href="index.jsp">Volver al Inicio</a>
    
    <button class="back-button" onclick="goBack()">Volver</button>

		<script>
			function goBack() {
				window.history.back();
			}
		</script>
</body>
</html>
