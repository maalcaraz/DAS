<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<c:set var="cliente" scope="request" value="${requestScope.cliente}"></c:set>
<c:set var="adquiridos" scope="request"
	value="${requestScope.adquiridos}"></c:set>

<h2> Estado de cuenta </h2>
<table>
		<c:forEach items="${adquiridos}" var="adquirido">
				<tr><td><b>Plan</b></td><td>${ adquirido.idPlan }</td> </tr>
				<tr><td><b>Concesionaria</b></td> <td>${ adquirido.idConcesionaria  }</td> </tr>
				<tr><td><b>Nro. chasis</b></td><td>${ adquirido.getItem("nroChasis")  }</td> </tr>
				<tr><td><b>Fecha entrega</b></td> <td>${ adquirido.getItem("fechaEntrega")  }</td> </tr>
				<tr><td><b>Cancelado</b></td> <td>${ adquirido.cancelado  }</td> </tr>
				<tr><td><b>Cant cuotas</b></td> <td>${ adquirido.getItem("cant_cuotas") }</td> </tr>
				<tr><td><b>Cuotas pagas</b></td> <td>${ adquirido.getItem("cuotas_pagas")  }</td> </tr>
      </c:forEach>
</table>