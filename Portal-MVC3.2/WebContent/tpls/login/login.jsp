<!DOCTYPE html>
<html lang="en">
<head>
  <title>Welcome</title>
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
   <h3>Bienvenido al Portal del Gobierno Nacional</h3>
     
    </div>
    <div class="container">
		<label for="user">Username:</label>
		<input type="text" id="user" name="user" placeholder="Enter username" autofocus required>
		   
		<label for="pwd">Password:</label>
		<input type="password" id="pwd" name="pwd" placeholder="Enter password" required >
		   
		<button type="button" name="login" onclick="jLogin.acceder()">Login</button>
		<button type="button" name="registrar" onclick="jLogin.primerIngreso()">Registrarse</button>
      <label>
        <input type="checkbox" checked="checked" name="remember"> Remember me
      </label>
    </div>
	<div class="container" style="background-color:#f1f1f1">
      <button type="button" name="cancelbtn" onclick="jLogin.cancelar(2, 'id01')" class="cancelbtn">Cancel</button>
      <span class="psw" >Forgot <a href="#">password?</a></span>
    </div>
    
    <div id="message"> </div>
    </form>
  </div>

  <script type="text/javascript">document.getElementById('id01').style.display='block';</script>
 
</body>
</html>