<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<head>
<script type="text/javascript" src="/util/Javascript.do/load=jquery,jquery.i18n.properties,utils,cuentas,login,concesionarias,sorteos"></script>
<link type="text/css" rel="stylesheet" href="/util/StyleSheet.do/load=home,page,messages,home,admin" />
</head>

<c:set var="concesionarias" scope="request"	value="${requestScope.concesionarias}"></c:set>
<c:set var="pendientes" scope="request"	value="${requestScope.pendientes}"></c:set>

<h2> Concesionarias por aprobar </h2>
	<div>
		<c:if test="${!empty pendientes}">
			<c:forEach var="pendiente" items="${ pendientes }" varStatus="status">
				<div class="grid-item">

					<h3> Concesionaria: ${ pendiente.nomConcesionaria } </h3>
			<br>
				<b>Tipo de Servicio:</b> ${ pendiente.codTecnologia } <br>
				<b>Url:</b> ${ pendiente.urlServicio } <br>
				<b>Direccion:</b> ${ pendiente.direccion } <br>
				<b>Telefono:</b> ${ pendiente.telefono } <br>
				<b>Email:</b> ${ pendiente.email } <br>
				
					
				<span> 
				<input type="button" id="aprobar" name="aprobar" value="Aprobar" onclick="jConcesionaria.aprobar(&quot;${ pendiente.idConcesionaria }&quot;) "> 
				<input type="button" id="rechazar" name="rechazar" value="Rechazar" onclick="jConcesionaria.rechazar(&quot;${ pendiente.idConcesionaria }&quot;)">
				<input type="button" class="normal button" value="Editar" onclick="jConcesionaria.editarConcesionaria('${ concesionaria.idConcesionaria }')"> 
			
				<input type="button" value="Test Connection" onclick="jConcesionaria.testingSyncro('${ pendiente.idConcesionaria }')">

				
				</span>
			<br>
			<div id="respuesta-${ pendiente.idConcesionaria }"></div>
			</div>
			
			</c:forEach>
		</c:if>
		<c:if test="${empty pendientes}">
			No hay concesionarias pendientes de aprobacion
		</c:if>
	</div>