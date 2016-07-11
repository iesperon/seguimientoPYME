<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<script type="text/javascript" charset="utf-8"> 
		$(document).ready(function() {
			$('#envios').dataTable({
				"language": {
					url:'https://cdn.datatables.net/plug-ins/1.10.11/i18n/Spanish.json'
	        	}
			});
		} );
</script> 

<div class="row">
<div class="col-md-9">

	<h2>Envios
	</h2>
	
	<table class="table table-hover" id="envios">
		<thead> 
			<tr>
        				<th>Nombre</th>
        				<th>Calle</th>
        				<th>Poblacion</th>
        				<th>Provincia</th>
        				<th>Pais</th>
        				<th>Transporte</th>
        				<th>Número Seguimiento</th>
        				<th>Estado</th>
        				<th> </th>
   			</tr>
		</thead> 
		
		<tbody> 
		<c:forEach var="envios" items="${enviosList}" varStatus="status">
    				<tr>
    					<td>${envios.nombre}</td>
    					<td>${envios.calle}</td>
    					<td>${envios.poblacion}</td>
    					<td>${envios.provincia}</td>
    					<td>${envios.pais}</td>
    					<td>${envios.empresa}</td>
    					<td>${envios.numSeguimiento}</td>
    					<td>${envios.estado}</td>
    					
						<c:url var="envioUrl" value="verEnvio">
							<c:param name="idEnvio" value="${envios.idEnvio}"/>
						</c:url>

						<td>
							<a href='<c:out value="${envioUrl}"/>' data-original-title="Ver ticket" data-toggle="tooltip" type="button" class="btn btn-sm btn-primary"><i class="glyphicon glyphicon-search"></i></a>						
						</td>
	   				</tr>
    	</c:forEach>
		</tbody>
	</table>	
	
	</div>
	</div>