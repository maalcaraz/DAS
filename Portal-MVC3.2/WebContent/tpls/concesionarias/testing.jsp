<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script type="text/javascript" src="/util/Javascript.do/load=jquery,jquery.i18n.properties,utils,cuentas,login,concesionarias,sorteos" ></script>
	<link type="text/css" rel="stylesheet" href="/util/StyleSheet.do/load=admin" />	
</head>
	      <section class="wrapper">
	        <div class="row">
				<div class="col-lg-12">
					<h3 class="page-header">
						<i class="fa fa fa-wrench"></i>
						Testing
					</h3>
					<ol class="breadcrumb">
						<li><i class="fa fa-home"></i><a href="/home/Home.do"><fmt:message key="home" bundle="${etq}"></fmt:message></a></li>
						<li><i class="fa fa-wrench"></i>
							Testing</li>
					</ol>
				</div>
			</div>
	        <!-- page start-->
	        <div class="main" id="contenido-admin">
	        	<div class="row">
		        	<div class="col-lg-12">
						<section class="panel">
							<header class="panel-heading"> Consulta quincenal </header>
						<div class="panel-body">
							<input type="button" id="consultaQuincenal" value="Consulta Quincenal" onclick="jConcesionaria.getClientes()">
							<div id="detalle-clientes">  </div>
						</div>
						</section>
					</div>
				</div>
				<div class="row">
		        	<div class="col-lg-12">
						<section class="panel">
							<header class="panel-heading"> Notificar ganador </header>
							<div class="panel-body">
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
						</section>
					</div>
				</div>
				<div class="row">
		        	<div class="col-lg-12">
						<section class="panel">
							<header class="panel-heading">Verificar cancelado </header>
							<div class="panel-body">
							<form id="verificarCanceladoForm" >
								<label for="dniVerificar"> Dni:  </label>
								<input type="text" id="dniVerificar" name="dniVerificar"> <br><br>
								<label for="idPlan"> Identificador de plan:  </label>
								<input type="text" id="idPlan" name="idPlan"><br><br>
								<button id="verificar" onclick="jConcesionaria.verificarCancelado()" name="verificar"> Verificar cancelado </button>
								<br><br>
							</form>
							</div>
							<div id="respuestaCancelado"> </div>
						</section>
					</div>
				</div>
				
				<div class="row">
		        	<div class="col-lg-12">
						<section class="panel">
							<header class="panel-heading">Prueba de sincronicidad de Concesionarias</header>
							<div class="panel-body">
								<input type="button" id="sync" value="Verificar" onclick="jConcesionaria.testingSyncro()">
								<div id="respuesta-servicio"></div>
							</div>
						</section>
					</div>
				</div>
				
				<input type="button" id="testMail" value="Mandar Mail" onclick="jPortal.mandarMail()">
				<div id="respuesta-mail"></div>

			</div>
	        <!-- page end-->
	      </section>
