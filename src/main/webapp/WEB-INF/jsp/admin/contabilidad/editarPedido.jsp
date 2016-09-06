<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<div class="row">
<div class="col-md-9">    

<form:form action="confEdicPedido" method="POST" modelAttribute="pedido" class="form-horizontal" role="form">
				<div class="col-sm-12">
				<div>
					<h2>Editar de Pedido</h2>
								
						<div class="row">
		
						<div class="col-xs-3">
							<label for="fechaCompra">Fecha de Compra</label>
							<fmt:formatDate value="${pedido.fechaCompra}" var="fechaCompra" pattern="yyyy-MM-dd" />
							<form:input class="form-control" path="fechaCompra"  value="${fechaCompra}" type="date"/>
						</div>
						
						<div class="col-xs-3">
							<label for="fechaVencimiento">Fecha de Vencimiento</label>
							<fmt:formatDate value="${pedido.fechaVencimiento}" var="fechaVencimiento" pattern="yyyy-MM-dd" />
							<form:input class="form-control" path="fechaVencimiento" value="${fechaVencimiento}" type="date"/>
						</div>
						
						<div class="col-xs-2">
							<label for="importe">Importe</label>
							<div class="input-group"> 
							<span class="input-group-addon">€</span>
							<form:input class="form-control" path="importe" value="${pedido.importe}" type="text"/>
							</div>
						</div>
						<div class="col-xs-2">
							<label for="estado">Estado</label>
							<form:select class="form-control" path="estado" value="${pedido.estado}">
  								<form:option value="Pendiente">Pendiente</form:option>
  								<form:option value="Pagado">Pagado</form:option>
 							</form:select>						
 						</div>
								
					<div class="col-xs-3">
						<form:input class="form-control" path="idPedidoProveedor" value="${pedido.idPedidoProveedor}" type="hidden"/>
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