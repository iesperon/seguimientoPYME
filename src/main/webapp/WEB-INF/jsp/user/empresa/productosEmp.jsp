<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>


	<div class="row">
	
	    <script type="text/javascript" charset="utf-8"> 
		$(document).ready(function() {
			$('#productos').dataTable({
			"language": {
				url:'https://cdn.datatables.net/plug-ins/1.10.11/i18n/Spanish.json'
        }
			});
		} );
	</script> 
	
		<div class="col-md-9">
       	<h2>Productos
       		<button type="button" class="btn btn-primary " data-toggle="modal" data-target="#myModal">
			Registrar Producto
			</button>
		</h2>
       
 	  
	  <table class="table table-hover" id="productos">
		<thead> 
			<tr>
        				<th>Código de barras</th>
        				<th>Nombre</th>
        				<th>Marca</th>
        				<th>Precio</th>
        				<th>IVA</th>
        				<th></th>
   			</tr>
		</thead> 
		
		<tbody> 
			<c:forEach var="productos" items="${productoslist}" varStatus="status">
    				<tr>
    					<td>${productos.codProd}</td>
    					<td>${productos.nombre}</td> 
    					<td>${productos.marca}</td>
    					<td>${productos.precio}</td>
    					<td>${productos.iva.porcentaje}%</td>
    					
    					<c:url var="productoUrl" value="eliminarProducto">
							<c:param name="idProducto" value="${productos.idProducto}"/>
						</c:url>
						<c:url var="productoUrl2" value="editarProducto">
							<c:param name="idProducto" value="${productos.idProducto}"/>
						</c:url>
						
						<td><a href='<c:out value="${productoUrl2}"/>' data-original-title="Editar producto" data-toggle="tooltip" type="button" class="btn btn-sm btn-warning"><i class="glyphicon glyphicon-edit"></i></a>   <a href='' data-toggle="modal" data-target="#deleteModal" data-original-title="Eliminar producto" data-toggle="tooltip" type="button" class="btn btn-sm btn-danger"><i class="glyphicon glyphicon-remove"></i></a> </td>
						
						
    					
    				
	   				</tr>    		
    			</c:forEach>
		</tbody>
	</table>
	
	
	<div class="modal fade bs-example-modal-lg" id="deleteModal" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel">
  			<div class="modal-dialog modal-lg" >
    		<div class="modal-content">
      			<div class="modal-header">
        		<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        		<h4 class="modal-title" id="myModalLabel">Eliminar Producto</h4>
      		</div>
      		<div class="modal-body">
      		<div class="row">
      		<h4>¿Está seguro de que desea eliminar este producto?</h4>
      		</div>

			 <div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">Cancelar</button>
				<a href='<c:out value="${productoUrl}"/>' type="button" class="btn btn-danger">Eliminar </a>
      		</div>
      		</div>
      		</div>
	</div>
	</div>
		  
    <!-- Hace que no pase nada pulsando intro -->
  		<script>
    		 function anular(e) {
    	     	 tecla = (document.all) ? e.keyCode : e.which;
          		return (tecla != 13);
     		}
		</script>
    		
			
			<!-- Modal -->
	<form:form action="addProducto" method="POST" modelAttribute="myForm" class="form-horizontal" role="form">

	<div class="modal fade bs-example-modal-lg" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel">
  		<div class="modal-dialog modal-lg" >
    		<div class="modal-content">
      			<div class="modal-header">
        		<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        		<h4 class="modal-title" id="myModalLabel">Registro de producto</h4>
      		</div>
      <div class="modal-body">
					<div class="row">
		
						<div class="col-xs-3">
							<label for="nombre">Código de barras</label>
							<form:input class="form-control" path="producto.codProd" type="text"/>
						</div>
						
						<div class="col-xs-3">
							<label for="calle">Nombre</label>
							<form:input class="form-control" path="producto.nombre" type="text"/>
						</div>
						
						<div class="col-xs-3">
							<label for="numero">Precio</label>
							<form:input class="form-control" path="producto.precio" type="text"/>
						</div>
						
						<div class="col-xs-3">
							<label for="poblacion">Marca</label>
							<form:input class="form-control" path="producto.marca" type="text"/>
						</div>

						<div class="col-xs-3">
							<label for="cp">Imagen</label>
							<form:input class="form-control" path="producto.foto" type="text"/>
						</div>
						
						<div class="col-xs-3">
							<label for="pais">Descuento</label>
							<form:input class="form-control" path="producto.descuento" type="text"/>
						</div>
					
						<div class="col-xs-3">
							<label for="iva">Iva</label>
							<form:select class="form-control" path="idIva">
  								<c:forEach var="ivas" items="${ivas}" varStatus="status">
  									<form:option value="${ivas.idIva}">${ivas.nombre}, ${ivas.porcentaje}% </form:option>
 								</c:forEach>
 							</form:select>						
 						</div>
					
						<div class="col-xs-3">
							<label for="descripcion">Descripcion</label>
							<form:textarea class="form-control" rows="3" path="producto.descripcion"/> 
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
    		
    		</div>	
          </div>
      

