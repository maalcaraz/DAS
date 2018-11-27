<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<head>
<script type="text/javascript" src="/util/Javascript.do/load=jquery,jquery.i18n.properties,utils,cuentas,login,concesionarias,sorteos"></script>
<link type="text/css" rel="stylesheet" href="/util/StyleSheet.do/load=home,page,messages,home,admin" />
</head>

<c:set var="concesionaria" scope="request"	value="${requestScope.concesionaria}"></c:set>
<c:set var="clientes" scope="request"	value="${requestScope.clientes}"></c:set>

<h1> Aqui se deberian mostrar los datos de concesionarias </h1> 
	<h4> ${concesionaria.idConcesionaria } </h4>
	<c:forEach var="cliente" items="${clientes }" varStatus="status">
		<div class="grid-item">
	
		<fmt:message key="dni" bundle="${etq}"> ${cliente.dniCliente}
		<fmt:message key="nombre" bundle="${etq}"> ${cliente.nomCliente}
		<fmt:message key="edad" bundle="${etq}">${cliente.edad }
		<fmt:message key="domicilio" bundle="${etq}"> ${cliente.domicilio }
		<fmt:message key="email" bundle="${etq}">${cliente.emailCliente }
					
		</div>
	</c:forEach>