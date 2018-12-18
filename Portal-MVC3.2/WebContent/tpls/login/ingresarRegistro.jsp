<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
  	<script type="text/javascript" src="/util/Javascript.do/load=jquery,jquery.i18n.properties,utils,cuentas,bootstrap.min,login" ></script>
	<link type="text/css" rel="stylesheet" href="/util/StyleSheet.do/load=page,messages,login" />
	<fmt:setLocale value="${ sessionScope.lang }" scope="session"/>
	<fmt:setBundle basename="ar.edu.ubp.das.src.portal.properties.messages" var="etq"/>
</head>
<!--  No necesitamos el close por ahora por que atras del login no hay nada
<span onclick="document.getElementById('id01').style.display='none'" class="close" title="Close">&times;</span>
-->
<form class="modal-content animate" id="form_registro" >
  
   <div class="imgcontainer">
   <h3><fmt:message key="ingresoRegistro" bundle="${etq}"></fmt:message> </h3>
     
    </div>
    <div class="container">
		<label for="user"><fmt:message key="usuario" bundle="${etq}"></fmt:message>:</label>
		<input type="text" id="userReg" name="userReg" placeholder="Enter username" autofocus required>
		   
		<label for="pwd"><fmt:message key="pass" bundle="${etq}"></fmt:message>:</label>
		<input type="password" id="pwdReg" name="pwdReg" placeholder="Enter password" required >
		   
		<button type="button" name="registrar" onclick="jLogin.registrar()"><fmt:message key="registrarse" bundle="${etq}"></fmt:message></button>
      
    </div>
	<div class="container" style="background-color:#f1f1f1">
      <!-- <button type="button" name="cancelbtn" onclick="document.getElementById('id01').style.display='none'" class="cancelbtn">Cancel</button>
      -->
      <button type="button" name="cancelbtn" onclick="window.location.replace('/login/Login.do')" class="cancelbtn">Cancel</button>
      
    </div>
    
    <div id="message"> </div>
    </form>