<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<div class="row">
       
<div class="col-md-9">   
	<form:form action="confEdProveedor" method="POST" modelAttribute="proveedor" class="form-horizontal" role="form">
			<h2>Editar Gasto ${proveedor.nombre}</h2>
			<div class="col-xs-3">
				<label for="concepto">Nombre</label>
				<form:input class="form-control" id="nombre" path="nombre" value="${proveedor.nombre}" type="text"/>
			</div>

			
			<div class="col-xs-2">
				<label for="estado">CIF</label>
				<form:input class="form-control" id="cif" path="cif" value="${proveedor.cif}" type="text"/>
			</div>
			
			<form:input class="form-control" path="idProveedor" value="${proveedor.idProveedor}" type="hidden"/>
			
		<br>
		<div class="col-xs-1">
			<input type="submit" value="Editar" class="btn btn-primary">
			
		</div>	
			
		</form:form>
</div>
</div>		