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


	<div>
	<c:if test="${!empty concesionarias}">
	
		<c:forEach var="concesionaria" items="${ concesionarias }"
			varStatus="status">
		<h4>Concesionarias Registradas</h4> <br><br> 
		<div class="grid-item">
		<h2>${ concesionaria.nomConcesionaria } </h2>
			Tipo de Servicio: ${ concesionaria.codTecnologia } <br>
			Direccion: ${ concesionaria.direccion } <br>
			Telefono: ${ concesionaria.telefono } <br>
			Ultima actualizacion: ${ concesionaria.ultimaActualizacion } <br>
		<input type="button" value="Editar" onclick="jConcesionaria.editarConcesionaria('${ concesionaria.idConcesionaria }')"> 
		</div> 
	</c:forEach>
	</c:if>
	</div>

