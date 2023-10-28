<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Nómina de Empleados</title>
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
	<h1>Nómina de Empleados</h1>
	<table border="1">
		<tr>
			<th>DNI</th>
			<th>Nombre</th>
			<th>Sexo</th>
			<th>Categoría</th>
			<th>Años Trabajados</th>
		</tr>
		<c:forEach items="${empleados}" var="empleado">
			<tr>
				<td>${empleado.dni}</td>
				<td>${empleado.nombre}</td>
				<td>${empleado.sexo}</td>
				<td>${empleado.categoria}</td>
				<td>${empleado.anyos}</td>
			</tr>
		</c:forEach>
	</table>

	<button class="back-button" onclick="goBack()">Volver</button>

	<script>
		function goBack() {
			window.history.back();
		}
	</script>

</body>
</html>
