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
				<form:input class="form-control" path="cierre.total" value="${total}" name="total" id="total" type="text"/>
				</div>
			</div>
			
			<div class="col-xs-2">
				<label for="descripcion">Contabilizado</label>
				<div class="input-group"> 
				<span class="input-group-addon">€</span>
				<form:input class="form-control" path="cierre.caja" id="saldoContabilizado" type="text"/>
				</div>
			</div>
			
			<div class="col-xs-3">
				<label for="estado">Observaciones</label>
				<form:input class="form-control" path="cierre.incidencias" name="observaciones" id="observaciones" type="text"/>
			</div>
			
			<form:input class="form-control" path="idCentro" value="${idCentro}" type="hidden"/> 
			
<!--         <button type="submit" class="btn btn-primary">Aceptar</button> -->
		<a class="open-AddBookDialog btn btn-success" href="#addBookDialog">Aceptar</a>		</form:form>
<%--             <p>Diferencia: ${cierre.diferencia}</p>  --%>
            </div>
      	</div>
	</div>
</div>       
</div>
</div>

<script type="text/javascript">
$(document).on("click", ".open-AddBookDialog", function (e) {

	e.preventDefault();

	var _self = $(this);

	var ingresoTotal = $('#total').val();
	$("#ingresoTotal").val(ingresoTotal);
	var ingresoContabilizadoModal = $('#saldoContabilizado').val();
	$("#ingresoContabilizadoModal").val(ingresoContabilizadoModal);
	var observacionesModal = $('observaciones').val();
	$("#observacionesModal").val(observacionesModal); 
	
	$("#diferencia").val(ingresoContabilizadoModal-ingresoTotal);
	$(_self.attr('href')).modal('show');
});
</script>

<div class="modal fade bs-example-modal-lg" id="addBookDialog" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel">
  			<div class="modal-dialog modal-lg" >
    		<div class="modal-content">
      			<div class="modal-header">
        		<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        		<h4 class="modal-title" id="myModalLabel">Confirmar cierre</h4>
      		</div>
      		<div class="modal-body">
      		<h4>¿Está de acuerdo con estos datos?</h4>
	      		<form:form action="addCierre" method="POST" modelAttribute="newFormCierre" class="form-horizontal" role="form">
	      		
	      		<div class="row">
	      		
	      		<div class="col-xs-2">
	      			<label for="ingresoTotal">Total</label>
	      			<div class="input-group"> 
					<span class="input-group-addon">€</span>
	      			<form:input type="text" name="ingresoTotal" id="ingresoTotal" path="cierre.total" value="" class="form-control" />
	      			</div>
	      		</div>
	      		<div class="col-xs-2">
	      			<label for="ingresoContabilizadoModal">Contabilizado</label>
	      			<div class="input-group"> 
					<span class="input-group-addon">€</span>
	      			<form:input type="text" name="ingresoContabilizadoModal" id="ingresoContabilizadoModal" path="cierre.caja" value="" class="form-control" />
	      			</div>
	      		</div>
	      		<div class="col-xs-2">
	      			<label for="diferencia">Diferencia</label>
	      			<div class="input-group"> 
					<span class="input-group-addon">€</span>
	      			<form:input type="text" name="diferencia" id="diferencia" path="cierre.diferencia" value="" class="form-control"/>
	      			</div>
	      		</div>
	      		<form:input class="form-control" path="cierre.efectivo" value="${efectivo}" type="hidden"/>
	      		<form:input class="form-control" path="cierre.tarjeta" value="${tarjeta}" type="hidden"/>
	      		<form:input type="hidden" name="observacionesModal" id="observacionesModal" path="cierre.incidencias" value="" class="form-control" />
	      		<form:input class="form-control" path="idCentro" value="${idCentro}" type="hidden"/> 
	      		
	      			
	      		</div>

			 <div class="modal-footer">
			 	<button type="submit" class="btn btn-primary">Aceptar</button>
				<button type="button" class="btn btn-default" data-dismiss="modal">Cancelar</button>
      		</div>
      		</form:form>
      		</div>
      		</div>
	</div>
</div>


	
