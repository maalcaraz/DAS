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
					  <input class="form-control" id="myInput" type="text" placeholder="Search..">
			<br>
              <table class="table table-striped table-advance table-hover">
                <tbody id="tabla-participantes">
                  <tr>
                    <th><i class="icon_profile"></i> Nombre </th>
                    <th><i class="icon_calendar"></i> Concesionaria </th>
                    <th><i class="icon_mail_alt"></i> Email</th>
                    <th><i class="icon_pin_alt"></i> City</th>
                  </tr>
                  
                  <c:forEach var="participante" items="${ participantes }" varStatus="status">
                  
                  
                  <tr>
                    <td>${ participante.apellidoNombre }</td>
                    <td>${ participante.nombreConcesionaria}</td>
                    <td>${ participante.email }</td>
                    <td>Rosser</td>
                    
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
					$(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
					});
				});
			});
</script>