<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<script type="text/javascript" src="/util/Javascript.do/load=jquery,jquery.i18n.properties,utils,cuentas,login,concesionarias" ></script>
	<link type="text/css" rel="stylesheet" href="/util/StyleSheet.do/load=messages,login" />

<fmt:setLocale value="${ sessionScope.lang }" scope="session"/>
<fmt:setBundle basename="ar.edu.ubp.das.src.portal.properties.messages" var="etq"/>


 <div id="sure" class="modal">
  <span onclick="document.getElementById('id01').style.display='none'" class="close" title="Close">&times;</span>

	<h1>Esta seguro de que quiere salir? </h1>

	<span>
	<input type="button" class="normal button" value="Si" onclick="jLogin.logout(1, 'sure')">
	<input type="button" class="normal button" value="No" onclick="jLogin.logout(0, 'sure')">
	</span>
	
</div>

 <script type="text/javascript">document.getElementById('sure').style.display='block'</script>