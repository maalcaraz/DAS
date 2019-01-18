<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<head>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<fmt:setLocale value="${ sessionScope.lang }" scope="session"/>
<fmt:setBundle basename="ar.edu.ubp.das.src.portal.properties.messages" var="etq"/>
</head>

<h3> <fmt:message key="registroExitoso" bundle="${etq}"></fmt:message> </h3>

<button type="button"  onclick="window.location.replace('/login/Login.do')"><fmt:message key="volver" bundle="${etq}"></fmt:message></button>


