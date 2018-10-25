<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<script type="text/javascript" src="/util/Javascript.do/load=jquery,jquery.i18n.properties,utils,cuentas,login,concesionarias,sorteos" ></script>
	<link type="text/css" rel="stylesheet" href="/util/StyleSheet.do/load=admin,home,page,messages" />	
</head>
<body>

<h1> Error </h1>


<c:set var="mensajeError" scope="request"	value="${requestScope.error}"></c:set>

<h2> Se ha detectado el siguiente error: ${ mensajeError } </h2>

</body>