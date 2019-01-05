var jSorteos = {
		obtenerSorteos : function (){
			jUtils.executing("main-content");
			 $.ajax({
		            url: "/sorteos/MostrarSorteos.do",
		            type: "post",
		            dataType: "html",
		            error: function(hr){
		                jUtils.showing("main-content", hr.responseText);
		            },
		            success: function(html) {
		            	jUtils.showing("main-content", html);
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
			if (this.validarFechaSorteo(fecha) == true){
				jUtils.executing("main-content");
				$.ajax({
		            url: "/sorteos/InsertarNuevo.do",
		            type: "post",
		            data: {"nuevaFecha" : fecha},
		            dataType: "html",
		            error: function(hr){
		                jUtils.showing("main-content", hr.responseText);
		            },
		            success: function(html) {
		            	jUtils.showing("main-content", html);
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
			jUtils.executing("main-content");
			$.ajax({
	            url: "/sorteos/EliminarSorteos.do",
	            type: "post",
	            dataType: "html",
	            data: {"sorteosAEliminar": sel.toString()},
	            error: function(hr){
	                jUtils.showing("main-content", hr.responseText);
	            },
	            success: function(html) {
	            	jUtils.showing("main-content", html);
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
		/*no usada*/
		guardarSorteo : function (idSorteo) {
			var nuevaFecha = $("#nuevaFecha").val();
			alert("idSorteo: "+ idSorteo);
			alert("nuevaFecha: "+nuevaFecha);
			jUtils.executing("contenido-admin");
			$.ajax({
	            url: "/sorteos/GuardarSorteo.do",
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
			jUtils.executing("main-content");
			$.ajax({
	            url: "/sorteos/ProximasFechas.do",
	            type: "post",
	            dataType: "html",
	            error: function(hr){
	                jUtils.showing("main-content", hr.responseText);
	            },
	            success: function(html) {
	            	jUtils.showing("main-content", html);
	            }
	        });
		},
		/*no usada*/
		resultadosUltimoSorteo : function () {
			jUtils.executing("contenido");
			$.ajax({
	            url: "/sorteos/ResultadosUltimoSorteo.do",
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
			var hoy = new Date();
			hoy = new Date(hoy.getDay(), hoy.getMonth()+1, hoy.getFullYear());
			var fechaGenerada = new Date(partes[0], --partes[1], partes[2]);
			console.log("Fecha de hoy:"+hoy.getDate());
			console.log("Fecha generada: "+fechaGenerada.getDate());
			if (fechaGenerada && (fechaGenerada.getDate() >= hoy.getDate())  ) {
				return true;
			}
			else return false;
		}
};