<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>


<div class="row">
<div class="col-md-9">

	<h2>Seleccione el centro</h2>
	<br>
	<br>
    <c:forEach var="centros" items="${centros}" varStatus="status">
    	<c:url var="centroUrl" value="addTicket">
			<c:param name="idCentro" value="${centros.idCentro}"/>
		</c:url>
		<a href='<c:out value="${centroUrl}"/>'  data-toggle="tooltip" type="button" class="btn btn-primary btn-lg btn-block">${centros.nombre}, ${centros.poblacion}, ${centros.calle} </a>
    </c:forEach>

</div>
</div>