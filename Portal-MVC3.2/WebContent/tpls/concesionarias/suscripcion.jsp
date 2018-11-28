<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
	
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<script type="text/javascript" src="/util/Javascript.do/load=jquery,jquery.i18n.properties,utils,cuentas,login,concesionarias" ></script>
	<link type="text/css" rel="stylesheet" href="/util/StyleSheet.do/load=home,page,messages,login" />
</head>
<body>
<div id="id02" class="modal">

<div class="container">
<form class="modal-content animate" id="formSuscripcion">
<h2><fmt:message key="suscripcion_de_concesionaria" bundle="${etq}"></fmt:message></h2>
<br>
<label for="nombreConcesionaria"><fmt:message key="nombre_de_la_concesionaria" bundle="${etq}"></fmt:message>: </label>
<input type="text" id="nombreConcesionaria" name="nombreConcesionaria" autofocus required>
<br><br>
<label for="tipoServicio"><fmt:message key="tipo_de_servicio" bundle="${etq}"></fmt:message>: </label>
	<select id="tipoServicio" name="tipoServicio">
		<option value="Rest"> Rest </option>
		<option value="CXF"> CXF </option>
		<option value="Axis2"> Axis </option>
	</select>
	
<br>
<br>
<label for="url"><fmt:message key="url" bundle="${etq}"></fmt:message>: </label>
<input type="text" id="url" name="url" autofocus required>
<br>
<label for="cuit"><fmt:message key="cuit" bundle="${etq}"></fmt:message>: </label>
<input type="text" id="cuit" name="cuit" autofocus required>
<br>
<label for="emailConcesionaria"><fmt:message key="email" bundle="${etq}"></fmt:message>: </label>
<input type="text" id="emailConcesionaria" name="emailConcesionaria" autofocus required>
<br>
<label for="direccionConcesionaria"><fmt:message key="direccion" bundle="${etq}"></fmt:message>: </label>
<input type="text" id="direccionConcesionaria" name="direccionConcesionaria" autofocus required>
<br>
<label for="telefonoConcesionaria"><fmt:message key="telefono" bundle="${etq}"></fmt:message>: </label>
<input type="text" id="telefonoConcesionaria" name="telefonoConcesionaria" autofocus required>

	<br>
	<div class="container" style="background-color:#f1f1f1">
	  <button type="button" name="suscript" class="button suscribir" onclick="jConcesionaria.insertarConcesionaria()"><fmt:message key="suscribir" bundle="${etq}"></fmt:message></button>
      <button type="button" name="cancelbtn" onclick="jLogin.cancelar(0, 'id02')" class="cancelbtn"><fmt:message key="cancelar" bundle="${etq}"></fmt:message></button>
    </div>
</form>
</div>

</div>


<script type="text/javascript">document.getElementById('id02').style.display='block'</script>
</body>
</html>