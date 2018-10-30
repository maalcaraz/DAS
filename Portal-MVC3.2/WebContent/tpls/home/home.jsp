<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<script type="text/javascript" src="/util/Javascript.do/load=jquery,jquery.i18n.properties,utils,cuentas,login,concesionarias,sorteos" ></script>
	<link type="text/css" rel="stylesheet" href="/util/StyleSheet.do/load=home,page,messages,login,sorteos" />
<title>HOME</title>
</head>
<body>

<div id="site">
<ul class="nav hor">
  <li class="item nav hor"><a href="#" onclick="jLogin.logout(1, null)">Home</a></li>
  <li class="item nav hor"><a href="#about">Como es el plan </a></li>
  <li class="item nav hor"><a href="#proximasfechas" onclick="jSorteos.proximasFechas()"> Proximas fechas </a></li>
  <li class="item nav hor"><a href="#" onclick="jConcesionaria.obtenerGanadores()">Ganadores</a></li>
  <li class="item nav hor"><a href="#" onclick="jConcesionaria.mostrarAdheridas()">Concesionarias Adheridas</a></li>
  <li class="item nav hor"><a href="#" onclick="jConcesionaria.suscribir()">Suscribir Concesionaria</a></li>
  <li class="item nav hor"><a href="#resultadosSorteo"> Resultados ultimo sorteo </a></li>
  <li class="item nav hor"><input type="button" class="boton" id="loginbutton" name="loginbutton" onclick="jLogin.login()" value="Iniciar Sesion"> </li>
</ul>

<div id="contenido"> 
	<div class="menu"> &nbsp; &nbsp; &nbsp; &nbsp; </div>
	<div class="main" id="contenido-admin"> 
	
		      <img src="/img/ford.png" alt="New York">
		      </br>
		      <p>El Gobierno Nacional lanza el plan "SorteAR" con el fin de facilitar la adquisicion de tu primer 0km.</p>
		      <p> Este plan nacional que consiste en la cancelacion completa de aquellos compradores que hayan pagado entre 24 y 36 cuotas en alguna de las concesionarias adheridas a este plan. </p>
		      <p> Si todavia no compraste tu primer 0km, chequea las concesionarias adheridas y participa del sorteo! No te pierdas esta oportunidad unica!</p>
	</div>
</div>
	  
	
</div>
</body>
</html>