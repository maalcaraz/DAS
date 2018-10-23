
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<h3> Configurar concesionaria ${ requestScope.idConcesionaria }</h3>

<form id="nuevaConfig">
	
	<label for="tipoServicio">Tipo de Servicio: </label>
		<select name="tipoServicio">
			<option value="Rest"> Rest </option>
			<option value="CXF"> CXF </option>
			<option value="Axis2"> Axis </option>
		</select>
		
	<br>
	<label for="url">Url: </label>
	<input type="text" name="url" autofocus required>
	
	<label for="cuit">Cuit: </label>
	<input type="text" name="cuit" autofocus required>
	
	<label for="emailConcesionaria">Email: </label>
	<input type="text" name="emailConcesionaria" autofocus required>
	
	<label for="direccionConcesionaria">Direccion: </label>
	<input type="text" name="direccionConcesionaria" autofocus required>
	
	<label for="telefonoConcesionaria">Telefono: </label>
	<input type="text" name="telefonoConcesionaria" autofocus required>

</form>

<input type="button" value="Guardar Cambios" onclick="jConcesionaria.guardarCambios()">
<input type="button" value="Cancelar">
