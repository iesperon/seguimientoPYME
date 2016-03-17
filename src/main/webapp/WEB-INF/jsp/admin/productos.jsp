<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>


	<div class="row">
	
      		
     	<div class="col-md-9">
  		
  		 <div class="col-sm-6 col-md-4">
    		<div class="thumbnail">
    		  <img src="..." alt="...">
      		<div class="caption">
      		  <h3>Thumbnail label</h3>
     		   <p>...</p>
    		    <p><a href="#" class="btn btn-primary" role="button">Button</a> <a href="#" class="btn btn-default" role="button">Button</a></p>
   		   </div>
  		  </div>
		  </div>	
		  
		   <div class="col-sm-6 col-md-4">
    		<div class="thumbnail">
    		  <img src="..." alt="...">
      		<div class="caption">
      		  <h3>Thumbnail label</h3>
     		   <p>...</p>
    		    <p><a href="#" class="btn btn-primary" role="button">Button</a> <a href="#" class="btn btn-default" role="button">Button</a></p>
   		   </div>
  		  </div>
		  </div>
		  
		   <div class="col-sm-6 col-md-4">
    		<div class="thumbnail">
    		  <img src="..." alt="...">
      		<div class="caption">
      		  <h3>Thumbnail label</h3>
     		   <p>...</p>
    		    <p><a href="#" class="btn btn-primary" role="button">Button</a> <a href="#" class="btn btn-default" role="button">Button</a></p>
   		   </div>
  		  </div>
		  </div>
		  
		   <div class="col-sm-6 col-md-4">
    		<div class="thumbnail">
    		  <img src="..." alt="...">
      		<div class="caption">
      		  <h3>Thumbnail label</h3>
     		   <p>...</p>
    		    <p><a href="#" class="btn btn-primary" role="button">Button</a> <a href="#" class="btn btn-default" role="button">Button</a></p>
   		   </div>
  		  </div>
		  </div>
		  
    	
    	 <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
	<script>
		$(document).ready(function(){
    	$("#boton").click(function(){
        	$("#oculto").slideToggle();
    	});
    	
	});
	</script>
    		
    		
    		<button id="boton" onclick="mostrar()" class="btn btn-primary btn-lg btn-block" >Registrar Productos</button>
    		
    		<div id="oculto" style="display: none;">
    		<form:form action="addProducto" method="POST" modelAttribute="myProducto" class="form-horizontal" role="form">
				<div class="col-sm-12">
				<div>
					<h2>Registro de Productos</h2>
						<div class="row">
		
						<div class="col-xs-3">
							<label for="nombre">Código de barras</label>
							<form:input class="form-control" path="codProd" type="text"/>
						</div>
						
						<div class="col-xs-3">
							<label for="calle">Nombre</label>
							<form:input class="form-control" path="nombre" type="text"/>
						</div>
						
						<div class="col-xs-3">
							<label for="numero">Precio</label>
							<form:input class="form-control" path="precio" type="text"/>
						</div>
						
						<div class="col-xs-3">
							<label for="poblacion">Marca</label>
							<form:input class="form-control" path="marca" type="text"/>
						</div>
						
						<div class="col-xs-3">
							<label for="provincia">Descripcion</label>
							<form:input class="form-control" path="descripcion" type="text"/>
						</div>
						
						<div class="col-xs-3">
							<label for="cp">Imagen</label>
							<form:input class="form-control" path="foto" type="text"/>
						</div>
						
						<div class="col-xs-3">
							<label for="pais">Descuento</label>
							<form:input class="form-control" path="descuento" type="text"/>
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

    
   



