<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Catálogo de Libros</title>
</head>
<body>

	<h2>Catálogo de Libros</h2>

	<a href="${pageContext.request.contextPath}/prestamos">Mis Préstamos</a> |
	<a href="${pageContext.request.contextPath}/libros?accion=nuevo">Agregar Libro</a> |
	<a href="${pageContext.request.contextPath}/logout">Cerrar sesión</a>

	<c:if test="${not empty error}">
		<p>
			<c:out value="${error}" />
		</p>
	</c:if>

	<table border="1">
		<tr>
			<th>ID</th>
			<th>Título</th>
			<th>Autor</th>
			<th>ISBN</th>
			<th>Disponible</th>
			<th>Acción</th>
		</tr>
		<c:forEach var="libro" items="${libros}">
			<tr>
				<td><c:out value="${libro.id}" /></td>
				<td><c:out value="${libro.titulo}" /></td>
				<td><c:out value="${libro.autor}" /></td>
				<td><c:out value="${libro.isbn}" /></td>
				<td><c:out value="${libro.disponible}" /></td>

				<td>
					<c:if test="${libro.disponible}">
						<form action="${pageContext.request.contextPath}/prestamos"
							method="POST" style="display:inline;">
							<input type="hidden" name="accion" value="prestar">
							<input type="hidden" name="libroId" value="${libro.id}">
							<button type="submit">Prestar</button>
						</form>
					</c:if>

					<a href="${pageContext.request.contextPath}/libros?accion=editar&id=${libro.id}">Editar</a>

					<form action="${pageContext.request.contextPath}/libros"
						method="POST" style="display:inline;"
						onsubmit="return confirm('¿Seguro que quieres eliminar este libro?');">
						<input type="hidden" name="accion" value="eliminar">
						<input type="hidden" name="id" value="${libro.id}">
						<button type="submit">Eliminar</button>
					</form>
				</td>
			</tr>
		</c:forEach>
	</table>

</body>
</html>