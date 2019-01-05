<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<head>
<link type="text/css" rel="stylesheet"
	href="/util/StyleSheet.do/load=admin" />
</head>

<fmt:setLocale value="${ sessionScope.lang }" scope="session"/>
<fmt:setBundle basename="ar.edu.ubp.das.src.portal.properties.messages" var="etq"/>

<c:set var="concesionarias" scope="request"	value="${requestScope.concesionarias}"></c:set>
<c:set var="pendientes" scope="request"	value="${requestScope.pendientes}"></c:set>


	      <section class="wrapper">
			<div class="row">
					<div class="col-lg-12">
						<h3 class="page-header">
							<i class="fa fa fa-car"></i>
							<fmt:message key="concesionarias_registradas" bundle="${etq}"></fmt:message>
						</h3>
						<ol class="breadcrumb">
							<li><i class="fa fa-home"></i><a href="/home/Home.do">Home</a></li>
							<li><i class="fa fa-car"></i>
							<fmt:message key="concesionarias_registradas" bundle="${etq}"></fmt:message></li>
							<!-- 
				              <li><i class="fa fa-square-o"></i>Pages</li>
				              -->
						</ol>
					</div>
			</div>
	        <!-- page start-->
	        <div class="main" id="contenido-admin">
	        
	        <div>
				<c:if test="${!empty concesionarias}">
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

			</div>
	        <!-- page end-->
	      </section>



