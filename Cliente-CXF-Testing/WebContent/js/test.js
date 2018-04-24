var jTest = {
	
		obtener : function(){
			jUtils.executing("result");
			$.ajax({
				url: "./getClientes.jsp",
				type: "post",
				dataType: "html",
				error: function(hr) {
					jUtils.hiding("result");
					jUtils.showing("message", hr.responseText);
				},
				success: function(html) {
					jUtils.showing("result", html);
				}		
			});
		},
		notificar : function(){
			jUtils.executing("result");
			$.ajax({
				url: "./notificarGanador.jsp",
				type: "post",
				dataType: "html",
				error: function(hr) {
					jUtils.hiding("result");
					jUtils.showing("message", hr.responseText);
				},
				success: function(html) {
					jUtils.showing("result", html);
				}		
			});
		},
		verificar : function(){
			jUtils.executing("result");
			$.ajax({
				url: "./verificarCancelado.jsp",
				type: "post",
				dataType: "html",
				error: function(hr) {
					jUtils.hiding("result");
					jUtils.showing("message", hr.responseText);
				},
				success: function(html) {
					jUtils.showing("result", html);
				}		
			});
		}
};