<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
   <script type="text/javascript" src="./js/jquery.js"></script>
   
   <title>Reporte de Error</title>            
</head>
<body>
<h2>
<%
	
	out.print(request.getAttribute("servicio"));
	%>

</h2>

	<%
	out.println(request.getAttribute("error"));
	//out.print(request.getAttribute("servicio"));
	%>

</body>
</html>