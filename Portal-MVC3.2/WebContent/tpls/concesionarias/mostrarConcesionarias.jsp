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

<div class="grid-container">
	<div class="grid-item">

		<c:if test="${!empty pendientes}">

			<c:forEach var="pendiente" items="${ pendientes }" varStatus="status">
				<div class="grid-item">

					Concesionaria: ${ pendiente.nomConcesionaria }
					<span> 
					<input type="button" id="aprobar" name="aprobar" value="Aprobar" onclick="jConcesionaria.aprobar()"> 
					<input type="button" id="rechazar" name="rechazar" value="Rechazar" onclick="jConcesionaria.rechazar()">
					</span>
				<br>
				</div>
			</c:forEach>
		</c:if>
	</div>

	<div class="grid-item">
	<c:if test="${!empty concesionarias}">
	
		<c:forEach var="concesionaria" items="${ concesionarias }"
			varStatus="status">
		Concesionaria: ${ concesionaria.nomConcesionaria }
	</c:forEach>
	</c:if>
	</div>


</div>
