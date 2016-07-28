<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class="row">
<div class="col-md-9">
<div class="panel panel-primary">
    <div class="panel-heading">
     	<h3 class="panel-title">CIERRE DE CAJA</h3>
                    
        <div class="panel-body">
        	<div class="tab-content">
            <form:form action="addCierre" method="POST" modelAttribute="newFormCierre" class="form-horizontal" role="form">

			<div class="col-xs-2">
				<label for="descripcion">Ingreso Tarjeta</label>
				<div class="input-group"> 
				<span class="input-group-addon">€</span>
				<form:input class="form-control" path="cierre.tarjeta" value="${tarjeta}" type="text"/>
				</div>
			</div>
			<div class="col-xs-2">
				<label for="descripcion">Ingreso Efectivo</label>
				<div class="input-group"> 
				<span class="input-group-addon">€</span>
				<form:input class="form-control" path="cierre.efectivo" value="${efectivo}" type="text"/>
				</div>
			</div>
			<div class="col-xs-2">
				<label for="descripcion">Total</label>
				<div class="input-group"> 
				<span class="input-group-addon">€</span>
				<form:input class="form-control" path="cierre.total" value="${total}" type="text"/>
				</div>
			</div>
			
			<div class="col-xs-2">
				<label for="descripcion">Saldo Contabilizado</label>
				<div class="input-group"> 
				<span class="input-group-addon">€</span>
				<form:input class="form-control" path="cierre.caja" type="text"/>
				</div>
			</div>
			
			<div class="col-xs-3">
				<label for="estado">Observaciones</label>
				<form:input class="form-control" path="cierre.incidencias" type="text"/>
			</div>
			
			<form:input class="form-control" path="idCentro" value="${idCentro}" type="hidden"/> 
			
        <button type="submit" class="btn btn-primary">Aceptar</button>
			
		</form:form>
            <p>Diferencia: ${cierre.diferencia}</p> 
            </div>
      	</div>
	</div>
</div>       
</div>
</div>




	
