<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<c:set var="cliente" scope="request" value="${requestScope.cliente}"></c:set>
<c:set var="adquiridos" scope="request"
	value="${requestScope.adquiridos}"></c:set>

<h2> Datos Personales </h2>

<table>
	<tr><td><b>Dni Cliente</b></td> <td>${ cliente.dniCliente }</td> </tr>
	<tr><td><b>Nombre y Apellido</b></td> <td>${ cliente.nomCliente }</td> </tr>
	<tr><td><b>Edad</b></td> <td>${ cliente.edad }</td></tr>
	<tr><td><b>Domicilio</b></td> <td>${ cliente.domicilio }</td></tr>
	<tr><td><b>Email</b></td> <td>${ cliente.emailCliente }</td></tr>
	<tr><td><b>Fecha ultima actualizacion datos</b></td> <td>${ cliente.getItem("fecha_ult_transaccion") }</td></tr>
</table>