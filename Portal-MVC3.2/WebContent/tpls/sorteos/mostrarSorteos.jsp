
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
		<tr> 
			 <td> <input type="checkbox" id=${ sorteo.idSorteo } value=${ sorteo.idSorteo } name=${ sorteo.idSorteo }> </td>
			 <td> ${ sorteo.fechaSorteado }  </td> 
			 <td> ${ sorteo.fechaProximo } </td> 
			 <td> - </td> 
			 <td> 
			 <input type="button" onclick="jSorteos.editarSorteo('${ sorteo.idSorteo }')" value="Editar"> 
			 </td> 
		</tr> 
	</c:forEach>
</table>

<input type="button" onclick="jSorteos.nuevoSorteo()" value="Agregar Sorteo">
<input type="button" onclick="jSorteos.eliminarSorteos()" value="Eliminar">
<input type="button" onclick="jSorteos.insertar()" value="Guardar">
