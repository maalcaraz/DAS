<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<fmt:setLocale value="${ sessionScope.lang }" scope="session" />
<fmt:setBundle basename="ar.edu.ubp.das.src.portal.properties.messages"
	var="etq" />
	
	<section class="wrapper">
	        
	        <div class="row">
				<div class="col-lg-12">
					<h3 class="page-header">
						<i class="fa fa fa-users"></i>
						<fmt:message key="clientes" bundle="${etq}"></fmt:message>
					</h3>
					<ol class="breadcrumb">
						<li><i class="fa fa-home"></i><a href="/home/Home.do"><fmt:message key="home" bundle="${etq}"></fmt:message></a></li>
						<li><i class="fa fa-users"></i>
						<fmt:message key="clientes" bundle="${etq}"></fmt:message></li>
						<!-- 
			              <li><i class="fa fa-square-o"></i>Pages</li>
			              -->
					</ol>
				</div>
			</div>
	        <!-- page start-->
	        <div class="main" id="contenido-admin">
	        
	        	<c:set var="concesionarias" scope="request"
						value="${ requestScope.concesionarias}">
					</c:set>
					
					<c:set var="hay_clientes" scope="request"
						value="${ requestScope.hay_clientes}">
					</c:set>
					
					<c:if test="${hay_clientes}">
					
					  <p><fmt:message key="busqueda_cliente_placeholder" bundle="${etq}"></fmt:message></p>  
					  <input class="form-control" id="myInput" type="text" placeholder="Search..">
					  <br>
					  <table class="table table-bordered table-striped">
					    <thead>
					      <tr>
								<th><fmt:message key="concesionaria" bundle="${etq}"></fmt:message></th>
								<th><fmt:message key="dni" bundle="${etq}"></fmt:message></th>
								<th><fmt:message key="nombre_y_apellido" bundle="${etq}"></fmt:message></th>
								<th><fmt:message key="edad" bundle="${etq}"></fmt:message></th>
								<th><fmt:message key="domicilio" bundle="${etq}"></fmt:message></th>
								<th><fmt:message key="email" bundle="${etq}"></fmt:message></th>
								<th><fmt:message key="id_plan" bundle="${etq}"></fmt:message></th>
								<th><fmt:message key="cuotas" bundle="${etq}"></fmt:message></th>
								<th><fmt:message key="cuotas_pagas" bundle="${etq}"></fmt:message></th>
								<th><fmt:message key="cuotas_sin_pagar" bundle="${etq}"></fmt:message></th>
								<th><fmt:message key="nro_chasis" bundle="${etq}"></fmt:message></th>
								<th><fmt:message key="fecha_entrega" bundle="${etq}"></fmt:message></th>
								<th><fmt:message key="cancelado" bundle="${etq}"></fmt:message></th>
								<th><fmt:message key="gano_sorteo" bundle="${etq}"></fmt:message></th>
					      </tr>
					    </thead>
					    <tbody id="myTable">
							<c:forEach var="concesionaria" items="${ concesionarias }">
					
									<c:forEach var="cliente" items="${ concesionaria.clientes }">
										<tr>
											<td>${ cliente.getItem("nombreConcesionaria") }</td>
											<td>${ cliente.dniCliente }</td>
											<td>${ cliente.nomCliente }</td>
											<td>${ cliente.edad }</td>
											<td>${ cliente.domicilio }</td>
											<td>${ cliente.emailCliente }</td>
											<td>${ cliente.getItem("idPlan") }</td>
											<td>${ cliente.getItem("cantCuotas") }</td>
											<td>${ cliente.getItem("cuotasPagas") }</td>
											<td>${ cliente.getItem("cuotasSinPagar") }</td>
											<td>${ cliente.getItem("nroChasis") }</td>
											<c:if test="${ cliente.getItem('fechaEntrega') eq '-' }">
												<td>${ cliente.getItem("fechaEntrega") }</td>
											</c:if>
											<c:if test="${ cliente.getItem('fechaEntrega') ne '-'}">
												<fmt:parseDate pattern="dd-MM-yyyy" value="${ cliente.getItem('fechaEntrega') }" var="date" />
												<td><fmt:formatDate value="${date}" type="date" dateStyle = "short" timeStyle="short" /></td>
											</c:if>
											<td>${ cliente.getItem("cancelado") }</td>
											<td>${ cliente.getItem("ganador") }</td>
										</tr>
									</c:forEach>
								</c:forEach>
					    </tbody>
					  </table>
	
					</c:if>
					
					<c:if test="${not hay_clientes}">
					<fmt:message key="no_hay_clientes" bundle="${etq}"></fmt:message>
					</c:if>			        	
	        	

			</div>
	        <!-- page end-->
	      </section>
	      
	      <script>
			$(document).ready(function(){
			$("#myInput").on("keyup", function() {
				var value = $(this).val().toLowerCase();
				$("#myTable tr").filter(function() {
					$(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
					});
				});
			});
			</script>

