<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="sec"	uri="http://www.springframework.org/security/tags"%>



<div  style="padding-top: auto;">
	<div class="row">	
    	<div class="container">
	
		<div id="contentwrapper">
			<div id="content">

	 <div class="row">
  		<div class="col-sm-6">
  		<div class="row">
  		<div class="col-sm-2"></div>  		
		 	<img src="<c:url value="/resources/images/logo11.png" />" class="img-rounded" alt="Logo" width="304" height="236">
		</div>
  			<h2>Descripción: </h2>
  				<div class="row">
  				  		<div class="col-sm-1"></div>
						<div class="col-sm-8">
						<p class="text-justify">
						La importancia de tener un sistema de control interno en las pequeñas y medianas empresas ha ido incrementado en los últimos años, 
						siendo esta la parte que se encarga de la organización de los procedimientos y necesidades del negocio, así como de verificar la eficiencia y la 
						productividad en las operaciones. 
						</p>
						<p class="text-justify">
						Por todo esto ofrecemos la posibilidad de realizar un control de los aspectos más esenciales de la actividad comercial de tú empresa. 
						</p>
						
						</div>
  				</div>
  		</div>
  		
  		<!-- Hace que no pase nada pulsando intro -->
  		<script>
    		 function anular(e) {
    	     	 tecla = (document.all) ? e.keyCode : e.which;
          		return (tecla != 13);
     		}
		</script>
  		
  	<sec:authorize access="not isAuthenticated()">
  		  		
  		<form:form action="addUser" name="myForm" method="POST" modelAttribute="myForm" class="form-horizontal" role="form" onkeypress="return anular(event)">
				<div class="col-sm-6">
				<div>
  				<h2>Registro de Empresas</h2>
				
				<div class="row">
				<h4>Usuario:</h4>	
				
					<div class="col-xs-3">
						<label for="nombre">Nombre</label>
						<form:input class="form-control" path="usuario.nombre" type="text"/>
					</div>
				
					<div class="col-xs-3">
						<label for="apellido1">1º Apellido</label>
						<form:input class="form-control" path="usuario.apellido1" type="text"/>
					</div>
					
					<div class="col-xs-3">
						<label for="apellido2">2º Apellido</label>
						<form:input class="form-control" path="usuario.apellido2" type="text"/>
					</div>
					
					<div class="col-xs-3">
						<label for="email">Email</label>
						<form:input class="form-control" path="usuario.email" type="text"/>
						<form:errors path="usuario.email" class="alert alert-danger" />	
					</div>
					
					<div class="col-xs-3">
						<label for="dni">DNI</label>
						<form:input class="form-control" id="dni" path="usuario.dni" type="text"/>
						<form:errors path="usuario.dni" class="alert alert-danger" />
						
					</div>
					
					<div class="col-xs-3">
						<label for="contr">Contraseña</label>
						<form:input class="form-control" id="contr" path="usuario.contrasena" type="text"/>
						<form:errors path="usuario.contrasena" class="alert alert-danger" />
						
					</div>
					<div class="col-xs-3">
						<label for="contr">Repita contraseña</label>
						<form:input class="form-control" id="contr" path="contra2" type="text"/>
					</div>
					
					<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
				</div>
					
				<div class="row">	
					<h4> Empresa: </h4>
					
					<div class="col-xs-3">
						<label for="nombre">Nombre</label>
						<form:input class="form-control" path="empresa.nombre" type="text"/>
					</div>
				
					<div class="col-xs-3">
						<label for="cif">CIF</label>
						<form:input class="form-control" path="empresa.cif" type="text"/>
					</div>
					
					<div class="col-xs-3">
						<label for="sector">Sector</label>
						<form:input class="form-control" path="empresa.sector"type="text"/>
					</div>
					
					<div class="col-xs-3">
						<label for="email">Email</label>
						<form:input class="form-control" path="empresa.email"  type="text"/>
					</div>
					
					<div class="col-xs-6">
						<label for="descripcion">Descripcion</label>
						<form:textarea class="form-control" rows="3" path="empresa.descripcion"/> 
					</div>
					
					
<!-- 					<div class="col-xs-6"> -->
<!-- 						<label for="logo">Logo</label> -->
<%-- 						<span class="btn btn-default btn-file"> --%>
<%-- 							<form:input path="empresa.logo"  type="file"/> --%>
<%-- 						</span> --%>
<!-- 					</div> -->
					
					
				</div>
					<div class="col-xs-9"></div>
					<div class="col-xs-3">
						<input type="submit" value="Aceptar" class="btn btn-primary btn-lg" onclick="submitForms()">
					</div>
					
				</div>
			</div>		
	</form:form>
	</sec:authorize>
	
	</div>
	
	</div>
</div>
</div>
</div>
</div>
