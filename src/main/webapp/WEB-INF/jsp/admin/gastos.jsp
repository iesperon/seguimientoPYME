<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<div class="row">
<div class="col-md-9">
	<h2>Gastos</h2>
	<div class="col-xs-3">
		<label for="concepto">Concepto</label>
	</div>
	<div class="col-xs-1">
		<label for="importe">Importe</label>
	</div>
	<div class="col-xs-2">
		<label for="fechaEmision">Fecha Emision</label>
	</div>
	<div class="col-xs-2">
		<label for="fechaPago">Fecha Vencimiento</label>
	</div>
	<div class="col-xs-2">
		<label for="estado">Estado</label>
	</div>
	
	<c:forEach var="gastos" items="${gastoslist}" varStatus="status">
		
		<form:form action="editGasto" method="POST" modelAttribute="newGasto" class="form-horizontal" role="form">
			<div class="col-xs-3">
				<form:input class="form-control" id="concepto" path="concepto" value="${gastos.concepto}" type="text"/>
			</div>

			<div class="col-xs-1">
				<form:input class="form-control" id="importe" path="importe" value="${gastos.importe}" type="text"/>
			</div>
			
			<div class="col-xs-2">
				<form:input class="form-control" id="fechaEmision" path="fechaEmision" value="${gastos.fechaEmision}" type="date"/>
			</div>
			
			<div class="col-xs-2">
				<form:input class="form-control" id="fechaPago" path="fechaPago" value="${gastos.fechaPago}" type="date"/>
			</div>
			
			<div class="col-xs-2">
				<form:input class="form-control" id="name" path="estado" value="${gastos.estado}" type="text"/>
			</div>
			
			<form:input class="form-control" path="idGasto" value="${gastos.idGasto}" type="hidden"/>
			
			<c:url var="gastoUrl" value="eliminarGasto">
				<c:param name="idGasto" value="${gastos.idGasto}"/>
			</c:url>


		<div class="col-xs-1">
			<input type="submit" value="Editar" class="btn btn-primary btn-sm">
			
		</div>	
		<div class="col-xs-1">
			<a href='<c:out value="${gastoUrl}"/>' class="btn btn-danger btn-sm">Eliminar </a>
		</div>	
			
		</form:form>
		
	</c:forEach>
	
	
	<div class="col-sm-12">
	<div>
	
	<form:form action="addGasto" method="POST" modelAttribute="newGasto" class="form-horizontal" role="form">
			
			<h2>Nuevo Gasto</h2>
			
			<div class="col-xs-3">
				<label for="descripcion">Concepto</label>
				<form:input class="form-control" path="concepto" type="text"/>
			</div>

			<div class="col-xs-1">
				<label for="descripcion">Importe</label>
				<form:input class="form-control" path="importe" type="text"/>
			</div>
			
			<div class="col-xs-2">
				<label for="descripcion">Fecha Emision</label>
				<form:input class="form-control" path="fechaEmision" type="datetime"/>
			</div>
			
			<div class="col-xs-2">
				<label for="descripcion">Fecha Vencimiento</label>
				<form:input class="form-control" path="fechaPago" type="datetime"/>
			</div>
			
			<div class="col-xs-2">
				<label for="estado">Estado</label>
				<form:input class="form-control" path="estado" type="text"/>
			</div>
			
			<br>
			<input type="submit" value="Aceptar" class="btn btn-primary">
		</form:form>
	
	
	
	</div>
	</div>



</div>
</div>