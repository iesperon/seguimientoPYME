<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<div class="row">
<div class="col-md-9">
<!-- cdn for modernizr, if you haven't included it already -->
<script
	src="http://cdn.jsdelivr.net/webshim/1.12.4/extras/modernizr-custom.js"></script>
<!-- polyfiller file to detect and load polyfills -->
<script src="http://cdn.jsdelivr.net/webshim/1.12.4/polyfiller.js"></script>
<script>
	webshims.setOptions('waitReady', false);
	webshims.setOptions('forms-ext', {
		types : 'date'
	});
	webshims.polyfill('forms forms-ext');
</script>

<script type="text/javascript" charset="utf-8">
	$(document)
			.ready(
					function() {
						$('#productos').dataTable({
							"language" : {url : 'https://cdn.datatables.net/plug-ins/1.10.11/i18n/Spanish.json'	},
						 	"order": [[ 0, "desc" ]]
										});
					});
</script>

<script type="text/javascript" charset="utf-8">
	$(document)
			.ready(
					function() {
						$('#centros').dataTable({
							"language" : {url : 'https://cdn.datatables.net/plug-ins/1.10.11/i18n/Spanish.json'	},
						 	"order": [[ 5, "desc" ]]
										});
					});
</script>

<script type="text/javascript" charset="utf-8">
	$(document)
			.ready(
					function() {
						$('#empleados').dataTable({
							"language" : {url : 'https://cdn.datatables.net/plug-ins/1.10.11/i18n/Spanish.json'	},
						 	"order": [[ 0, "desc" ]]
										});
					});
</script>

<script type="text/javascript" charset="utf-8">
	$(document)
			.ready(
					function() {
						$('#pedidos').dataTable({
							"language" : {url : 'https://cdn.datatables.net/plug-ins/1.10.11/i18n/Spanish.json'	},
						 	"order": [[ 0, "desc" ]]
										});
					});
</script>

<script>
	$(document).ready(function() {
		 $("#boton1").click(function(){
		      $("#oculto2").hide();
		      $("#oculto3").hide();
		      $("#oculto1").slideToggle(600);
		      $("#oculto4").hide();
		      
		    });
		    
		    $("#boton2").click(function(){
		      $("#oculto1").hide();
		      $("#oculto3").hide();
		      $("#oculto2").slideToggle(600);
		      $("#oculto4").hide();
		      
		    });

		    $("#boton3").click(function(){
			      $("#oculto1").hide();
			      $("#oculto2").hide();
			      $("#oculto3").slideToggle(600);
			      $("#oculto4").hide();
			      
			    });
		    
		    $("#boton4").click(function(){
			      $("#oculto1").hide();
			      $("#oculto2").hide();
			      $("#oculto3").hide();
			      $("#oculto4").slideToggle(600);
			    });
	});
    
</script>

		<img src="<c:url value="/resources/images/estadisticas.jpg" />"	 alt="Empleados" width="1015" height="120"  class="center-block img-rounded" >
		<div class="col-md-11"></div>
	<div class="row ">
		<div class="col-md-3">
			<button id="boton1" onclick="mostrar()" class="btn btn-primary btn-lg btn-block">Ventas por producto</button>
		</div>
		<div class="col-md-3">
			<button id="boton2" onclick="mostrar()" class="btn btn-primary btn-lg btn-block">Ventas por centros</button>
		</div>
		<div class="col-md-3">
			<button id="boton3" onclick="mostrar()" class="btn btn-primary btn-lg btn-block">Ventas por empleado</button>
		</div>
		<div class="col-md-3">
			<button id="boton4" onclick="mostrar()" class="btn btn-primary btn-lg btn-block">Pedidos proveedores</button>
		</div>
		<div class="row">
		<div class="col-md-9">
		
			<div id="oculto1" style="display: none;">
				<h2>Ventas de la empresa por producto</h2>
			
		<div class="col-md-12">
		<table class="table table-hover" id="productos">
			<thead>
				<tr>
					<th>Ventas</th>
					<th>Nombre</th>
					<th>Código de barras</th>
				</tr>
			</thead>

			<tbody>
				<c:forEach var="estadisticasProd" items="${estadisticasProd}" varStatus="status">
					<tr>
						<td>${estadisticasProd.cantidad}</td>
						<td>${estadisticasProd.producto.nombre}</td>
						<td>${estadisticasProd.producto.codProd}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		</div>
			</div>
			
			<div id="oculto2" style="display: none;">
				<h2>Ventas por Centro</h2>
				
						<div class="col-md-12">
		<table class="table table-hover" id="centros">
			<thead>
				<tr>
					<th>Nombre</th>
					<th>Calle</th>
					<th>Provincia</th>
					<th>Efectivo</th>
					<th>Tarjeta</th>
					<th>Total</th>
				</tr>
			</thead>

			<tbody>
				<c:forEach var="estadisticasCentroVentas" items="${estadisticasCentroVentas}" varStatus="status">
					<tr>
						<td>${estadisticasCentroVentas.centro.nombre}</td>
						<td>${estadisticasCentroVentas.centro.calle}</td>
						<td>${estadisticasCentroVentas.centro.provincia}</td>
						<td><fmt:formatNumber type="number" maxFractionDigits="3" value="${estadisticasCentroVentas.efectivoCentro}" />€</td>
						<td><fmt:formatNumber type="number" maxFractionDigits="3" value="${estadisticasCentroVentas.tarjetaCentro}" />€</td>
						<td><fmt:formatNumber type="number" maxFractionDigits="3" value="${estadisticasCentroVentas.totalCentro}" />€</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		</div>
		
			</div>
		<div id="oculto3" style="display: none;">
							<h2>Ventas de la empresa por empleado</h2>
			
		<div class="col-md-12">
		<table class="table table-hover" id="empleados">
			<thead>
				<tr>
					<th>Nombre</th>
					<th>Centro</th>
					<th>Importe</th>
				</tr>
			</thead>

			<tbody>
				<c:forEach var="estadisticasEmplVentas" items="${estadisticasEmplVentas}" varStatus="status">
					<tr>
						<td>${estadisticasEmplVentas.usuario.nombre} ${estadisticasEmplVentas.usuario.apellido1} ${estadisticasEmplVentas.usuario.apellido2}</td>
						<td>${estadisticasEmplVentas.usuario.centro.nombre}, ${estadisticasEmplVentas.usuario.centro.calle},  ${estadisticasEmplVentas.usuario.centro.provincia}</td>
						<td><fmt:formatNumber type="number" maxFractionDigits="3" value="${estadisticasEmplVentas.totalUsuario}" />€</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		</div>
		
		</div>
		
		<div id="oculto4" style="display: none;">
			<h2>Pedidos a proveedores</h2>
			
		<div class="col-md-12">
		<table class="table table-hover" id="pedidos">
			<thead>
				<tr>
					<th>Nombre</th>
					<th>CIF</th>
					<th>Número de pedidos</th>
					<th>Importe total</th>
					
				</tr>
			</thead>

			<tbody>
				<c:forEach var="estadisticasPedidoProveedor" items="${estadisticasPedidoProveedor}" varStatus="status">
					<tr>
						<td>${estadisticasPedidoProveedor.proveedor.nombre}</td>
						<td>${estadisticasPedidoProveedor.proveedor.cif}</td>
						<td>${estadisticasPedidoProveedor.pedidos}</td>
						<td><fmt:formatNumber type="number" maxFractionDigits="3" value="${estadisticasPedidoProveedor.totalPedido}" />€</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		</div>
		
		</div>
		
		
			</div>
		</div>
		</div>
</div>
</div>