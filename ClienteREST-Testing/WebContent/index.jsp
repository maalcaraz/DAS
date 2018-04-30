<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script type="text/javascript" src="./js/jquery.js"></script>
<script type="text/javascript" src="./js/utils.js"></script>
<script type="text/javascript" src="./js/test.js"></script>
<title>Testing de Servicio REST</title>
</head>
<body>

<h1>Seleccione el servicio a consumir</h1>

<div id="message"></div>
<div id="result"> </div>

<form id="form" method="post" action="javascript:void(null)">

<select id="servicio" name="servicio">
	<option value="" selected > Seleccione el servicio a apuntar </option>
	<option value="AutoHaus"> AutoHaus </option>
	<option value="Montironi"> Montironi </option>
</select>


<div class="box">
<h2> Obtener clientes </h2>
<button id="obtener" onclick="jTest.obtener()" name="obtener"> Obtener Datos de Clientes </button>
<br><br>
</div>

<div class="box">
<h2> Notificar ganador </h2>
	
	<label for="idConcesionaria"> Concesionaria:  </label>
	<input type="text" id="idConcesionaria"> <br><br>
	<label for="dniCliente"> Dni del cliente:  </label>
	<input type="text" id="dniCliente"><br><br>
	<label for="nombreApellido"> Nombre y apellido: </label>
	<input type="text" id="nombreApellido"><br><br>
	<label for="emailCliente"> Email:  </label>
	<input type="text" id="emailCliente"><br><br>
	<label for="fechaSorteo"> Fecha del sorteo:  </label>
	<input type="text" id="fechaSorteo"><br><br>
	
	<button id="notificar" onclick="jTest.notificar()" name="notificar"> Notificar ganador </button><br><br>
</div>

<div class="box">
<h2> Verificar ganador </h2>
<button id="verificar" onclick="jTest.verificar()" name="verificar"> Verificar cancelado </button>
</div>

<br>

</form>


</body>
</html>