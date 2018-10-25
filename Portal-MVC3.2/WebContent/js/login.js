var modal = document.getElementById('id01');
var modalSalir = document.getElementById('sure');
var logged;
var jLogin = {
		login: function() {
			
			if (logged == 1){
				logged = 0;
				jUtils.executing("result");
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
		            	$("#loginbutton").val("Iniciar Sesion");
		            	jUtils.showing("contenido-admin", html);
		            }
		        });
			}
			else {
				logged = 1;
		        jUtils.executing("result");
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
		            	jUtils.showing("contenido-admin", html);
		            }
		        });	
			}
		
		},
		logout : function (val){
			if (val == 1){
				modalSalir.style.display = "none";
				$("#site").val(" ");
			}
			else {
				modalSalir.style.display = "none";
				
			}
		},
		
		acceder: function() {
			 jUtils.executing("result");
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
		            	jUtils.showing("site", html);
		            }
		        });	
		},
		primerIngreso : function () {
			jUtils.executing("result");
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
			jUtils.executing("result");
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
		}
};


