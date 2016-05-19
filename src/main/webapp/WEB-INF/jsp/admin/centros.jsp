<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>


	<div class="row">
    <script type="text/javascript" charset="utf-8"> 
		$(document).ready(function() {
			$('#centros').dataTable({
			"language": {
				url:'https://cdn.datatables.net/plug-ins/1.10.11/i18n/Spanish.json'
        }
			});
		} );
	</script> 
	

     	<div class="col-md-9">
     	
    	    <img src="<c:url value="/resources/images/centros.png" />" class="center-block" alt="Empleados" width="800" height="200">
    		    <h2> Centros 
    		
			<!-- Button trigger modal -->
			<button type="button" class="btn btn-primary " data-toggle="modal" data-target="#myModal">
			Registrar Centro
			</button>
			</h2>
    		<table class="table table-hover" id="centros">
		<thead> 
			<tr>
        				<th>Nombre</th>
        				<th>Calle</th>
        				<th>Poblacion</th>
        				<th>Provincia</th>
        				<th>Pais</th>
        				<th>Email</th>
        				<th>Teléfono</th>
        				<th> </th>
   			</tr>
		</thead> 
		
		<tbody> 
		<c:forEach var="centro" items="${centroslist}" varStatus="status">
    				<tr>
    					<td>${centro.nombre}</td>
    					<td>${centro.calle}</td>
    					<td>${centro.poblacion}</td>
    					<td>${centro.provincia}</td>
    					<td>${centro.pais}</td>
    					<td>${centro.email}</td>
    					<td>${centro.telefono}</td>
    					
    					
						<c:url var="centroUrl2" value="crearEmpleado">
							<c:param name="idCentro" value="${centro.idCentro}"/>
						</c:url>
						<c:url var="centroUrl3" value="editarCentro">
							<c:param name="idCentro" value="${centro.idCentro}"/>
						</c:url>
						<c:url var="centroUrl4" value="stock">
							<c:param name="idCentro" value="${centro.idCentro}"/>
						</c:url>
						<c:url var="centroUrl" value="eliminarCentro">
							<c:param name="idCentro" value="${centro.idCentro}"/>
						</c:url>
						<td> 
							<div class="dropdown">
  								<button class="btn btn-primary btn-sm dropdown-toggle" type="button" data-toggle="dropdown">
    								<div class="input-group"> 
    								<span class="glyphicon glyphicon-wrench"></span> Opciones
    								</div>
    								<span class="caret"></span>
  								</button>
  								<ul class="dropdown-menu" aria-labelledby="dLabel">
									<li> <a href='<c:out value="${centroUrl3}"/>'>Editar</a>	</li>
									<li> <a href='<c:out value="${centroUrl4}"/>'>Stock</a> </li>
									<li> <a href='<c:out value="${centroUrl2}"/>'>Añadir empleado</a> </li>
									<li> <a href='' data-toggle="modal" data-target="#deleteModal">Eliminar </a> </li> 								
								</ul>
							</div>
						
						
						</td>	
					
	   				</tr>
    			</c:forEach>
		</tbody>
	</table>	
	
	<div class="modal fade bs-example-modal-lg" id="deleteModal" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel">
  			<div class="modal-dialog modal-lg" >
    		<div class="modal-content">
      			<div class="modal-header">
        		<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        		<h4 class="modal-title" id="myModalLabel">Eliminar Centro</h4>
      		</div>
      		<div class="modal-body">
      		<div class="row">
      		<h4>¿Está seguro de que desea eliminar este centro?</h4>
      		</div>

			 <div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">Cancelar</button>
				<a href='<c:out value="${centroUrl}"/>' type="button" class="btn btn-danger">Eliminar </a>
      		</div>
      		</div>
      		</div>
	</div>
	</div>
	
 		
	<script>
		$(document).ready(function(){
    	$("#boton").click(function(){
        	$("#oculto").slideToggle();
    	});
    	
	});
	</script>
    		
    		

    		<form:form action="addCentro" method="POST" modelAttribute="myCentro" class="form-horizontal" role="form">
			<div class="modal fade bs-example-modal-lg" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel">
  			<div class="modal-dialog modal-lg" >
    		<div class="modal-content">
      			<div class="modal-header">
        		<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        		<h4 class="modal-title" id="myModalLabel">Registro de usuario</h4>
      		</div>
      		<div class="modal-body">
					<div class="row">
		
						<div class="col-xs-3">
							<label for="nombre">Nombre</label>
							<form:input class="form-control" path="nombre" type="text"/>
						</div>
						
						<div class="col-xs-3">
							<label for="calle">Calle</label>
							<form:input class="form-control" path="calle" type="text"/>
						</div>
						
						<div class="col-xs-3">
							<label for="numero">Numero</label>
							<form:input class="form-control" path="numero" type="text"/>
						</div>
						
						<div class="col-xs-3">
							<label for="poblacion">Poblacion</label>
							<form:input class="form-control" path="poblacion" type="text"/>
						</div>
						
						<div class="col-xs-3">
							<label for="provincia">Provincia</label>
							<form:input class="form-control" path="provincia" type="text"/>
						</div>
						
						<div class="col-xs-3">
							<label for="cp">C.P</label>
							<form:input class="form-control" path="cp" type="text"/>
						</div>
						
						<div class="col-xs-3">
							<label for="pais">Pais</label>
							<form:input class="form-control" path="pais" type="text"/>
						</div>
						
						<div class="col-xs-3">
							<label for="email">Email</label>
							<form:input class="form-control" path="email" type="text"/>
						</div>
						
						<div class="col-xs-3">
							<label for="telefono">Telefono</label>
							<form:input class="form-control" path="telefono" type="text"/>
						</div>
					
					
						</div>				
					</div>
						
					 <div class="modal-footer">
					 	<button type="button" class="btn btn-default" data-dismiss="modal">Cerrar</button>
						<input type="submit" value="Aceptar" class="btn btn-primary">
      				</div>
    			</div>
  			</div>
		</div>
		</form:form>
    		
    		</div>	
	
    	</div>
      




