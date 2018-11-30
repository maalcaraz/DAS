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

<h2><fmt:message key="concesionarias_pendientes" bundle="${etq}"></fmt:message></h2>
	<div>
		<c:if test="${!empty pendientes}">
			<c:forEach var="pendiente" items="${ pendientes }" varStatus="status">
				<div class="grid-item">

					<h3> <fmt:message key="concesionaria" bundle="${etq}"></fmt:message>: ${ pendiente.nomConcesionaria } </h3>
			<br>
				<b><fmt:message key="tipo_de_servicio" bundle="${etq}"></fmt:message>:</b> ${ pendiente.codTecnologia } <br>
				<b><fmt:message key="url" bundle="${etq}"></fmt:message> :</b> ${ pendiente.urlServicio } <br>
				<b><fmt:message key="direccion" bundle="${etq}"></fmt:message>:</b> ${ pendiente.direccion } <br>
				<b><fmt:message key="telefono" bundle="${etq}"></fmt:message>:</b> ${ pendiente.telefono } <br>
				<b><fmt:message key="email" bundle="${etq}"></fmt:message>:</b> ${ pendiente.email } <br>
				
					
				<span> 
				<input type="button" id="aprobar" name="aprobar" value="<fmt:message key="aprobar" bundle="${etq}"></fmt:message>" onclick="jConcesionaria.aprobar(&quot;${ pendiente.idConcesionaria }&quot;) "> 
				<input type="button" id="rechazar" name="rechazar" value="<fmt:message key="rechazar" bundle="${etq}"></fmt:message>" onclick="jConcesionaria.rechazar(&quot;${ pendiente.idConcesionaria }&quot;)">
				<input type="button" class="normal button" value="<fmt:message key="editar" bundle="${etq}"></fmt:message>" onclick="jConcesionaria.editarConcesionaria('${ concesionaria.idConcesionaria }')"> 
			
				<input type="button" value="<fmt:message key="conexion" bundle="${etq}"></fmt:message>" onclick="jConcesionaria.testingSyncro('${ pendiente.idConcesionaria }')">

				
				</span>
			<br>
			<div id="respuesta-${ pendiente.idConcesionaria }"></div>
			</div>
			
			</c:forEach>
		</c:if>
		<c:if test="${empty pendientes}">
			<fmt:message key="no_hay_concesionarias_pendientes" bundle="${etq}"></fmt:message>
		</c:if>
	</div>