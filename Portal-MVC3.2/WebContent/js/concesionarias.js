var modal = document.getElementById('id02');
var jConcesionaria ={
		
		getClientes : function(){
			jUtils.executing("result");
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
			jUtils.executing("result");
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
			jUtils.executing("result");
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
			jUtils.executing("result");
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
			jUtils.executing("result");
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
	            	alert (nomDiv);
	            	jUtils.showing(nomDiv, html);
	            }
	        });	
		},
		mostrarAdheridas : function(){
			jUtils.executing("result");
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
			jUtils.executing("result");
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
		mostrarPendientes : function () {
			jUtils.executing("result");
	        jUtils.hiding("message");
	        $.ajax({
	            url: "./concesionarias/ConcesionariasPendientes.do",
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
			alert(idConcesionaria);
			jUtils.executing("result");
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
			alert(idConcesionaria);
			jUtils.executing("result");
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
		            alert("Se rechazo!");
	            }
	        });	
		},
		getDatosConcesionaria : function(idConcesionaria){
			alert(idConcesionaria);
			jUtils.executing("result");
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
			$.ajax({
	            url: "./concesionarias/ConfigurarConcesionaria.do",
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
		guardarCambios : function () {
			$.ajax({
	            url: "./concesionarias/GuardarConfiguracion.do",
	            type: "post",
	            dataType: "html",
	            data: $("#nuevaConfig").serialize(),
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