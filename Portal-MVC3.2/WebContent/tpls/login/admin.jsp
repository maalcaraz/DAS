<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
	
<fmt:setLocale value="${ sessionScope.lang }" scope="session"/>
<fmt:setBundle basename="ar.edu.ubp.das.src.portal.properties.messages" var="etq"/>
	
</head>
<title>Admin</title>
</head>
<body>
<div>
<br>
	<div class="menu">
	<br>
		<ul class="nav ver"> 
		  <li class="nav tit"> <h3> Menu - Admin </h3> </li>
		  <li class="item nav ver" > <a onclick="jLogin.login()" href="#"><fmt:message key="cerrar_sesion" bundle="${etq}"></fmt:message></a></li>
		  <li class="item nav ver" > <a onclick="jConcesionaria.mostrarRegistradas()" href="#"><fmt:message key="concesionarias_registradas" bundle="${etq}"></fmt:message></a></li>
		  <li class="item nav ver" > <a onclick="jSorteos.obtenerSorteos()" href="#"><fmt:message key="fechas_de_sorteo" bundle="${etq}"></fmt:message></a></li>
		  <li class="item nav ver" > <a onclick="jConcesionaria.testing()" href="#">Testing </a> </li>
		  <li class="item nav ver" > <a onclick="jClientes.datosTodosClientes()" href="#"><fmt:message key="estados_cuentas_clientes" bundle="${etq}"></fmt:message></a> </li>
		</ul>
	</div>
<br>
	<div class="main">
		<div id="contenido-admin"></div>
	</div>
</div>

</body>
</html>