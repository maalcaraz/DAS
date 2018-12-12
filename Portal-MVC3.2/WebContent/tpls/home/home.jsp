<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<script type="text/javascript" src="/util/Javascript.do/load=jquery,jquery.i18n.properties,utils,portal,cuentas,login,concesionarias,sorteos,clientes" ></script>
	<link type="text/css" rel="stylesheet" href="/util/StyleSheet.do/load=home,page,messages,login,sorteos,admin" />
	
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
	
	<fmt:setLocale value="${ sessionScope.lang }" scope="session"/>
	<fmt:setBundle basename="ar.edu.ubp.das.src.portal.properties.messages" var="etq"/>
		
	<title><fmt:message key="home" bundle="${etq}"></fmt:message> </title>
</head>
	<body>
	
		<div id="site">
			<ul class="nav hor">
			  <li class="item nav hor"><a href="/home/Home.do"><fmt:message key="home" bundle="${etq}"></fmt:message></a></li>
			  <li class="item nav hor"><a href="#about"><fmt:message key="about" bundle="${etq}"></fmt:message></a></li>
			  <li class="item nav hor"><a href="#proximasfechas" onclick="jSorteos.proximasFechas()"><fmt:message key="proximas_fechas" bundle="${etq}"></fmt:message></a></li>
			  <!-- <li class="item nav hor"><a href="#" onclick="jConcesionaria.obtenerGanadores()"><fmt:message key="ganadores" bundle="${etq}"></fmt:message></a></li>
			   -->
			  <li class="item nav hor"><a href="#" onclick="jConcesionaria.mostrarAdheridas()"><fmt:message key="concesionarias_adheridas" bundle="${etq}"></fmt:message></a></li>
			 <!-- <li class="item nav hor"><a href="#" onclick="jConcesionaria.suscribir()"><fmt:message key="suscribir_concesionaria" bundle="${etq}"></fmt:message></a></li>
			  -->
			  <li class="item nav hor"><a href="#resultadosSorteo" onclick="jConcesionaria.obtenerGanadores()"><fmt:message key="historico" bundle="${etq}"></fmt:message></a></li>
			  <li class="item nav hor"><input type="button" class="boton" id="loginbutton" name="loginbutton" onclick="jLogin.login(1)" value="<fmt:message key="cerrar_sesion" bundle="${etq}"></fmt:message>" > </li>
			  <li class="item nav hor"><button value="&nbsp;" class="spanish" onclick="jPortal.lang('es')"></button></li>
			  <li class="item nav hor"><button value="&nbsp;" class="english" onclick="jPortal.lang('en')"></button></li>
			</ul>
			<div id="contenido"> 
					<div class="menu">
							<br>
								<ul class="nav ver"> 
								  <li class="nav tit"> <h3> Menu - Admin </h3> </li>
								  <li class="item nav ver" > <a onclick="jConcesionaria.mostrarRegistradas()" href="#"><fmt:message key="concesionarias_registradas" bundle="${etq}"></fmt:message></a></li>
								  <li class="item nav ver" > <a onclick="jSorteos.obtenerSorteos()" href="#"><fmt:message key="fechas_de_sorteo" bundle="${etq}"></fmt:message></a></li>
								  <li class="item nav ver" > <a onclick="jConcesionaria.testing()" href="#">Testing </a> </li>
								  <li class="item nav ver" > <a onclick="jClientes.datosTodosClientes()" href="#"><fmt:message key="estados_cuentas_clientes" bundle="${etq}"></fmt:message></a> </li>
								</ul>
							</div>
						<div class="main" id="contenido-admin"> 
						
							      <img src="/img/ford.png" alt="New York">
							      <br>
								  <fmt:message key="frase_home" bundle="${etq}"></fmt:message>
						</div>
			</div>
		</div>
	</body>
</html>