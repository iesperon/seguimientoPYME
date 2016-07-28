<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<div class="row">
       
<div class="col-md-9">   
	<form:form action="confEdGasto" method="POST" modelAttribute="gasto" class="form-horizontal" role="form">
			<h2>Editar Gasto ${gasto.concepto}</h2>
			<div class="col-xs-3">
				<label for="concepto">Concepto</label>
				<form:input class="form-control" id="concepto" path="concepto" value="${gasto.concepto}" type="text"/>
			</div>

			<div class="col-xs-2">
			<label for="importe">Importe</label>
			<div class="input-group"> 
				<span class="input-group-addon">€</span>
				<form:input class="form-control" id="importe" path="importe" value="${gasto.importe}" type="text"/>
			</div>
			</div>
			
			<div class="col-xs-2">
				<label for="fechaEmision">Fecha Emision</label>
				<form:input class="form-control" id="fechaEmision" path="fechaEmision" value="${gasto.fechaEmision}" type="date"/>
			</div>
			
			<div class="col-xs-2">
				<label for="fechaPago">Fecha Pago</label>
				<form:input class="form-control" id="fechaPago" path="fechaPago" value="${gasto.fechaPago}" type="date"/>
			</div>
			
			<div class="col-xs-2">
				<label for="estado">Estado</label>
				<form:input class="form-control" id="name" path="estado" value="${gasto.estado}" type="text"/>
			</div>
			
			<form:input class="form-control" path="idGasto" value="${gasto.idGasto}" type="hidden"/>
			
		<br>
		<div class="col-xs-1">
			<input type="submit" value="Editar" class="btn btn-primary">
			
		</div>	
			
		</form:form>
</div>
</div>		