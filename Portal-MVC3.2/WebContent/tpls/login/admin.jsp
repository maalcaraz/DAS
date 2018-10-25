<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<script type="text/javascript" src="/util/Javascript.do/load=jquery,jquery.i18n.properties,utils,cuentas,login,concesionarias,sorteos" ></script>
	<link type="text/css" rel="stylesheet" href="/util/StyleSheet.do/load=admin,home,page,messages" />	
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
		  <li class="item nav ver" > <a onclick="jConcesionaria.mostrarPendientes()" href="#"> Concesionarias por aprobar </a></li>
		  <li class="item nav ver" > <a onclick="jConcesionaria.mostrarRegistradas()" href="#"> Concesionarias registradas </a></li>
		  <li class="item nav ver" > <a onclick="jSorteos.obtenerSorteos()" href="#">Fechas de sorteo </a></li>
		  <li class="item nav ver" > <a onclick="jSorteos.nuevoSorteo()" href="#"> Nuevo sorteo   </a></li>
		  <li class="item nav ver" > <a onclick="jConcesionaria.testing()" href="#">Testing </a> </li>
		</ul>
	</div>
<br>
	<div class="main">
		<div id="contenido-admin"></div>
	</div>
</div>

</body>
</html>