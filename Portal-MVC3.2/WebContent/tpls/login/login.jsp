<!DOCTYPE html>
<html lang="en">
<head>
  <title>Login</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  	<script type="text/javascript" src="/util/Javascript.do/load=jquery,jquery.i18n.properties,utils,cuentas,bootstrap.min,login" ></script>
	<link type="text/css" rel="stylesheet" href="/util/StyleSheet.do/load=page,messages,bootstrap.min," />
</head>
<body>

 
 
 
 <div id="id01" class="modal">
  <span onclick="document.getElementById('id01').style.display='none'" 
class="close" title="Close Modal">&times;</span>

  <div class="modal-content animate" >
  <form class="form-group" id="form">
  <h3>Bienvenido al Portal del Gobierno</h3>
		    <div class="form-group">
		      <label for="user">User:</label>
		      <input type="text" class="form-control" id="user" name="user" placeholder="Enter username" autofocus required>
		    </div>
		    <div class="form-group">
		      <label for="pwd">Password:</label>
		      <input type="password" class="form-control" id="pwd" name="pwd" placeholder="Enter password" required>
		    </div>
		    <div class="checkbox">
		      <label><input type="checkbox"> Remember me</label>
		    </div>
		     <div class="form-group">
		    <input type="button" onclick="jLogin.acceder()" value="Login"/>
		      </div>
	<div class="form-group">
      <button type="button" onclick="document.getElementById('id01').style.display='none'" class="cancelbtn">Cancel</button>
      <span class="psw">Forgot <a href="#">password?</a></span>
    </div>
    </form>
    </div>
  
	</div>
 <script type="text/javascript">document.getElementById('id01').style.display='block'</script>
 
</body>
</html>