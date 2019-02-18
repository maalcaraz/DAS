<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<fmt:setLocale value="${ sessionScope.lang }" scope="session"/>
<fmt:setBundle basename="ar.edu.ubp.das.src.portal.properties.messages" var="etq"/>

<c:set var="sorteo" scope="request" value="${requestScope.sorteo}"> </c:set>
<c:set var="participantes" scope="request" value="${requestScope.participantes}"> </c:set>

<div class="col-lg-12">
<h2>Resultados </h2>
            <section class="panel">
              <header class="panel-heading">
                Participantes del sorteo del ${sorteo.idSorteo}
              </header>

			<br>
			<p><fmt:message key="busqueda_sorteo_placeholder" bundle="${etq}"></fmt:message></p>  
					  
					  
			<div class="row">
				<div class="col-lg-2">
					<select id="selectFilter" name="column" class="form-control">
							<option value="td1">
								<fmt:message key="nombre_y_apellido" bundle="${etq}"></fmt:message>
							</option>
							<option value="td2">
								<fmt:message key="concesionaria" bundle="${etq}"></fmt:message>
							</option>
							<option value="td3">
								<fmt:message key="email" bundle="${etq}"></fmt:message>
							</option>
							<option value="td4">
								<fmt:message key="dni" bundle="${etq}"></fmt:message>
							</option>
						</select>
				</div>
				<div class="col-lg-10">
					<input class="form-control" id="myInput" type="text" placeholder="Search..">
				</div>
			</div>
			
			<br>
              <table class="table table-striped table-advance table-hover">
              <thead>
              	  <tr>
                    <th><i class="icon_profile"></i> <fmt:message key="nombre_y_apellido" bundle="${etq}"></fmt:message> </th>
                    <th><i class="icon_calendar"></i> <fmt:message key="concesionaria" bundle="${etq}"></fmt:message> </th>
                    <th><i class="icon_mail_alt"></i> <fmt:message key="email" bundle="${etq}"></fmt:message> </th>
                    <th><i class="icon_id"></i> <fmt:message key="dni" bundle="${etq}"></fmt:message> </th>
                  </tr>
                </thead>
                <tbody id="tabla-participantes">
                  <c:forEach var="participante" items="${ participantes }" varStatus="status">

                  <tr>
                    <td id="td1">${ participante.apellidoNombre }</td>
                    <td id="td2">${ participante.nombreConcesionaria}</td>
                    <td id="td3">${ participante.email }</td>
                    <td id="td4">${ participante.dniCliente }</td>
                    
                  </tr>
                </c:forEach>
                </tbody>
              </table>
            </section>
          </div>
          
<script>
	$(document).ready(function(){
		$("#myInput").on("keyup", function() {
			var value = $(this).val().toLowerCase();
			$("#tabla-participantes tr").filter(function() {
				$(this).toggle($(this).find("td[id="+$("#selectFilter").val()+"]").text().toLowerCase().indexOf(value) > -1)
				});
			});
		});
</script>