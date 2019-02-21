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
			var id = document.getElementById("nuevos-sorteos");
			nuevoSorteo = "<div class=\"col-lg-4\">" +
							"<section class=\"panel\" >" +
								"<header class=\"panel-heading\">" +
									"Nuevo sorteo " +
								"</header>" +
								"<div class=\"panel-body\">" +
									"<div class=\"panel panel-primary\">" +
									"Nueva Fecha <input type='date' name='fechaSorteo' id='nuevaFecha' size='11' maxlength='10'/>"+
									"<input type='button' class='normal button' onclick='jSorteos.insertar()' value='Guardar'>" +
									"</div>" +
								"</div>" +
							"</section>" +
						"</div>";
			id.innerHTML += nuevoSorteo;
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
		eliminarSorteo : function(idSorteo){
			jUtils.executing("main-content");
			$.ajax({
	            url: "/sorteos/EliminarSorteos.do",
	            type: "post",
	            dataType: "html",
	            data: {"sorteoAEliminar": idSorteo},
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
			var nuevoSorteo = "<section class=\"panel\" >" +
								"<header class=\"panel-heading\">" +
									"Editar sorteo " +
								"</header>" +
								"<div class=\"panel-body\">" +
									"<div class=\"panel panel-primary\">" +
									"Nueva Fecha <input type='date' name='fechaSorteo' id='fechaSorteo' size='11' maxlength='10'/>"+
									"<input type='button' class='normal button' onclick='jSorteos.guardarSorteo(\""+idSorteo+"\")' value='Guardar'>" +
									"</div>" +
								"</div>" +
								"</section>";
			id.innerHTML = nuevoSorteo;	
		},
		
		guardarSorteo : function (idSorteo) {
			var nuevaFecha = $("#fechaSorteo").val();
			if (this.validarFechaSorteo(nuevaFecha) == true){
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
		            	jUtils.showing("main-content", html);
		            }
		        });
			}
			else {
				alert ("La fecha que intenta insertar no es valida");
			}
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
			hoy = new Date(hoy.getFullYear(),hoy.getMonth(),hoy.getDate());
			var fechaGenerada = new Date(partes[0], --partes[1], partes[2]);
			console.log("Fecha de hoy:"+hoy);
			console.log("Fecha generada: "+fechaGenerada);
			if (fechaGenerada && (fechaGenerada >= hoy )  ) {
				return true;
			}
			else return false;
		},
		verResultados : function(idSorteo){
			$.ajax({
	            url: "/sorteos/VerResultadosSorteo.do",
	            type: "post",
	            dataType: "html",
	            data: {"idSorteo":idSorteo},
	            error: function(hr){
	                jUtils.showing("contenido", hr.responseText);
	            },
	            success: function(html) {
	            	jUtils.showing("resultadosSorteo", html);
	            }
	        });
		}
};