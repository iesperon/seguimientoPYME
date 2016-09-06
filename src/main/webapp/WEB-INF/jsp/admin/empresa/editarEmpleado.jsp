<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>


<div class="row">
       
<div class="col-md-9">   


<form:form action="confEdicEmp" method="POST" modelAttribute="myForm" class="form-horizontal" role="form">
				<div class="col-sm-12">
				<div>
					<h2>Editar Empleado</h2>
						<div class="row">
		
						<div class="col-xs-3">
							<label for="nombre">Nombre</label>
							<form:input class="form-control" path="usuario.nombre" value="${usuario.nombre}" type="text"/>
						</div>
						
						<div class="col-xs-3">
							<label for="apellido1">1º Apellido</label>
							<form:input class="form-control" path="usuario.apellido1" value="${usuario.apellido1}" type="text"/>
						</div>
						
						<div class="col-xs-3">
							<label for="apellido2">2º Apellido</label>
							<form:input class="form-control" path="usuario.apellido2" value="${usuario.apellido2}" type="text"/>
						</div>
						
						<div class="col-xs-3">
							<label for="dni">DNI</label>
							<form:input class="form-control" path="usuario.dni" value="${usuario.dni}" type="text"/>
						</div>
						
						<div class="col-xs-3">
							<label for="email">Email</label>
							<form:input class="form-control" path="usuario.email" value="${usuario.email}" type="text"/>
						</div>
						
						<div class="col-xs-3">
							<label for="contrasena">Contraseña</label>
							<form:input class="form-control" path="usuario.contrasena" value="${usuario.contrasena}" type="text"/>
						</div>
						
						<div class="col-xs-3">
							<label for="centro">Centro</label>
							<form:select class="form-control" path="idCentro">
  								<c:forEach var="centros" items="${centroslist}" varStatus="status">
  									<form:option value="${centros.idCentro}"> ${centros.nombre}, ${centros.calle}, ${centros.poblacion} </form:option>
 								</c:forEach>
 							</form:select>						
 						</div>
						
						<div class="col-xs-3">
						<form:input class="form-control" path="usuario.idUsuario" value="${usuario.idUsuario}" type="hidden"/>
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