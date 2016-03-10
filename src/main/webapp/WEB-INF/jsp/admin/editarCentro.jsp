<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>


<div class="row">
       
<div class="col-md-9">   

<form:form action="confirmarEdicion" method="POST" modelAttribute="centro" class="form-horizontal" role="form">
				<div class="col-sm-12">
				<div>
					<h2>Modificare Centros</h2>
						<div class="row">
		
						<div class="col-xs-3">
							<label for="nombre">Nombre</label>
							<form:input class="form-control" path="nombre" value="${centro.nombre}" type="text"/>
						</div>
						
						<div class="col-xs-3">
							<label for="calle">Calle</label>
							<form:input class="form-control" path="calle" value="${centro.calle}" type="text"/>
						</div>
						
						<div class="col-xs-3">
							<label for="numero">Numero</label>
							<form:input class="form-control" path="numero" value="${centro.numero}" type="text"/>
						</div>
						
						<div class="col-xs-3">
							<label for="poblacion">Poblacion</label>
							<form:input class="form-control" path="poblacion" value="${centro.poblacion}" type="text"/>
						</div>
						
						<div class="col-xs-3">
							<label for="provincia">Provincia</label>
							<form:input class="form-control" path="provincia" value="${centro.provincia}" type="text"/>
						</div>
						
						<div class="col-xs-3">
							<label for="cp">C.P</label>
							<form:input class="form-control" path="cp" value="${centro.cp}" type="text"/>
						</div>
						
						<div class="col-xs-3">
							<label for="pais">Pais</label>
							<form:input class="form-control" path="pais" value="${centro.pais}"  type="text"/>
						</div>
						
						<div class="col-xs-3">
							<label for="email">Email</label>
							<form:input class="form-control" path="email" value="${centro.email}"  type="text"/>
						</div>
						
						<div class="col-xs-3">
							<label for="telefono">Telefono</label>
							<form:input class="form-control" path="telefono" value="${centro.telefono}"  type="text"/>
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