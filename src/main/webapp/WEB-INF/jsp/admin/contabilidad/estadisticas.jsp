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

<script>
	$(document).ready(function() {
		 $("#boton1").click(function(){
		      $("#oculto2").hide();
		      $("#oculto1").slideToggle(600);
		    });
		    
		    $("#boton2").click(function(){
		      $("#oculto1").hide();
		      $("#oculto2").slideToggle(600);
		    });
	});
    
</script>


		<div class="col-md-2">
			<button id="boton1" onclick="mostrar()" class="btn btn-primary btn-lg btn-block">Ventas por producto</button>
		</div>
		<div class="col-md-2">
			<button id="boton2" onclick="mostrar()" class="btn btn-primary btn-lg btn-block">Ventas por centros</button>
		</div>
		<div class="col-md-2">
			<button id="boton3" onclick="mostrar()" class="btn btn-primary btn-lg btn-block">Ventas por empleado</button>
		</div>
		<div class="col-md-2">
			<button id="boton3" onclick="mostrar()" class="btn btn-primary btn-lg btn-block">Ventas mensuales</button>
		</div>
		<div class="col-md-2">
			<button id="boton4" onclick="mostrar()" class="btn btn-primary btn-lg btn-block">Pedidos proveedores</button>
		</div>
		<div class="row">
		<div class="col-md-9">
		
			<div id="oculto1" style="display: none;">
				<h2>Ventas de la empresa</h2>
			
		<div class="col-md-7">
		<table class="table table-hover" id="productos">
			<thead>
				<tr>
					<th>Ventas</th>
					<th>Nombre</th>
					<th>Código de barras</th>
				</tr>
			</thead>

			<tbody>
				<c:forEach var="estadisticas" items="${estadisticas}" varStatus="status">
					<tr>
						<td>${estadisticas.cantidad}</td>
						<td>${estadisticas.producto.nombre}</td>
						<td>${estadisticas.producto.codProd}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		</div>
			</div>
			
			<div id="oculto2" style="display: none;">
			HOLA VENTAS
			</div>
			</div>
		</div>
</div>
</div>