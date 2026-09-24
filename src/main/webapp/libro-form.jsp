<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Formulario de Libro</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet">
<link href="https://fonts.googleapis.com/css2?family=Lora:wght@600;700&family=Inter:wght@400;500;600&display=swap" rel="stylesheet">
<link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet">
</head>
<body>

    <div class="container mt-5">
        <div class="row justify-content-center">
            <div class="col-md-6">
                <div class="tarjeta-login p-4">
                    <h3 class="mb-4">
                        <c:choose>
                            <c:when test="${not empty libro}">Editar Libro</c:when>
                            <c:otherwise>Nuevo Libro</c:otherwise>
                        </c:choose>
                    </h3>

                    <form action="${pageContext.request.contextPath}/libros" method="POST">
                        <input type="hidden" name="accion" value="guardar">
                        <input type="hidden" name="id" value="${libro.id}">

                        <div class="mb-3">
                            <label class="form-label">Título</label>
                            <input type="text" class="form-control" name="titulo" value="${libro.titulo}" required>
                        </div>

                        <div class="mb-3">
                            <label class="form-label">Autor</label>
                            <input type="text" class="form-control" name="autor" value="${libro.autor}" required>
                        </div>

                        <div class="mb-3">
                            <label class="form-label">ISBN</label>
                            <input type="text" class="form-control" name="isbn" value="${libro.isbn}" required>
                        </div>

                        <div class="mb-3 form-check">
                            <input type="checkbox" class="form-check-input" name="disponible" ${libro.disponible ? 'checked' : ''}>
                            <label class="form-check-label">Disponible</label>
                        </div>

                        <button type="submit" class="btn btn-primary-biblioteca w-100">Guardar</button>
                    </form>

                    <a href="${pageContext.request.contextPath}/libros" class="d-block text-center mt-3">Cancelar</a>
                </div>
            </div>
        </div>
    </div>

</body>
</html>