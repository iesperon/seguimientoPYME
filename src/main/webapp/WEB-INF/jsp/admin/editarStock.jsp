<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<div class="row">    
<div class="col-md-9">   
<form:form action="confEditStock" method="POST" modelAttribute="stock" class="form-horizontal" role="form">

	<div class="col-sm-12">
				<div>
					<h2>Stock ${stock.producto.nombre}</h2>
						<div class="row">
		
						<div class="col-xs-3">
							<label for="stockActual">Stock actual</label>
							<form:input class="form-control" path="stockActual" value="${stock.stockActual}" type="text"/>
						</div>
						
						<div class="col-xs-3">
							<label for="stockMin">Stock minimo</label>
							<form:input class="form-control" path="stockMin" value="${stock.stockMin}" type="text"/>
						</div>
						
						<div class="col-xs-3">
							<form:input class="form-control" path="idStock" value="${idStock}" type="hidden"/>
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