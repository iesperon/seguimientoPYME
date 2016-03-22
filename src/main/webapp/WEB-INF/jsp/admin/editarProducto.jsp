<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>


<div class="row">
       
<div class="col-md-9">   

	<form:form action="confEdicProd" method="POST" modelAttribute="producto" class="form-horizontal" role="form" onkeypress="return anular(event)">
			<div class="col-sm-12">
			<div>
				<h2>Editar Producto</h2>
					<div class="row">
					<div class="col-xs-3">
						<label for="nombre">Código de barras</label>
						<form:input class="form-control" path="codProd" value="${producto.codProd}" type="text"/>
					</div>
					
					<div class="col-xs-3">
						<label for="calle">Nombre</label>
						<form:input class="form-control" path="nombre" value="${producto.nombre}" type="text"/>
					</div>
					
					<div class="col-xs-3">
						<label for="numero">Precio</label>
						<form:input class="form-control" path="precio" value="${producto.precio}" type="text"/>
					</div>
					
					<div class="col-xs-3">
						<label for="poblacion">Marca</label>
						<form:input class="form-control" path="marca" value="${producto.marca}" type="text"/>
					</div>
						<div class="col-xs-3">
						<label for="cp">Imagen</label>
						<form:input class="form-control" path="foto" value="${producto.foto}" type="text"/>
					</div>
					
					<div class="col-xs-3">
						<label for="pais">Descuento</label>
						<form:input class="form-control" path="descuento" value="${producto.descuento}" type="text"/>
					</div>
				
					<div class="col-xs-6">
						<label for="descripcion">Descripcion</label>
						<form:textarea class="form-control" rows="3" path="descripcion" value="${producto.descripcion}"/> 
					</div>
					
					<div class="col-xs-3">
						<form:input class="form-control" path="idProducto" value="${idProducto}" type="hidden"/>
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