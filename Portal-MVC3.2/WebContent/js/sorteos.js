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
			var fila = 	"<tr>\
								<td> </td>\
								<td> <input type='text' name='fechaSorteo' id='nuevaFecha' size='11' maxlength='10'/> </td> \
								<td> </td>\
								<td> </td>\
								<td> </td>\
						</tr>";
				$("#tablaSorteos").append(fila);
		},
		insertar : function () {
			fecha = $("#nuevaFecha").val(); 
			alert(fecha);
			if (validarFechaSorteo()){
				$.ajax({
		            url: "./sorteos/InsertarNuevo.do",
		            type: "post",
		            data: {"nuevaFecha" : fecha},
		            dataType: "html",
		            error: function(hr){
		                jUtils.showing("contenido-admin", hr.responseText);
		            },
		            success: function(html) {
		            	jUtils.showing("contenido-admin", html);
		            }
		        });
			}
			else {
				alert ("La fecha que intenta insertar no es valida");
				$("#nuevaFecha").val("");
			}
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
		},
		resultadosUltimoSorteo : function () {
			$.ajax({
	            url: "./sorteos/ResultadosUltimoSorteo.do",
	            type: "post",
	            dataType: "html",
	            error: function(hr){
	                jUtils.showing("contenido", hr.responseText);
	            },
	            success: function(html) {
	            	jUtils.showing("contenido", html);
	            }
	        });
		},
		validarFechaSorteo : function (fecha){
			return true;
		}
};