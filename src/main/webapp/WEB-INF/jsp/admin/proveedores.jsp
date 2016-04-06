<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>


<div class="row">
<div class="col-md-9">
	<h2>Proveedores
 		<!-- Button trigger modal -->
		<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#myModal">
		Registrar Proveedor 
		</button>      	
	</h2>

	<!-- Modal -->
	<form:form action="addProveedor" method="POST" modelAttribute="proveedor" class="form-horizontal" role="form">

	<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel">
  		<div class="modal-dialog" >
    		<div class="modal-content">
      			<div class="modal-header">
        		<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        		<h4 class="modal-title" id="myModalLabel">Registro de proveedor</h4>
      		</div>
      <div class="modal-body">
					<div class="row">
		
						<div class="col-xs-5">
							<label for="nombre">Nombre</label>
							<form:input class="form-control" path="nombre" type="text"/>
						</div>
						
						<div class="col-xs-5">
							<label for="cif">CIF</label>
							<form:input class="form-control" path="cif" type="text"/>
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


 <script type="text/javascript" charset="utf-8"> 
		$(document).ready(function() {
			$('#proveedores').dataTable({
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
		} );
	</script> 
	
	<table class="table table-hover" id="proveedores">
		<thead> 
			<tr>
        				<th>Nombre</th>
        				<th>CIF</th>
        				<th></th>
        				
   			</tr>
		</thead> 
		
		<tbody> 
			<c:forEach var="proveedor" items="${proveedorlist}" varStatus="status">
    				<tr>
    					<td>${proveedor.nombre}</td>
    					<td>${proveedor.cif}</td> 
    				<c:url var="proveedorUrl" value="eliminarProveedor">
							<c:param name="idProveedor" value="${proveedor.idProveedor}"/>
					</c:url>
					<c:url var="proveedorUrl2" value="addPedido">
							<c:param name="idProveedor" value="${proveedor.idProveedor}"/>
					</c:url>
					<td><a href='<c:out value="${proveedorUrl}"/>'><i class="glyphicon glyphicon-remove" data-toggle="tooltip" data-placement="top" title="Eliminar proveedor"></i></a>   <a href='<c:out value="${proveedorUrl2}"/>'><i class="glyphicon glyphicon-plus" data-toggle="tooltip" data-placement="top" title="Añadir pedido"></i></a></td>
	   				</tr>    		
    			</c:forEach>
		</tbody>
	</table>

</div>
</div>       	
       	