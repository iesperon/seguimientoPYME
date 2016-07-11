<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class="row">
<div class="col-md-9">

	<form:form action="editarEnvio" method="POST" modelAttribute="envio" class="form-horizontal" role="form">
				<div class="col-sm-12">
				<div>
					<h2>Detalles del envio</h2>
						<div class="row">
		
						<div class="col-xs-3">
							<label for="nombre">Nombre</label>
							<form:input class="form-control" path="nombre" value="${envio.nombre}" type="text"/>
						</div>
						
						<div class="col-xs-3">
							<label for="calle">Calle</label>
							<form:input class="form-control" path="calle" value="${envio.calle}" type="text"/>
						</div>
						
						<div class="col-xs-3">
							<label for="numero">Numero</label>
							<form:input class="form-control" path="numero" value="${envio.numero}" type="text"/>
						</div>
						
						<div class="col-xs-3">
							<label for="poblacion">Puerta</label>
							<form:input class="form-control" path="puerta" value="${envio.puerta}" type="text"/>
						</div>
						
						<div class="col-xs-3">
							<label for="poblacion">CP</label>
							<form:input class="form-control" path="cp" value="${envio.cp}" type="text"/>
						</div>
						
						<div class="col-xs-3">
							<label for="provincia">Provincia</label>
							<form:input class="form-control" path="provincia" value="${envio.provincia}" type="text"/>
						</div>
						
						<div class="col-xs-3">
							<label for="provincia">Poblacion</label>
							<form:input class="form-control" path="poblacion" value="${envio.poblacion}" type="text"/>
						</div>
						
				
						<div class="col-xs-3">
							<label for="pais">Pais</label>
							<form:input class="form-control" path="pais" value="${envio.pais}" type="text"/>
						</div>
						
						<div class="col-xs-3">
							<label for="provincia">Empresa Transporte</label>
							<form:input class="form-control" path="empresa" value="${envio.empresa}" type="text"/>
						</div>
						
						<div class="col-xs-3">
							<label for="email">Numero Seguimiento</label>
							<form:input class="form-control" path="numSeguimiento" value="${envio.numSeguimiento}" type="text"/>
						</div>
						
						<div class="col-xs-3">
							<label for="provincia">Estado</label>
							<form:input class="form-control" path="estado" value="${envio.estado}" type="text"/>
						</div>
						
						<div class="col-xs-3">
							<form:input class="form-control" path="idEnvio" value="${envio.idEnvio}" type="hidden"/>
						</div>	
										
						</div>
						
					<br>
					<div class="col-xs-6">
						<input type="submit" value="Editar" class="btn btn-primary">
					</div>
				</div>
			</div>	
	
			</form:form>
	</div>
</div>