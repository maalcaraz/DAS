<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

</head>
<body>

<h1> Insercion de un nuevo sorteo </h1>
<form id="formSorteo">

Nueva Fecha <input type="date" name="nuevaFecha" id="nuevaFecha">

<br>(Habria que validar la fecha segun los requerimientos de la profe: dia habil, no feriado y bla bla)<br>
<input type="button" value="Guardar" onclick="jSorteos.insertar()"> 
</form>
</body>
</html>