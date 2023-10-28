<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Salario del Empleado</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<style>
.result-container {
	margin: 20px auto;
	padding: 20px;
	border: 1px solid #ddd;
	box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
}

.error {
	color: #ff0000;
	font-size: 20px;
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
	<div class="container result-container">
		<h1 class="mt-5">Salario del Empleado</h1>

		<c:if test="${not empty salario}">
			<p>El salario del empleado es: ${salario}</p>
		</c:if>

		<c:if test="${not empty error}">
			<div class="error">${error}</div>
		</c:if>

		<button class="back-button" onclick="goBack()">Volver</button>

		<script>
			function goBack() {
				window.history.back();
			}
		</script>

	</div>

	<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.3/dist/umd/popper.min.js"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>

