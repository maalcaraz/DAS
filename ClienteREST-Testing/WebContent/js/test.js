var jTest = {
	
		consumir : function(){
			jUtils.executing("result");
			$.ajax({
				url: "./consumir.jsp",
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