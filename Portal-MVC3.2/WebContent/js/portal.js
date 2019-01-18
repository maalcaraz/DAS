var jPortal = {
		lang : function(lang){
			$.ajax({
	            url: "/portal/Internacionalizar.do",
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
		},
		langOnSelect : function(lang){
			$.ajax({
	            url: "/portal/Internacionalizar.do",
	            type: "post",
	            dataType: "html",
	            data: {"lang": lang.value},
	            error: function(hr){
	                jUtils.hiding("result");
	                jUtils.showing("message", hr.responseText);
	            },
	            success: function(html) {
	            	location.reload();
	            }
	        });
		},
		mandarMail : function(){
			nomDiv = "respuesta-mail";
			jUtils.executing(nomDiv);
	        jUtils.hiding("message");
	        $.ajax({
	            url: "/portal/enviarMail.do",
	            type: "post",
	            dataType: "html",
	            error: function(hr){
	                jUtils.hiding("result");
	                jUtils.showing("message", hr.responseText);
	            },
	            success: function(html) {
	            	nomDiv = "respuesta-mail";
	            	jUtils.showing(nomDiv, html);
	            }
	        });	
		},
		
};