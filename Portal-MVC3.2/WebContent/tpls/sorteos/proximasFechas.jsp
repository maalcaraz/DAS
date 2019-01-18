<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<br>

<c:set var="sorteos" scope="request" value="${requestScope.sorteos}"> </c:set>
<fmt:setLocale value="${ sessionScope.lang }" scope="session"/>
<fmt:setBundle basename="ar.edu.ubp.das.src.portal.properties.messages" var="etq"/>

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
						<!-- 
			              <li><i class="fa fa-square-o"></i>Pages</li>
			              -->
					</ol>
				</div>
			</div>
	        <!-- page start-->
	        <div class="main" id="contenido-admin">
	        
	        	 <p><fmt:message key="busqueda_sorteo_placeholder" bundle="${etq}"></fmt:message></p>  
				  <input class="form-control" id="myInput" type="text" placeholder="Search..">
				  <br>
				  <table class="table table-bordered table-striped" id="tablaSorteos">
				    <thead>
				      <tr>
				        <th><fmt:message key="estado" bundle="${etq}"></fmt:message></th>
				        <th><fmt:message key="fecha_sorteo" bundle="${etq}"></fmt:message></th>
				        <th><fmt:message key="fecha_ejecucion" bundle="${etq}"></fmt:message></th>
				        <th><fmt:message key="fecha_notificacion" bundle="${etq}"></fmt:message></th>
				      </tr>
				    </thead>
				    <tbody id="myTable">
				      <c:if test="${not empty sorteos}">
						<c:forEach var="sorteo" items="${ sorteos }" varStatus="status">
						<tr>
							<td> 
								<c:if test="${ sorteo.pendiente eq '-' }">
									Sin sortear
								</c:if>
								<c:if test="${ sorteo.pendiente ne '-' }">
									${ sorteo.pendiente }
								</c:if>
							</td>
								 <fmt:parseDate pattern="dd-MM-yyyy" value="${ sorteo.fechaSorteado }" var="date" />
								 <td><fmt:formatDate value="${date}" type="date" dateStyle = "short" timeStyle="short" /></td>
								 
								 <c:if test="${ sorteo.fechaEjecucion ne '-'}">
									  <fmt:parseDate pattern="dd-MM-yyyy" value="${ sorteo.fechaEjecucion }" var="date" />
									  <td><fmt:formatDate value="${date}" type="date" dateStyle = "short" timeStyle="short" /></td>
									  
									  <c:if test="${ sorteo.fechaNotificacion ne '-'}">
									  	<fmt:parseDate pattern="dd-MM-yyyy" value="${ sorteo.fechaNotificacion }" var="date" />
								 		<td><fmt:formatDate value="${date}" type="date" dateStyle = "short" timeStyle="short" /></td>
									  </c:if>
									  <c:if test="${ sorteo.fechaNotificacion eq '-'}">
									  <td> - </td>
									  </c:if>
								 </c:if>
								 <c:if test="${ sorteo.fechaEjecucion eq '-'}">
									   <td> - </td>
									   <td> - </td>
								 </c:if>
						</tr> 
						</c:forEach>
						</c:if>
				
						<c:if test="${empty sorteos}">
							<td><fmt:message key="no_hay_fecha_definida" bundle="${etq}"></fmt:message></td>
							<td>-</td>
							<td>-</td>
							<td>-</td>
							<td>-</td>
						</c:if>
				    </tbody>
				  </table>
	               	

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

