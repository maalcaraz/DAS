var jCuentas = {
		buscarClientes: function() {
	        jUtils.executing("result");
	        jUtils.hiding("message");
	        $.ajax({
	            url: "./cuentas/Buscar.do",
	            type: "post",
	            dataType: "html",
	            data:$("#form").serialize(),
	            error: function(hr){
	                jUtils.hiding("result");
	                jUtils.showing("message", hr.responseText);
	            },
	            success: function(html) {
	                jUtils.showing("result", html);
	            }
	        });		
		}
};