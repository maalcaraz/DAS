var modal = document.getElementById('id02');
var jConcesionaria ={
		
		getClientes : function(){
			jUtils.executing("detalle-clientes");
	        jUtils.hiding("message");
	        $.ajax({
	            url: "/concesionarias/ConsultaQuincenal.do",
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
		/*No usada actualmente*/
		suscribir : function (){
			jUtils.executing("contenido");
	        jUtils.hiding("message");
	        $.ajax({
	            url: "/concesionarias/Suscripcion.do",
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
		/*No usada actualmente*/
		insertarConcesionaria : function (){
			jUtils.executing("result");
	        jUtils.hiding("message");
	        $.ajax({
	            url: "/concesionarias/InsertarConcesionaria.do",
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
	            url: "/concesionarias/NotificarGanador.do",
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
	            url: "/concesionarias/VerificarCancelado.do",
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
	            url: "/concesionarias/TestingSync.do",
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
			jUtils.executing("main-content");
	        jUtils.hiding("message");
	        $.ajax({
	            url: "/concesionarias/MostrarAdheridas.do",
	            type: "post",
	            dataType: "html",
	            error: function(hr){
	                jUtils.hiding("result");
	                jUtils.showing("message", hr.responseText);
	            },
	            success: function(html) {
	            	jUtils.showing("main-content", html);
	            	
	            }
	        });	
		},
		mostrarRegistradas : function(){
			jUtils.executing("main-content");
	        jUtils.hiding("message");
	        $.ajax({
	            url: "/concesionarias/ConcesionariasRegistradas.do",
	            type: "post",
	            dataType: "html",
	            error: function(hr){
	                jUtils.hiding("result");
	                jUtils.showing("message", hr.responseText);
	            },
	            success: function(html) {
	            	jUtils.showing("main-content", html);
	            }
	        });	
		},
		aprobar : function (idConcesionaria){
			jUtils.executing("main-content");
	        jUtils.hiding("message");
	        $.ajax({
	            url: "/concesionarias/AprobarConcesionaria.do",
	            type: "post",
	            dataType: "html",
	            data: {"idConcesionaria": idConcesionaria},
	            error: function(hr){
	                jUtils.hiding("result");
	                jUtils.showing("message", hr.responseText);
	            },
	            success: function(html) {
	            	jUtils.showing("main-content", html);
	            }
	        });	
		},
		rechazar : function(idConcesionaria){
			jUtils.executing("main-content");
	        jUtils.hiding("message");
	        $.ajax({
	            url: "/concesionarias/RechazarConcesionaria.do",
	            type: "post",
	            dataType: "html",
	            data: {"idConcesionaria": idConcesionaria},
	            error: function(hr){
	                jUtils.hiding("result");
	                jUtils.showing("message", hr.responseText);
	            },
	            success: function(html) {
	            	jUtils.showing("main-content", html);
	            }
	        });	
		},
		/*No Usada */
		getDatosConcesionaria : function(idConcesionaria){
			alert(idConcesionaria);
			jUtils.executing("contenido-admin");
	        jUtils.hiding("message");
	        $.ajax({
	            url: "/concesionarias/MostrarDatosConcesionaria.do",
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
			
			var previousText = document.getElementById("url-"+idConcesionaria+"").textContent;
			previousText = previousText.substring(previousText.indexOf(":") + 1);
			
			var url = "<b>Url:</b>\
				<input type=\"text\" name=\"url\" value=\"" + previousText +"\" size=\"40\" required>";
			document.getElementById("url-"+idConcesionaria+"").innerHTML = url;
			
			var previousText = document.getElementById("cuit-"+idConcesionaria+"").textContent;
			previousText = previousText.substring(previousText.indexOf(":") + 1);
			
			var cuit = "<b>Cuit:</b>\
				<input type=\"text\" name=\"cuit\" value=\"" + previousText +"\" size=\"10\" required>";
			document.getElementById("cuit-"+idConcesionaria+"").innerHTML = cuit;
			var servicioActual = document.getElementById("servicioActual-"+idConcesionaria+"").value;
			var ts = "<b>Tipo de servicio: </b> \
			        <select name=\"tipoServicio\">\
						<option value=\"Rest\" ";
			if (servicioActual == "Rest") ts+= " selected";
			ts += "> Rest </option>\
						<option value=\"CXF\" ";
			if (servicioActual == "CXF") ts+= " selected";
			ts += "> CXF </option>\
						<option value=\"Axis2\" ";
			if (servicioActual == "Axis2") ts+= " selected";
			ts += "> Axis </option>\
					</select>";
			document.getElementById("ts-"+idConcesionaria+"").innerHTML = ts;
			
			previousText = document.getElementById("dir-"+idConcesionaria+"").textContent;
			previousText = previousText.substring(previousText.indexOf(":") + 1);
			
			var dir = "<b>Direccion:</b>\
						<input type=\"text\" name=\"dir\" value=\"" + previousText +"\" size=\"40\" required>";
			document.getElementById("dir-"+idConcesionaria+"").innerHTML = dir;
			
			previousText = document.getElementById("tel-"+idConcesionaria+"").textContent;
			previousText = previousText.substring(previousText.indexOf(":") + 1);
			var previousNumber = parseInt(previousText, 10);
			
			var tel = "<b> Telefono: </b>\
				<input type=\"number\" name=\"tel\" value=\"" + previousNumber +"\" min=\"0000000000\" max=\"9999999999\" required>";
			document.getElementById("tel-"+idConcesionaria+"").innerHTML = tel;
			
			previousText = document.getElementById("dias-"+idConcesionaria+"").textContent;
			previousText = previousText.substring(previousText.indexOf(":") + 1);
			previousNumber = parseInt(previousText, 10);
			
			var dias = "<b> Dias Caducidad: </b>\
				<input type=\"number\" name=\"diasCaducidad\" value=\"" + previousNumber +"\" min=\"0\" max=\"100\" required>";
			document.getElementById("dias-"+idConcesionaria+"").innerHTML = dias;
			
			previousText = document.getElementById("em-"+idConcesionaria+"").textContent;
			previousText = previousText.substring(previousText.indexOf(":") + 1);
			
			var em = "<b> Email: </b>\
			<input type=\"email\" name=\"emailConcesionaria\" value=\"" + previousText +"\" required>";
			document.getElementById("em-"+idConcesionaria+"").innerHTML = em;
			
			var boton = ' <fmt:message key=\"guardar\" bundle=\"${etq}\"> </fmt:message>';
			document.getElementById("config-"+idConcesionaria+"").value = "Guardar";
			document.getElementById("config-"+idConcesionaria+"").setAttribute("onClick", "jConcesionaria.guardarCambios('"+idConcesionaria+"')");
			
		},
		guardarCambios : function (idConcesionaria) {
			$.ajax({
	            url: "/concesionarias/GuardarConfiguracion.do",
	            type: "post",
	            dataType: "html",
	            data: $("#configurarForm-"+idConcesionaria+"").serialize(),
	            error: function(hr){
	                jUtils.showing("contenido-admin", hr.responseText);
	            },
	            success: function(html) {
	            	jUtils.showing("main-content", html);
	            }
	        });
		},
		eliminarConcesionaria : function (idConcesionaria){
			$.ajax({
	            url: "/concesionarias/EliminarConcesionaria.do",
	            type: "post",
	            dataType: "html",
	            data: {"idConcesionaria" : idConcesionaria},
	            error: function(hr){
	                jUtils.showing("main-content", hr.responseText);
	            },
	            success: function(html) {
	            	jUtils.showing("main-content", html);
	            }
	        });
		},
		testing : function (){
			jUtils.executing("main-content");
	        jUtils.hiding("message");
	        $.ajax({
	            url: "/concesionarias/Testing.do",
	            type: "post",
	            dataType: "html",
	            error: function(hr){
	                jUtils.hiding("main-content");
	                jUtils.showing("message", hr.responseText);
	            },
	            success: function(html) {
	            	jUtils.showing("main-content", html);
	            }
	        });	
		},
		cancelarSuscripcion : function () {
			modal.style.display = "none";
		},
		obtenerGanadores : function (){
			jUtils.executing("main-content");
	        jUtils.hiding("message");
	        $.ajax({
	            url: "/concesionarias/ObtenerGanadores.do",
	            type: "post",
	            dataType: "html",
	            error: function(hr){
	                jUtils.hiding("result");
	                jUtils.showing("message", hr.responseText);
	            },
	            success: function(html) {
	            	jUtils.showing("main-content", html);
	            }
	        });	
		}
};