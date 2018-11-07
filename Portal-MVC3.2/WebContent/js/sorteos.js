var jSorteos = {
	
		obtenerSorteos : function (){
			 $.ajax({
		            url: "./sorteos/MostrarSorteos.do",
		            type: "post",
		            dataType: "html",
		            error: function(hr){
		                jUtils.showing("contenido-admin", hr.responseText);
		            },
		            success: function(html) {
		            	jUtils.showing("contenido-admin", html);
		            }
		        });	
		},
		nuevoSorteo : function () {
			$.ajax({
	            url: "./sorteos/CargarSorteo.do",
	            type: "post",
	            dataType: "html",
	            error: function(hr){
	                jUtils.showing("contenido-admin", hr.responseText);
	            },
	            success: function(html) {
	            	
	            	jUtils.showing("contenido-admin", html);
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
	                jUtils.showing("contenido-admin", hr.responseText);
	            },
	            success: function(html) {
	            	
	            	jUtils.showing("contenido-admin", html);
	            }
	        });	
		},
		eliminarSorteos : function(){
			var sel = [];
			$("input:checkbox:checked").each(function () {
				sel.push($(this).val());
			});
			alert(sel);
			$.ajax({
	            url: "./sorteos/EliminarSorteos.do",
	            type: "post",
	            dataType: "html",
	            data: {"sorteosAEliminar": sel.toString()},
	            error: function(hr){
	                jUtils.showing("contenido-admin", hr.responseText);
	            },
	            success: function(html) {
	            	jUtils.showing("contenido-admin", html);
	            }
	        });	
		},
		editarSorteo : function (idSorteo) {
			$.ajax({
	            url: "./sorteos/EditarSorteo.do",
	            type: "post",
	            dataType: "html",
	            data: {"idSorteo": idSorteo},
	            error: function(hr){
	                jUtils.showing("contenido-admin", hr.responseText);
	            },
	            success: function(html) {
	            	jUtils.showing("contenido-admin", html);
	            }
	        });
		},
		guardarSorteo : function () {
			$.ajax({
	            url: "./sorteos/GuardarSorteo.do",
	            type: "post",
	            dataType: "html",
	            data: $("#nuevoSorteoForm").serialize(),
	            error: function(hr){
	                jUtils.showing("contenido-admin", hr.responseText);
	            },
	            success: function(html) {
	            	jUtils.showing("contenido-admin", html);
	            }
	        });
		},
		proximasFechas : function () {
			$.ajax({
	            url: "./sorteos/ProximasFechas.do",
	            type: "post",
	            dataType: "html",
	            error: function(hr){
	                jUtils.showing("contenido", hr.responseText);
	            },
	            success: function(html) {
	            	jUtils.showing("contenido", html);
	            }
	        });
		}
};