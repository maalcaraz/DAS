<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<c:set var="cliente" scope="request" value="${requestScope.cliente}"></c:set>
<c:set var="adquiridos" scope="request"
	value="${requestScope.adquiridos}"></c:set>

<table>
	<thead>
		<tr>
			<td>Dni Cliente</td>
			<td>Nombre y Apellido</td>
			<td>Edad</td>
			<td>Domicilio</td>
			<td>Email</td>
			<td>Fecha ultima actualizacion datos</td>

		</tr>
	</thead>
	<tbody>

		<tr>
			<td>${ cliente.dniCliente }</td>
			<td>${ cliente.nomCliente }</td>
			<td>${ cliente.edad }</td>
			<td>${ cliente.domicilio }</td>
			<td>${ cliente.emailCliente }</td>
			<td>${ cliente.getItem("fecha_ult_transaccion") }</td>
		</tr>

	</tbody>
</table>

<table>
	<thead>
		<tr>
			<td>plan</td>
			<td>concesionaria</td>
			<td>nro chasis</td>
			<td>fecha entrega</td>
			<td>Cancelado</td>
			<td>Cant cuotas</td>
			<td>Cuotas pagas</td>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${adquiridos}" var="adquirido">
			<tr>
				<td>${ adquirido.idPlan }</td>
				<td>${ adquirido.idConcesionaria  }</td>
				<td>${ adquirido.getItem("nroChasis")  }</td>
				<td>${ adquirido.getItem("fechaEntrega")  }</td>
				<td>${ adquirido.cancelado  }</td>
				<td>${ adquirido.getItem("cant_cuotas") }</td>
				<td>${ adquirido.getItem("cuotas_pagas")  }</td>
			</tr>
      </c:forEach>
	</tbody>
</table>