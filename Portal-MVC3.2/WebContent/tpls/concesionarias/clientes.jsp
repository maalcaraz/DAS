<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


	<c:set var="concesionarias" scope="request" value="${ requestScope.concesionarias}"> </c:set>
	
	<c:set var="consumo" scope="request" value="${ requestScope.consumo}"> </c:set>
	<c:set var="clientes" scope="request" value="${ requestScope.clientes }"> </c:set>
	<c:set var="transaccion" scope="request" value="${ requestScope.transaccion }"> </c:set>
	<c:set var="planes" scope="request" value="${ requestScope.planes }"> </c:set>
	<c:set var="adquiridos" scope="request" value="${ requestScope.adquiridos }"> </c:set>
	<c:set var="cuotas" scope="request" value="${ requestScope.cuotas }"> </c:set>

<h1> Viendo las concesionarias</h1>

	<c:forEach var="concesionaria" items="${ concesionarias }" varStatus="status">
		${ concesionaria.nomConcesionaria }
	</c:forEach>

