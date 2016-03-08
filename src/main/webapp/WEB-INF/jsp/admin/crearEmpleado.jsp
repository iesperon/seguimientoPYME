<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>


	<div class="row">
       
      		
     	<div class="col-md-9">    
    
    		<form:form action="addEmpleado" method="POST" modelAttribute="usuario" class="form-horizontal" role="form">
				<div class="col-sm-12">
				<div>
					<h2>Registro de Empleados</h2>
						<div class="row">
		
						<div class="col-xs-3">
							<label for="nombre">Nombre</label>
							<form:input class="form-control" path="nombre" type="text"/>
						</div>
						
						<div class="col-xs-3">
							<label for="apellido1">1º Apellido</label>
							<form:input class="form-control" path="apellido1" type="text"/>
						</div>
						
						<div class="col-xs-3">
							<label for="apellido2">2º Apellido</label>
							<form:input class="form-control" path="apellido2" type="text"/>
						</div>
						
						<div class="col-xs-3">
							<label for="dni">DNI</label>
							<form:input class="form-control" path="dni" type="text"/>
						</div>
						
						<div class="col-xs-3">
							<label for="email">Email</label>
							<form:input class="form-control" path="email" type="text"/>
						</div>
						
						<div class="col-xs-3">
							<label for="contrasena">Contraseña</label>
							<form:input class="form-control" path="contrasena" type="text"/>
						</div>

														
						</div>
						
					<br>
					<div class="col-xs-6">
						<input type="submit" value="Aceptar" class="btn btn-primary">
					</div>
				</div>
			</div>	
	
			</form:form>
			
			</div>
		</div>
    		
