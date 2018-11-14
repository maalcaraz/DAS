<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


	<c:set var="concesionarias" scope="request" value="${ requestScope.concesionarias}"> </c:set>
	
	<c:set var="clientes" scope="request" value="${ requestScope.clientes }"> </c:set>
	<c:set var="transaccion" scope="request" value="${ requestScope.transaccion }"> </c:set>
	<c:set var="planes" scope="request" value="${ requestScope.planes }"> </c:set>
	<c:set var="adquiridos" scope="request" value="${ requestScope.adquiridos }"> </c:set>
	<c:set var="cuotas" scope="request" value="${ requestScope.cuotas }"> </c:set>

<h1> Viendo las concesionarias</h1>

	<c:forEach var="concesionaria" items="${ concesionarias }" varStatus="status">
		${ concesionaria.nomConcesionaria }
	</c:forEach>


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