var jSorteos = {
		obtenerSorteos : function (){
			jUtils.executing("contenido-admin");
			 $.ajax({
		            url: "/sorteos/MostrarSorteos.do",
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
								<td> <input type='date' name='fechaSorteo' id='nuevaFecha' size='11' maxlength='10'/> </td> \
								<td> </td>\
								<td> </td>\
								<td colspan='3' > <input type='button' class='normal button' onclick='jSorteos.insertar()' value='Guardar'> </td>\
						</tr>";
				$("#tablaSorteos").append(fila);
		},
		insertar : function () {
			fecha = $("#nuevaFecha").val(); 
			if (this.validarFechaSorteo(fecha)){
				jUtils.executing("contenido-admin");
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
			jUtils.executing("contenido-admin");
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
			var id = document.getElementById(idSorteo);
			var fila = 	"<td> </td>\
				<td> <input type='date' name='nuevaFecha' id='nuevaFecha' size='11' maxlength='10'/> </td> \
				<td> </td>\
				<td> </td>\
				<td> <input type='button' class='normal button' onclick='jSorteos.guardarSorteo(\""+idSorteo+"\")' value='Guardar'>  </td>";
			id.innerHTML = fila;
			
		},
		guardarSorteo : function (idSorteo) {
			var nuevaFecha = $("#nuevaFecha").val();
			alert("idSorteo: "+ idSorteo);
			alert("nuevaFecha: "+nuevaFecha);
			jUtils.executing("contenido-admin");
			$.ajax({
	            url: "./sorteos/GuardarSorteo.do",
	            type: "post",
	            dataType: "html",
	            data: {"idSorteo": idSorteo, "nuevaFecha": nuevaFecha},
	            error: function(hr){
	                jUtils.showing("contenido-admin", hr.responseText);
	            },
	            success: function(html) {
	            	jUtils.showing("contenido-admin", html);
	            }
	        });
		},
		proximasFechas : function () {
			jUtils.executing("contenido-admin");
			$.ajax({
	            url: "/sorteos/ProximasFechas.do",
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
		resultadosUltimoSorteo : function () {
			jUtils.executing("contenido");
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
			var fechaGenerada = new Date(partes[0], --partes[1], partes[2]);
			var hoy = new Date();
			console.log("hoy:"+hoy);
			    console.log("Generada: "+fechaGenerada);
			    if (fechaGenerada &&
			     fechaGenerada >= hoy  ) {
			        return true;
			    }
			    return false;
		}
};