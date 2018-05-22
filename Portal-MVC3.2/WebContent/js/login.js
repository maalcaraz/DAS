var modal = document.getElementById('id01');

var jLogin = {
		login: function() {
			jUtils.executing("result");
	        jUtils.hiding("message");
	        $.ajax({
	            url: "./login/Login.do",
	            type: "post",
	            dataType: "html",
	            error: function(hr){
	                jUtils.hiding("result");
	                jUtils.showing("message", hr.responseText);
	            },
	            success: function(html) {
	            	$("#login").val("Cerrar Sesion");
	            	
	            	jUtils.showing("content", html);
	            	
	            }
	        });	
		},
		
		logout : function (){
			alert("Logging out");
			
			$("#login").val("Iniciar Sesion");
		},
		
		acceder: function() {
			 jUtils.executing("result");
		        jUtils.hiding("message");
		        $.ajax({
		            url: "./login/validateLogin.do",
		            type: "post",
		            dataType: "html",
		            data: $("#form").serialize(),
		            error: function(hr){
		                jUtils.hiding("result");
		                jUtils.showing("message", hr.responseText);
		            },
		            success: function(html) {
		            	modal.style.display = "none";
		            	jUtils.showing("content", html);
		            }
		        });	
		}	
};


window.onclick = function(event) {
    if (event.target == modal) {
        modal.style.display = "none";
    }
}

