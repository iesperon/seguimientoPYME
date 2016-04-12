<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

       <script type="text/javascript" charset="utf-8"> 
		$(document).ready(function() {
			$('#stock').dataTable({
			"language": {
				url:'https://cdn.datatables.net/plug-ins/1.10.11/i18n/Spanish.json'
			}
			});
		} );
	</script> 
	
<div class="row">
<div class="col-md-9">
<h2>Avisos de Stock </h2>
<br>
	<table class="table table-hover" id="stock">
		<thead> 
			<tr>
						<th>Centro</th>
						<th>Calle</th>
						<th>Poblacion</th>
        				<th>Referencia</th>
        				<th>Nombre</th>
        				<th>Marca</th>
        				<th>Precio</th>
        				<th>Stock Actual</th>
        				<th>Stock Mínimo </th>
        				<th> </th>
   			</tr>
		</thead> 
		
		<tbody> 
		<c:forEach var="stock" items="${stockList}" varStatus="status">
    				<tr>
    					<td>${stock.centro.nombre}</td>
    					<td>${stock.centro.calle}</td>
    					<td>${stock.centro.poblacion}</td>
    					<td>${stock.producto.codProd}</td>
    					<td>${stock.producto.nombre} </td>
    					<td>${stock.producto.marca}</td>
    					<td>${stock.producto.precio}</td>
    					<td>${stock.stockActual}</td>
						<td>${stock.stockMin}</td>
						<c:url var="stockUrl" value="editarStock">
							<c:param name="idStock" value="${stock.idStock}"/>
						</c:url>
						<td><a href='<c:out value="${stockUrl}"/>' title="Editar Stock" data-toggle="tooltip" type="button" class="btn btn-sm btn-warning"><i class="glyphicon glyphicon-edit"></i></a></td>
	   				</tr>
    	</c:forEach>
		</tbody>
	</table>
	  
</div>
</div>

