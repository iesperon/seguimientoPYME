<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>



<script type="text/javascript" charset="utf-8"> 
		$(document).ready(function() {
			$('#cierres').dataTable({
				"language": {
					url:'https://cdn.datatables.net/plug-ins/1.10.11/i18n/Spanish.json'
	        	}
			});
		} );
</script> 


<div class="row">
<div class="col-md-9">
	<img src="<c:url value="/resources/images/cierres de caja.jpg" />" 	class="center-block img-rounded" alt="Empleados" width="1000" height="120">

	<h2>Cierres de Caja
	<!-- Button trigger modal -->
	<a href='/seguimientoPYMES/admin/ventas/cierreCentro' type="button" class="btn btn-primary"> Registrar Cierre</a>
	</h2>
	
	<table class="table table-hover" id="cierres">
		<thead> 
			<tr>
        				<th>Fecha</th>
        				<th>Tarjeta</th>
        				<th>Efectivo</th>
        				<th>Total</th>
        				<th>Caja</th>
        				<th>Diferencia </th>
        				<th>Opciones </th>
   			</tr>
		</thead> 
		
		<tbody> 
		<c:forEach var="cierres" items="${cierreslist}" varStatus="status">
    				<tr>
    					<td><fmt:formatDate value="${cierres.fecha}" pattern="dd-MM-yyyy" /></td>
    					<td>${cierres.tarjeta} €</td>
    					<td>${cierres.efectivo} €</td>
    					<td>${cierres.total} €</td>
    					<td>${cierres.caja} €</td>
    					<td>${cierres.diferencia} €</td>
						<c:url var="cierreUrl" value="eliminarCierre">
							<c:param name="idCierre" value="${cierres.idCierre}"/>
						</c:url>
						<c:url var="cierreUrl2" value="editarCierre">
							<c:param name="idCierre" value="${cierres.idCierre}"/>
						</c:url>
						<td>
							<a href='<c:out value="${cierreUrl2}"/>' data-original-title="Editar cierre" data-toggle="tooltip" type="button" class="btn btn-sm btn-warning"><i class="glyphicon glyphicon-edit"></i></a>  <a href='<c:out value="${cierreUrl}"/>' data-original-title="Eliminar cierre" data-toggle="tooltip" type="button" class="btn btn-sm btn-danger"><i class="glyphicon glyphicon-remove"></i></a>
						</td>
	   				</tr>
    	</c:forEach>
		</tbody>
	</table>	
	
			
</div>
</div>
