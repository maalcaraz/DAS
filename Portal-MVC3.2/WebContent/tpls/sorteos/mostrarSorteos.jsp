
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<fmt:setLocale value="${ sessionScope.lang }" scope="session" />
<fmt:setBundle basename="ar.edu.ubp.das.src.portal.properties.messages" var="etq" />
	
<section class="wrapper">
	<div class="row">
		<div class="col-lg-12">
			<h3 class="page-header">
				<i class="fa fa fa-ticket"></i>
				<fmt:message key="sorteos" bundle="${etq}"></fmt:message>
			</h3>
			<ol class="breadcrumb">
				<li><i class="fa fa-home"></i><a href="/home/Home.do"><fmt:message key="home" bundle="${etq}"></fmt:message></a></li>
				<li><i class="fa fa-ticket"></i>
				<fmt:message key="sorteos" bundle="${etq}"></fmt:message></li>
				
			</ol>
			<input type="button" class="btn btn-primary" onclick="jSorteos.nuevoSorteo()" value="<fmt:message key="agregar_sorteo" bundle="${etq}"></fmt:message>">
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
	
				<section class="panel"  id="${ sorteo.idSorteo }">
			       <header class="panel-heading">
			       
			       Sorteo de la fecha ${sorteo.fechaDefinida} 
			       
			       
			       </header>
			              <div class="panel-body">
			                <div class="panel panel-primary">
			                  <div class="panel-content">
			                  
			                  Status: 
			                  	<c:if test="${ sorteo.fechaEjecucion eq '-' }">
			                  		Pendiente <br>
									Fecha Ejecucion: - <br>
									Fecha Notificacion: - <br>
									
									<br><br>
			             
				                	<a href="javascript:;" class="dropdown-toggle" data-toggle="dropdown">Opciones</a>
					            	<ul class="dropdown-menu">
				                      <li><a href="#" onclick="jSorteos.editarSorteo('${ sorteo.idSorteo }')">Editar</a></li>
				                      <li><a href="#" onclick="jSorteos.eliminarSorteo('${ sorteo.idSorteo }')">Eliminar</a></li>
				                    </ul>
							 	</c:if>
			                 	<c:if test="${ sorteo.fechaEjecucion ne '-'}">
			                 	Sorteado <br>
			                 		Fecha Ejecucion: 
									<fmt:parseDate pattern="dd-MM-yyyy" value="${sorteo.fechaEjecucion}" var="date" />
									<fmt:formatDate value="${date}" type="date" dateStyle="short" timeStyle="short" />
									<br>
									<c:if test="${ sorteo.fechaNotificacion ne '-'}">
										Fecha Notificacion: 
										<fmt:parseDate pattern="dd-MM-yyyy" value="${sorteo.fechaNotificacion}" var="date" />
											<fmt:formatDate value="${date}" type="date" dateStyle="short" timeStyle="short" />
											<br>
									</c:if>
									<c:if test="${ sorteo.fechaNotificacion eq '-'}">
										Fecha Notificacion:	 -
									</c:if>
									<a onclick="jSorteos.verResultados('${ sorteo.idSorteo }')" href="#"> Ver resultados </a>
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