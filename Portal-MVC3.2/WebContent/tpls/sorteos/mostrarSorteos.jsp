<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<h2>Sorteos </h2>
<c:set var="sorteos" scope="request" value="${requestScope.sorteos}"> </c:set>

<table id="tablaSorteos" border=1> <thead>
	<tr>  
		<td> </td> 
		<td>Fecha definida</td> 
		<td>Fecha de ejecucion</td> 
		<td>Fecha de notificacion</td>
		<td> </td>
	</tr> </thead>
	<c:forEach var="sorteo" items="${ sorteos }" varStatus="status">
		<tr id="${ sorteo.idSorteo }"> 
			 <c:if test="${ sorteo.fechaEjecucion eq '-' }">
				 <td>
				  <input type="checkbox" id="cb"${ sorteo.idSorteo } value=${ sorteo.idSorteo } name=${ sorteo.idSorteo }>
				 </td>
				 <td> ${ sorteo.fechaSorteado }  </td> 
				 <td> ${ sorteo.fechaEjecucion } </td> 
				 <td> - </td> 
				 <td> 
				 <input type="button" class="normal button" onclick="jSorteos.editarSorteo('${ sorteo.idSorteo }')" value="Editar"> 
				  </td> 
			 
			 </c:if>
			 <c:if test="${ sorteo.fechaEjecucion ne '-'}">
			 <td></td>
			 <td> ${ sorteo.fechaSorteado }  </td> 
			 <td> ${ sorteo.fechaEjecucion } </td> 
			 <td> - </td> 
			 <td> </td> 
			 </c:if>
		</tr> 
	</c:forEach>
</table>

<input type="button" class="normal button" onclick="jSorteos.nuevoSorteo()" value="Agregar Sorteo">
<input type="button" class="normal button" onclick="jSorteos.eliminarSorteos()" value="Eliminar">
