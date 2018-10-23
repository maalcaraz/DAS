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
	                jUtils.showing("message", hr.responseText);
	            },
	            success: function(html) {
	            	
	            	jUtils.showing("content", html);
	            	
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
	            	modal.style.display = "none";
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
	            	
	            	jUtils.showing("content", html);
	            	
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
	            
	            error: function(hr){
	                jUtils.hiding("result");
	                jUtils.showing("message", hr.responseText);
	            },
	            success: function(html) {
	            	
	            	jUtils.showing("respuestaCancelado", html);
	            	
	            }
	        });	
		},
		testingSyncro : function(){
			jUtils.executing("result");
	        jUtils.hiding("message");
	        $.ajax({
	            url: "./concesionarias/TestingSync.do",
	            type: "post",
	            dataType: "html",
	            error: function(hr){
	                jUtils.hiding("result");
	                jUtils.showing("message", hr.responseText);
	            },
	            success: function(html) {
	            	
	            	jUtils.showing("content", html);
	            	
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
	            	
	            	jUtils.showing("concesionarias", html);
	            	
	            }
	        });	
		},
		mostrarConcesionarias : function(){
			jUtils.executing("result");
	        jUtils.hiding("message");
	        $.ajax({
	            url: "./concesionarias/MostrarConcesionarias.do",
	            type: "post",
	            dataType: "html",
	            error: function(hr){
	                jUtils.hiding("result");
	                jUtils.showing("message", hr.responseText);
	            },
	            success: function(html) {
	            	
	            	jUtils.showing("concesionarias", html);
	            	
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
	            	
	            	jUtils.showing("listado_sorteos", html);
	            	
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
		            alert("se rechazo!");
	            }
	        });	
		},
		modificar : function(){
			
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
	            	jUtils.showing("concesionarias", html);
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
	                jUtils.showing("concesionarias", hr.responseText);
	            },
	            success: function(html) {
	            	jUtils.showing("concesionarias", html);
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
	                jUtils.showing("concesionarias", hr.responseText);
	            },
	            success: function(html) {
	            	jUtils.showing("concesionarias", html);
	            }
	        });
		}
};