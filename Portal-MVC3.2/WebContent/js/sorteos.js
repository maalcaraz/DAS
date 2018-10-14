var jSorteos = {
	
		getSorteos : function (){
			 $.ajax({
		            url: "./sorteos/MostrarSorteos.do",
		            type: "post",
		            dataType: "html",
		            error: function(hr){
		                jUtils.showing("concesionarias", hr.responseText);
		            },
		            success: function(html) {
		            	
		            	jUtils.showing("concesionarias", html);
		            }
		        });	
		},
		nuevoSorteo : function () {
			$.ajax({
	            url: "./sorteos/CargarSorteo.do",
	            type: "post",
	            dataType: "html",
	            error: function(hr){
	                jUtils.showing("concesionarias", hr.responseText);
	            },
	            success: function(html) {
	            	
	            	jUtils.showing("concesionarias", html);
	            }
	        });	
		},
		insertar : function () {
			alert($("#nuevaFecha").val());
			$.ajax({
	            url: "./sorteos/InsertarNuevo.do",
	            type: "post",
	            data: {"nuevaFecha" : $("#nuevaFecha").val()},
	            dataType: "html",
	            error: function(hr){
	                jUtils.showing("concesionarias", hr.responseText);
	            },
	            success: function(html) {
	            	
	            	jUtils.showing("concesionarias", html);
	            }
	        });	
		}
};