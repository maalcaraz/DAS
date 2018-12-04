<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<head>
<script type="text/javascript" src="/util/Javascript.do/load=jquery,jquery.i18n.properties,utils,cuentas,login,concesionarias,sorteos"></script>
<link type="text/css" rel="stylesheet" href="/util/StyleSheet.do/load=home,page,messages,home,admin" />
</head>
<fmt:setLocale value="${ sessionScope.lang }" scope="session"/>
<fmt:setBundle basename="ar.edu.ubp.das.src.portal.properties.messages" var="etq"/>

<c:set var="concesionarias" scope="request"	value="${requestScope.concesionarias}"></c:set>
<c:set var="pendientes" scope="request"	value="${requestScope.pendientes}"></c:set>

<br>
	<div>
	<c:if test="${!empty concesionarias}">
	<h2><fmt:message key="concesionarias_adheridas" bundle="${etq}"></fmt:message></h2> <br><br> 
		<c:forEach var="concesionaria" items="${ concesionarias }" varStatus="status">
		
			<div class="grid-item">
			<h3>${ concesionaria.nomConcesionaria } </h3>
				<b><fmt:message key="tipo_de_servicio" bundle="${etq}"></fmt:message></b>: ${ concesionaria.codTecnologia } <br>
				<b><fmt:message key="url" bundle="${etq}"></fmt:message></b>: ${ concesionaria.urlServicio } <br>
				<b><fmt:message key="direccion" bundle="${etq}"></fmt:message></b>: ${ concesionaria.direccion } <br>
				<b><fmt:message key="telefono" bundle="${etq}"></fmt:message></b>: ${ concesionaria.telefono } <br>
				<b><fmt:message key="email" bundle="${etq}"></fmt:message></b>: ${ concesionaria.email } <br>
				<b><fmt:message key="ultima_actualizacion" bundle="${etq}"></fmt:message></b>: ${ concesionaria.ultimaActualizacion } <br> 
			</div> 
		</c:forEach>
	</c:if>
	</div>

