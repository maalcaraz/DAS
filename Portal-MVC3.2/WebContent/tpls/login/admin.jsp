<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<script type="text/javascript" src="/util/Javascript.do/load=jquery,jquery.i18n.properties,utils,cuentas,login,concesionarias" ></script>
	<link type="text/css" rel="stylesheet" href="/util/StyleSheet.do/load=home,page,messages,login,admin" />	
</head>
<title>Admin</title>
</head>
<body>
<h1>Te loggeaste como admin del gobierno</h1>

<div class="grid-container">
	<div class="grid-item">
	<h2> Consulta quincenal </h2>
		<input type="button" id="consultaQuincenal" value="Consulta Quincenal" onclick="jConcesionaria.getClientes()">
		
		<div id="detalle-clientes">  </div>
	</div>

	<div class="grid-item">
	<h2> Notificar ganador </h2>
	<span>
	<label for="idConcesionaria"> Concesionaria:  </label>
	<input type="text" id="idConcesionaria" name="idConcesionaria" value="Colcar"></span> 
	<br><br>
	<label for="dniCliente"> Dni del cliente:  </label>
	<input type="text" id="dniCliente" name="dniCliente" value="25555555">
	<br><br>
	<label for="nombreApellido"> Nombre y apellido: </label>
	<input type="text" id="nombreApellido" name="nombreApellido">
	<br><br>
	<label for="idPlan"> Email:  </label>
	<input type="text" id="idPlan" name="idPlan" value="303456">
	<br><br>
	<label for="fechaSorteo"> Fecha del sorteo:  </label>
	<input type="text" id="fechaSorteo" name="fechaSorteo" value="02-02-18">
	<br><br>
	
	<input type="button" id="notificarGanador" value="Notificar Ganador" onclick="jConcesionaria.notificarGanador()">
	
	</div>
	
	<div class="grid-item"> <h2> Verificar cancelado </h2> 
	<label for="dniVerificar"> Dni:  </label>
	<input type="text" id="dniVerificar" name="dniVerificar"> <br><br>
	<label for="idPlan"> Identificador de plan:  </label>
	<input type="text" id="idPlan" name="idPlan"><br><br>
<button id="verificar" onclick="jConcesionaria.verificarCancelado()" name="verificar"> Verificar cancelado </button>
<br><br>
	<div id="respuestaCancelado"> </div>
	
	</div>
</div>

<div class="grid-item">
<h2>Prueba de sincronicidad de Concesionarias</h2>

<input type="button" id="sync" value="Verificar" onclick="jConcesionaria.testingSyncro()">

</div>
	
</body>
</html>