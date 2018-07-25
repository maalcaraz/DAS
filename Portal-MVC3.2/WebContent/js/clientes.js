var modal = document.getElementById('id02');
var jClientes ={
		
		estadoCuenta : function(){
			jUtils.executing("result");
	        jUtils.hiding("message");
	        $.ajax({
	            url: "./clientes/Cliente.do",
	            type: "post",
	            dataType: "html",
	            error: function(hr){
	            	alert("error");
	                jUtils.hiding("result");
	                jUtils.showing("message", hr.responseText);
	            },
	            success: function(html) {
	            
	            	alert("sucess");
	            	jUtils.showing("detalle-cliente", html);
	            	
	            }
	        });
	       }
		
};