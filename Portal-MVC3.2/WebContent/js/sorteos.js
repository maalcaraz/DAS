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
								<td colspan='3' > <input type='button' class='normal button' onclick='jSorteos.insertar()' value='Guardar'> </td>\
						</tr>";
				$("#tablaSorteos").append(fila);
		},
		insertar : function () {
			fecha = $("#nuevaFecha").val(); 
			if (this.validarFechaSorteo(fecha)){
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
			var fila = 	"<td> </td>\
				<td> <input type='text' name='fechaSorteo' id='nuevaFecha' size='11' maxlength='10'/> </td> \
				<td> <input type='button' class='normal button' onclick='jSorteos.insertar()' value='Guardar'> </td>\
				<td> </td>\
				<td> </td>";
			var id = document.getElementById(idSorteo);
			$(id).innerHTML = fila;
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
		validarFechaSorteo : function(fecha){
			var partes = (fecha || '').split('-');
			var fechaGenerada = new Date(partes[2], --partes[1], partes[0]);
			    
			    if (partes.length == 3 && fechaGenerada
			     && partes[0] == fechaGenerada.getDate()
			     && partes[1] == fechaGenerada.getMonth()
			     && partes[2] == fechaGenerada.getFullYear()) {
			        return true;
			    }
			    return false;
		}
};