<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>


<html>

<head>
    <title> MiPymeOnline </title>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">

 	<style type="text/css">
        <%@include file="../../resources/css/bootstrap.min.css" %>
    </style>
 
    
</head>

<body>

	<nav class="navbar navbar-inverse">
 		 <div class="container-fluid">
    		<div class="navbar-header">
      			<a class="navbar-brand" href="<c:url value="/" />">MiPymeOnline</a>
    		</div>
    		
    		<form class="navbar-form navbar-right" role="search">
        		<div class="form-group">
          			<input type="text" class="form-control" placeholder="Email">
          			<input type="password" class="form-control" placeholder="Contraseña">
        		</div>
        		<button type="submit" class="btn btn-primary">Aceptar</button>
      		</form>
    	</div>
    </nav>
	
	<div class="row">
		<div class="col-sm-4"></div>
		<div class="col-sm-4">
		 	<img src="<c:url value="/resources/images/logo11.png" />" class="img-rounded" alt="Logo" width="304" height="236">
		</div>
			
	</div>

	 <div class="row">
  		<div class="col-sm-6">
  			<h2>Descripción: </h2>
  				<div class="row">
  				  		<div class="col-sm-1"></div>
						<div class="col-sm-8">
						<p>
						La importancia de tener un sistema de control interno en las pequeñas y medianas empresas ha ido incrementado en los últimos años, 
						siendo esta la parte que se encarga de la organización de los procedimientos y necesidades del negocio, así como de verificar la eficiencia y la 
						productividad en las operaciones. Por todo esto se va a desarrollar una aplicación web donde el administrador de una PYME pueda registrar su empresa 
						para hacer un seguimiento de ella. 
						
						</p>
						
						</div>
  				</div>
  		</div>
  		
  		<div class="col-sm-6">
  			<div>
  				<h2>Registro de Empresas</h2>
				<div class="row">
				<h4>Usuario:</h4>
				
				<form:form action="addUser" method="POST" modelAttribute="usuario" class="form-horizontal" role="form">
					
					<div class="col-xs-3">
						<label for="nombre">Nombre</label>
						<form:input class="form-control" path="nombre" name="nombre" type="text"/>
					</div>
				
					<div class="col-xs-3">
						<label for="apellido1">1º Apellido</label>
						<form:input class="form-control" path="apellido1" name="apellido1" type="text"/>
					</div>
					
					<div class="col-xs-3">
						<label for="apellido2">2º Apellido</label>
						<form:input class="form-control" path="apellido2" name="apellido2" type="text"/>
					</div>
					
					<div class="col-xs-3">
						<label for="email">Email</label>
						<form:input class="form-control" path="email" name="email" type="text"/>
					</div>
					
					<div class="col-xs-3">
						<label for="dni">DNI</label>
						<form:input class="form-control" path="dni" name="dni" type="text"/>
					</div>
					
					<div class="col-xs-3">
						<label for="contrasena">Contraseña</label>
						<form:input class="form-control" path="contrasena" name="contrasena" type="password"/>
					</div>
					
					<div class="col-xs-3">
						<form:input class="form-control" path="admin" name="admin" type="hidden" value="1"/>
					</div>
				
					<div class="col-xs-3">
						<input type="submit" value="Aceptar" class="btn btn-primary">
					</div>
					
				</form:form>	
				</div>
			</div>
			
			<div class="row">
				<h4> Empresa: </h4>
				
			</div>
				
			</div>
  		</div>
	</div>
	


</body>
</html>