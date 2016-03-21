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

 	<style type="text/css">
        <%@include file="../../resources/bootstrap/css/bootstrap.min.css" %>
    </style>

    
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
						<input type="hidden" name="${_csrf.parameterName}"
							value="${_csrf.token}" />
					</form>

					<script>
						function formSubmit() {
							document.getElementById("logoutForm").submit();
						}
					</script>
				
				<div class="navbar-form navbar-right">
					<a href="javascript:formSubmit()"class="btn btn-danger">Logout </a>
				</div>	
				</sec:authorize>		
    		
    		   		
    	</div>
    </nav>
	
	<section>	
		<sec:authorize access="isAuthenticated()"> 
			 <div class="col-md-2" id="leftCol">
              	
				<div class="well"> 
              	<ul class="nav nav-stacked" id="sidebar">
                  <li><a href="/seguimientoPYMES/admin/centros">Centros</a></li>
                  <li><a href="/seguimientoPYMES/admin/empleados">Empleados</a></li>
                  <li><a href="/seguimientoPYMES/admin/productos">Productos</a></li>
                  <li><a href="#sec4">Section 4</a></li>
              	</ul>
  				</div>

      		</div> 
      	</sec:authorize>		
      		
				<tiles:insertAttribute name="content"/> 
			
    </section>
    

    
</body>
</html>