<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script type="text/javascript" src="/util/Javascript.do/load=jquery,jquery.i18n.properties,utils,cuentas,login,concesionarias,sorteos" ></script>
	<link type="text/css" rel="stylesheet" href="/util/StyleSheet.do/load=admin,home,page,messages" />	
</head>
<div class="grid-container">
	<div class="grid-item">
	<h2> Consulta quincenal </h2>
		<input type="button" id="consultaQuincenal" value="Consulta Quincenal" onclick="jConcesionaria.getClientes()">
		
		<div id="detalle-clientes">  </div>
		
	
	</div>

	<div class="grid-item">
	<h2> Notificar ganador </h2>
	<form id="formNotificar">
		<span>
		<label for="idConcesionaria"> Concesionaria:  </label>
		<input type="text" id="idConcesionaria" name="idConcesionaria" value="Colcar2023979636"></span> 
		<br><br>
		<label for="dniCliente"> Dni del cliente:  </label>
		<input type="text" id="dniCliente" name="dniCliente" value="23432255">
		<br><br>
		<label for="nombreApellido"> Nombre y apellido: </label>
		<input type="text" id="nombreApellido" name="nombreApellido" value="Pablo Alcaraz">
		<br><br>
		<label for="idPlan"> IdPlan:  </label>
		<input type="text" id="idPlan" name="idPlan" value="303455">
		<br><br>
		<label for="fechaSorteo"> Fecha del sorteo:  </label>
		<input type="text" id="fechaSorteo" name="fechaSorteo" value="02-02-18">
		<br><br>
		
		<input type="button" id="notificarGanador" value="Notificar Ganador" onclick="jConcesionaria.notificarConcesionaria()">
		</form>
	<div id="respuestaNotificar"> </div>
	
	</div>
	
	<div class="grid-item"> <h2> Verificar cancelado </h2> \
	<form id="verificarCanceladoForm" >
	
	
		<label for="dniVerificar"> Dni:  </label>
		<input type="text" id="dniVerificar" name="dniVerificar"> <br><br>
		<label for="idPlan"> Identificador de plan:  </label>
		<input type="text" id="idPlan" name="idPlan"><br><br>
		<button id="verificar" onclick="jConcesionaria.verificarCancelado()" name="verificar"> Verificar cancelado </button>
		<br><br>
	</form>
	<div id="respuestaCancelado"> </div>
	
	</div>
	
</div>

<div class="grid-item">
<h2>Prueba de sincronicidad de Concesionarias</h2>

<input type="button" id="sync" value="Verificar" onclick="jConcesionaria.testingSyncro()">
<div id="respuesta-servicio"></div>

</div>