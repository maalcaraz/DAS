
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<c:set var="sorteos" scope="request" value="${requestScope.sorteos}"> </c:set>

<table id="tablaSorteos" border=1>
	<tr>  <td> </td> <td> Id Sorteo : </td> <td>  Fecha Sorteo	</td> </tr>
	<c:forEach var="sorteo" items="${ sorteos }" varStatus="status">
		<tr> 
			 <td> <input type="checkbox" id=${ sorteo.idSorteo } value=${ sorteo.idSorteo } name=${ sorteo.idSorteo }> </td>
			 <td> ${ sorteo.idSorteo }</td> 
			 <td> ${ sorteo.fechaSorteado }  </td> 
			 <td> <input type="button" onclick="jSorteos.editarSorteo('${ sorteo.idSorteo }')" value="Editar"> </td> 
		</tr> 
	</c:forEach>
</table>

<input type="button" onclick="jSorteos.eliminarSorteos()" value="Eliminar">
