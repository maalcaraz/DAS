<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<br>
<h2>Sorteos </h2>
<c:set var="sorteos" scope="request" value="${requestScope.sorteos}"> </c:set>
<fmt:setLocale value="${ sessionScope.lang }" scope="session"/>
<fmt:setBundle basename="ar.edu.ubp.das.src.portal.properties.messages" var="etq"/>

<table id="tablaSorteos" border=1>
	<thead>
	<tr> 
		<td><fmt:message key="proximas_fechas" bundle="${etq}"></fmt:message></td> 
		<td><fmt:message key="fecha_sorteado" bundle="${etq}"></fmt:message></td> 
	</tr>
	</thead>
	<tbody>
	<c:if test="${not empty ganadores}">
	<c:forEach var="sorteo" items="${ sorteos }" varStatus="status">
		<tr> 
			 <td> ${ sorteo.idSorteo }</td> 
			 <td> ${ sorteo.fechaSorteado }  </td> 
		</tr> 
	</c:forEach>
	</c:if>

		<c:if test="${empty ganadores}">
			<td><fmt:message key="no_hay_fecha_definida" bundle="${etq}"></fmt:message></td>
			<td><fmt:message key="no_hay_fecha_definida" bundle="${etq}"></fmt:message></td>
		</c:if>
	</tbody>
</table>

