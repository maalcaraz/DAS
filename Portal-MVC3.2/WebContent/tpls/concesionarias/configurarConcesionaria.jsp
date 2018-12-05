
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<h3> Configurar concesionaria ${ requestScope.idConcesionaria }</h3>

<form id="nuevaConfig">
	
	
		
	
	<label for="cuit">Cuit: </label>
	<input type="text" name="cuit" autofocus required>
	
	<label for="emailConcesionaria">Email: </label>
	<input type="text" name="emailConcesionaria" autofocus required>
	
	<label for="dir">Direccion: </label>
	<input type="text" name="dir" autofocus required>
	
	<label for="telefonoConcesionaria">Telefono: </label>
	<input type="text" name="telefonoConcesionaria" autofocus required>

</form>

<input type="button" class="normal button" value="Guardar Cambios" onclick="jConcesionaria.guardarCambios()">
<input type="button" value="Cancelar">
