<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<div class="row">
<div class="col-md-9">
<div class="col-md-12">
	<h2>Buscar Producto</h2>
		<form:form action="addLinea" method="POST" modelAttribute="myForm" class="form-horizontal" role="form">
			<div class="col-xs-2">
				<form:input class="form-control" id="codProd" path="codProd"  type="text"/>
			</div>
			<div class="col-xs-3">
				<form:input class="form-control" path="ticket.idTicket" value="${ticket.idTicket}" type="text"/>
			</div> 
		<div class="col-xs-1">
			<input type="submit" value="Buscar" class="btn btn-primary btn-sm">
		</div>	
		</form:form>

</div>

<div class="col-md-12">

	<h2>Ticket</h2>
	<div class="col-xs-2">
		<label for="cantidad">Cantidad</label>
	</div>
	<div class="col-xs-3">
		<label for="producto">Producto</label>
	</div>
	<div class="col-xs-2">
		<label for="iva">IVA</label>
	</div>
	<div class="col-xs-2">
		<label for="precio">Precio</label>
	</div>


	<c:forEach var="lineas" items="${lineas}" varStatus="status">
	<div class="col-md-12">
	<div class="row">
		<form:form action="editLinea" method="POST" modelAttribute="linea" class="form-horizontal" role="form"> 
			<div class="col-xs-2">
				<form:input class="form-control" id="cantidad" path="cantidad" value="${lineas.cantidad}" type="text"/>
			</div>
			<div class="col-xs-3">
				<form:input class="form-control" id="producto" path="producto.nombre" value="${lineas.producto.nombre}" type="text"/>
			</div>
			<div class="col-xs-2">
				<form:input class="form-control" id="iva" path="iva" value="${lineas.iva}" type="text"/>
			</div>
			<div class="col-xs-2">
				<form:input class="form-control" id="precio" path="precio" value="${lineas.precio}" type="text"/>
			</div>
			<form:input class="form-control" path="idLineaTicket" value="${lineas.idLineaTicket}" type="hidden"/> 
			<form:input class="form-control" path="ticket.idTicket" value="${lineas.ticket.idTicket}" type="hidden"/> 
			
			
		<c:url var="lineaUrl" value="eliminarGasto">
			<c:param name="idLineaTicket" value="${lineas.idLineaTicket}"/>
		</c:url>
			
		<div class="col-xs-1">
			<input type="submit" value="Editar" class="btn btn-primary btn-sm">
		</div>	
		
		<div class="col-xs-1">
			<a href='<c:out value="${lineaUrl}"/>' class="btn btn-danger btn-sm">Eliminar </a>
		</div>
		 
		</form:form>
		</div>
	</div>
	</c:forEach>

</div>



</div>
</div>