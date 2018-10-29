var modal = document.getElementById('id02');
var jClientes ={
		
		estadoCuenta : function(){
			jUtils.executing("result");
	        jUtils.hiding("message");
	        $.ajax({
	            url: "./clientes/EstadoCuenta.do",
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
	       datosCliente : function(){
				jUtils.executing("result");
		        jUtils.hiding("message");
		        $.ajax({
		            url: "./clientes/DatosCliente.do",
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
		       }
};