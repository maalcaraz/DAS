<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script type="text/javascript" src="./js/jquery.js"></script>
<script type="text/javascript" src="./js/utils.js"></script>
<script type="text/javascript" src="./js/test.js"></script>
<title>Testing de Servicio REST</title>
</head>
<body>

<h1>Seleccione el servicio a consumir</h1>

<button id="obtener" onclick="jTest.obtener()" name="obtener"> Obtener Datos de Clientes </button>
<br>
<button id="notificar" onclick="jTest.notificar()" name="notificar"> Notificar ganador </button>
<br>
<button id="verificar" onclick="jTest.verificar()" name="verificar"> Verificar cancelado </button>

<br>
<div id="message"></div>
<div id="result"></div>

<script type="text/javascript">jTest.consumir()</script>

</body>
</html>