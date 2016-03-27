<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>


	<div class="row">
       
      		
     	<div class="col-md-9">
     	    <img src="<c:url value="/resources/images/centros.png" />" class="center-block" alt="Empleados" width="800" height="200">
    		<h2> Centros </h2>
    			<table class="table table-hover">
    				<tr>
        				<th>Nombre</th>
        				<th>Calle</th>
        				<th>Poblacion</th>
        				<th>Provincia</th>
        				<th>Pais</th>
        				<th>Email</th>
        				<th>Teléfono</th>
        				<th>Empleados</th>
        				<th> </th>
   					</tr>
    			<c:forEach var="centro" items="${centroslist}" varStatus="status">
    				<tr>
    					<td>${centro.nombre}</td>
    					<td>${centro.calle}</td>
    					<td>${centro.poblacion}</td>
    					<td>${centro.provincia}</td>
    					<td>${centro.pais}</td>
    					<td>${centro.email}</td>
    					<td>${centro.telefono}</td>
    					
    					<c:url var="centroUrl" value="eliminarCentro">
							<c:param name="idCentro" value="${centro.idCentro}"/>
						</c:url>
						<c:url var="centroUrl2" value="crearEmpleado">
							<c:param name="idCentro" value="${centro.idCentro}"/>
						</c:url>
						<c:url var="centroUrl3" value="editarCentro">
							<c:param name="idCentro" value="${centro.idCentro}"/>
						</c:url>
						<c:url var="centroUrl4" value="stock">
							<c:param name="idCentro" value="${centro.idCentro}"/>
						</c:url>
						
						<td><a href='<c:out value="${centroUrl2}"/>'>Añadir</a></td>
						<td>
							<select class="form-control">
								<option>Accion</option>
  								<option><a href='<c:out value="${centroUrl}"/>'>Eliminar </a></option>
  								<option><a href='<c:out value="${centroUrl3}"/>'>Editar</a></option>
  								<option><a href='<c:out value="${centroUrl4}"/>'>Stock</a></option>
  							</select>
						</td>		
						<td><a href='<c:out value="${centroUrl}"/>'>Eliminar </a> | <a href='<c:out value="${centroUrl3}"/>'>Editar</a> | <a href='<c:out value="${centroUrl4}"/>'>Stock</a></td>
					
								
						
	   				</tr>    		
    			</c:forEach>
    		

    		
    			
    			</table>
 		
	<script>
		$(document).ready(function(){
    	$("#boton").click(function(){
        	$("#oculto").slideToggle();
    	});
    	
	});
	</script>
    		
    		
    		<button id="boton" onclick="mostrar()" class="btn btn-primary btn-lg btn-block" >Registrar Centro</button>
    		
    		<div id="oculto" style="display: none;">
    		<form:form action="addCentro" method="POST" modelAttribute="myCentro" class="form-horizontal" role="form">
				<div class="col-sm-12">
				<div>
					<h2>Registro de Centros</h2>
						<div class="row">
		
						<div class="col-xs-3">
							<label for="nombre">Nombre</label>
							<form:input class="form-control" path="nombre" type="text"/>
						</div>
						
						<div class="col-xs-3">
							<label for="calle">Calle</label>
							<form:input class="form-control" path="calle" type="text"/>
						</div>
						
						<div class="col-xs-3">
							<label for="numero">Numero</label>
							<form:input class="form-control" path="numero" type="text"/>
						</div>
						
						<div class="col-xs-3">
							<label for="poblacion">Poblacion</label>
							<form:input class="form-control" path="poblacion" type="text"/>
						</div>
						
						<div class="col-xs-3">
							<label for="provincia">Provincia</label>
							<form:input class="form-control" path="provincia" type="text"/>
						</div>
						
						<div class="col-xs-3">
							<label for="cp">C.P</label>
							<form:input class="form-control" path="cp" type="text"/>
						</div>
						
						<div class="col-xs-3">
							<label for="pais">Pais</label>
							<form:input class="form-control" path="pais" type="text"/>
						</div>
						
						<div class="col-xs-3">
							<label for="email">Email</label>
							<form:input class="form-control" path="email" type="text"/>
						</div>
						
						<div class="col-xs-3">
							<label for="telefono">Telefono</label>
							<form:input class="form-control" path="telefono" type="text"/>
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
      
      </div>

    
   



