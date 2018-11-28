<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<br>
<h2><fmt:message key="GANADORES" bundle="${etq}"></fmt:message></h2>

<fmt:message key="frase_ganadores" bundle="${etq}"></fmt:message>

Estos son los compradores que salieron sorteados.
Sus cuotas fueron canceladas y ahora disfrutan su primer 0km!
<br>
<c:set var="ganadores" scope="request" value="${requestScope.ganadores}"> </c:set>

<table id="tablaGanadores" >
	<thead><tr> <td> <fmt:message key="fecha_de_sorteo" bundle="${etq}"></fmt:message>   </td> 
				<td> <fmt:message key="nombre_y_apellido" bundle="${etq}"></fmt:message> </td>
				<td> <fmt:message key="concesionaria" bundle="${etq}"></fmt:message> </td> 
			</tr></thead>
	<tbody>
	<c:forEach var="ganador" items="${ ganadores }" varStatus="status">
		<tr> 
			 <td> ${ ganador.getItem('fechaSorteo') }</td> 
			 <td> ${ ganador.getItem('apellidoNombre') }</td>
			 <td> ${ ganador.getItem('nombreConcesionaria') }</td> 
		</tr> 
	</c:forEach>
	</tbody>
</table>