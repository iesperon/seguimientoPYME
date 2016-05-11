<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="sec"	uri="http://www.springframework.org/security/tags"%>


<html>

<head>
    <title> MiPymeOnline </title>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">

 	<!-- JQUERY -->    
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
	<script src="http://code.jquery.com/jquery-migrate-1.3.0.js"></script>
 	
 	<!-- BOOTSTRAP -->    
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
    
 	<!-- DATATABLES -->    
	<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/t/bs/dt-1.10.11/datatables.min.css"/>
	<script src="https://cdn.datatables.net/t/bs/dt-1.10.11/datatables.min.js"></script> 
	 
	<!-- D3.js -->    
	<script src="//d3js.org/d3.v3.min.js" charset="utf-8"></script>
	
	 <script src="<c:url value="/resources/typeahead.bundle.js" />"></script>
	 <script src="<c:url value="/resources/jquery.scrollIntoView.min.js" />"></script>
	 
</head>

<body>

	<nav class="navbar navbar-inverse">
 		 <div class="container-fluid">
 		 <sec:authorize access="not isAuthenticated()">
 		 	<div class="navbar-header">
      			<a class="navbar-brand" href="<c:url value="/" />">MiPymeOnline</a>
    		</div>
		</sec:authorize>
		
		<sec:authorize access="isAuthenticated()">
 		 	<div class="navbar-header">
      			<a class="navbar-brand" href="<c:url value="/admin/centros" />">MiPymeOnline</a>
    		</div>
		</sec:authorize>
    		
    		
    	<sec:authorize access="not isAuthenticated()">
    		
    		<form:form action="login" name="login" method="POST" class="navbar-form navbar-right" role="search">
        		<div class="form-group">
        			<div class="input-group">
						<span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
          				<input name="email" type="text" class="form-control" placeholder="Email"/>
          			</div>
          			<div class="input-group">
          				<span class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></span>
          				<input name="contrasena" type="password" class="form-control" placeholder="Contraseña"/>
        			</div>
        		</div>
        		<input type="submit" class="btn btn-primary" value="Aceptar">
        		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
      		</form:form>		    		
    	
    	</sec:authorize>
    		
    		
    		<!-- LOGOUT -->
				<!--Esta autorizado-->
				<sec:authorize access="isAuthenticated()">
				
				 
				<form action=<c:url value="/logout"/> method="post" class="navbar-form navbar-right"  id="logoutForm">
						<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
				</form>

					<script>
						function formSubmit() {
							document.getElementById("logoutForm").submit();
						}
					</script>
				
				<ul class="nav navbar-nav navbar-right">
					
					<li class="dropdown">
  								<a href="#" class="dropdown-toggle" data-toggle="dropdown">
    								Mi cuenta, <sec:authentication property="principal.username" />
    								<span class="caret"></span>
  								</a>
  								<ul class="dropdown-menu" aria-labelledby="dLabel">
									<li> <a href='/seguimientoPYMES/admin/miempresa'><i class="glyphicon glyphicon-briefcase"></i> Mi empresa</a>	</li>
									<li> <a href='/seguimientoPYMES/admin/miperfil'><i class="glyphicon glyphicon-user"></i> Mi perfil</a> </li>
									<li class="divider"></li>
									<li><a href="javascript:formSubmit()"><i class="glyphicon glyphicon-off"></i> Logout</a></li>
									
								</ul>
					
					</li>
					
				</ul>	
				</sec:authorize>		
    		
    		   		
    	</div>
    </nav>
	
	<section>	
		<sec:authorize access="isAuthenticated()"> 
			 <div class="col-md-2" id="leftCol">
              	
				<div class="well"> 
              	<ul class="nav nav-stacked" id="sidebar">
                  <li><a href="/seguimientoPYMES/admin/centros"><i class="glyphicon glyphicon-home"></i> Centros</a></li>
                  <li><a href="/seguimientoPYMES/admin/empleados"><i class="glyphicon glyphicon-user"></i> Empleados</a></li>
                  <li><a href="/seguimientoPYMES/admin/productos"><i class="glyphicon glyphicon-shopping-cart"></i> Productos</a></li>
                  <li><a href="/seguimientoPYMES/admin/gastos"><i class="glyphicon glyphicon-euro"></i> Gastos</a></li>
                  <li><a href="/seguimientoPYMES/admin/cajaCentro"><i class="glyphicon glyphicon-barcode"></i> Caja</a></li>
                  <li><a href="/seguimientoPYMES/admin/tickets"><i class="glyphicon glyphicon-list-alt"></i> Tickets</a></li>
                  <li><a href="/seguimientoPYMES/admin/proveedores"><i class="glyphicon glyphicon-briefcase"></i> Proveedores</a></li>
                  <li><a href="/seguimientoPYMES/admin/cierresCaja"><i class="glyphicon glyphicon-floppy-saved"></i> Cierres de caja	</a></li>
                  
                  
              	</ul>
  				</div>

      		</div> 
      	</sec:authorize>		
      		
				<tiles:insertAttribute name="content"/> 
			
    </section>
    

    
</body>
</html>