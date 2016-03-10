<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>


	<div class="row">
       
      		
     	<div class="col-md-9">
    		<h2> Empleados </h2>
    			<table class="table table-hover">
    				<tr>
        				<th>Nombre</th>
        				<th>1º Apellido</th>
        				<th>2º Apellido</th>
        				<th>DNI</th>
        				<th>Email</th>
        				<th></th>
   					</tr>
    			<c:forEach var="usuario" items="${usuarioslist}" varStatus="status">
    				<tr>
    					<td>${usuario.nombre}</td>
    					<td>${usuario.apellido1}</td> 
    					<td>${usuario.apellido2}</td>
    					<td>${usuario.dni}</td>
    					<td>${usuario.email}</td>
    					
    					<c:url var="usuarioUrl" value="eliminarEmpleado">
							<c:param name="idUsuario" value="${usuario.idUsuario}"/>
						</c:url>
						<c:url var="usuarioUrl2" value="editarEmpleado">
							<c:param name="idUsuario" value="${usuario.idUsuario}"/>
						</c:url>
						
						<td><a href='<c:out value="${usuarioUrl}"/>'>Eliminar </a> | <a href='<c:out value="${usuarioUrl2}"/>'>Editar</a></td>
						
    					
    				
	   				</tr>    		
    			</c:forEach>
    			</table>
    		
    	</div>
      
      </div>

    
   



