<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

	<c:set var="mensaje" scope="request" value="${ requestScope.mensaje }"> </c:set>	
		<h4>
		<c:if test="${ mensaje eq 'Conectado' }">
			<span class="label label-success">
		</c:if>
		<c:if test="${ mensaje ne 'Conectado' }">
			<span class="label label-danger ">
		</c:if>
		${ mensaje }</span>
		</h4>