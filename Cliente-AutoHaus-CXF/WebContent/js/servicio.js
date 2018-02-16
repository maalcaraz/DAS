var jServicio = {
		buscarClientes: function() {
	        //jUtils.executing("result");
	        $.ajax({
	            url: "./buscarEstados.jsp",
	            type: "get",
	            dataType: "html",
	            //data: $.param($("input[type=text],input[type=radio]:checked", $("#form"))),
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