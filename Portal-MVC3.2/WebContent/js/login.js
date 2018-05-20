var jLogin = {
		login: function() {
			window.location.replace("./login/Login.do");
			 /*jUtils.executing("result");
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
		            	//window.location.replace("./cuentas/Default.do");
		            }
		        });		*/
		},
		
		acceder: function() {
			/*window.location.replace("./login/validateLogin.do");*/
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
		            success: function() {
		            	//window.location.replace("./cuentas/Default.do");
		            }
		        });		
			
		}	
};

// Get the modal
var modal = document.getElementById('id01');

// When the user clicks anywhere outside of the modal, close it
window.onclick = function(event) {
    if (event.target == modal) {
        modal.style.display = "none";
    }
}

