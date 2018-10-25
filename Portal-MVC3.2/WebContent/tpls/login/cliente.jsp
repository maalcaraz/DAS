<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script type="text/javascript"
	src="/util/Javascript.do/load=jquery,jquery.i18n.properties,utils,cuentas,login,clientes"></script>
<link type="text/css" rel="stylesheet" href="/util/StyleSheet.do/load=home,admin,page,messages,login,cliente" />
</head>
<body>

<div class="menu">
		<ul class="nav ver"> 
		  <li class="nav tit"> <h3> Menu - Clientes </h3> </li>
		  <li class="item nav ver" > <a onclick="jClientes.estadoCuenta()" href="#"> Estado Cuenta </a></li>
		  <li class="item nav ver" > <a href="#"> Mi producto </a></li>
		  <li class="item nav ver" > <a href="#">Mis datos</a></li>
		</ul>
</div>
	<div class="main">
		<div id="contenido-admin"></div>
	</div>
</body>
</html>