<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Catálogo de Libros</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet">
<link href="https://fonts.googleapis.com/css2?family=Lora:wght@600;700&family=Inter:wght@400;500;600&display=swap" rel="stylesheet">
<link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet">
</head>
<body>

	<nav class="navbar navbar-biblioteca navbar-dark mb-4">
		<div class="container">
			<span class="navbar-brand mb-0 h1">Biblioteca Digital UNTEC</span>
			<div>
				<a href="${pageContext.request.contextPath}/prestamos" class="me-3">Mis Préstamos</a>
				<a href="${pageContext.request.contextPath}/logout">Cerrar sesión</a>
			</div>
		</div>
	</nav>

	<div class="container">

		<div class="d-flex justify-content-between align-items-center mb-4">
			<h2 class="mb-0">Catálogo de Libros</h2>
			<a href="${pageContext.request.contextPath}/libros?accion=nuevo" class="btn btn-primary-biblioteca">Agregar Libro</a>
		</div>

		<c:if test="${not empty error}">
			<div class="alert alert-danger">
				<c:out value="${error}" />
			</div>
		</c:if>

		<c:if test="${not empty param.msg}">
			<div class="alert alert-success">
				<c:choose>
					<c:when test="${param.msg == 'creado'}">Libro creado correctamente.</c:when>
					<c:when test="${param.msg == 'actualizado'}">Libro actualizado correctamente.</c:when>
					<c:when test="${param.msg == 'eliminado'}">Libro eliminado correctamente.</c:when>
					<c:when test="${param.msg == 'prestado'}">Préstamo registrado correctamente.</c:when>
					<c:when test="${param.msg == 'devuelto'}">Devolución registrada correctamente.</c:when>
				</c:choose>
			</div>
		</c:if>

		<c:forEach var="libro" items="${libros}">
			<div class="ficha-libro d-flex justify-content-between align-items-center">
				<div>
					<h5><c:out value="${libro.titulo}" /></h5>
					<div class="text-muted mb-2">
						<c:out value="${libro.autor}" /> · ISBN: <c:out value="${libro.isbn}" />
					</div>
					<c:choose>
						<c:when test="${libro.disponible}">
							<span class="sello-disponible">Disponible</span>
						</c:when>
						<c:otherwise>
							<span class="sello-prestado">Prestado</span>
						</c:otherwise>
					</c:choose>
				</div>

				<div>
					<c:if test="${libro.disponible}">
						<form action="${pageContext.request.contextPath}/prestamos" method="POST" style="display:inline;">
							<input type="hidden" name="accion" value="prestar">
							<input type="hidden" name="libroId" value="${libro.id}">
							<button type="submit" class="btn btn-sm btn-primary-biblioteca">Prestar</button>
						</form>
					</c:if>

					<a href="${pageContext.request.contextPath}/libros?accion=editar&id=${libro.id}" class="btn btn-sm btn-outline-secondary">Editar</a>

					<form action="${pageContext.request.contextPath}/libros" method="POST" style="display:inline;"
						onsubmit="return confirm('¿Seguro que quieres eliminar este libro?');">
						<input type="hidden" name="accion" value="eliminar">
						<input type="hidden" name="id" value="${libro.id}">
						<button type="submit" class="btn btn-sm btn-outline-danger">Eliminar</button>
					</form>
				</div>
			</div>
		</c:forEach>

	</div>

</body>
</html>