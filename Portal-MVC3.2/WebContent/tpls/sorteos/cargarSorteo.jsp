<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

</head>
<body>

<h2> Insercion de un nuevo sorteo </h2>
<form id="formSorteo">

Nueva Fecha <input type="text" name="nuevaFecha" id="nuevaFecha">
Formato de fecha: dd-MM-YYYY
<input type="button" value="Guardar" onclick="jSorteos.insertar()"> 
</form>
</body>
</html>