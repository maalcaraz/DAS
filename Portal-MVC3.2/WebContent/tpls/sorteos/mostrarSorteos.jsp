<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<fmt:setLocale value="${ sessionScope.lang }" scope="session"/>
<fmt:setBundle basename="ar.edu.ubp.das.src.portal.properties.messages" var="etq"/>

<h2><fmt:message key="sorteos" bundle="${etq}"></fmt:message> </h2>
<c:set var="sorteos" scope="request" value="${requestScope.sorteos}"> </c:set>

<table id="tablaSorteos" border=1> <thead>
	<tr>  
		<td> </td> 
		<td><fmt:message key="fecha_definida" bundle="${etq}"></fmt:message></td> 
		<td><fmt:message key="fecha_ejecucion" bundle="${etq}"></fmt:message></td> 
		<td><fmt:message key="fecha_notificacion" bundle="${etq}"></fmt:message></td>
		<td> </td>
	</tr> </thead>
	<c:forEach var="sorteo" items="${ sorteos }" varStatus="status">
		<tr id="${ sorteo.idSorteo }"> 
			 <c:if test="${ sorteo.fechaEjecucion eq '-' }">
				 <td>
				  <input type="checkbox" value=${ sorteo.idSorteo } name=${ sorteo.idSorteo }>
				 </td>
				 <!--  <td> ${ sorteo.fechaSorteado }  </td>  -->
				 <fmt:parseDate pattern="dd-MM-yyyy" value="${sorteo.fechaSorteado}" var="date" />
			 	 <td><fmt:formatDate value="${date}" type="date" dateStyle = "short" timeStyle="short" /></td>
				
				 <td> ${ sorteo.fechaEjecucion } </td> 
				 <td> - </td> 
				 <td> 
				 <input type="button" class="normal button" onclick="jSorteos.editarSorteo('${ sorteo.idSorteo }')" value="Editar"> 
				  </td> 
			 </c:if>
			 <c:if test="${ sorteo.fechaEjecucion ne '-'}">
			 <td></td>
			 <!--  <td> ${ sorteo.fechaSorteado }  </td>  -->
			 <fmt:parseDate pattern="dd-MM-yyyy" value="${sorteo.fechaSorteado}" var="date" />
			 <td><fmt:formatDate value="${date}" type="date" dateStyle = "short" timeStyle="short" /></td>
			 <!-- <td> ${ sorteo.fechaEjecucion } </td> -->
			 <fmt:parseDate pattern="dd-MM-yyyy" value="${sorteo.fechaEjecucion}" var="date" />
			 <td><fmt:formatDate value="${date}" type="date" dateStyle = "short" timeStyle="short" /></td>
			 <td> - </td> 
			 <td> </td> 
			 </c:if>
		</tr> 
	</c:forEach>
</table>

<input type="button" class="normal button" onclick="jSorteos.nuevoSorteo()" value="<fmt:message key="agregar_sorteo" bundle="${etq}"></fmt:message>">
<input type="button" class="normal button" onclick="jSorteos.eliminarSorteos()" value="<fmt:message key="eliminar" bundle="${etq}"></fmt:message>">
