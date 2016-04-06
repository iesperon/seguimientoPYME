<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>


<div class="row">
<div class="col-md-9">

	<h2>Caja</h2>

	<div class="col-xs-2">
		<label for="codProd">Referencia</label>
	</div>
	<div class="col-xs-3">
		<label for="nombre">Nombre</label>
	</div>
	<div class="col-xs-2">
		<label for="precio">Precio</label>
	</div>
	<div class="col-xs-1">
		<label for="cantidad">Cantidad</label>
	</div>
	<div class="col-xs-1">
		<label for="descuento">Descuento</label>
	</div>
	<div class="col-xs-2">
		<label for="total">Total</label>
	</div>

	<form:form action="addArticulo" method="GET" modelAttribute="producto" class="form-horizontal" role="form">
				<div class="col-sm-12">
				<div>
								
						<div class="row">
		
						<div class="col-xs-3">
							<label for="referencia">Referencia</label>
							<form:input class="form-control" path="referencia" type="text"/>
						</div>
						
						<div class="col-xs-3">
							<label for="nombre">Nombre</label>
							<form:input class="form-control" path="nombre" type="text"/>
						</div>
						
						<div class="col-xs-3">
							<label for="precio">Precio</label>
							<form:input class="form-control" path="precio" type="text"/>
						</div>
						
						<div class="col-xs-3">
							<label for="cantidad">Cantidad</label>
							<form:input class="form-control" path="cantidad" type="text"/>
						</div>
						
						<div class="col-xs-3">
							<label for="descuento">Descuento</label>
							<form:input class="form-control" path="descuento" type="text"/>
						</div>
						
						<div class="col-xs-3">
							<label for="contrasena">Contraseña</label>
							<form:input class="form-control" path="usuario.contrasena" type="text"/>
						</div>
						
						<div class="col-xs-3">
							<form:input class="form-control" path="idCentro" value="${idCentro}" type="hidden"/>
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