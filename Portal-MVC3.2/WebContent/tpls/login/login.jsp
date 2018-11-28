<!DOCTYPE html>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html lang="en">
<head>
  <title><fmt:message key="bienvenido" bundle="${etq}"></fmt:message></title>
  <meta charset="utf-8"><meta name="viewport" content="width=device-width, initial-scale=1">
  	<script type="text/javascript" src="/util/Javascript.do/load=jquery,jquery.i18n.properties,utils,cuentas,bootstrap.min,login" ></script>
	<link type="text/css" rel="stylesheet" href="/util/StyleSheet.do/load=page,messages,login" />
</head>
<body>
 
 <div id="id01" class="modal">
  <span onclick="document.getElementById('id01').style.display='none'" class="close" title="Close">&times;</span>

  <!-- Modal Content -->
  <form class="modal-content animate" id="form" >
  
   <div class="imgcontainer">
   <h3><fmt:message key="bienvenido_al_portal" bundle="${etq}"></fmt:message></h3>
     
    </div>
    <div class="container">
		<label for="user"><fmt:message key="usuario" bundle="${etq}"></fmt:message>:</label>
		<input type="text" id="user" name="user" placeholder="Enter username" autofocus required>
		   
		<label for="pwd"><fmt:message key="pass" bundle="${etq}"></fmt:message>:</label>
		<input type="password" id="pwd" name="pwd" placeholder="Enter password" required >
		   
		<button type="button" name="login" onclick="jLogin.acceder()"><fmt:message key="ingresar" bundle="${etq}"></fmt:message></button>
		<button type="button" name="registrar" onclick="jLogin.primerIngreso()"><fmt:message key="registrarse" bundle="${etq}"></fmt:message></button>
      <label>
        <input type="checkbox" checked="checked" name="remember"><fmt:message key="recordarme" bundle="${etq}"></fmt:message>
      </label>
    </div>
	<div class="container" style="background-color:#f1f1f1">
      <button type="button" name="cancelbtn" onclick="jLogin.cancelar(2, 'id01')" class="cancelbtn"><fmt:message key="cancelar" bundle="${etq}"></fmt:message></button>
      <span class="psw" ><a href="#"> <fmt:message key="olvido_su_contrasenia" bundle="${etq}"></fmt:message>?</a></span>
    </div>
    
    <div id="message"> </div>
    </form>
  </div>

  <script type="text/javascript">document.getElementById('id01').style.display='block';</script>
 
</body>
</html>