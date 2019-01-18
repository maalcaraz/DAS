var modal = document.getElementById('id02');
var jClientes ={
		/* NO se usa actualmente. Usada en lo que era home cliente. */
		estadoCuenta : function(){
			jUtils.executing("contenido-admin");
	        jUtils.hiding("message");
	        $.ajax({
	            url: "/clientes/EstadoCuenta.do",
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
	       /* NO se usa actualmente. Usada en lo que era home cliente. */
	       datosCliente : function(){
				jUtils.executing("contenido-admin");
		        jUtils.hiding("message");
		        $.ajax({
		            url: "/clientes/DatosCliente.do",
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
		       datosTodosClientes : function(){
					jUtils.executing("main-content");
			        jUtils.hiding("message");
			        $.ajax({
			            url: "/clientes/EstadoDatosClientes.do",
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