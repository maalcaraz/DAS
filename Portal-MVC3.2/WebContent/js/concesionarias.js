var modal = document.getElementById('id02');
var jConcesionaria ={
		
		getClientes : function(){
			jUtils.executing("detalle-clientes");
	        jUtils.hiding("message");
	        $.ajax({
	            url: "./concesionarias/ConsultaQuincenal.do",
	            type: "post",
	            dataType: "html",
	            error: function(hr){
	                jUtils.hiding("result");
	                jUtils.showing("message", hr.responseText);
	            },
	            success: function(html) {
	            	jUtils.showing("detalle-clientes", html);
	            }
	        });	
			
		},
		suscribir : function (){
			jUtils.executing("contenido");
	        jUtils.hiding("message");
	        $.ajax({
	            url: "./concesionarias/Suscripcion.do",
	            type: "post",
	            dataType: "html",
	            error: function(hr){
	                jUtils.hiding("result");
	                jUtils.showing("contenido", hr.responseText);
	            },
	            success: function(html) {
	            	
	            	jUtils.showing("contenido", html);
	            	
	            }
	        });	
		},
		insertarConcesionaria : function (){
			jUtils.executing("result");
	        jUtils.hiding("message");
	        $.ajax({
	            url: "./concesionarias/InsertarConcesionaria.do",
	            type: "post",
	            dataType: "html",
	            data: $("#formSuscripcion").serialize(),
	            error: function(hr){
	                jUtils.hiding("result");
	                jUtils.showing("message", hr.responseText);
	            },
	            success: function(html) {
	            	alert ("Concesionaria suscripta!");
	            	this.logout(1, modal);
	            }
	        });	
		},
		
		notificarConcesionaria : function (){
			jUtils.executing("respuestaNotificar");
	        jUtils.hiding("message");
	        $.ajax({
	            url: "./concesionarias/NotificarGanador.do",
	            type: "post",
	            dataType: "html",
	            data: $("#formNotificar").serialize(),
	            error: function(hr){
	                jUtils.hiding("result");
	                jUtils.showing("message", hr.responseText);
	            },
	            success: function(html) {
	            	jUtils.showing("respuestaNotificar", html);
	            }
	        });	
		},
		
		verificarCancelado : function (){
			jUtils.executing("respuestaCancelado");
	        jUtils.hiding("message");
	        $.ajax({
	            url: "./concesionarias/VerificarCancelado.do",
	            type: "post",
	            dataType: "html",
	            data: $("#verificarCanceladoForm").serialize(),
	            error: function(hr){
	                jUtils.hiding("result");
	                jUtils.showing("message", hr.responseText);
	            },
	            success: function(html) {
	            	jUtils.showing("respuestaCancelado", html);
	            	
	            }
	        });	
		},
		testingSyncro : function(idConcesionaria){
			nomDiv = "respuesta-"+idConcesionaria;
			jUtils.executing(nomDiv);
	        jUtils.hiding("message");
	        $.ajax({
	            url: "./concesionarias/TestingSync.do",
	            type: "post",
	            dataType: "html",
	            data: {"idConcesionaria" : idConcesionaria},
	            error: function(hr){
	                jUtils.hiding("result");
	                jUtils.showing("message", hr.responseText);
	            },
	            success: function(html) {
	            	nomDiv = "respuesta-"+idConcesionaria;
	            	jUtils.showing(nomDiv, html);
	            }
	        });	
		},
		mostrarAdheridas : function(){
			jUtils.executing("contenido");
	        jUtils.hiding("message");
	        $.ajax({
	            url: "./concesionarias/MostrarAdheridas.do",
	            type: "post",
	            dataType: "html",
	            error: function(hr){
	                jUtils.hiding("result");
	                jUtils.showing("message", hr.responseText);
	            },
	            success: function(html) {
	            	jUtils.showing("contenido", html);
	            	
	            }
	        });	
		},
		mostrarRegistradas : function(){
			jUtils.executing("contenido-admin");
	        jUtils.hiding("message");
	        $.ajax({
	            url: "./concesionarias/ConcesionariasRegistradas.do",
	            type: "post",
	            dataType: "html",
	            error: function(hr){
	                jUtils.hiding("result");
	                jUtils.showing("message", hr.responseText);
	            },
	            success: function(html) {
	            	jUtils.showing("contenido-admin", html);
	            }
	        });	
		},
		aprobar : function (idConcesionaria){
			jUtils.executing("contenido-admin");
	        jUtils.hiding("message");
	        $.ajax({
	            url: "./concesionarias/AprobarConcesionaria.do",
	            type: "post",
	            dataType: "html",
	            data: {"idConcesionaria": idConcesionaria},
	            error: function(hr){
	                jUtils.hiding("result");
	                jUtils.showing("message", hr.responseText);
	            },
	            success: function(html) {
	            	jUtils.showing("contenido-admin", html);
	            }
	        });	
		},
		rechazar : function(idConcesionaria){
			jUtils.executing("contenido-admin");
	        jUtils.hiding("message");
	        $.ajax({
	            url: "./concesionarias/RechazarConcesionaria.do",
	            type: "post",
	            dataType: "html",
	            data: {"idConcesionaria": idConcesionaria},
	            error: function(hr){
	                jUtils.hiding("result");
	                jUtils.showing("message", hr.responseText);
	            },
	            success: function(html) {
	            	jUtils.showing("contenido-admin", html);
	            }
	        });	
		},
		getDatosConcesionaria : function(idConcesionaria){
			alert(idConcesionaria);
			jUtils.executing("contenido-admin");
	        jUtils.hiding("message");
	        $.ajax({
	            url: "./concesionarias/MostrarDatosConcesionaria.do",
	            type: "post",
	            dataType: "html",
	            data: {"idConcesionaria": idConcesionaria},
	            error: function(hr){
	                jUtils.hiding("result");
	                jUtils.showing("message", hr.responseText);
	            },
	            success: function(html) {
	            	jUtils.showing("contenido-admin", html);
	            }
	        });	
		},
		editarConcesionaria : function (idConcesionaria){
			var cuit = "<b>Cuit:</b>\
				<input type=\"text\" name=\"cuit\" size=\"10\" required>";
			document.getElementById("cuit-"+idConcesionaria+"").innerHTML = cuit;
			
			var ts = "<b>Tipo de servicio: </b> \
			        <select name=\"tipoServicio\">\
						<option value=\"Rest\"> Rest </option>\
						<option value=\"CXF\"> CXF </option>\
						<option value=\"Axis2\"> Axis </option>\
					</select>";
			document.getElementById("ts-"+idConcesionaria+"").innerHTML = ts;
			
			var dir = "<b>Direccion:</b>\
						<input type=\"text\" name=\"dir\" size=\"30\" required>";
			document.getElementById("dir-"+idConcesionaria+"").innerHTML = dir;
			
			var tel = "<b> Telefono: </b>\
				<input type=\"number\" name=\"tel\" min=\"0000000000\" max=\"9999999999\" required>";
			document.getElementById("tel-"+idConcesionaria+"").innerHTML = tel;
			
			var dias = "<b> Dias Caducidad: </b>\
				<input type=\"number\" name=\"diasCaducidad\" min=\"0\" max=\"100\" required>";
			document.getElementById("dias-"+idConcesionaria+"").innerHTML = dias;
			
			var em = "<b> Email: </b>\
			<input type=\"email\" name=\"emailConcesionaria\" required>";
			document.getElementById("em-"+idConcesionaria+"").innerHTML = em;
			
			var boton = '<fmt:message key=\"guardar\" bundle=\"${etq}\"> </fmt:message>';
			$("#config-"+idConcesionaria+"").val(boton);
			document.getElementById("config-"+idConcesionaria+"").setAttribute("onClick", "jConcesionaria.guardarCambios('"+idConcesionaria+"')");
			
		},
		guardarCambios : function (idConcesionaria) {
			$.ajax({
	            url: "./concesionarias/GuardarConfiguracion.do",
	            type: "post",
	            dataType: "html",
	            data: $("#configurarForm-"+idConcesionaria+"").serialize(),
	            error: function(hr){
	                jUtils.showing("contenido-admin", hr.responseText);
	            },
	            success: function(html) {
	            	jUtils.showing("contenido-admin", html);
	            }
	        });
		},
		eliminarConcesionaria : function (idConcesionaria){
			$.ajax({
	            url: "./concesionarias/EliminarConcesionaria.do",
	            type: "post",
	            dataType: "html",
	            data: {"idConcesionaria" : idConcesionaria},
	            error: function(hr){
	                jUtils.showing("contenido-admin", hr.responseText);
	            },
	            success: function(html) {
	            	jUtils.showing("contenido-admin", html);
	            }
	        });
		},
		testing : function (){
			jUtils.executing("result");
	        jUtils.hiding("message");
	        $.ajax({
	            url: "./concesionarias/Testing.do",
	            type: "post",
	            dataType: "html",
	            error: function(hr){
	                jUtils.hiding("result");
	                jUtils.showing("message", hr.responseText);
	            },
	            success: function(html) {
	            	jUtils.showing("contenido-admin", html);
	            }
	        });	
		},
		cancelarSuscripcion : function () {
			modal.style.display = "none";
		},
		obtenerGanadores : function (){
			jUtils.executing("result");
	        jUtils.hiding("message");
	        $.ajax({
	            url: "./concesionarias/ObtenerGanadores.do",
	            type: "post",
	            dataType: "html",
	            error: function(hr){
	                jUtils.hiding("result");
	                jUtils.showing("message", hr.responseText);
	            },
	            success: function(html) {
	            	jUtils.showing("contenido", html);
	            }
	        });	
		}
};