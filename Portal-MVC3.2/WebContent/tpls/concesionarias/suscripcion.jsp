<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
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
<label for="nombreConcesionaria">Nombre de la concesionaria: </label>
<input type="text" id="nombreConcesionaria" name="nombreConcesionaria" autofocus required>

<label for="tipoServicio">Tipo de Servicio: </label>
	<select id="tipoServicio" name="tipoServicio">
		<option value="R">Rest </option>
		<option value="C"> CXF </option>
		<option value="A">Axis </option>
	</select>
	
<br>
<label for="url">Url: </label>
<input type="text" id="url" name="url" autofocus required>

<label for="cuit">Cuit: </label>
<input type="text" id="cuit" name="cuit" autofocus required>

<label for="emailConcesionaria">Email: </label>
<input type="text" id="emailConcesionaria" name="emailConcesionaria" autofocus required>

<label for="direccionConcesionaria">Direccion: </label>
<input type="text" id="direccionConcesionaria" name="direccionConcesionaria" autofocus required>

<label for="telefonoConcesionaria">Telefono: </label>
<input type="text" id="telefonoConcesionaria" name="telefonoConcesionaria" autofocus required>

	<br>
	<div class="container" style="background-color:#f1f1f1">
	  <button type="button" name="suscript" onclick="jConcesionaria.insertarConcesionaria()">Suscribir</button>
      <button type="button" name="cancelbtn" onclick="document.getElementById('id02').style.display='none'" class="cancelbtn">Cancel</button>
    </div>
</form>
</div>

</div>


<script type="text/javascript">document.getElementById('id02').style.display='block'</script>
</body>
</html>