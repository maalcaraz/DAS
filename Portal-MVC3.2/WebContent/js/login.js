var modal = document.getElementById('id01');
var modalSalir = document.getElementById('sure');
/*var logged;*/
var jLogin = {
		login: function(logged) {
			
			if (logged == 1){
				logged = 0;
		        jUtils.hiding("message");
		        $.ajax({
		            url: "/login/Logout.do",
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
		        jUtils.hiding("message");
		        $.ajax({
		            url: "/login/Login.do",
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
			if(val == 1){
				window.location.replace("/login/Login.do");
			}
			else{
				jUtils.hiding("result");
				window.location.replace("/home/Home.do");
			}
			/*
			$.ajax({
	            url: "/home/Home.do",
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
	        */
		},
		
		/* Utilizada en home. Click en Iniciar Sesion  */
		acceder: function() {
		        jUtils.hiding("message");
		        $.ajax({
		            url: "/login/validarUsuario.do",
		            type: "post",
		            dataType: "html",
		            data: $("#form").serialize(),
		            error: function(hr){
		                jUtils.hiding("result");
		                jUtils.showing("message", hr.responseText);
		            },
		            success: function(html) {
		            	/*modal.style.display = "none";*/
		            	window.location.replace("/home/Home.do");
		            }
		        });	
		},
		/* Utilizada. Click en "registrar" en login */
		primerIngreso : function () {
			jUtils.executing("message");
	        $.ajax({
	            url: "/login/ingresarRegistro.do",
	            type: "post",
	            dataType: "html",
	            error: function(hr){
	                jUtils.showing("message", hr.responseText);
	            },
	            success: function(html) {
	            	jUtils.showing("contenido-modal", html);
	            }
	        });
		},
		/* Utilizada en Registrar usuario. click en registrarse */
		registrar : function (){
			jUtils.executing("message");
	        $.ajax({
	            url: "/login/registrarUsuario.do",
	            type: "post",
	            dataType: "html",
	            data: $("#form_registro").serialize(),
	            error: function(hr){
	                jUtils.hiding("result");
	                jUtils.showing("message", hr.responseText);
	            },
	            success: function(html) {
	            	$("[name='registrar']").hide();
	            	$("[name='cancelbtn']").hide();
	            	jUtils.showing("message", html);
	            }
	        });
		},
		/* Utilizada */
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


