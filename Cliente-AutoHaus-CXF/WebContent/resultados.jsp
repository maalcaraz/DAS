<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<h1> <%
		String res = (request.getAttribute("resultado") == null) ? "No recibimos nada" : (String) request.getAttribute("resultado"); 
		out.println(res);
		%>
</h1>