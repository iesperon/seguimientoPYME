<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<div class="row">
<div class="col-md-9">
	<img src="<c:url value="/resources/images/empleados.jpg" />" class="center-block" alt="Empleados" width="800" height="200">
    <h2> Empleados 
    		
	<!-- Button trigger modal -->
	<button type="button" class="btn btn-primary " data-toggle="modal" data-target="#myModal">
	Registrar Empleado
	</button>
	</h2>
	<!-- Modal -->
	<form:form action="addEmpleado" method="POST" modelAttribute="myForm" class="form-horizontal" role="form">

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
						
					 </div>
      			
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Cerrar</button>
        <button type="submit" class="btn btn-primary">Aceptar</button>
      </div>
    </div>
  </div>
</div>
</form:form>
    
    <input class="typeahead" type="text" data-provide="typeahead" autocomplete="off">


     
    <script type="text/javascript" charset="utf-8"> 
		$(document).ready(function() {
			$('#empleados').dataTable({
			"language": {
            	"lengthMenu": "Mostrar _MENU_ registros por página",
           		"zeroRecords": "Ningún resultado encontrado",
            	"info": "Mostrando página _PAGE_ de _PAGES_",
            	"infoEmpty": "Ningún resultado encontrado",
            	"infoFiltered": "(filtrados _MAX_ registros en total)",
            	 "sSearch": "Buscar:",
            	 "paginate": {
        			"first":      "Primera",
        			"last":       "Ultima",
        			"next":       "Siguiente",
        			"previous":   "Anterior"
    			}
        }
			});
			 $('input.typeahead').typeahead({
          source: function (query, process) {
            $.ajax({
              url: 'empleados/',
              type: 'GET',
              dataType: 'JSON',
              data: 'nombre=' + query.query,
              success: function(data) {
                console.log(data);
                process(data);
              }
            });
          }
        });
		} );
	</script> 
	
	<table class="table table-hover" id="empleados">
		<thead> 
			<tr>
        				<th>Nombre</th>
        				<th>1º Apellido</th>
        				<th>2º Apellido</th>
        				<th>DNI</th>
        				<th>Email</th>
        				<th></th>
   			</tr>
		</thead> 
		
		<tbody> 
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
						
						<td><a href='<c:out value="${usuarioUrl2}"/>' data-original-title="Editar empleado" data-toggle="tooltip" type="button" class="btn btn-sm btn-warning"><i class="glyphicon glyphicon-edit"></i></a>   <a href='<c:out value="${usuarioUrl}"/>' data-original-title="Eliminar usuario" data-toggle="tooltip" type="button" class="btn btn-sm btn-danger"><i class="glyphicon glyphicon-remove"></i></a> </td>
						
						
    					
    				
	   				</tr>    		
    			</c:forEach>
		</tbody>
	</table>
	
</div>
      
</div>

    
   



