<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>



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

	<h2>Tickets</h2>
	<br>
	<table class="table table-hover" id="centros">
		<thead> 
			<tr>
						<th>ID</th>
						<th>Centro</th>
        				<th>Fecha</th>
        				<th>Subtotal</th>
        				<th>Total</th>
        				<th>Forma de pago</th>
        				<th></th>
        				
 
   			</tr>
		</thead> 
		
		<tbody> 
		<c:forEach var="tickets" items="${tickets}" varStatus="status">
    				<tr>
    				   	<td>${tickets.idTicket}</td>
    				    <td>${tickets.centro.nombre}</td>
    					<td><fmt:formatDate value="${tickets.fecha}" type="both" dateStyle="short" timeStyle="short" /></td>
    					<td><fmt:formatNumber value="${tickets.subtotal}" maxFractionDigits="2"/> €</td>
    					<td><fmt:formatNumber value="${tickets.total}" maxFractionDigits="2"/> €</td>
    					<td>${tickets.formaPago}</td>
						<c:url var="ticketUrl" value="verTicket">
							<c:param name="idTicket" value="${tickets.idTicket}"/>
						</c:url>
						<td>
							<a href='<c:out value="${ticketUrl}"/>' data-original-title="Ver ticket" data-toggle="tooltip" type="button" class="btn btn-sm btn-primary"><i class="glyphicon glyphicon-search"></i></a>
	   					</td>
	   				</tr>
    	</c:forEach>
		</tbody>
	</table>	
	
	</div>
	</div>