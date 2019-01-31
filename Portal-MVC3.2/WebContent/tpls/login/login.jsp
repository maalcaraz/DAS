<!DOCTYPE html>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<fmt:setLocale value="${ sessionScope.lang }" scope="session" />
<fmt:setBundle basename="ar.edu.ubp.das.src.portal.properties.messages"
	var="etq" />

<head>
<title><fmt:message key="bienvenido" bundle="${etq}"></fmt:message></title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<script type="text/javascript"
	src="/util/Javascript.do/load=jquery,jquery.i18n.properties,utils,portal,cuentas,login,bootstrap.min"></script>
<link type="text/css" rel="stylesheet"
	href="/util/StyleSheet.do/load=page,messages,bootstrap.min" />
</head>
<body>

	<div class="container-fluid">
		<div class="row">
			<div class="col-md-12">

				<!-- Modal -->
				<div class="modal fade" id="exampleModalCenter" tabindex="-1"
					role="dialog" data-backdrop="static" data-keyboard="false" aria-labelledby="exampleModalCenterTitle"
					aria-hidden="true">
					<div class="modal-dialog modal-dialog-centered modal-lg" role="document">
						<div class="modal-content" id="contenido-modal">
							<div class="modal-header text-center">

								<h4>
									<fmt:message key="bienvenido_al_portal" bundle="${etq}"></fmt:message>
								</h4>

							</div>
							<div class="modal-body">

								<form role="form" id="form">
									<div class="form-group">

										<label for="user"> <fmt:message
												key="usuario" bundle="${etq}"></fmt:message>
										</label> <input type="text" class="form-control"
											id="user" name="user" placeholder="Enter username"
											autofocus required />
											
									</div>
									<div class="form-group">

										<label for="pwd"> <fmt:message
												key="pass" bundle="${etq}"></fmt:message>
										</label> <input type="password" class="form-control"
											id="pwd" name="pwd" placeholder="Enter password" required/>
											
									</div>
									
									<button type="button" class="btn btn-primary" name="login"
										onclick="jLogin.acceder()">
										<fmt:message key="iniciar_sesion" bundle="${etq}"></fmt:message>
									</button>
									<button type="button" class="btn btn-primary" name="registrar"
										onclick="jLogin.primerIngreso()">
										<fmt:message key="registrarse" bundle="${etq}"></fmt:message>
									</button>
								</form>
							</div>
							<div class="modal-footer">

								<span class="psw pull-left"><a href="#"> <fmt:message
											key="olvido_su_contrasenia" bundle="${etq}"></fmt:message>?
								</a></span> <b><fmt:message key="lenguaje" bundle="${etq}"></fmt:message></b>

								<select name="lenguaje" selected="lenguaje"
									onchange="jPortal.langOnSelect(this)">
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
								
								<div id="message" class="text-center"> </div>



							</div>
						</div>

					</div>

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