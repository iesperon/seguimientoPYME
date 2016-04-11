<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

       <script type="text/javascript" charset="utf-8"> 
		$(document).ready(function() {
			$('#stock').dataTable({
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
	
<div class="row">
<div class="col-md-9">
<h2>Stock del centro ${centroNombre}</h2>
<br>
	<table class="table table-hover" id="stock">
		<thead> 
			<tr>
        				<th>Referencia</th>
        				<th>Nombre</th>
        				<th>Marca</th>
        				<th>Precio</th>
        				<th>Stock Actual</th>
        				<th>Stock Mínimo </th>
   			</tr>
		</thead> 
		
		<tbody> 
		<c:forEach var="stock" items="${stockList}" varStatus="status">
    				<tr>
    					<td>${stock.producto.codProd}</td>
    					<td>${stock.producto.nombre} </td>
    					<td>${stock.producto.marca}</td>
    					<td>${stock.producto.precio}</td>
    					<td>${stock.stockActual}</td>
						<td>${stock.stockMin}</td>
	   				</tr>
    	</c:forEach>
		</tbody>
	</table>
	  
</div>
</div>

