<!DOCTYPE html>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<fmt:setLocale value="${ sessionScope.lang }" scope="session" />
<fmt:setBundle basename="ar.edu.ubp.das.src.portal.properties.messages" var="etq" />

<head>
<title><fmt:message key="bienvenido" bundle="${etq}"></fmt:message></title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<script type="text/javascript" src="/util/Javascript.do/load=jquery,jquery.i18n.properties,utils,portal,cuentas,login,bootstrap.min"></script>
<link type="text/css" rel="stylesheet"	href="/util/StyleSheet.do/load=messages,bootstrap.min,elegant-icons-style,bootstrap-theme,style,style-responsive" />
</head>
<body class="login-img3-body">

	<div class="container-fluid">
		<div class="row">
			<div class="col-md-12">

				
				
						<div class="container">
						    <form id="form" class="login-form" action="index.html">
						      <div class="login-wrap">
						        <p class="login-img"><i class="icon_lock_alt"></i></p>
						        <div class="input-group">
						          <span class="input-group-addon"><i class="icon_profile"></i></span>
						          <input type="text" class="form-control" id="user" name="user" placeholder="Enter username" autofocus required>
						        </div>
						        <div class="input-group">
						          <span class="input-group-addon"><i class="icon_key_alt"></i></span>
						          <input type="password" class="form-control" id="pwd" name="pwd" placeholder="Enter password" required>
						        </div>
						        <button type="button" class="btn btn-primary btn-lg btn-block" name="login" onclick="jLogin.acceder()">
									<fmt:message key="iniciar_sesion" bundle="${etq}"></fmt:message></button>
						        <button type="button" class="btn btn-info btn-lg btn-block" onclick="jLogin.primerIngreso()">
									<fmt:message key="registrarse" bundle="${etq}"></fmt:message></button>
									
								<div class="input-group">
								<br>
									<select name="lenguaje" selected="lenguaje"
									onchange="jPortal.langOnSelect(this)" class="form-control mbot-15">
									<option value="lenguaje"><fmt:message key="lenguaje"
											bundle="${etq}"></fmt:message>
									</option>
									<option value="es">
										<fmt:message key="espaniol" bundle="${etq}"></fmt:message>
									</option>
									<option value="en">
										<fmt:message key="ingles" bundle="${etq}"></fmt:message>
									</option>
								</select>
								</div>
								<div id="message" class="text-center"> </div>
						      </div>
						    </form>
						</div>
						
						
						
			</div>
		</div>
	</div>
						
						
	<script type="text/javascript">
		$(window).on('load', function() {
			$('#exampleModalCenter').modal('show');
		});
	</script>
</body>
</html>