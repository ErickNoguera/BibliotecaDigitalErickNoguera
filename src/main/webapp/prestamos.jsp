<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Mis Préstamos</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet">
<link href="https://fonts.googleapis.com/css2?family=Lora:wght@600;700&family=Inter:wght@400;500;600&display=swap" rel="stylesheet">
<link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet">
</head>
<body>

    <nav class="navbar navbar-biblioteca navbar-dark mb-4">
        <div class="container">
            <span class="navbar-brand mb-0 h1">Biblioteca Digital UNTEC</span>
            <div>
                <a href="${pageContext.request.contextPath}/libros" class="me-3">Volver al catálogo</a>
                <a href="${pageContext.request.contextPath}/logout">Cerrar sesión</a>
            </div>
        </div>
    </nav>

    <div class="container">

        <h2 class="mb-4">Mis Préstamos</h2>

        <c:if test="${not empty error}">
            <div class="alert alert-danger">
                <c:out value="${error}" />
            </div>
        </c:if>

        <c:if test="${empty prestamos}">
            <p class="text-muted">No tienes préstamos activos.</p>
        </c:if>

        <c:forEach var="prestamo" items="${prestamos}">
            <div class="ficha-libro d-flex justify-content-between align-items-center">
                <div>
                    <h5><c:out value="${prestamo.tituloLibro}" /></h5>
                    <div class="text-muted">
                        Prestado el: <c:out value="${prestamo.fechaPrestamo}" />
                    </div>
                </div>

                <form action="${pageContext.request.contextPath}/prestamos" method="POST">
                    <input type="hidden" name="accion" value="devolver">
                    <input type="hidden" name="prestamoId" value="${prestamo.id}">
                    <input type="hidden" name="libroId" value="${prestamo.libroId}">
                    <button type="submit" class="btn btn-sm btn-primary-biblioteca">Devolver</button>
                </form>
            </div>
        </c:forEach>

    </div>

</body>
</html>