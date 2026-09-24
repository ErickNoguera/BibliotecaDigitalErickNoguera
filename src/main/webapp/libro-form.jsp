<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Formulario de Libro</title>
</head>
<body>

    <h2>
        <c:choose>
            <c:when test="${not empty libro}">Editar Libro</c:when>
            <c:otherwise>Nuevo Libro</c:otherwise>
        </c:choose>
    </h2>

    <form action="${pageContext.request.contextPath}/libros" method="POST">
        <input type="hidden" name="accion" value="guardar">
        <input type="hidden" name="id" value="${libro.id}">

        <label>Título</label>
        <input type="text" name="titulo" value="${libro.titulo}" required>

        <label>Autor</label>
        <input type="text" name="autor" value="${libro.autor}" required>

        <label>ISBN</label>
        <input type="text" name="isbn" value="${libro.isbn}" required>

        <label>
            <input type="checkbox" name="disponible" ${libro.disponible ? 'checked' : ''}>
            Disponible
        </label>

        <button type="submit">Guardar</button>
    </form>

    <a href="${pageContext.request.contextPath}/libros">Cancelar</a>

</body>
</html>