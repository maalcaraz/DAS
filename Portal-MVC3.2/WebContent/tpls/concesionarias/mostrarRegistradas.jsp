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
	<h2>Concesionarias Registradas</h2> <br><br> 
		<c:forEach var="concesionaria" items="${ concesionarias }" varStatus="status">
		
			<div class="grid-item">
			<h3>${ concesionaria.nomConcesionaria } </h3>
				<b>Tipo de Servicio:</b> ${ concesionaria.codTecnologia } <br>
				<b>Url:</b> ${ concesionaria.urlServicio } <br>
				<b>Direccion:</b> ${ concesionaria.direccion } <br>
				<b>Telefono:</b> ${ concesionaria.telefono } <br>
				<b>Email:</b> ${ concesionaria.email } <br>
				<b>Ultima actualizacion:</b> ${ concesionaria.ultimaActualizacion } <br>
			<input type="button" value="Editar" onclick="jConcesionaria.editarConcesionaria('${ concesionaria.idConcesionaria }')"> 
			</div> 
		</c:forEach>
	</c:if>
	</div>

