<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<br>
<h2>Ganadores</h2>

Estos son los compradores que salieron sorteados.
Sus cuotas fueron canceladas y ahora disfrutan su primer 0km!
<br>
<c:set var="ganadores" scope="request" value="${requestScope.ganadores}"> </c:set>

<table id="tablaGanadores" >
	<thead><tr> <td> Fecha de Sorteo   </td> 
				<td> Nombre y Apellido </td>
				<td> Concesionaria </td> 
			</tr></thead>
	<tbody>
	<c:forEach var="ganador" items="${ ganadores }" varStatus="status">
		<tr> 
			 <td> ${ ganador.getItem('fechaSorteado') }</td> 
			 <td> ${ ganador.getItem('nombre') }</td>
			 <td> ${ ganador.getItem('concesionaria') }</td> 
		</tr> 
	</c:forEach>
	</tbody>
</table>