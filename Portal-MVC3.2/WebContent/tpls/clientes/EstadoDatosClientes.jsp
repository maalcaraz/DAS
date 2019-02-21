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
					  
					  <div class="row">
						<div class="col-lg-2">

						  <select id="selectFilter" name="column" class="form-control">
							<option value="td1">
								<fmt:message key="concesionaria" bundle="${etq}"></fmt:message>
							</option>
							<option value="td2">
								<fmt:message key="dni" bundle="${etq}"></fmt:message>
							</option>
							<option value="td3">
								<fmt:message key="nombre_y_apellido" bundle="${etq}"></fmt:message>
							</option>
							<option value="td4">
								<fmt:message key="edad" bundle="${etq}"></fmt:message>
							</option>
							<option value="td5">
								<fmt:message key="domicilio" bundle="${etq}"></fmt:message>
							</option>
							<option value="td6">
								<fmt:message key="email" bundle="${etq}"></fmt:message>
							</option>
							<option value="td7">
								<fmt:message key="id_plan" bundle="${etq}"></fmt:message>
							</option>
							<option value="td8">
								<fmt:message key="cuotas" bundle="${etq}"></fmt:message>
							</option>
							<option value="td9">
								<fmt:message key="cuotas_sin_pagar" bundle="${etq}"></fmt:message>
							</option>
							<option value="td9">
								<fmt:message key="cuotas_pagas" bundle="${etq}"></fmt:message>
							</option>
							<option value="td10">
								<fmt:message key="cuotas_sin_pagar" bundle="${etq}"></fmt:message>
							</option>
							<option value="td11">
								<fmt:message key="nro_chasis" bundle="${etq}"></fmt:message>
							</option>
							<option value="td12">
								<fmt:message key="fecha_entrega" bundle="${etq}"></fmt:message>
							</option>
							<option value="td13">
								<fmt:message key="cancelado" bundle="${etq}"></fmt:message>
							</option>
							<option value="td14	">
								<fmt:message key="gano_sorteo" bundle="${etq}"></fmt:message>
							</option>
						</select>
					  </div>
					  <div class="col-lg-10">
					  	<input class="form-control" id="myInput" type="text" placeholder="Search..">
					  </div>
					 </div>
					 
					  <br>
					  <table class="table table-bordered table-striped table-advance">
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
											<td id="td1">${ cliente.getItem("nombreConcesionaria") }</td>
											<td id="td2">${ cliente.dniCliente }</td>
											<td id="td3">${ cliente.nomCliente }</td>
											<td id="td4">${ cliente.edad }</td>
											<td id="td5">${ cliente.domicilio }</td>
											<td id="td6">${ cliente.emailCliente }</td>
											<td id="td7">${ cliente.getItem("idPlan") }</td>
											<td id="td8">${ cliente.getItem("cantCuotas") }</td>
											<td id="td9">${ cliente.getItem("cuotasPagas") }</td>
											<td id="td10">${ cliente.getItem("cuotasSinPagar") }</td>
											<td id="td11">${ cliente.getItem("nroChasis") }</td>
											<c:if test="${ cliente.getItem('fechaEntrega') eq '-' }">
												<td>${ cliente.getItem("fechaEntrega") }</td>
											</c:if>
											<c:if test="${ cliente.getItem('fechaEntrega') ne '-'}">
												<fmt:parseDate pattern="dd-MM-yyyy" value="${ cliente.getItem('fechaEntrega') }" var="date" />
												<td id="td12"><fmt:formatDate value="${date}" type="date" dateStyle = "short" timeStyle="short" /></td>
											</c:if>
											<td id="td13">${ cliente.getItem("cancelado") }</td>
											<td id="td14">${ cliente.getItem("ganador") }</td>
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
					$(this).toggle($(this).find("td[id="+$("#selectFilter").val()+"]").text().toLowerCase().indexOf(value) > -1)
					});
				});
			});
		 </script>

