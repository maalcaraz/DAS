<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script type="text/javascript" src="/util/Javascript.do/load=jquery,jquery.i18n.properties,utils,portal,cuentas,login,concesionarias,sorteos,clientes,bootstrap.bundle.min,scripts,jquery.localscroll,jquery.nicescroll,jquery.scrollTo.min,jquery.slimscroll.min"></script>
<link type="text/css" rel="stylesheet" href="/util/StyleSheet.do/load=bootstrap.min,bootstrap-theme,style,style-responsive,line-icons,elegant-icons-style,font-awesome.min" />
<link href="https://fonts.googleapis.com/css?family=Lato" rel="stylesheet">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<fmt:setLocale value="${ sessionScope.lang }" scope="session" />
<fmt:setBundle basename="ar.edu.ubp.das.src.portal.properties.messages" var="etq" />

<title><fmt:message key="home" bundle="${etq}"></fmt:message></title>
</head>
<body>
	<section id="container" class="">


		<header class="header dark-bg">
			<div class="toggle-nav">
				<div class="icon-reorder tooltips" data-original-title="Toggle Navigation" data-placement="bottom">
					<i class="icon_menu"></i>
				</div>
			</div>

			<!--logo start-->
			<a href="/home/Home.do" class="logo"><span class="lite">Portal </span><fmt:message key="gobierno" bundle="${etq}"></fmt:message></a>
			<!--logo end-->
			
			<div class="top-nav notification-row"> 
        		<ul class="nav pull-right top-menu">
					<select name="lenguaje" onchange="jPortal.langOnSelect(this)" class="btn text-primary bg-dark border-dark">
						<option value="lenguaje"><fmt:message key="lenguaje" bundle="${etq}"></fmt:message>
						</option>
						<option value="es">
							<fmt:message key="espaniol" bundle="${etq}"></fmt:message>
						</option>
						<option value="en">
							<fmt:message key="ingles" bundle="${etq}"></fmt:message>
						</option>
					</select>
        
			        <li id="alert_notificatoin_bar" class="dropdown">
			            <a data-toggle="dropdown" class="dropdown-toggle" href="#">
							<i class="icon-task-l"></i>
			            </a>
			            <ul class="dropdown-menu extended notification">
			              <li>
			                <a href="#proximasfechas" onclick="jSorteos.proximasFechas()">
	                            <span class="label label-primary"><i class="icon_menu"></i></span>
	                            <fmt:message key="proximas_fechas" bundle="${etq}"></fmt:message>
			                </a>
			              </li>
			              <li>
			                <a href="#" onclick="jConcesionaria.mostrarAdheridas()">
                                <span class="label label-warning"><i class="icon_menu"></i></span>
                                <fmt:message key="concesionarias_adheridas" bundle="${etq}"></fmt:message>
			                </a>
			              </li>
			              <li>
			                <a href="#resultadosSorteo" onclick="jConcesionaria.obtenerGanadores()">
			                	<span class="label label-danger"><i class="icon_menu"></i></span>
			                    <fmt:message key="historico" bundle="${etq}"></fmt:message>
			                </a>
			              </li>
			            </ul>
			        </li>

	          <!-- user login dropdown start-->
	          		<li class="dropdown">
	            	<a data-toggle="dropdown" class="dropdown-toggle" href="#">
	                	<span class="username">Admin</span>
	                    <b class="caret"></b>
	            	</a>
	            <ul class="dropdown-menu extended logout">	
	             
	              <li>
	                <a href="#" id="loginbutton" name="loginbutton" onclick="jLogin.logout(1)"><i class="icon_key_alt"></i> <fmt:message key="cerrar_sesion" bundle="${etq}"></fmt:message></a>
	              </li>
	            </ul>
	          </li>
          <!-- user login dropdown end -->
        		</ul>
        	<!-- notification dropdown end-->
      		</div>

		</header>

		<!--sidebar start-->
		<aside>
			<div id="sidebar" class="nav-collapse " tabindex="5000" style="overflow: hidden; outline: none; margin-left: 0px;">
				<!-- sidebar menu start-->
				<ul class="sidebar-menu" style="display: block;">
					<li class="active">
						<a class="" href="/home/Home.do"> 
							<i class="icon_house_alt"></i> 
							<span>
								<fmt:message key="home" bundle="${etq}"></fmt:message>
							</span>
						</a>
					</li>
					
					<li>
						<a onclick="jConcesionaria.mostrarRegistradas()" href="#"> 
							<i class="icon_document_alt"></i> 
							<span>
								<fmt:message key="concesionarias" bundle="${etq}"></fmt:message>
							</span>
						</a>
					</li>
					
					<li class="sub-menu">
						<a href="#">
						
							<i class="icon_calendar"></i> 
							<span>
								<fmt:message key="sorteos" bundle="${etq}"></fmt:message>
							</span>
						</a>
						<ul class="sub" style="overflow: hidden; display: none;">
							<li><a onclick="jSorteos.obtenerSorteos()" href="#">Fechas</a></li>
			              	<li><a onclick="jConcesionaria.obtenerGanadores()" href="#">Ganadores</a></li>
            			</ul>
					</li>
					
					<li><a onclick="jClientes.datosTodosClientes()" href="#"> 
						<i class="icon_contacts_alt"></i> 
						<span>
							<fmt:message key="clientes" bundle="${etq}"></fmt:message>
						</span>
						</a>
					</li>
					
					<li>
						<a onclick="jConcesionaria.testing()" href="#"> 
						<i class="icon_tool"></i> 
						<span>Testing</span>
						</a>
					</li>

				</ul>
				
				
				<!-- sidebar menu end-->
			</div>
		</aside>
		<!--sidebar end-->

		<section id="main-content">
	      <section class="wrapper">
	        <div class="row">
	          <div class="col-lg-12">
	            <h3 class="page-header"><i class="fa fa fa-home"></i> <fmt:message key="home" bundle="${etq}"></fmt:message></h3>
	            <ol class="breadcrumb">
	              <li><i class="fa fa-home"></i><a href="index.html"><fmt:message key="home" bundle="${etq}"></fmt:message></a></li>
	            </ol>
	          </div>
	        </div>
	        <!-- page start-->
	        <div class="main" id="contenido-admin">
	        	<div class="row">
					<div class="col-lg-6">
						<img src="/img/ford.png" alt="New York">
					</div>
					<br>
					<p class="col-lg-6  align-middle">
						<fmt:message key="frase_home" bundle="${etq}"></fmt:message>
					</p>
				</div>
			</div>
	        <!-- page end-->
	      </section>
	    </section>
		<!--main content end-->
	</section>
</body>
</html>