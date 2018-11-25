<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<c:set var="concesionarias" scope="request"
	value="${ requestScope.concesionarias}">
</c:set>

<h1>Clientes</h1>
<table>
	<thead>
		<tr>
			<td>concesionaria</td>
			<td>Dni</td>
			<td>Nombre</td>
			<td>Edad</td>
			<td>Domicilio</td>
			<td>Email</td>
			<td>id plan</td>
			<td>cant cuotas</td>
			<td>cuotas pagas</td>
			<td>cuotas sin pagar</td>
			<td>nro chasis</td>
			<td>fecha entrega</td>
			<td>cancelado</td>
			<td>ganador sorteo</td>
		</tr>
	</thead>
	<tbody>

		<c:forEach var="concesionaria" items="${ concesionarias }">

			<c:forEach var="cliente" items="${ concesionaria.clientes }">
				<tr>
				    <td>${ cliente.getItem("nombreConcesionaria") }</td>
					<td>${ cliente.dniCliente }</td>
					<td>${ cliente.nomCliente }</td>
					<td>${ cliente.edad }</td>
					<td>${ cliente.domicilio }</td>
					<td>${ cliente.emailCliente }</td>
					<td>${ cliente.getItem("idPlan") }</td>
					<td>${ cliente.getItem("cantCuotas") }</td>
					<td>${ cliente.getItem("cuotasPagas") }</td>
					<td>${ cliente.getItem("cuotasSinPagar") }</td>
					<td>${ cliente.getItem("nroChasis") }</td>
					<td>${ cliente.getItem("fechaEntrega") }</td>
					<td>${ cliente.getItem("cancelado") }</td>
					<td>${ cliente.getItem("ganador") }</td>

				</tr>
			</c:forEach>
		</c:forEach>

	</tbody>
</table>
