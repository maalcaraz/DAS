<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<fmt:setLocale value="${ sessionScope.lang }" scope="session"/>
<fmt:setBundle basename="ar.edu.ubp.das.src.portal.properties.messages" var="etq"/>

<c:set var="concesionarias" scope="request"	value="${requestScope.concesionarias}"></c:set>
<c:set var="pendientes" scope="request"	value="${requestScope.pendientes}"></c:set>

<br>
	<h2><fmt:message key="concesionarias_registradas" bundle="${etq}"></fmt:message></h2> <br><br> 
	<div>
	<c:if test="${!empty concesionarias}">
	<h2><fmt:message key="concesionarias_adheridas" bundle="${etq}"></fmt:message></h2> <br><br> 
		<c:forEach var="concesionaria" items="${ concesionarias }" varStatus="status">
		
			<div class="grid-item">
			<h3>${ concesionaria.nomConcesionaria } </h3>
				<b><fmt:message key="tipo_de_servicio" bundle="${etq}"></fmt:message></b>: ${ concesionaria.codTecnologia } <br>
				<b><fmt:message key="url" bundle="${etq}"></fmt:message></b>: ${ concesionaria.urlServicio } <br>
				<b><fmt:message key="direccion" bundle="${etq}"></fmt:message></b>: ${ concesionaria.direccion } <br>
				<b><fmt:message key="telefono" bundle="${etq}"></fmt:message></b>: ${ concesionaria.telefono } <br>
				<b><fmt:message key="email" bundle="${etq}"></fmt:message></b>: ${ concesionaria.email } <br>
				<fmt:parseDate pattern="dd-MM-yyyy" value="${ concesionaria.ultimaActualizacion } " var="date" />
				<b><fmt:message key="ultima_actualizacion" bundle="${etq}"></fmt:message></b>
					: <fmt:formatDate value="${date}" type="date" dateStyle = "short" timeStyle="short" /> <br> 
			</div> 
		</c:forEach>
	</c:if>
	<c:if test="${empty concesionarias}">
		<h3><fmt:message key="no_hay_concesionarias_aprobadas" bundle="${etq}"></fmt:message></h3>
	</c:if>
	</div>

