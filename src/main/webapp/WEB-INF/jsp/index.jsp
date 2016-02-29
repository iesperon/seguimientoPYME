<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

	 <div class="row">
  		<div class="col-sm-6">
  		<div class="row">
  		<div class="col-sm-2"></div>  		
		 	<img src="<c:url value="/resources/images/logo11.png" />" class="img-rounded" alt="Logo" width="304" height="236">
		</div>
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
  		
  		<form:form action="addUser" name="myForm" method="POST" modelAttribute="myForm" class="form-horizontal" role="form">
				<div class="col-sm-6">
				<div>
  				<h2>Registro de Empresas</h2>
				
				<div class="row">
				<h4>Usuario:</h4>	
				
					<div class="col-xs-3">
						<label for="nombre">Nombre</label>
						<form:input class="form-control" path="usuario.nombre" type="text"/>
					</div>
				
					<div class="col-xs-3">
						<label for="apellido1">1º Apellido</label>
						<form:input class="form-control" path="usuario.apellido1" type="text"/>
					</div>
					
					<div class="col-xs-3">
						<label for="apellido2">2º Apellido</label>
						<form:input class="form-control" path="usuario.apellido2" type="text"/>
					</div>
					
					<div class="col-xs-3">
						<label for="email">Email</label>
						<form:input class="form-control" path="usuario.email" type="text"/>
					</div>
					
					<div class="col-xs-3">
						<label for="dni">DNI</label>
						<form:input class="form-control" path="usuario.dni" type="text"/>
					</div>
					
					<div class="col-xs-3">
						<label for="contrasena">Contraseña</label>
						<form:input class="form-control" path="usuario.contrasena" type="password"/>
					</div>
					
					<div class="col-xs-3">
						<form:input class="form-control" path="usuario.admin" type="hidden" value="1"/>
					</div>
					
				</div>
					
				<div class="row">	
					<h4> Empresa: </h4>
					
					<div class="col-xs-3">
						<label for="nombre">Nombre</label>
						<form:input class="form-control" path="empresa.nombre" type="text"/>
					</div>
				
					<div class="col-xs-3">
						<label for="cif">CIF</label>
						<form:input class="form-control" path="empresa.cif" type="text"/>
					</div>
					
					<div class="col-xs-3">
						<label for="sector">Sector</label>
						<form:input class="form-control" path="empresa.sector"type="text"/>
					</div>
					
					<div class="col-xs-3">
						<label for="descripcion">Descripcion</label>
						<form:input class="form-control" path="empresa.descripcion" type="text"/>
					</div>
					
					<div class="col-xs-3">
						<label for="email">Email</label>
						<form:input class="form-control" path="empresa.email"  type="text"/>
					</div>
					
					<div class="col-xs-3">
						<label for="logo">Logo</label>
						<span class="btn btn-default btn-file">
							<form:input path="empresa.logo"  type="file"/>
						</span>
					</div>
					
				</div>
				
					<div class="col-xs-3">
						<input type="submit" value="Aceptar" class="btn btn-primary" onclick="submitForms()">
					</div>
					
				</div>
			</div>		
	</form:form>
			</div>
  		</div>
