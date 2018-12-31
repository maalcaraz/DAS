<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<head>
<script type="text/javascript" src="/util/Javascript.do/load=jquery,jquery.i18n.properties,utils,cuentas,login,concesionarias,sorteos"></script>
<link type="text/css" rel="stylesheet" href="/util/StyleSheet.do/load=admin" />
</head>

<fmt:setLocale value="${ sessionScope.lang }" scope="session"/>
<fmt:setBundle basename="ar.edu.ubp.das.src.portal.properties.messages" var="etq"/>

<c:set var="concesionarias" scope="request"	value="${requestScope.concesionarias}"></c:set>
<c:set var="pendientes" scope="request"	value="${requestScope.pendientes}"></c:set>


	<h2><fmt:message key="concesionarias_registradas" bundle="${etq}"></fmt:message></h2> <br><br> 
	<div>
	<c:if test="${!empty concesionarias}">
		<c:forEach var="concesionaria" items="${ concesionarias }" varStatus="status">
			<c:if test="${ concesionaria.aprobada eq 'S'}">
				<c:set var="estado" scope="request" value="Aprobada"> </c:set>
			</c:if>
			<c:if test="${ concesionaria.aprobada eq 'N'}">
				<c:set var="estado" scope="request" value="Rechazada"> </c:set>
			</c:if>
			
			
			<div class="grid-item">
			<h3>${ concesionaria.nomConcesionaria } </h3>
			<form id="configurarForm-${ concesionaria.idConcesionaria }">
			<input type="hidden" name="idConcesionaria" value="${ concesionaria.idConcesionaria }">
			<input type="hidden" name="ultimaActualizacion" value="${ concesionaria.ultimaActualizacion }">
			<!-- <input type="hidden" name="url" value="${ concesionaria.urlServicio }"> -->
				<b><fmt:message key="estado" bundle="${etq}"></fmt:message>:</b> ${ estado } <br>
				<div class="editable" id="url-${ concesionaria.idConcesionaria }"> <b>Url</b>:${ concesionaria.urlServicio } </div>
				 <div class="editable" id="ts-${ concesionaria.idConcesionaria }"> <b><fmt:message key="tipo_de_servicio" bundle="${etq}"></fmt:message></b>:${ concesionaria.codTecnologia } </div>
				 <div class="editable" id="cuit-${ concesionaria.idConcesionaria }"> <b><fmt:message key="cuit" bundle="${etq}"></fmt:message>:</b>${ concesionaria.cuit } </div>
				 <div class="editable" id="dir-${ concesionaria.idConcesionaria }"> <b><fmt:message key="direccion" bundle="${etq}"></fmt:message>:</b>${ concesionaria.direccion } </div>
				 <div class="editable" id="tel-${ concesionaria.idConcesionaria }"> <b><fmt:message key="telefono" bundle="${etq}"></fmt:message>:</b> ${ concesionaria.telefono } </div>
				 <div class="editable" id="em-${ concesionaria.idConcesionaria }"> <b><fmt:message key="email" bundle="${etq}"></fmt:message>:</b> ${ concesionaria.email } </div>
				 <div class="editable" id="dias-${ concesionaria.idConcesionaria }"> <b><fmt:message key="dias" bundle="${etq}"></fmt:message>:</b> ${ concesionaria.cantDiasCaducidad } </div>
				 <fmt:parseDate pattern="dd-MM-yyyy" value="${ concesionaria.ultimaActualizacion } " var="date" />
				 <div class="editable"> <b><fmt:message key="ultima_actualizacion" bundle="${etq}"></fmt:message>:</b> <fmt:formatDate value="${date}" type="date" dateStyle = "short" timeStyle="short" /></div><br>
			<c:if test="${ concesionaria.aprobada eq 'S'}">
				<input type="button" class="normal button" value="<fmt:message key="rechazar" bundle="${etq}"></fmt:message>" onclick="jConcesionaria.rechazar('${ concesionaria.idConcesionaria }')">
			</c:if>
			<c:if test="${ concesionaria.aprobada eq 'N'}">
				<input type="button" class="normal button" value="<fmt:message key="aprobar" bundle="${etq}"></fmt:message>" onclick="jConcesionaria.aprobar('${ concesionaria.idConcesionaria }')">
			</c:if>
			
			<input type="button" id="config-${ concesionaria.idConcesionaria }" class="normal button" value="<fmt:message key="configurar" bundle="${etq}"></fmt:message> " onclick="jConcesionaria.editarConcesionaria('${ concesionaria.idConcesionaria }')">
			<input type="button" value="<fmt:message key="conexion" bundle="${etq}"></fmt:message>" onclick="jConcesionaria.testingSyncro('${ concesionaria.idConcesionaria }')"> <div id="respuesta-${ concesionaria.idConcesionaria }"></div>
			</form>
			</div> 
		</c:forEach>
	</c:if>
	<c:if test="${empty concesionarias}">
			<fmt:message key="mensaje_no_hay_registradas" bundle="${etq}"></fmt:message>
		</c:if>
	</div>

