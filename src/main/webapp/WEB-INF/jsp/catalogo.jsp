<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>


	<div class="row">
		<div class="col-md-9">
       	<h2>Catálogo de la empresa: ${empresa.nombre }</h2>
       
       <c:forEach var="productos" items="${productoslist}" varStatus="status">
        		 <div class="col-sm-6 col-md-4">
    		<div class="thumbnail">
    		  <img src="..." alt="...">
      		<div class="caption">
      		  <h3>${productos.nombre}</h3>
     		   <p>${productos.descripcion}</p>
     		   <p>${productos.precio}€</p>
     		</div>
  		  </div>
		  </div>	
		  
	  </c:forEach>

    		</div>	
          </div>
  