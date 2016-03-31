<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>


	<div class="row">
		<div class="col-md-9">
       
       <c:forEach var="productos" items="${productoslist}" varStatus="status">
        		 <div class="col-sm-6 col-md-4">
    		<div class="thumbnail">
    		  <img src="..." alt="...">
      		<div class="caption">
      		  <h3>${productos.nombre}</h3>
     		   <p>${productos.descripcion}</p>
     		   <p>${productos.precio}€</p>
     		   <c:url var="productoUrl" value="eliminarProducto">
					<c:param name="idProducto" value="${productos.idProducto}"/>
				</c:url>
				<c:url var="productoUrl2" value="editarProducto">
					<c:param name="idProducto" value="${productos.idProducto}"/>
				</c:url>
    		    <p><a href='<c:out value="${productoUrl2}"/>' class="btn btn-default" role="button">Editar</a> <a href='<c:out value="${productoUrl}"/>' class="btn btn-danger" role="button">Eliminar</a></p>
   		   </div>
  		  </div>
		  </div>	
		  
	  </c:forEach>
		  
		  
    	
	<script>
		$(document).ready(function(){
    	$("#boton").click(function(){
        	$("#oculto").slideToggle();
    	});
    	
	});
	</script>
    <!-- Hace que no pase nada pulsando intro -->
  		<script>
    		 function anular(e) {
    	     	 tecla = (document.all) ? e.keyCode : e.which;
          		return (tecla != 13);
     		}
		</script>
    		
    		<button id="boton" onclick="mostrar()" class="btn btn-primary btn-lg btn-block" >Registrar Productos</button>
    		
    		<div id="oculto" style="display: none;">
    		<form:form action="addProducto" method="POST" modelAttribute="myForm" class="form-horizontal" role="form" onkeypress="return anular(event)">
				<div class="col-sm-12">
				<div>
					<h2>Registro de Productos</h2>
						<div class="row">
		
						<div class="col-xs-3">
							<label for="nombre">Código de barras</label>
							<form:input class="form-control" path="producto.codProd" type="text"/>
						</div>
						
						<div class="col-xs-3">
							<label for="calle">Nombre</label>
							<form:input class="form-control" path="producto.nombre" type="text"/>
						</div>
						
						<div class="col-xs-3">
							<label for="numero">Precio</label>
							<form:input class="form-control" path="producto.precio" type="text"/>
						</div>
						
						<div class="col-xs-3">
							<label for="poblacion">Marca</label>
							<form:input class="form-control" path="producto.marca" type="text"/>
						</div>

						<div class="col-xs-3">
							<label for="cp">Imagen</label>
							<form:input class="form-control" path="producto.foto" type="text"/>
						</div>
						
						<div class="col-xs-3">
							<label for="pais">Descuento</label>
							<form:input class="form-control" path="producto.descuento" type="text"/>
						</div>
					
						<div class="col-xs-3">
							<label for="iva">Iva</label>
							<form:select class="form-control" path="idIva">
  								<c:forEach var="ivas" items="${ivas}" varStatus="status">
  									<form:option value="${ivas.idIva}">${ivas.nombre}, ${ivas.porcentaje}% </form:option>
 								</c:forEach>
 							</form:select>						
 						</div>
					
						<div class="col-xs-3">
							<label for="descripcion">Descripcion</label>
							<form:textarea class="form-control" rows="3" path="producto.descripcion"/> 
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

    
   



