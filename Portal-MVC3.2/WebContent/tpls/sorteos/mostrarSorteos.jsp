
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<fmt:setLocale value="${ sessionScope.lang }" scope="session" />

<fmt:setBundle basename="ar.edu.ubp.das.src.portal.properties.messages" var="etq" />
	
<section class="wrapper">
	<div class="row">
		<div class="col-lg-12">
			<h3 class="page-header">
				<i class="icon_calendar"></i>
				<fmt:message key="fechas" bundle="${etq}"></fmt:message>
			</h3>
			<ol class="breadcrumb">
				<li><i class="fa fa-home"></i><a href="/home/Home.do"><fmt:message key="home" bundle="${etq}"></fmt:message></a></li>
				<li><i class="fa fa fa-ticket"></i>
				<fmt:message key="sorteos" bundle="${etq}"></fmt:message></li>
				<li><i class="icon_calendar"></i>
				<fmt:message key="fechas" bundle="${etq}"></fmt:message></li>
				
			</ol>
			<input type="button" id="agregar_sorteo" name="agregar_sorteo" class="btn btn-info" onclick="jSorteos.nuevoSorteo('<fmt:message key="guardar" bundle="${etq}"> </fmt:message>', '<fmt:message key="cancelar" bundle="${etq}"></fmt:message>')" value="<fmt:message key="agregar_sorteo" bundle="${etq}"></fmt:message>">
			<br>
			<br>
		</div>
	</div>
	<!-- page start-->
	<div class="main" id="contenido-admin">

		<c:set var="sorteos" scope="request" value="${requestScope.sorteos}"> </c:set>
		
		<div class="row" id="nuevos-sorteos">
		  <br>
		      <c:forEach var="sorteo" items="${ sorteos }" varStatus="status">
		      
		      <div class="col-lg-4" >
	
	
				<c:if test="${ sorteo.fechaEjecucion ne '-' }">
					<section class="panel panel-success"  id="${ sorteo.idSorteo }">
				</c:if>
				<c:if test="${ sorteo.fechaEjecucion eq '-' }">
					<section class="panel panel-info"  id="${ sorteo.idSorteo }">
				</c:if>
			       <header class="panel-heading">
			       
			       <fmt:message key="sorteo_de_la_fecha" bundle="${etq}"></fmt:message> 
			       
			       <fmt:parseDate pattern="dd-MM-yyyy" value="${sorteo.fechaDefinida}" var="date" />
				   <fmt:formatDate value="${date}" type="date" dateStyle="short" timeStyle="short" />
			       
			       </header>
			              <div class="panel-body">
			                <div class="panel panel-primary">
			                  <div class="panel-content">
			                  
			                  <fmt:message key="estado" bundle="${etq}"></fmt:message>: 
			                  	<c:if test="${ sorteo.fechaEjecucion eq '-' }">
			                  		<fmt:message key="pendiente" bundle="${etq}"></fmt:message><br>
									<fmt:message key="fecha_ejecucion" bundle="${etq}"></fmt:message>: - <br>
									<fmt:message key="fecha_notificacion" bundle="${etq}"></fmt:message>: - <br>
									
				                	<a href="javascript:;" class="dropdown-toggle" data-toggle="dropdown"><fmt:message key="opciones" bundle="${etq}"></fmt:message> </a>
					            	<ul class="dropdown-menu">
				                      <li><a href="#" onclick="jSorteos.editarSorteo('${ sorteo.idSorteo }')"><fmt:message key="editar" bundle="${etq}"></fmt:message></a></li>
				                      <li><a href="#" onclick="jSorteos.eliminarSorteo('${ sorteo.idSorteo }')"><fmt:message key="eliminar" bundle="${etq}"></fmt:message></a></li>
				                    </ul>
							 	</c:if>
			                 	<c:if test="${ sorteo.fechaEjecucion ne '-'}">
			                 	<fmt:message key="ejecutado" bundle="${etq}"></fmt:message> <br>
			                 		<fmt:message key="fecha_ejecucion" bundle="${etq}"></fmt:message>: 
									<fmt:parseDate pattern="dd-MM-yyyy" value="${sorteo.fechaEjecucion}" var="date" />
									<fmt:formatDate value="${date}" type="date" dateStyle="short" timeStyle="short" />
									<br>
									<c:if test="${ sorteo.fechaNotificacion ne '-'}">
										<fmt:message key="fecha_notificacion" bundle="${etq}"></fmt:message>: 
										<fmt:parseDate pattern="dd-MM-yyyy" value="${sorteo.fechaNotificacion}" var="date" />
											<fmt:formatDate value="${date}" type="date" dateStyle="short" timeStyle="short" />
											<br>
									</c:if>
									<c:if test="${ sorteo.fechaNotificacion eq '-'}">
										<fmt:message key="fecha_notificacion" bundle="${etq}"></fmt:message>:	 -
									</c:if>
									<a onclick="jSorteos.verResultados('${ sorteo.idSorteo }')" href="#"> <fmt:message key="ver_resultados" bundle="${etq}"></fmt:message> </a>
								</c:if>
			                </div>
			              	</div>
			                
			              </div>
			    </section>
				</div>
		      </c:forEach>
		    </div>
		    <div class="row">
		      <div class="col-lg-12" id="resultadosSorteo"></div>
		    </div>
	</div>
	<br>
	
</section>