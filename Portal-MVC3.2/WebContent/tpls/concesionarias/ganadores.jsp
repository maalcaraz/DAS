<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<fmt:setLocale value="${ sessionScope.lang }" scope="session" />
<fmt:setBundle basename="ar.edu.ubp.das.src.portal.properties.messages"
	var="etq" />

<br>
<h2>
	<fmt:message key="ganadores" bundle="${etq}"></fmt:message>
</h2>

<br>
<c:set var="ganadores" scope="request" value="${requestScope.ganadores}">
</c:set>

<c:if test="${not empty ganadores}">

<fmt:message key="frase_ganadores" bundle="${etq}"></fmt:message>

<table id="tablaGanadores">
	<thead>
		<tr>
			<td><fmt:message key="fecha_de_sorteo" bundle="${etq}"></fmt:message>
			</td>
			<td><fmt:message key="nombre_y_apellido" bundle="${etq}"></fmt:message>
			</td>
			<td><fmt:message key="concesionaria" bundle="${etq}"></fmt:message>
			</td>
		</tr>
	</thead>
	<tbody>
			<c:forEach var="ganador" items="${ ganadores }" varStatus="status">
				<tr>
					<!--<td>${ ganador.getItem('fechaSorteo') }</td>-->
					<fmt:parseDate pattern="yyyy-MM-dd" value="${ ganador.getItem('fechaSorteo') }" var="date" />
					<td><fmt:formatDate value="${date}" type="date" dateStyle = "short" timeStyle="short" /></td>
					<td>${ ganador.getItem('apellidoNombre') }</td>
					<td>${ ganador.getItem('nombreConcesionaria') }</td>
				</tr>
			</c:forEach>
	</tbody>
</table>
</c:if>
<c:if test="${empty ganadores}">
			<h3><fmt:message key="no_hay_sorteos" bundle="${etq}"></fmt:message></h3>
</c:if>