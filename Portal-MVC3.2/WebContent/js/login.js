var modal = document.getElementById('id01');
var modalSalir = document.getElementById('sure');
var logged;
var jLogin = {
		login: function() {
			
			if (logged == 1){
				logged = 0;
				jUtils.executing("site");
		        jUtils.hiding("message");
		        $.ajax({
		            url: "./login/Logout.do",
		            type: "post",
		            dataType: "html",
		            error: function(hr){
		                jUtils.hiding("result");
		                jUtils.showing("message", hr.responseText);
		            },
		            success: function(html) {
		            	jUtils.showing("site", html);
		            }
		        });
			}
			else {
				logged = 1;
		        jUtils.executing("contenido");
		        jUtils.hiding("message");
		        $.ajax({
		            url: "./login/Login.do",
		            type: "post",
		            dataType: "html",
		            error: function(hr){
		                jUtils.hiding("result");
		                jUtils.showing("message", hr.responseText);
		            },
		            success: function(html) {
		            	$("#loginbutton").val("Cerrar Sesion");
		            	jUtils.showing("contenido", html);
		            }
		        });	
			}
		
		},
		logout : function (val, mod){
			if (mod != null){
				c = document.getElementById(mod);
				c.style.display="none"; 
			} 
			jUtils.executing("site");
			$.ajax({
	            url: "./home/Home.do",
	            type: "post",
	            dataType: "html",
	            error: function(hr){
	                jUtils.hiding("result");
	                jUtils.showing("message", hr.responseText);
	            },
	            success: function(html) {
	            	jUtils.showing("site", html);
	            }
	        });
		},
		
		acceder: function() {
			 jUtils.executing("contenido");
		        jUtils.hiding("message");
		        $.ajax({
		            url: "./login/validarUsuario.do",
		            type: "post",
		            dataType: "html",
		            data: $("#form").serialize(),
		            error: function(hr){
		                jUtils.hiding("result");
		                jUtils.showing("message", hr.responseText);
		            },
		            success: function(html) {
		            	modal.style.display = "none";
		            	jUtils.showing("contenido", html);
		            }
		        });	
		},
		primerIngreso : function () {
			jUtils.executing("site");
	        jUtils.hiding("message");
	        $.ajax({
	            url: "./login/ingresarRegistro.do",
	            type: "post",
	            dataType: "html",
	            error: function(hr){
	                jUtils.hiding("result");
	                jUtils.showing("message", hr.responseText);
	            },
	            success: function(html) {
	            	jUtils.showing("site", html);
	            }
	        });
		},
		registrar : function (){
			jUtils.executing("site");
	        jUtils.hiding("message");
	        $.ajax({
	            url: "./login/registrarUsuario.do",
	            type: "post",
	            dataType: "html",
	            data: $("#form_registro").serialize(),
	            error: function(hr){
	                jUtils.hiding("result");
	                jUtils.showing("message", hr.responseText);
	            },
	            success: function(html) {
	            	jUtils.showing("site", html);
	            }
	        });
		},
		cancelar : function (op, mod){
			if (op == 2){
				alert("Se cancelo la iniciada de sesion");
				logged = 0 ; 
				$('#loginbutton').val('Iniciar Sesion');
			}
			if (op == 0 ) {
				alert("Se cancelo la suscripcion"); 
			}
			this.logout(1, mod);
		}
};


