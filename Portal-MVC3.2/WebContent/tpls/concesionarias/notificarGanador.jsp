<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Notificar ganador</title>
</head>
<body>
<br>
<div>Mensajes: </div>
<c:set var="consumos" scope="request" value="${ requestScope.consumos }"> </c:set>
	<c:forEach var="consumo" items="${ consumos }" varStatus="status">
		<h5>${consumo.getItem('mensaje')}</h5>
	</c:forEach>

</body>
</html>