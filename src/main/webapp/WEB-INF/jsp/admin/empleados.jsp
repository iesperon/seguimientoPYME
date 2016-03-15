<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>


	<div class="row">
	
      		
     	<div class="col-md-9">
     		<img src="<c:url value="/resources/images/empleados.jpg" />" class="center-block" alt="Empleados" width="800" height="200">
    		<h2> Empleados </h2>
    			<table class="table table-hover">
    				<tr>
        				<th>Nombre</th>
        				<th>1º Apellido</th>
        				<th>2º Apellido</th>
        				<th>DNI</th>
        				<th>Email</th>
        				<th></th>
   					</tr>
    			<c:forEach var="usuario" items="${usuarioslist}" varStatus="status">
    				<tr>
    					<td>${usuario.nombre}</td>
    					<td>${usuario.apellido1}</td> 
    					<td>${usuario.apellido2}</td>
    					<td>${usuario.dni}</td>
    					<td>${usuario.email}</td>
    					
    					<c:url var="usuarioUrl" value="eliminarEmpleado">
							<c:param name="idUsuario" value="${usuario.idUsuario}"/>
						</c:url>
						<c:url var="usuarioUrl2" value="editarEmpleado">
							<c:param name="idUsuario" value="${usuario.idUsuario}"/>
						</c:url>
						
						<td><a href='<c:out value="${usuarioUrl}"/>'>Eliminar </a> | <a href='<c:out value="${usuarioUrl2}"/>'>Editar</a></td>
						
    					
    				
	   				</tr>    		
    			</c:forEach>
    			</table>
    		
    	
    	 <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
	<script>
		$(document).ready(function(){
    	$("#boton").click(function(){
        	$("#oculto").slideToggle();
    	});
    	
	});
	</script>
    		
    		
    		<button id="boton" onclick="mostrar()" class="btn btn-primary btn-lg btn-block" >Registrar Usuario</button>
    		
    		<div id="oculto" style="display: none;">
    		<form:form action="addEmpleado" method="POST" modelAttribute="myUsuario" class="form-horizontal" role="form">
				<div class="col-sm-12">
				<div>
					<h2>Registrar Empleado</h2>
						<div class="row">
		
						<div class="col-xs-3">
							<label for="nombre">Nombre</label>
							<form:input class="form-control" path="nombre" type="text"/>
						</div>
						
						<div class="col-xs-3">
							<label for="calle">1º Apellido</label>
							<form:input class="form-control" path="apellido1" type="text"/>
						</div>
						
						<div class="col-xs-3">
							<label for="numero">2º Apellido</label>
							<form:input class="form-control" path="apellido2" type="text"/>
						</div>
						
						<div class="col-xs-3">
							<label for="poblacion">DNI</label>
							<form:input class="form-control" path="dni" type="text"/>
						</div>
						
						<div class="col-xs-3">
							<label for="provincia">Email</label>
							<form:input class="form-control" path="email" type="text"/>
						</div>
						
						<div class="col-xs-3">
							<label for="cp">Contraseña</label>
							<form:input class="form-control" path="contrasena" type="text"/>
						</div>
						
						
						<div class="dropdown">
  <button class="btn btn-default dropdown-toggle" type="button" id="dropdownMenu1" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
    Dropdown
    <span class="caret"></span>
  </button>
  <ul class="dropdown-menu" aria-labelledby="dropdownMenu1">
    <li><a href="#">Action</a></li>
    <li><a href="#">Another action</a></li>
    <li><a href="#">Something else here</a></li>
    <li role="separator" class="divider"></li>
    <li><a href="#">Separated link</a></li>
  </ul>
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
      
      </div>

    
   



