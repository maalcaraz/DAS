
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<form id="nuevoSorteoForm" name="nuevoSorteoForm">

<c:set var="idSorteo" scope="request"	value="${requestScope.idSorteo}"></c:set>

<input type="hidden" value="${idSorteo}" id="idSorteo" name="idSorteo"/>

Nueva fecha: 
<input type="text" name="nuevaFecha">

Nueva descripcion: 
<input type="text" name="nuevaDescripcion" name="nuevaDescripcion">
<br>
<input type="button" value="Guardar Cambios" onclick="jSorteos.guardarSorteo()">
<input type="button" value="Cancelar" >

</form>