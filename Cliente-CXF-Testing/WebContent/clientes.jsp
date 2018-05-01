<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<link href="">

<c:set var="clientes" scope="request" value="${ requestScope.clientes }"> </c:set>

<table >
	<thead>
		<tr>
			<td> Concesionaria</td>
			<td> Dni Cliente </td>
			<td> Nombre y Apellido </td>
			<td> Email </td>
			<td> Fecha de sorteo </td>
		</tr>
	</thead>
	<tbody>
		
		<c:forEach var="cliente" items="${ clientes }" varStatus="status">
		<tr> 
			<td> ${ servicio } </td>
			<td> ${ cliente.dniCliente } </td>
			<td> ${ cliente.nomCliente } </td>
			<td> ${ cliente.emailCliente } </td>
			<td> ${ cliente.fechaSorteo } </td>
		</tr>	
	</c:forEach>
		
	</tbody>
		
			

</table>