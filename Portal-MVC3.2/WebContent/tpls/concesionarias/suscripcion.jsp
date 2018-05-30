<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<script type="text/javascript" src="/util/Javascript.do/load=jquery,jquery.i18n.properties,utils,cuentas,login" ></script>
	<link type="text/css" rel="stylesheet" href="/util/StyleSheet.do/load=home,page,messages,login" />
</head>
<body>
<div id="id02" class="modal">

<div class="container">
<form class="modal-content animate" id="formSuscripcion">
<label for="nombreConcesionaria">Nombre de la concesionaria: </label>
<input type="text" id="nombreConcesionaria" name="nombreConcesionaria" autofocus required>

<label for="tipoServicio">Tipo de Servicio: </label>
	<select>
		<option id="rest" value="rest">Rest </option>
		<option id="cxf" value="cxf"> CXF </option>
		<option id="axis" value="axis">Axis </option>
	</select>
	
	<br>
	<div class="container" style="background-color:#f1f1f1">
	  <button type="button" name="suscript" onclick="jLogin.acceder()">Login</button>
      <button type="button" name="cancelbtn" onclick="document.getElementById('id02').style.display='none'" class="cancelbtn">Cancel</button>
    </div>
</form>
</div>

</div>


<script type="text/javascript">document.getElementById('id02').style.display='block'</script>
</body>
</html>