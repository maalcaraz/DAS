var jConcesionarias ={
		
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
	            	$("#login").val("Cerrar Sesion");
	            	
	            	jUtils.showing("content", html);
	            	
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
	            	$("#login").val("Cerrar Sesion");
	            	
	            	jUtils.showing("content", html);
	            	
	            }
	        });	
		}
};