<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


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
		<label for="referencia">Referencia</label>
	</div>
	<div class="col-xs-1">
		<label for="cantidad">Cantidad</label>
	</div>
	<div class="col-xs-3">
		<label for="producto">Producto</label>
	</div>
	<div class="col-xs-1">
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
				<form:input class="form-control" id="referencia" path="producto.codProd" value="${lineas.producto.codProd}"  readonly="true"  type="text"/>
			</div>
			<div class="col-xs-1">
				<form:input class="form-control" id="cantidad" path="cantidad" value="${lineas.cantidad}" type="text"/>
			</div>
			<div class="col-xs-3">
				<form:input class="form-control" id="producto" path="producto.nombre" value="${lineas.producto.nombre}"  readonly="true" type="text"/>
			</div>
			<div class="col-xs-1">
				<form:input class="form-control" id="iva" path="iva" value="${lineas.iva}" type="text"/>
			</div>
			<div class="col-xs-2">
				<form:input class="form-control" id="precio" path="precio" value="${lineas.precio}" type="text"/>
			</div>
			<form:input class="form-control" path="idLineaTicket" value="${lineas.idLineaTicket}" type="hidden"/> 
			<form:input class="form-control" path="ticket.idTicket" value="${lineas.ticket.idTicket}" type="hidden"/> 
			
		<div class="col-xs-1">
			<c:url var="lineaUrl" value="eliminarGasto">
				<c:param name="idLineaTicket" value="${lineas.idLineaTicket}"/>
			</c:url>
		</div>	
		<div class="col-xs-1">
		<button type="submit" class="btn btn-sm btn-warning">
			<i class="glyphicon glyphicon-edit"></i>
		</button>
		</div>	
		
		<div class="col-xs-1">
			<a href='<c:out value="${lineaUrl}"/>' data-toggle="tooltip" type="button" class="btn btn-sm btn-danger"><i class="glyphicon glyphicon-remove"></i></a>
		</div>
		 
		</form:form>
		</div>
	</div>
	</c:forEach>


</div>
<div class="col-md-12 text-left" >
	<h3>Subtotal : <fmt:formatNumber value="${subtotal}" maxFractionDigits="2"/> €</h3>
	<h3>IVA: <fmt:formatNumber value="${iva}" maxFractionDigits="2"/> €</h3>
	<h3>TOTAL: <fmt:formatNumber value="${total}" maxFractionDigits="2"/> €</h3>
</div>

	<form:form action="cerrarTicket" method="POST" modelAttribute="ticket" class="form-horizontal" role="form"> 
		<form:input class="form-control" path="subtotal" value="${subtotal}" type="hidden"/> 
		<form:input class="form-control" path="iva" value="${iva}" type="hidden"/> 
		<form:input class="form-control" path="total" value="${total}" type="hidden"/> 
		<form:input class="form-control" path="idTicket" value="${ticket.idTicket}" type="hidden"/> 
		
		<div class="col-xs-2">
			<label for="entregado">Entregado</label>
			<form:input class="form-control" id="entregado" path="entregado"  type="text"/>
		</div>
		<div class="col-xs-3">
			<label for="centro">Forma de pago</label>
			<form:select class="form-control" path="formaPago">
				<form:option value="Efectivo">Efectivo</form:option>
				<form:option value="Tarjeta">Tarjeta</form:option>
			</form:select>						
 		</div>
 		
 		<button type="submit" class="btn btn-primary">Imprimir Ticket</button>
 		
	
	</form:form>
	<h3>Cambio: <fmt:formatNumber value="${ticket.cambio}" maxFractionDigits="2"/> €</h3>
		
</div>
</div>