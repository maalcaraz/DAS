<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<br>
<h2>Sorteos </h2>
<c:set var="sorteos" scope="request" value="${requestScope.sorteos}"> </c:set>

<table id="tablaSorteos" border=1>
	<thead><tr> <td> Id Sorteo  </td> <td>  Fecha Sorteo	</td> </tr></thead>
	<tbody>
	<c:forEach var="sorteo" items="${ sorteos }" varStatus="status">
		<tr> 
			 <td> ${ sorteo.idSorteo }</td> 
			 <td> ${ sorteo.fechaSorteado }  </td> 
		</tr> 
	</c:forEach>
	</tbody>
</table>

