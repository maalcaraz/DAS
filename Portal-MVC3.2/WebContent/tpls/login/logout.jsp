<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<fmt:setLocale value="${ sessionScope.lang }" scope="session"/>
<fmt:setBundle basename="ar.edu.ubp.das.src.portal.properties.messages" var="etq"/>


<section class="wrapper">
	<!-- page start-->
	<div class="main">

		<div class="row">
			<div class="col-lg-12">
			
			<div class="info-box blue-bg">
              <i class="fa fa-clock-o"></i>
              <div class="count">La sesion ha expirado</div>   
              <div class="title">Debe iniciar sesion nuevamente</div>
              
              <button type="button" class="btn linkedin-bg mt-4" value="Si" onclick="jLogin.logout(1)">Entendido</button>
			  
            </div>

			</div>
		</div>

	</div>
	<!-- page end-->
</section>
