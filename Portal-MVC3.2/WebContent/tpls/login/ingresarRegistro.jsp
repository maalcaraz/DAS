<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
  	<script type="text/javascript" src="/util/Javascript.do/load=jquery,jquery.i18n.properties,utils,cuentas,bootstrap.min,login" ></script>
	<link type="text/css" rel="stylesheet" href="/util/StyleSheet.do/load=page,messages,login" />
</head>

<form class="modal-content animate" id="form_registro" >
  
   <div class="imgcontainer">
   <h3>Ingrese sus datos para realizar el registro en el sistema </h3>
     
    </div>
    <div class="container">
		<label for="user">Usuario:</label>
		<input type="text" id="userReg" name="userReg" placeholder="Enter username" autofocus required>
		   
		<label for="pwd">Clave:</label>
		<input type="password" id="pwdReg" name="pwdReg" placeholder="Enter password" required >
		   
		<button type="button" name="registrar" onclick="jLogin.registrar()">Registrarse</button>
      
    </div>
	<div class="container" style="background-color:#f1f1f1">
      <button type="button" name="cancelbtn" onclick="document.getElementById('id01').style.display='none'" class="cancelbtn">Cancel</button>
      
    </div>
    
    <div id="message"> </div>
    </form>