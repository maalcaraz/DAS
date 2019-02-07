<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
  	<script type="text/javascript" src="/util/Javascript.do/load=jquery,jquery.i18n.properties,utils,portal,cuentas,login,bootstrap.min,jquery.easing.min"></script>
	<link type="text/css" rel="stylesheet" href="/util/StyleSheet.do/load=messages,bootstrap.min,nivo-lightbox,default,nivo-lightbox-theme/default/default,animate,style" />
	<fmt:setLocale value="${ sessionScope.lang }" scope="session"/>
	<fmt:setBundle basename="ar.edu.ubp.das.src.portal.properties.messages" var="etq"/>
</head>
							<div class="modal-header text-center">

								 <h4><fmt:message key="ingresoRegistro" bundle="${etq}"></fmt:message> </h4>

							</div>
							<div class="modal-body">

								<form role="form" id="form_registro">
									<div class="form-group">

										<label for="user"> <fmt:message
												key="usuario" bundle="${etq}"></fmt:message>
										</label> <input type="text" class="form-control"
											id="userReg" name="userReg" placeholder="Enter username"
											autofocus required />
											
									</div>
									<div class="form-group">

										<label for="pwd"> <fmt:message
												key="pass" bundle="${etq}"></fmt:message>
										</label> <input type="password" class="form-control"
											id="pwdReg" name="pwdReg" placeholder="Enter password" required/>
											
									</div>
									<button type="button" class="btn btn-primary" name="registrar"
										onclick="jLogin.registrar()">
										<fmt:message key="registrarse" bundle="${etq}"></fmt:message>
									</button>
									
								</form>
							</div>
							<div class="modal-footer">
							<div class="pull-left">
							<button type="button" name="cancelbtn" onclick="window.location.replace('/login/Login.do')" class="cancelbtn">Cancel</button>
							</div>
								
							
								 <b><fmt:message key="lenguaje" bundle="${etq}"></fmt:message></b>

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
