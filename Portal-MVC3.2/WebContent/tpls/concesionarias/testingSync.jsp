<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="consumos" scope="request" value="${ requestScope.consumos }"> </c:set>
	<c:forEach var="consumo" items="${ consumos }" varStatus="status">
		<h5>${consumo.getItem('mensaje')}</h5>
	</c:forEach>