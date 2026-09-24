<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Mis Préstamos</title>
</head>
<body>

    <h2>Mis Préstamos</h2>

    <a href="${pageContext.request.contextPath}/libros">Volver al catálogo</a> |
    <a href="${pageContext.request.contextPath}/logout">Cerrar sesión</a>

    <c:if test="${not empty error}">
        <p><c:out value="${error}" /></p>
    </c:if>

    <table border="1">
        <tr>
            <th>Título</th>
            <th>Fecha de préstamo</th>
            <th>Acción</th>
        </tr>
        <c:forEach var="prestamo" items="${prestamos}">
            <tr>
                <td><c:out value="${prestamo.tituloLibro}" /></td>
                <td><c:out value="${prestamo.fechaPrestamo}" /></td>
                <td>
                    <form action="${pageContext.request.contextPath}/prestamos" method="POST">
                        <input type="hidden" name="accion" value="devolver">
                        <input type="hidden" name="prestamoId" value="${prestamo.id}">
                        <input type="hidden" name="libroId" value="${prestamo.libroId}">
                        <button type="submit">Devolver</button>
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>

</body>
</html>