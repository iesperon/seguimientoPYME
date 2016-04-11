<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<div class="row">
<div class="col-md-9">    

<form:form action="addPedido" method="POST" modelAttribute="pedido" class="form-horizontal" role="form">
				<div class="col-sm-12">
				<div>
					<h2>Registro de Pedidos</h2>
								
						<div class="row">
		
						<div class="col-xs-3">
							<label for="fechaCompra">Fecha de Compra</label>
							<form:input class="form-control" path="pedido.fechaCompra" type="date"/>
						</div>
						
						<div class="col-xs-3">
							<label for="fechaVencimiento">Fecha de Vencimiento</label>
							<form:input class="form-control" path="pedido.fechaVencimiento" type="date"/>
						</div>
						
						<div class="col-xs-3">
							<label for="importe">Importe</label>
							<form:input class="form-control" path="pedido.importe" type="text"/>
						</div>
						
						<div class="col-xs-3">
							<label for="estado">Estado</label>
							<form:input class="form-control" path="pedido.estado" type="text"/>
						</div>
						
						<div class="col-xs-3">
							<form:input class="form-control" path="idProveedor" value="${idProveedor}" type="hidden"/>
						</div>										
					</div>
						
					<br>
					<div class="col-xs-6">
						<input type="submit" value="Aceptar" class="btn btn-primary">
					</div>
				</div>
			</div>	
	
			</form:form>


</div>
</div>