<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>


<div class="row">
       
<div class="col-md-9">   

<h2>Subir Imagen </h2>

	<form method="POST" action="singleUpload?${_csrf.parameterName}=${_csrf.token}" enctype="multipart/form-data">
		Archivo: <input type="file" name="file"><br /> 
		<input type="hidden" name="idProducto" value="${idProducto}">
		<input type="submit" value="Aceptar">
		
	</form>

</div>
</div>