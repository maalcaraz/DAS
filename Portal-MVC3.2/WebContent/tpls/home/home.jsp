<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<script type="text/javascript" src="/util/Javascript.do/load=jquery,jquery.i18n.properties,utils,cuentas,login,concesionarias" ></script>
	<link type="text/css" rel="stylesheet" href="/util/StyleSheet.do/load=home,page,messages,login" />
<title>HOME</title>
</head>
<body>

<ul>
  <li><a class="active" href="#home">Home</a></li>
  <li><a href="#novedades">Novedades</a></li>
  <li><a href="#proximasfechas"> Proximas fechas </a></li>
  <li><a href="#ganadores">Ganadores</a></li>
  <li><a href="#concesionarias">Concesionarias Adheridas</a></li>
  <li><a href="#suscribir" onclick="jConcesionaria.suscribir()">Suscribir Concesionaria</a></li>
  <li><a href="#about">Como es el plan </a></li>
  <li><a href="#resultadosSorteo"> Resultados ultimo sorteo </a></li>
  <li><input type="button" id="login" name="login" onclick="jLogin.login()" value="Iniciar Sesion"> </li>
</ul>



<div id="content"></div>
</body>
</html>