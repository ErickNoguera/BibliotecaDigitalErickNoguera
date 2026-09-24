<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Biblioteca Digital UNTEC</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet">
<link href="https://fonts.googleapis.com/css2?family=Lora:wght@600;700&family=Inter:wght@400;500;600&display=swap" rel="stylesheet">
<link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet">
</head>
<body>

    <div class="container mt-5">
        <div class="row justify-content-center">
            <div class="col-md-5">
                <div class="tarjeta-login p-4">
                    <h3 class="text-center mb-4">Biblioteca Digital UNTEC</h3>

                    <c:if test="${not empty error}">
                        <div class="alert alert-danger">
                            <c:out value="${error}" />
                        </div>
                    </c:if>

                    <form action="${pageContext.request.contextPath}/login" method="POST">
                        <div class="mb-3">
                            <label class="form-label">Correo</label>
                            <input type="email" class="form-control" name="correo" required>
                        </div>
                        <div class="mb-3">
                            <label class="form-label">Contraseña</label>
                            <input type="password" class="form-control" name="password" required>
                        </div>
                        <button type="submit" class="btn btn-primary-biblioteca w-100">Ingresar</button>
                    </form>
                </div>
            </div>
        </div>
    </div>

</body>
</html>