<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script type="text/javascript"
	src="/util/Javascript.do/load=jquery,jquery.i18n.properties,utils,cuentas,login,clientes"></script>
<link type="text/css" rel="stylesheet"
	href="/util/StyleSheet.do/load=home,page,messages,login,cliente" />
</head>
<body>

	<h1>Te loggeaste como cliente</h1>

	<div class="grid-container">
		
		
		<div class="grid-item">
			<h2>Estado Cuenta</h2>
			<br> <span>
				Conoce el estado de tu cuenta: cuotas abonadas, cuotas restantes,
				plan adquirido y descripcion de financiacion. </span>
			<input type="button" id="estadoCuenta"
				value="Estado Cuenta" onclick="jClientes.estadoCuenta()">

			<div id="detalle-cliente"></div>
		</div>

		<div>
			<h4>Mi producto</h4>
			<br>
			<p>Conoce el auto que vas a tener, sus caracteristicas y
				especificaciones tecnicas.</p>
		</div>

		<div>
			<h4>Mis datos</h4>
			<br>
			<p>Actualiza tus datos personales.</p>

		</div>

	</div>

</body>
</html>