<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<div class="row">
<div class="col-md-9">

	<h2>Ticket ${ticket.idTicket}</h2>
	<br>
	<h4>Fecha: ${ticket.fecha}</h4>
	
	<div class="col-xs-3"><h5>Producto</h5></div><div class="col-xs-1"><h5>Cantidad</h5></div><div class="col-xs-2"><h5>IVA</h5></div><div class="col-xs-2"><h5>Precio ud.</h5></div>
	<c:forEach var="lineas" items="${lineas}" varStatus="status">
	<p><div class="col-xs-3">${lineas.producto.nombre }</div> <div class="col-xs-1">${lineas.cantidad }</div> <div class="col-xs-2">${lineas.iva }</div> <div class="col-xs-2">${lineas.precio }</div>   </p>
	</c:forEach>
	
	<h5>Forma pago: ${ticket.formaPago}    Subtotal: ${ticket.subtotal }   Total: ${ticket.total }</h5>
	
</div>
</div>
	
	
	
	
	