<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<br>
<h2>Resultados ultimo sorteo</h2>
<c:set var="sorteo" scope="request" value="${requestScope.sorteo}"> </c:set>


    <table id="tablaSorteos" border=1>
	<thead><tr><td>Fecha Sorteo</td><td> Nombre</td><td>Nombre Concesionaria</td></tr></thead>
	<tbody>
		<tr>
		<c:if test="${not empty sorteo}">
			 <td> ${ sorteo.fechaSorteado }</td> 
			 <td> ${ sorteo.apellidoNombre }  </td>
			 <td> ${ sorteo.nombreConcesionaria }  </td>
		</c:if>
		<c:if test="${empty sorteo}">
			 <td> No hay sorteos ejecutados </td> 
			 <td> No hay sorteos ejecutados </td> 
			 <td> No hay sorteos ejecutados </td> 
		</c:if>
		
		</tr> 
	</tbody>
	</table>


