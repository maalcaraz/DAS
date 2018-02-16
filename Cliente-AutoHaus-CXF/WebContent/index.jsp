<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script type="text/javascript" src="./js/jquery.js"></script>
<script type="text/javascript" src="./js/servicio.js"></script>
<script type="text/javascript" src="./js/utils.js"></script>
<title>Probando un cliente CXF </title>
</head>
<body>

<input type="button" value="Buscar datos de clientes" onclick="jServicio.buscarClientes()" />

<div id="result" type="hidden" > </div>
<div id="message" > </div>

</body>
</html>