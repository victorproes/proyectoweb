<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Resultados de Búsqueda</title>

<style>
body {
	font-family: Arial, sans-serif;
	background-color: #f5f5f5;
	margin: 0;
	padding: 0;
	text-align: center;
}

h1 {
	color: #333;
}

table {
	margin: 20px auto;
	border-collapse: collapse;
	width: 80%;
	max-width: 800px;
	background-color: #fff;
	box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
}

th, td {
	padding: 12px 15px;
	text-align: left;
	border-bottom: 1px solid #ddd;
}

th {
	background-color: #007BFF;
	color: #fff;
}

tr:nth-child(even) {
	background-color: #f2f2f2;
}

p {
	color: #666;
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

/* Estilo para el botón "Editar" */
.custom-button {
	/* Mantiene los estilos de btn y btn-primary */
	background-color: #007BFF;
	color: #fff;
	border: none;
	padding: 5px 10px;
	text-decoration: none;
}

/* Estilo para el botón "Editar" al pasar el mouse sobre él */
.custom-button:hover {
	background-color: #0056b3;
}
</style>
</head>
<body>
	<h1>Resultados de Búsqueda</h1>

	<c:if test="${not empty empleados}">
		<table class="table">
			<thead>
				<tr>
					<th>DNI</th>
					<th>Nombre</th>
					<th>Sexo</th>
					<th>Categoría</th>
					<th>Años Trabajados</th>
					<th>Acciones</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${empleados}" var="empleado">
					<tr>
						<td>${empleado.dni}</td>
						<td>${empleado.nombre}</td>
						<td>${empleado.sexo}</td>
						<td>${empleado.categoria}</td>
						<td>${empleado.anyos}</td>
						<td><a href="editarEmpleado?dni=${empleado.dni}"
							class="btn btn-primary custom-button">Editar</a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</c:if>

	<button class="back-button" onclick="goBack()">Volver</button>

	<script>
		function goBack() {
			window.history.back();
		}
	</script>
</body>
</html>