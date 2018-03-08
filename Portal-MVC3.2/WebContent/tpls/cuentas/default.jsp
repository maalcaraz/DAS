<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<html>
	<head>
		<script type="text/javascript" src="/util/Javascript.do/load=jquery,jquery.i18n.properties,utils,cuentas,bootstrap.min,login" ></script>
		<link type="text/css" rel="stylesheet" href="/util/StyleSheet.do/load=page,messages,bootstrap.min," />
		
		<title>Arranque</title>
	</head>
	<body>
		
		<h1> Bienvenido al portal del gobierno </h1>
		<h3> (Aca iria el login?) </h3>
		<div id="message"></div>
		<div id="response"></div>
		<div id="main">
			<form id="form" action="javascript:void(null)" method="post" onsubmit="jCuentas.buscarClientes()">
				<input type="button" value="Buscar datos de clientes" onclick="jCuentas.buscarClientes()" />
				<br/>
				<div id="result"></div>
			</form>
		</div>
		
	</body>
</html>