<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!-- cdn for modernizr, if you haven't included it already -->
<script src="http://cdn.jsdelivr.net/webshim/1.12.4/extras/modernizr-custom.js"></script>
<!-- polyfiller file to detect and load polyfills -->
<script src="http://cdn.jsdelivr.net/webshim/1.12.4/polyfiller.js"></script>
<script>
  webshims.setOptions('waitReady', false);
  webshims.setOptions('forms-ext', {types: 'date'});
  webshims.polyfill('forms forms-ext');
</script>

<script type="text/javascript" charset="utf-8"> 
		$(document).ready(function() {
			$('#centros').dataTable({
				"language": {
					url:'https://cdn.datatables.net/plug-ins/1.10.11/i18n/Spanish.json'
	        	}
			});
		} );
</script> 

<div class="row">
<div class="col-md-9">

	<h2>Gastos
	<!-- Button trigger modal -->
	<button type="button" class="btn btn-primary " data-toggle="modal" data-target="#myModal">
	Registrar Gasto
	</button>
	</h2>
	
	<table class="table table-hover" id="centros">
		<thead> 
			<tr>
        				<th>Concepto</th>
        				<th>Importe</th>
        				<th>Fecha Emisión</th>
        				<th>Fecha Vencimiento</th>
        				<th>Estado</th>
        				<th> </th>
   			</tr>
		</thead> 
		
		<tbody> 
		<c:forEach var="gastos" items="${gastoslist}" varStatus="status">
    				<tr>
    					<td>${gastos.concepto}</td>
    					<td>${gastos.importe} €</td>
    					<td><fmt:formatDate value="${gastos.fechaEmision}" pattern="dd-MM-yyyy" /></td>
    					<td><fmt:formatDate value="${gastos.fechaPago}" pattern="dd-MM-yyyy" /></td>
    					<td>${gastos.estado}</td>
    					
						<c:url var="gastoUrl" value="eliminarGasto">
							<c:param name="idGasto" value="${gastos.idGasto}"/>
						</c:url>
						<c:url var="gastoUrl2" value="editarGasto">
							<c:param name="idGasto" value="${gastos.idGasto}"/>
						</c:url>
						<td>
							<a href='<c:out value="${gastoUrl2}"/>' data-original-title="Editar gasto" data-toggle="tooltip" type="button" class="btn btn-sm btn-warning"><i class="glyphicon glyphicon-edit"></i></a>  <a href='<c:out value="${gastoUrl}"/>' data-original-title="Eliminar gasto" data-toggle="tooltip" type="button" class="btn btn-sm btn-danger"><i class="glyphicon glyphicon-remove"></i></a>
						</td>
	   				</tr>
    	</c:forEach>
		</tbody>
	</table>	
	
			
	<div class="col-sm-12">
	<div>
	
	<!-- Modal -->
	<form:form action="addGasto" method="POST" modelAttribute="newGasto" class="form-horizontal" role="form">
			
	<div class="modal fade bs-example-modal-lg" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel">
  	<div class="modal-dialog modal-lg" >
    	<div class="modal-content">
      		<div class="modal-header">
        	<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        	<h4 class="modal-title" id="myModalLabel">Registrar Gasto</h4>
      	</div>
		<div class="modal-body">
		<div class="row">
			
			<div class="col-xs-3">
				<label for="descripcion">Concepto</label>
				<form:input class="form-control" path="concepto" type="text"/>
			</div>

			<div class="col-xs-2">
				<label for="descripcion">Importe</label>
				<div class="input-group"> 
				<span class="input-group-addon">€</span>
				<form:input class="form-control" path="importe" type="text"/>
				</div>
			</div>
			
			<div class="col-xs-3">
				<label for="descripcion">Fecha Emision</label>
				<form:input class="form-control" path="fechaEmision" type="date"/>
			</div>
			
			<div class="col-xs-3">
				<label for="descripcion">Fecha Vencimiento</label>
				<form:input class="form-control" path="fechaPago" type="date"/>
			</div>
			
			<div class="col-xs-3">
				<label for="estado">Estado</label>
				<form:input class="form-control" path="estado" type="text"/>
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



</div>
</div>