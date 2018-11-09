<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


	<c:set var="concesionaria" scope="request" value="${ requestScope.concesionaria}"> </c:set>
	
	<c:set var="clientes" scope="request" value="${ concesionaria.getClientes() }"> </c:set>
	<c:set var="transaccion" scope="request" value="${ concesionaria.getTransacciones() }"> </c:set>
	<c:set var="planes" scope="request" value="${ concesionaria.getPlanes() }"> </c:set>
	<c:set var="adquiridos" scope="request" value="${ concesionaria.getAdquiridos() }"> </c:set>
	<c:set var="cuotas" scope="request" value="${ concesionaria.getCuotas() }"> </c:set>


<table >
	<thead>
		<tr>
			<td> Dni Cliente </td>
			<td> Nombre y Apellido </td>
			<td> Edad </td>
			<td> Domicilio </td>
			<td> Email </td>
			<td> Telefono </td>
			<td> Localidad </td>
			<td> Provincia </td>
			<td> Concesionaria </td>
		</tr>
	</thead>
	<tbody>
		
	
		<c:forEach var="cliente" items="${ clientes }" varStatus="status">
		<tr> 
			<td> ${ cliente.dniCliente } </td>
			<td> ${ cliente.nomCliente } </td>
			<td> ${ cliente.edad } </td>
			<td> ${ cliente.domicilio } </td>
			<td> ${ cliente.emailCliente } </td>
			<td> ${ cliente.telefono } </td>
			<td> ${ cliente.idLocalidad } </td>
			<td> ${ cliente.codProvincia } </td>
				<td> ${ transaccion.idConcesionaria } </td>
		</tr>	
	</c:forEach>
	</tbody>
</table>
<br>

<table >
	<thead>
		<tr>
			<td> Id Plan </td>
			<td> Descripcion </td>
			<td> Nombre Plan </td>
			<td> Cantidad de cuotas </td>
			<td> Entrega Pactada </td>
			<td> Financiacion </td>
			<td> Dueño Plan </td>
		</tr>
	</thead>
	<tbody>
		<c:forEach var="plan" items="${ planes }" varStatus="status">
		<tr> 
			<td> ${ plan.idPlan } </td>
			<td> ${ plan.descripcion } </td>
			<td> ${ plan.nom_plan } </td>
			<td> ${ plan.cant_cuotas } </td>
			<td> ${ plan.entrega_pactada } </td>
			<td> ${ plan.financiacion } </td>
			<td> ${ plan.duenoPlan } </td>
		</tr>	
	</c:forEach>
	</tbody>
</table>

<br>
<table >
	<thead>
		<tr>
			<td> Dni Cliente </td>
			<td> Id Plan </td>
			<td> Id Cuota </td>
			<td> Importe </td>
			<td> Fecha de Vencimiento </td>
			<td> Pagada </td>
		</tr>
	</thead>
	<tbody>
		<c:forEach var="cuota" items="${ cuotas }" varStatus="status">
		<tr> 
			<td> ${ cuota.dniCliente } </td>
			<td> ${ cuota.idPlan } </td>
			<td> ${ cuota.idCuota } </td>
			<td> ${ cuota.importe } </td>
			<td> ${ cuota.fechaVencimiento } </td>
			<td> ${ cuota.pagada } </td>
		</tr>	
	</c:forEach>
	</tbody>
</table>