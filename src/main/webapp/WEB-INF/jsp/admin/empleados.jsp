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
    		
    	
	<script>
		$(document).ready(function(){
    	$("#boton").click(function(){
        	$("#oculto").slideToggle();
    	});
    	
	});
	</script>
    		
    		
    		<button id="boton" onclick="mostrar()" class="btn btn-primary btn-lg btn-block" >Registrar Usuario</button>
    		
    		<div id="oculto" style="display: none;">
    		<form:form action="addEmpleado" method="POST" modelAttribute="myForm" class="form-horizontal" role="form">
				<div class="col-sm-12">
				<div>
					<h2>Registrar Empleado</h2>
						<div class="row">
		
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
							<label for="dni">DNI</label>
							<form:input class="form-control" path="usuario.dni" type="text"/>
						</div>
						
						<div class="col-xs-3">
							<label for="email">Email</label>
							<form:input class="form-control" path="usuario.email" type="text"/>
						</div>
						
						<div class="col-xs-3">
							<label for="contrasena">Contraseña</label>
							<form:input class="form-control" path="usuario.contrasena" type="text"/>
						</div>
						
						<div class="col-xs-3">
							<label for="centro">Centro</label>
							<form:select class="form-control" path="idCentro">
  								<c:forEach var="centros" items="${centroslist}" varStatus="status">
  									<form:option value="${centros.idCentro}">${centros.nombre}, ${centros.calle}, ${centros.poblacion} </form:option>
 								</c:forEach>
 							</form:select>						
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
    		
    		
    		
    		
    			
	
	
	<script type="text/javascript" charset="utf-8"> 
	$(document).ready(function() {
		$('#example').dataTable();
	} );
	</script> 
	
	<table cellpadding="0" cellspacing="0" border="0" class="display" id="example">
		<thead> 
			<tr> 
				<th>Rendering engine</th> 
				<th>Browser</th> 
				<th>Platform(s)</th> 
				<th>Engine version</th> 
				<th>CSS grade</th> 
			</tr> 
		</thead> 
		
		<tbody> 
			<tr class="gradeX"> 
				<td>Trident</td> 
				<td>Internet
					 Explorer 4.0</td> 
				<td>Win 95+</td> 
				<td class="center">4</td> 
				<td class="center">X</td> 
			</tr> 
			<tr class="gradeC"> 
				<td>Trident</td> 
				<td>Internet
					 Explorer 5.0</td> 
				<td>Win 95+</td> 
				<td class="center">5</td> 
				<td class="center">C</td> 
			</tr> 
			<tr class="gradeA"> 
				<td>Trident</td> 
				<td>Internet
					 Explorer 5.5</td> 
				<td>Win 95+</td> 
				<td class="center">5.5</td> 
				<td class="center">A</td> 
			</tr> 
			<tr class="gradeA"> 
				<td>Trident</td> 
				<td>Internet
					 Explorer 6</td> 
				<td>Win 98+</td> 
				<td class="center">6</td> 
				<td class="center">A</td> 
			</tr> 
			
			............................
			
			<tr class="gradeA"> 
				<td>Gecko</td> 
				<td>Mozilla 1.5</td> 
				<td>Win 95+ / OSX.1+</td> 
				<td class="center">1.5</td> 
				<td class="center">A</td> 
			</tr>
		</tbody>
	</table>
    		
    			
          </div>
      
      </div>

    
   



