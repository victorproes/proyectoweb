<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Página de Error</title>
</head>
<body>
    <div class="container">
        <h1 class="mt-5">¡Ha ocurrido un error!</h1>
        <div class="alert alert-danger mt-3">
            <strong>Error:</strong> <%= request.getAttribute("errorMessage") %>
        </div>
        <p><a href="<%= request.getAttribute("backLink") %>">Volver</a></p>
    </div>

</body>
</html>
