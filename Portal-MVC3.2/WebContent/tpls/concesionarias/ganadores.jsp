<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<fmt:setLocale value="${ sessionScope.lang }" scope="session" />
<fmt:setBundle basename="ar.edu.ubp.das.src.portal.properties.messages" var="etq" />


	      <section class="wrapper">
	        <div class="row">
	          <div class="col-lg-12">
	            <h3 class="page-header">
						<i class="fa fa fa-users"></i>
						<fmt:message key="ganadores" bundle="${etq}"></fmt:message>
					</h3>
	            <ol class="breadcrumb">
						<li><i class="fa fa-home"></i><a href="/home/Home.do"><fmt:message key="home" bundle="${etq}"></fmt:message></a></li>
						<li><i class="fa fa-users"></i>
						<fmt:message key="ganadores" bundle="${etq}"></fmt:message></li>
					</ol>
	          </div>
	        </div>
	        <!-- page start-->
	        <div class="main" id="contenido-admin">
	        
	        	<c:set var="ganadores" scope="request" value="${requestScope.ganadores}">
				</c:set>
				
				<c:if test="${not empty ganadores}">
				
				<fmt:message key="frase_ganadores" bundle="${etq}"></fmt:message>
				
				<table class="table table-striped table-advance table-hover">
                <tbody id="tablaGanadores">
                  <tr>
                    <th><i class="icon_calendar"></i> <fmt:message key="fecha_de_sorteo" bundle="${etq}"></fmt:message> </th>
                    <th><i class="icon_profile"></i> <fmt:message key="nombre_y_apellido" bundle="${etq}"></fmt:message> </th>
                    <th><i class="icon_key_alt"></i> <fmt:message key="concesionaria" bundle="${etq}"></fmt:message></th>
                    <th><i class="icon_id"></i> <fmt:message key="dni" bundle="${etq}"></fmt:message></th>
                  </tr>
				
					<c:forEach var="ganador" items="${ ganadores }" varStatus="status">
						<tr>
							
							<fmt:parseDate pattern="dd-MM-yyyy" value="${ ganador.getItem('fechaSorteo') }" var="date" />
							<td><fmt:formatDate value="${date}" type="date" dateStyle = "short" timeStyle="short" /></td>
							<td>${ ganador.getItem('apellidoNombre') }</td>
							<td>${ ganador.getItem('nombreConcesionaria') }</td>
							<td>${ ganador.getItem('dni') }</td>
						</tr>
					</c:forEach>
					</tbody>
				</table>
				</c:if>
				<c:if test="${empty ganadores}">
							<h3><fmt:message key="no_hay_sorteos" bundle="${etq}"></fmt:message></h3>
				</c:if>

			</div>
	        <!-- page end-->
	      </section>
