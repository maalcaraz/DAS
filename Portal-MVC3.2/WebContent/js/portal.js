var jPortal = {
		lang : function(lang){
			$.ajax({
	            url: "./portal/Internacionalizar.do",
	            type: "post",
	            dataType: "html",
	            data: {"lang": lang},
	            error: function(hr){
	                jUtils.hiding("result");
	                jUtils.showing("message", hr.responseText);
	            },
	            success: function(html) {
	            	jUtils.showing("site", html);
	            }
	        });
		}	
};