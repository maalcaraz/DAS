<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="./css/style.css">
<script type="text/javascript" src="./js/jquery.js"></script>
<script type="text/javascript" src="./js/utils.js"></script>
<script type="text/javascript" src="./js/test.js"></script>
<title>Testing de Servicio REST</title>
</head>
<body>

<h1>Seleccione el servicio a consumir</h1>

<form id="form" method="post" action="javascript:void(null)">

<select id="servicio" name="servicio">
	<option value="AutoHaus"> AutoHaus </option>
	<option value="Montironi"> Montironi </option>
</select>
<br><br>

<div class="box">
<h2> Obtener clientes </h2>
<button id="obtener" onclick="jTest.obtener()" name="obtener"> Obtener Datos de Clientes </button>
<br><br>
</div>

<div class="box">
<h2> Notificar ganador </h2>
	
		<label for="idConcesionaria"> Concesionaria:  </label>
	<input type="text" id="idConcesionaria" name="idConcesionaria" value="Colcar"> <br><br>
	<label for="dniCliente"> Dni del cliente:  </label>
	<input type="text" id="dniCliente" name="dniCliente" value="25555555"><br><br>
	<label for="nombreApellido"> Nombre y apellido: </label>
	<input type="text" id="nombreApellido" name="nombreApellido"><br><br>
	<label for="idPlan"> Email:  </label>
	<input type="text" id="idPlan" name="idPlan" value="303456"><br><br>
	<label for="fechaSorteo"> Fecha del sorteo:  </label>
	<input type="text" id="fechaSorteo" name="fechaSorteo" value="02-02-18"><br><br>
	
	<button id="notificar" onclick="jTest.notificar()" name="notificar"> Notificar ganador </button><br><br>
</div>

<div class="box">
<h2> Verificar ganador </h2>
<br><br>
	<label for="dniVerificar"> Dni:  </label>
	<input type="text" id="dniVerificar" name="dniVerificar"> <br><br>
	<label for="idPlan"> Identificador de plan:  </label>
	<input type="text" id="idPlan" name="idPlan"><br><br>
<button id="verificar" onclick="jTest.verificar()" name="verificar"> Verificar cancelado </button>
<br><br>
</div>

<br>

</form>
<h2>Resultados </h2>
<div id="message"></div>
<div id="result"> </div>

</body>
</html>