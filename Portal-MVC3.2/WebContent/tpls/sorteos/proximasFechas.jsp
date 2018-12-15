<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<br>

<c:set var="sorteos" scope="request" value="${requestScope.sorteos}"> </c:set>
<fmt:setLocale value="${ sessionScope.lang }" scope="session"/>
<fmt:setBundle basename="ar.edu.ubp.das.src.portal.properties.messages" var="etq"/>

<h2><fmt:message key="sorteos" bundle="${etq}"></fmt:message> </h2>
<table id="tablaSorteos" border=1>
	<thead>
	<tr> 
		<td><fmt:message key="estado" bundle="${etq}"></fmt:message></td> 
		<td><fmt:message key="fecha_sorteo" bundle="${etq}"></fmt:message></td> 
		<td><fmt:message key="fecha_ejecucion" bundle="${etq}"></fmt:message></td>
		<td><fmt:message key="fecha_notificacion" bundle="${etq}"></fmt:message></td>
		  
	</tr>
	</thead>
	<tbody>
	<c:if test="${not empty sorteos}">
	<c:forEach var="sorteo" items="${ sorteos }" varStatus="status">
		<tr>
		<td> 
			<c:if test="${ sorteo.pendiente eq '-' }">
				Sin sortear
			</c:if>
			<c:if test="${ sorteo.pendiente ne '-' }">
				${ sorteo.pendiente }
			</c:if>
		</td>
			 <fmt:parseDate pattern="dd-MM-yyyy" value="${ sorteo.fechaSorteado }" var="date" />
			 <td><fmt:formatDate value="${date}" type="date" dateStyle = "short" timeStyle="short" /></td>
			 
			 <c:if test="${ sorteo.fechaEjecucion ne '-'}">
				  <fmt:parseDate pattern="dd-MM-yyyy" value="${ sorteo.fechaEjecucion }" var="date" />
				  <td><fmt:formatDate value="${date}" type="date" dateStyle = "short" timeStyle="short" /></td>
				  
				  <c:if test="${ sorteo.fechaNotificacion ne '-'}">
				  	<fmt:parseDate pattern="dd-MM-yyyy" value="${ sorteo.fechaNotificacion }" var="date" />
			 		<td><fmt:formatDate value="${date}" type="date" dateStyle = "short" timeStyle="short" /></td>
				  </c:if>
				  <c:if test="${ sorteo.fechaNotificacion eq '-'}">
				  <td> - </td>
				  </c:if>
			 </c:if>
			 <c:if test="${ sorteo.fechaEjecucion eq '-'}">
				   <td> - </td>
				   <td> - </td>
			 </c:if>
		</tr> 
	</c:forEach>
	</c:if>

		<c:if test="${empty sorteos}">
			<td><fmt:message key="no_hay_fecha_definida" bundle="${etq}"></fmt:message></td>
		</c:if>
	</tbody>
</table>

