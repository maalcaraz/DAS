<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

</head>
<body>

<c:set var="sorteos" scope="request" value="${requestScope.sorteos}"> </c:set>

<table>
	<c:forEach var="sorteo" items="${ sorteos }" varStatus="status">
		<tr> <td> Id Sorteo : </td> <td>  ${ sorteo.idSorteo } 			</td>
			 <td> Fecha Sorteo :  </td> <td> ${ sorteo.fechaSorteado }  </td> 
		</tr> 
	</c:forEach>
</table>

</body>
</html>