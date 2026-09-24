<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<%@ taglib uri="jakarta.tags.functions" prefix="fn"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>
	
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<c:if test="${ not empty error }">
		<div class="alert alert-danger" role="alert">
			<c:out value="${ error }" />
		</div>
	</c:if>
	<form action="${pageContext.request.contextPath}/login" method="POST">
		<input type="email" name="correo" required> <input
			type="password" name="password" required>
		<button type="submit">Ingresar</button>
	</form>

</body>
</html>